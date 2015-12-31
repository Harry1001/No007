package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.TruckBLService;
import constent.Constent;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.infovo.TruckVO;

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
public class TruckListPanel extends JPanel implements ActionListener {

    TruckBLService truckBLService;

    MainFrame parent;
    MyButton addbt=new MyButton("新建(N)");
    MyButton deletebt=new MyButton("删除(D)");
    MyButton modifybt=new MyButton("修改(M)");

    MyDefaultTableModel defaultTableModel;
    MyTable table;

    String storID;
    Vector<String> names;

    public TruckListPanel(MainFrame par){
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder("车辆信息"));
        
        this.storID=parent.getUserIdentity().getId().substring(0,6);
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

    private void initNames(){
        String [] namestr={"车辆代号","发动机号","车牌号","底盘号","购买时间","服役时间"};
        names=new Vector<String>();
        for (int i=0;i<namestr.length;i++){
            names.add(namestr[i]);
        }
    }

    protected void initBL(){
        try {
            truckBLService= BLFactory.getTruckBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    /**
     * 载入数据
     */
    protected void refreshList(){
        if (truckBLService!=null) {
            try{
                ArrayList<TruckVO> truckVOs=truckBLService.getTruckList();
                Vector<Vector> data=new Vector<Vector>();
                for (TruckVO vo: truckVOs){
                    String storeid=vo.getTruckID().substring(0,6);//筛选车辆信息，只有属于本营业厅的车辆才显示出来

                    if (storeid.equals(this.storID)){
                        String id=vo.getTruckID();
                        String engine=vo.getEngineID();
                        String chepai=vo.getLicenceID();
                        String dipan=vo.getChassisID();
                        String buyTime= Constent.BIRTHDAY_FORMAT.format(vo.getBuyTime());
                        Integer fuyiTime=vo.getServeTime();
                        Vector<Object> item=new Vector<Object>();
                        item.add(id);
                        item.add(engine);
                        item.add(chepai);
                        item.add(dipan);
                        item.add(buyTime);
                        item.add(fuyiTime);
                        data.add(item);
                    }
                }
                defaultTableModel.setDataVector(data, names);
                table.revalidate();
                table.updateUI();
            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
            } catch (SQLException e) {
                System.out.println("车辆信息："+e.getMessage());
                new ErrorDialog(parent, "SQLException");
            }
        }
        else {
            initBL();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            if (truckBLService!=null){
                JDialog dialog=new JDialog(parent,"新增车辆信息",true);
                dialog.getContentPane().add(new TruckInfoPanel(parent, dialog, this, truckBLService));
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
            if (row==-1){//没有选择任何行
                new TranslucentFrame(this, "请选择待删除行", Color.RED);
            } else {//选择了待删除的行
                if (truckBLService!=null){
                    String id= (String)table.getValueAt(row, 0);
                    try {
                        truckBLService.deleteTruck(id);
                        refreshList();
                        new TranslucentFrame(this, MessageType.DELETE_SUCCESS, Color.GREEN);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (SQLException e1) {
                        new ErrorDialog(parent, "SQLException");
                    }
                }
                else{
                    initBL();
                }
            }
        }
        else if (e.getSource()==modifybt){
            int row=table.getSelectedRow();
            if (row==-1){//没有选择任何行
                new TranslucentFrame(this, "请选择待修改行", Color.RED);
            } else {//选择了待修改的行
                if (truckBLService!=null){
                    String id=(String)table.getValueAt(row, 0);
                    String engine=(String) table.getValueAt(row, 1);
                    String chepai=(String) table.getValueAt(row, 2);
                    String dipan=(String) table.getValueAt(row, 3);
                    Date buyTime=null;
                    try {
                        buyTime=Constent.BIRTHDAY_FORMAT.parse((String)table.getValueAt(row, 4));
                    } catch (ParseException e1) {
                        System.out.println("不该发生的情况");
                    }
                    int fuyiTime=(Integer) table.getValueAt(row, 5);

                    TruckVO vo=new TruckVO(id,chepai, engine, dipan, buyTime, fuyiTime);

                    JDialog dialog=new JDialog(parent,"修改车辆信息",false);
                    dialog.getContentPane().add(new TruckModifyPanel(parent,dialog, this, truckBLService, vo));
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
}
