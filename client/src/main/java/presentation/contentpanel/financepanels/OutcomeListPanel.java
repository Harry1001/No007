package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.receiptblservice.PayReceiptBLService;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 * Created by Harry on 2015/12/25.
 */
public class OutcomeListPanel extends JPanel {

    private MainFrame parent;
    private OutcomePanel outcomePanel;

    private MyTable table;
    private MyDefaultTableModel defaultTableModel;
    private MyButton addbt = new MyButton("New Receipt");//新建单据按钮
    private MyButton handlebt=new MyButton("Handle");//处理已被审批的单据按钮
    private MyButton refreshbt=new MyButton("Refresh");//刷新通知按钮

    private Vector<String> names;//表格列名

    private PayReceiptBLService payReceiptBLService;

    public OutcomeListPanel(MainFrame par, OutcomePanel outcomePanel){
        this.parent=par;
        this.outcomePanel=outcomePanel;

        initUI();
        setHotKey();
        //todo
    }

    private void initBL(){
        try {
            payReceiptBLService= BLFactory.getPayReceiptBLService();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI(){

        names=new Vector<String>();
        names.clear();
        names.add("付款单编号");
        names.add("付款金额");
        names.add("单据状态");
        names.add("付款条目");
        names.add("付款时间");

        defaultTableModel=new MyDefaultTableModel(names, 0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=gbc.weighty=1.0;
        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=4;
        this.add(table, gbc);
        gbc.weightx=gbc.weighty=0.0;
        gbc.gridwidth=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;

        gbc.gridy=1;
        this.add(refreshbt, gbc);
        gbc.gridx=1;
        this.add(handlebt, gbc);
        gbc.gridx=3;
        this.add(addbt, gbc);

    }

    /**
     * 设置快捷键
     */
    private void setHotKey(){
        refreshbt.setMnemonic('R');
        handlebt.setMnemonic('H');
        addbt.setMnemonic('N');
    }
}
