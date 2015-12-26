package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.receiptblservice.PayReceiptBLService;
import constent.Constent;
import presentation.Images.Images;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import typeDefinition.ReceiptState;
import vo.receiptvo.PayReceiptVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Harry on 2015/12/25.
 */
public class OutcomeListPanel extends JPanel implements ActionListener {

    private MainFrame parent;
    private OutcomePanel outcomePanel;

    private MyTable table;
    private MyDefaultTableModel defaultTableModel;
    private MyButton addbt = new MyButton(" New Receipt", Images.GO_IMAGE);//新建单据按钮
    private MyButton handlebt=new MyButton("Handle");//处理已被审批的单据按钮
    private MyButton refreshbt=new MyButton("Refresh");//刷新通知按钮

    private Vector<String> names;//表格列名

    private PayReceiptBLService payReceiptBLService;

    public OutcomeListPanel(MainFrame par, OutcomePanel outcomePanel){
        this.parent=par;
        this.outcomePanel=outcomePanel;

        initUI();
        setHotKey();

        initBL();
        refreshList();

        refreshbt.addActionListener(this);
        addbt.addActionListener(this);
        handlebt.addActionListener(this);
    }

    private void initBL(){
        try {
            payReceiptBLService= BLFactory.getPayReceiptBLService();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void refreshList(){
        if (payReceiptBLService!=null){
            try {
                ArrayList<PayReceiptVO> approvedVOs=payReceiptBLService.getListByState(ReceiptState.APPROVED);
                ArrayList<PayReceiptVO> unapprovedVOs=payReceiptBLService.getListByState(ReceiptState.UNAPPROVED);
                approvedVOs.addAll(unapprovedVOs);
                defaultTableModel.getDataVector().clear();
                Vector<Vector> data=new Vector<Vector>();

                for (PayReceiptVO vo : approvedVOs ) {
                    String id=vo.getId();
                    double fee=vo.getFee();
                    String statestr;
                    if (vo.getState()==ReceiptState.APPROVED){
                        statestr="审批通过";
                    } else {
                        statestr="审批不通过";
                    }
                    String typestr= Constent.FEE_TYPE_STR[vo.getPayType().ordinal()];
                    String timestr=Constent.DATE_FORMAT.format(vo.getPayTime());
                    Vector<Object> item=new Vector<Object>();
                    item.add(id);
                    item.add(fee);
                    item.add(statestr);
                    item.add(typestr);
                    item.add(timestr);
                    data.add(item);
                }
                defaultTableModel.setDataVector(data, names);
                table.revalidate();
                table.updateUI();
            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            initBL();
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
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=gbc.weighty=1.0;
        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=4;
        this.add(new JScrollPane(table), gbc);
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
        gbc.gridy++;
        gbc.gridx=0;
        gbc.ipady=30;
        this.add(new BlankBlock(), gbc);

        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"付款单列表",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
    }

    /**
     * 设置快捷键
     */
    private void setHotKey(){
        refreshbt.setMnemonic('R');
        handlebt.setMnemonic('H');
        addbt.setMnemonic('N');
    }

    private void setReceiptHandled(){
        int [] rows=table.getSelectedRows();
        if (rows.length<=0){
            new TranslucentFrame(this, "请选择待处理单据(按住ctrl可多选)", Color.ORANGE);
        }
        else {
            try{
                for (int i:rows){
                    String id=(String)table.getValueAt(i, 0);
                    payReceiptBLService.updateState(id, ReceiptState.HANDLED);

                }
                refreshList();
            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==refreshbt){
            refreshList();
        }
        else if (e.getSource()==addbt){
            outcomePanel.showPayReceipt();
        }
        else if (e.getSource()==handlebt){
            setReceiptHandled();
        }
    }
}
