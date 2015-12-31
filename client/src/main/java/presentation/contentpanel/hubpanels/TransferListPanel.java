package presentation.contentpanel.hubpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.receiptblservice.TransferReceiptBLService;
import constent.Constent;
import presentation.Images.Images;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import typeDefinition.ReceiptState;
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
 * Created by Harry on 2015/12/26.
 */
public class TransferListPanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private TransferPanel transferPanel;

    private MyTable table;
    private MyDefaultTableModel defaultTableModel;
    private MyButton addbt = new MyButton("新建单据(N)", Images.GO_IMAGE);//新建单据按钮
    private MyButton handlebt=new MyButton("处理(H)");//处理已被审批的单据按钮
    private MyButton refreshbt=new MyButton("刷新(R)");//刷新通知按钮

    private Vector<String> names;//表格列名

    private TransferReceiptBLService transferReceiptBLService;

    public TransferListPanel(MainFrame par, TransferPanel transferPanel){
        this.parent=par;
        this.transferPanel=transferPanel;

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
            transferReceiptBLService= BLFactory.getTransferReceiptBLService();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void refreshList(){
        if (transferReceiptBLService!=null){
            try {
                ArrayList<TransferReceiptVO> approvedVOs=transferReceiptBLService.getListByState(ReceiptState.APPROVED);
                ArrayList<TransferReceiptVO> unapprovedVOs=transferReceiptBLService.getListByState(ReceiptState.UNAPPROVED);
                approvedVOs.addAll(unapprovedVOs);
                defaultTableModel.getDataVector().clear();
                Vector<Vector> data=new Vector<Vector>();

                for (TransferReceiptVO vo : approvedVOs ) {
                    String id=vo.getTransferID();
                    if (id.substring(0,4).equals(getMyHubID())){
                        String timestr=Constent.DATE_FORMAT.format(vo.getTransferDate());
                        double fee=vo.getTransferFee();
                        String fromPlace=vo.getDepartLoc();
                        String toPlace=vo.getArriveLoc();
                        String statestr;
                        if (vo.getState()==ReceiptState.APPROVED){
                            statestr="审批通过";
                        } else {
                            statestr="审批不通过";
                        }

                        Vector<Object> item=new Vector<Object>();
                        item.add(id);
                        item.add(timestr);
                        item.add(fee);
                        item.add(fromPlace);
                        item.add(toPlace);
                        item.add(statestr);
                        data.add(item);
                    }
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

    private String getMyHubID(){
        String s=parent.getUserIdentity().getId();
        return s.substring(0,4);
    }

    private void initUI(){

        names=new Vector<String>();
        names.clear();
        names.add("中转单编号");
        names.add("中转日期");
        names.add("运费");
        names.add("出发地");
        names.add("目的地");
        names.add("审批结果");

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
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"中转单列表",
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
            new TranslucentFrame(this, "请选择待处理单据(按住ctrl可多选)", Color.RED);
        }
        else {
            try{
                for (int i:rows){
                    String id=(String)table.getValueAt(i, 0);
                    transferReceiptBLService.updateState(id, ReceiptState.HANDLED);

                }
                refreshList();
                new TranslucentFrame(this, "单据已处理", Color.GREEN);
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
            new TranslucentFrame(this, "刷新成功", Color.GREEN);
        }
        else if (e.getSource()==addbt){
            transferPanel.showTransferReceipt();
        }
        else if (e.getSource()==handlebt){
            setReceiptHandled();

        }
    }
}
