package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.TruckBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commonpanel.ErrorDialog;
import presentation.contentpanel.managerpanels.AgencyModifyPanel;
import vo.infovo.AgencyVO;
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

/**
 * Created by Harry on 2015/11/27.
 */
public class TruckListPanel extends JPanel implements ActionListener {

    TruckBLService truckBLService;

    MainFrame parent;
    MyButton addbt=new MyButton("新增");
    MyButton deletebt=new MyButton("删除");
    MyButton modifybt=new MyButton("修改");

    MyDefaultTableModel defaultTableModel;
    MyTable table;

    String [] names={"车辆代号","发动机号","车牌号","底盘号","购买时间","服役时间"};

    public TruckListPanel(MainFrame par){
        this.parent=par;

        String [][] data={};

        defaultTableModel=new MyDefaultTableModel(data,names);
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

        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);

        initBL();
        refreshList();
    }

    protected void initBL(){
        try {
            truckBLService= BLFactory.getTruckBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
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
                defaultTableModel.getDataVector().clear();//先清空
                for (TruckVO vo: truckVOs){
                    String id=vo.getTruckID();
                    String engine=vo.getEngineID();
                    String chepai=vo.getLicenceID();
                    String dipan=vo.getChassisID();
                    String buyTime= Constent.BIRTHDAY_FORMAT.format(vo.getBuyTime());
                    Integer fuyiTime=vo.getServeTime();
                    Object [] data={id,engine,chepai,dipan,buyTime,fuyiTime};
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
            if (truckBLService!=null){
                JDialog dialog=new JDialog(parent,"新增车辆信息",true);
                dialog.getContentPane().add(new TruckInfoPanel(parent, dialog, this, truckBLService));
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
            if (row==-1){//没有选择任何行
                new ErrorDialog(parent, "请选择一行待删除条目");
            } else {//选择了待删除的行
                if (truckBLService!=null){
                    String id= (String)table.getValueAt(row, 0);
                    try {
                        truckBLService.deleteTruck(id);
                        refreshList();
                    } catch (RemoteException e1) {
                        new ErrorDialog(parent, "网络连接超时");
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
                new ErrorDialog(parent, "请选择一行待修改条目");
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
