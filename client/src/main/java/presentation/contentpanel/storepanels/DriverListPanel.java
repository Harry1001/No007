package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.DriverBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commonpanel.ErrorDialog;
import vo.infovo.DriverVO;

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

/**
 * Created by Harry on 2015/11/27.
 */
public class DriverListPanel extends JPanel implements ActionListener{
    private MainFrame parent;
    MyButton addbt=new MyButton("新增");
    MyButton deletebt=new MyButton("删除");
    MyButton modifybt=new MyButton("修改");

    MyDefaultTableModel defaultTableModel;
    MyTable table;

    DriverBLService driverBLService;

    public DriverListPanel(MainFrame par){
        this.parent=par;

        initUI();

        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);

        initBL();
        refreshList();
    }

    private void initBL(){
        try {
            driverBLService= BLFactory.getDriverBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    protected void refreshList(){
        if (driverBLService!=null){
            try {
                ArrayList<DriverVO> driverVOs = driverBLService.getDriverList();
                defaultTableModel.getDataVector().clear();
                for (DriverVO vo : driverVOs) {
                    String driverID=vo.getDriverID();
                    String name=vo.getName();
                    String birthday= Constent.BIRTHDAY_FORMAT.format(vo.getBirthday());
                    String personID=vo.getIDNum();
                    String phone=vo.getPhoneNum();
                    String gender=vo.getGender();
                    String limitTime=Constent.BIRTHDAY_FORMAT.format(vo.getLicenseLimit());
                    String[] data={driverID, name, birthday, personID, phone, gender, limitTime};
                    defaultTableModel.addRow(data);
                }
                table.revalidate();
                table.updateUI();
            } catch (RemoteException e) {
                new ErrorDialog(parent, "服务器连接超时");
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
                new ErrorDialog(parent, "请选择待删除条目");
            }
            else {
                if (driverBLService!=null){
                    String id=(String)table.getValueAt(row, 0);
                    try {
                        driverBLService.deleteDriver(id);
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
                new ErrorDialog(parent, "请选择待删除条目");
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
                        new ErrorDialog(parent, "不改发生的情况");
                    }
                    
                    DriverVO vo=new DriverVO(driverID, name, birthday, personID, phone, gender, limitTime);

                    JDialog dialog=new JDialog(parent,"修改司机信息",false);
                    dialog.getContentPane().add(new DriverModifyPanel(parent,dialog, this, driverBLService, vo));
                    dialog.setLocationRelativeTo(parent);
                    dialog.pack();
                    dialog.setVisible(true);
                }
                else {
                    initBL();
                }
            }
        }
    }

    private void initUI(){
        String [] names={"司机编号","姓名","出生日期","身份证号","手机号","性别","行驶证期限"};

        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridwidth=6;
        gbc.gridheight=10;
        this.add(new JScrollPane(table),gbc);

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
