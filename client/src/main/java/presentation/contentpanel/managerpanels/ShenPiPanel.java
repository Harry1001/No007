package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.receiptblservice.PayReceiptBLService;
import businessLogicService.receiptblservice.TransferReceiptBLService;
import businessLogicService.transportblservice.TransferBLService;
import javafx.scene.control.SelectionMode;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import typeDefinition.ReceiptState;
import vo.receiptvo.PayReceiptVO;
import vo.receiptvo.TransferReceiptVO;

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
 * Created by Harry on 2015/12/5.
 */
public class ShenPiPanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private PayReceiptBLService payReceiptBLService;
    private TransferReceiptBLService transferReceiptBLService;
    private MyTable table;
    private EditableTableModel defaultTableModel;
    private Vector<String> names;
    private MyButton approvebt=new MyButton("通过(A)");
    private MyButton disappbt=new MyButton("不通过(D)");
    private MyButton refreshbt=new MyButton("刷新(R)");

    public ShenPiPanel(MainFrame par){
        this.parent=par;

        initUI();
        setHotKey();

        initBL();

        approvebt.addActionListener(this);
        disappbt.addActionListener(this);
        refreshbt.addActionListener(this);
        refreshList();
    }

    private void setHotKey(){
        approvebt.setMnemonic('A');
        disappbt.setMnemonic('D');
        refreshbt.setMnemonic('R');
    }

    private void initBL(){
        try {
            transferReceiptBLService=BLFactory.getTransferReceiptBLService();
            payReceiptBLService= BLFactory.getPayReceiptBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    private void refreshList(){
        if (isBLInited()){
            defaultTableModel.getDataVector().clear();
            try {
                ArrayList<PayReceiptVO> payReceiptVOs=payReceiptBLService.getListByState(ReceiptState.SUBMITTED);
                ArrayList<TransferReceiptVO> transferReceiptVOs=transferReceiptBLService.getListByState(ReceiptState.SUBMITTED);
                Vector<Vector> data=new Vector<Vector>();
                for (PayReceiptVO vo: payReceiptVOs){
                    Vector<Object> item=new Vector<Object>();
                    item.add(vo.getId());
                    item.add("付款单");
                    item.add(vo.print());
                    data.add(item);
                }
                for (TransferReceiptVO vo:transferReceiptVOs){
                    Vector<Object> item=new Vector<Object>();
                    item.add(vo.getTransferID());
                    item.add("中转单");
                    item.add(vo.print());
                    data.add(item);
                }
                defaultTableModel.setDataVector(data, names);
                table.revalidate();
                table.updateUI();
            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            initBL();
        }
    }

    private void initUI(){
        names=new Vector<String>();
        names.add("单号");
        names.add("单据类型");
        names.add("单据简述");
        defaultTableModel=new EditableTableModel(names, 0);
        table=new MyTable(defaultTableModel);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);//设置单据简述一列较宽
        table.getColumnModel().getColumn(0).setWidth(50);
        table.getColumnModel().getColumn(1).setWidth(50);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//设置可多选
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);
        gbc.anchor=GridBagConstraints.EAST;

        gbc.weightx=gbc.weighty=1.0;
        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(new JScrollPane(table), gbc);

        gbc.weightx=gbc.weighty=0.0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridy++;
        this.add(approvebt, gbc);
        gbc.gridx++;
        this.add(disappbt, gbc);
        gbc.gridx++;
        this.add(refreshbt,gbc);

        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"审批单据",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
    }



    private boolean isBLInited(){
        return payReceiptBLService!=null && transferReceiptBLService!=null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==approvebt){
            int [] rows = table.getSelectedRows();
            if (rows.length<=0){
                new TranslucentFrame(this, "请选择要审批的单据(按住ctrl可多选)", Color.RED);
            }
            else {//选择了行
                if (isBLInited()){
                    for (int i:rows){
                        String id=(String)table.getValueAt(i, 0);
                        String receiptType=(String)table.getValueAt(i,1);
                        try {
                            if (receiptType.equals("付款单")) {
                                payReceiptBLService.updateState(id, ReceiptState.APPROVED);
                            } else if (receiptType.equals("中转单")) {
                                transferReceiptBLService.updateState(id, ReceiptState.APPROVED);
                            }

                        } catch (RemoteException e1) {
                            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                        } catch (SQLException e1) {
                            System.out.println(e1.getMessage());
                        }
                    }
                    refreshList();
                    new TranslucentFrame(this, "审批成功", Color.GREEN);
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==disappbt){
            int [] rows = table.getSelectedRows();
            if (rows.length<=0){
                new TranslucentFrame(this, "请选择要审批的单据(按住ctrl可多选)", Color.RED);
            }
            else {//选择了行
                if (isBLInited()){
                    for (int i:rows){
                        String id=(String)table.getValueAt(i, 0);
                        String receiptType=(String)table.getValueAt(i,1);
                        try {
                            if (receiptType.equals("付款单")) {
                                payReceiptBLService.updateState(id, ReceiptState.UNAPPROVED);
                            } else if (receiptType.equals("中转单")) {
                                transferReceiptBLService.updateState(id, ReceiptState.UNAPPROVED);
                            }

                        } catch (RemoteException e1) {
                            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                        } catch (SQLException e1) {
                            System.out.println(e1.getMessage());
                        }
                    }
                    refreshList();
                    new TranslucentFrame(this, "审批成功", Color.GREEN);
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==refreshbt){
            refreshList();
            new TranslucentFrame(this, "刷新成功", Color.GREEN);
        }
    }
}

class EditableTableModel extends MyDefaultTableModel{

    public EditableTableModel() {
    }

    public EditableTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public EditableTableModel(Vector columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public EditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public EditableTableModel(Vector data, Vector columnNames) {
        super(data, columnNames);
    }

    public EditableTableModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }
}
