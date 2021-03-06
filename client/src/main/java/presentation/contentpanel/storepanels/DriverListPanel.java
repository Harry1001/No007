package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.DriverBLService;
import businessLogicService.recordblservice.RecordBLService;
import constent.Constent;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.infovo.DriverVO;
import vo.recordvo.RecordVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Harry on 2015/11/27.
 */
public class DriverListPanel extends JPanel implements ActionListener{
    private MainFrame parent;
    MyButton addbt=new MyButton("新建(N)");
    MyButton deletebt=new MyButton("删除(D)");
    MyButton modifybt=new MyButton("修改(M)");

    MyDefaultTableModel defaultTableModel;
    MyTable table;

    DriverBLService driverBLService;
    RecordBLService rb;

    Vector<String> names;
    String storeID;

    public DriverListPanel(MainFrame par){
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder("司机信息"));
        
        this.storeID=parent.getUserIdentity().getId().substring(0,6);
        initUI();
        setHotKey();

        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);

        initBL();
        refreshList();
    }

    private void setHotKey(){
        addbt.setMnemonic('N');
        deletebt.setMnemonic('D');
        modifybt.setMnemonic('M');
    }

    private void initBL(){
        try {
            rb= BLFactory.getRecordBLService();
            driverBLService= BLFactory.getDriverBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.orange);
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    protected void refreshList(){
        if (driverBLService!=null){
            try {
                ArrayList<DriverVO> driverVOs = driverBLService.getDriverList();
                Vector<Vector> data=new Vector<Vector>();
                for (DriverVO vo : driverVOs) {
                    String storeid=vo.getDriverID().substring(0,6);
                    if (storeid.equals(this.storeID)){
                        String driverID=vo.getDriverID();
                        String name=vo.getName();
                        String birthday= Constent.BIRTHDAY_FORMAT.format(vo.getBirthday());
                        String personID=vo.getIDNum();
                        String phone=vo.getPhoneNum();
                        String gender=vo.getGender();
                        String limitTime=Constent.BIRTHDAY_FORMAT.format(vo.getLicenseLimit());
                        Vector<Object> item=new Vector<Object>();
                        item.add(driverID);
                        item.add(name);
                        item.add(birthday);
                        item.add(personID);
                        item.add(phone);
                        item.add(gender);
                        item.add(limitTime);
                        data.add(item);
                    }
                }
                defaultTableModel.setDataVector(data, names);
                table.revalidate();
                table.updateUI();
            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.orange);
            } catch (SQLException e) {
                new ErrorDialog(parent, "SQLException");
            }
        }
        else {
            initBL();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            if (driverBLService!=null){
                JDialog dialog=new JDialog(parent,"新增司机信息",true);
                dialog.getContentPane().add(new DriverInfoPanel(parent, dialog, this, driverBLService));
                dialog.setLocationRelativeTo(parent);
                dialog.setLocation(dialog.getX()/3, dialog.getY()/3);
                dialog.pack();
                dialog.setVisible(true);
            }
            else {
                initBL();
            }
        }
        else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row==-1){
                new TranslucentFrame(this, "请选择待删除行", Color.RED);
            }
            else {
                if (driverBLService!=null){
                    String name=(String)table.getValueAt(row, 1);
                    String id=(String)table.getValueAt(row, 0);
                    try {
                        driverBLService.deleteDriver(id);
                        refreshList();
                        new TranslucentFrame(this, MessageType.DELETE_SUCCESS, Color.GREEN);
                        RecordVO rvo=new RecordVO(new Date(),parent.getUserIdentity().getName(),"删除司机信息:"+name);
                        rb.add(rvo);
                    } catch (RemoteException e1) {
                        new ErrorDialog(parent, "服务器连接超时");
                    } catch (SQLException e1) {
                        new ErrorDialog(parent, "SQLException");
                    }
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==modifybt){
            int row=table.getSelectedRow();
            if (row==-1){
                new TranslucentFrame(this, "请选择待修改行", Color.RED);
            }
            else {
                if (driverBLService!=null){
                    String driverID= (String)table.getValueAt(row, 0);
                    String name= (String)table.getValueAt(row, 1);
                    String personID= (String)table.getValueAt(row, 3);
                    String phone= (String)table.getValueAt(row, 4);
                    String gender= (String)table.getValueAt(row, 5);

                    Date birthday=null;
                    Date limitTime=null;
                    try {
                        birthday= Constent.BIRTHDAY_FORMAT.parse((String)table.getValueAt(row, 2));
                        limitTime=Constent.BIRTHDAY_FORMAT.parse((String)table.getValueAt(row, 6));
                    } catch (ParseException e1) {
                        new ErrorDialog(parent, "不该发生的情况");
                    }
                    
                    DriverVO vo=new DriverVO(driverID, name, birthday, personID, phone, gender, limitTime);

                    JDialog dialog=new JDialog(parent,"修改司机信息",false);
                    dialog.getContentPane().add(new DriverModifyPanel(parent,dialog, this, driverBLService, vo));
                    dialog.setLocationRelativeTo(parent);
                    dialog.setLocation(dialog.getX()/3, dialog.getY()/3);
                    dialog.pack();
                    dialog.setVisible(true);
                }
                else {
                    initBL();
                }
            }
        }
    }

    private void initNames(){
        String [] nameStr={"司机编号","姓名","出生日期","身份证号","手机号","性别","行驶证期限"};
        names=new Vector<String>();
        for (int i=0;i<nameStr.length;i++){
            names.add(nameStr[i]);
        }
    }

    private void initUI(){

        initNames();
        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        gbc.weightx=gbc.weighty=1.0;
        gbc.gridwidth=6;
        gbc.gridheight=10;
        this.add(new JScrollPane(table),gbc);

        gbc.weightx=gbc.weighty=0.0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        gbc.gridx=3;
        gbc.gridy=13;
        this.add(addbt,gbc);
        gbc.gridx=4;
        this.add(deletebt,gbc);
        gbc.gridx=5;
        this.add(modifybt,gbc);
    }

}
