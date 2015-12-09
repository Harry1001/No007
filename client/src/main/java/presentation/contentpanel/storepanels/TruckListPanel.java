package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.TruckBLService;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commonpanel.ErrorDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            JDialog dialog=new JDialog(parent,"新增车辆信息",true);
            dialog.getContentPane().add(new TruckInfoPanel(parent, dialog, this, truckBLService));
            dialog.setLocationRelativeTo(parent);
            dialog.pack();
            dialog.setVisible(true);
        }
        else if (e.getSource()==deletebt){

        }
        else if (e.getSource()==modifybt){

        }
    }
}
