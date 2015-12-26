package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.receiptblservice.PayReceiptBLService;
import businessLogicService.transportblservice.TransferBLService;
import javafx.scene.control.SelectionMode;
import presentation.commoncontainer.ErrorDialog;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import typeDefinition.ReceiptState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Harry on 2015/12/5.
 */
public class ShenPiPanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private PayReceiptBLService payReceiptBLService;
    private TransferBLService transferBLService;
    private MyTable table;
    private MyDefaultTableModel defaultTableModel;
    private Vector<String> names;
    private MyButton approvebt=new MyButton("通过");
    private MyButton disappbt=new MyButton("不通过");
    private MyButton refreshbt=new MyButton("刷新");

    public ShenPiPanel(MainFrame par){
        this.parent=par;

        initUI();

        initBL();

        approvebt.addActionListener(this);
        disappbt.addActionListener(this);
        refreshbt.addActionListener(this);
        refreshList();
    }

    private void initBL(){
        try {
            transferBLService=BLFactory.getTransferBLService();
            payReceiptBLService= BLFactory.getPayReceiptBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    private void refreshList(){
        //todo 用两个service更新list，只读取"提交"状态的单据
    }

    private void initUI(){
        names=new Vector<String>();
        names.add("单号");
        names.add("单据类型");
        names.add("单据简述");
        defaultTableModel=new MyDefaultTableModel(names, 0);
        table=new MyTable(defaultTableModel);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);//设置单据简述一列较宽
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//设置可多选

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.fill=GridBagConstraints.NONE;
        gbc.insets=new Insets(10,10,10,10);
        gbc.anchor=GridBagConstraints.EAST;

        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(table, gbc);

        gbc.gridwidth=1;
        gbc.gridy++;
        this.add(approvebt, gbc);
        gbc.gridx++;
        this.add(disappbt, gbc);
        gbc.gridx++;
        this.add(refreshbt,gbc);

        this.setOpaque(false);
    }



    private boolean isBLInited(){
        return payReceiptBLService!=null && transferBLService!=null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==approvebt){
            int [] rows = table.getSelectedRows();
            if (rows.length<=0){
                new ErrorDialog(parent, "请选择待审批单据");
            }
            else {//选择了行
                if (isBLInited()){
                    for (int i=0;i<rows.length;i++){
                        String id=(String)table.getValueAt(i, 0);
                        String receiptType=(String)table.getValueAt(i,1);
                        try {
                            if (receiptType.equals("付款单")) {
                                payReceiptBLService.updateState(id, ReceiptState.APPROVED);
                            } else if (receiptType.equals("中转单")) {
                                //todo 等中转单接口
                            }
                            refreshList();
                        } catch (RemoteException e1) {
                            new ErrorDialog(parent, "服务器连接超时");
                        } catch (SQLException e1) {
                            new ErrorDialog(parent, "SQLException");
                        }
                    }
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==disappbt){
            int [] rows = table.getSelectedRows();
            if (rows.length<=0){
                new ErrorDialog(parent, "请选择待审批单据");
            }
            else {//选择了行
                if (isBLInited()){
                    for (int i=0;i<rows.length;i++){
                        String id=(String)table.getValueAt(i, 0);
                        String receiptType=(String)table.getValueAt(i,1);
                        try {
                            if (receiptType.equals("付款单")) {
                                payReceiptBLService.updateState(id, ReceiptState.UNAPPROVED);
                            } else if (receiptType.equals("中转单")) {
                                //todo
                            }
                            refreshList();
                        } catch (RemoteException e1) {
                            new ErrorDialog(parent, "服务器连接超时");
                        } catch (SQLException e1) {
                            new ErrorDialog(parent, "SQLException");
                        }
                    }
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==refreshbt){
            refreshList();
        }
    }
}
