package presentation.contentpanel.hubpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.strategyblservice.CalCarriageService;
import businessLogicService.transportblservice.TransferBLService;
import constent.Constent;
import presentation.Images.Images;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import typeDefinition.ReceiptState;
import typeDefinition.Vehicle;
import vo.receiptvo.TransferReceiptVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 新建中转单面板
 */
public class TransferReceiptPanel extends JPanel implements ActionListener, FocusListener {
    TransferBLService transferBLService;
    CalCarriageService carriageService;
    MainFrame parent;
    TransferPanel transferPanel;

    MyLabel[] labels=new MyLabel[8];
    MyTextField[] textFields=new MyTextField[7];
    JRadioButton[] vehicles=new JRadioButton[3];
    MyButton calfeebt=new MyButton("CalFee");
    MyButton addbt=new MyButton("Add");
    MyButton deletebt=new MyButton("Delete");
    MyButton submitbt=new MyButton("Submit");
    MyButton cancelbt=new MyButton("Refresh");
    MyButton backbt=new MyButton(" Back ", Images.BACK_IMAGE);

    MyDefaultTableModel defaultTableModel;
    JTable table;
    JPanel transferWay;
    MyTextField orderT=new MyTextField();

    public TransferReceiptPanel(MainFrame par, TransferPanel transferPanel) {
        this.parent=par;
        this.transferPanel=transferPanel;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"中转单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));

        initUI();
        setHotKey();

        calfeebt.addActionListener(this);
        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
        backbt.addActionListener(this);
        textFields[1].addFocusListener(this);

        refresh();
        initBL();
    }

    private void setHotKey(){
        backbt.setMnemonic('B');
        calfeebt.setMnemonic('C');
        addbt.setMnemonic('A');
        deletebt.setMnemonic('D');
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('R');
    }

    private void initBL(){
        this.transferBLService= BLFactory.getTransferBLService();
        try {
            this.carriageService=BLFactory.getCalCarriageService();
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void refresh(){
        for (int i=0;i<7;i++){
            textFields[i].setText("");
        }
        orderT.setText("");
        setPresentTime();
        vehicles[0].setSelected(true);
        String hubID=parent.getUserIdentity().getId().substring(0,4);

        textFields[1].setText(hubID+ Constent.RECIEPT_NUM_FORMAT.format(new Date())+"+7位数字");
        defaultTableModel.getDataVector().clear();
        table.revalidate();
        table.updateUI();
    }

    private boolean checkAll(){
        if (!checkTime()){
            new TranslucentFrame(this, "日期格式必须为：yyyy-MM-dd HH:mm:ss", Color.RED);
            return false;
        }

        if (!checkTransID()){
            new TranslucentFrame(this, "中转单号必须为"+Constent.Transfer_ID_LENGTH+"位数字", Color.RED);
            return false;
        }

        if (!checkVehicleID()){
            new TranslucentFrame(this, "班次/车牌号不可为空", Color.RED);
            return false;
        }

        if (!checkFee()){
            new TranslucentFrame(this, "运费必须为正数", Color.RED);
            return false;
        }

        if (!checkFromLoc()){
            new TranslucentFrame(this, "出发地前两位必须为市名", Color.RED);
            return false;
        }

        if (!checkToLoc()){
            new TranslucentFrame(this, "到达地前两位必须为市名", Color.RED);
            return false;
        }

        if (!checkCounterID()){
            new TranslucentFrame(this, "货柜号必须为正整数", Color.RED);
            return false;
        }

        return true;
    }

    private boolean checkCalFeeCondition(){
        if (!checkFromLoc()){
            new TranslucentFrame(this, "出发地前两位必须为市名", Color.RED);
            return false;
        }

        if (!checkToLoc()){
            new TranslucentFrame(this, "到达地前两位必须为市名", Color.RED);
            return false;
        }

        return true;
    }

    private boolean checkTime(){
        String time=textFields[0].getText();
        try{
            Constent.DATE_FORMAT.parse(time);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTransID(){
        String id=textFields[1].getText();
        if (id.length()!=Constent.Transfer_ID_LENGTH){
            return false;
        }
        return isDigit(id);
    }

    private boolean checkVehicleID(){
        String id=textFields[2].getText();
        return !id.isEmpty();
    }

    private boolean checkFee(){
        String s=textFields[6].getText();
        try{
            double fee=Double.parseDouble(s);
            return (fee>0);
        } catch (NumberFormatException e){
            return false;
        }
    }

    private boolean checkFromLoc(){
        String s1=textFields[3].getText();
        if (s1.length()<2){
            return false;
        }
        String s2=s1.substring(0,2);
        for (int i=0;i<Constent.LOCATIONS.length;i++){
            if (s2.equals(Constent.LOCATIONS[i])){
                return true;
            }
        }
        return false;
    }

    private boolean checkToLoc(){
        String s1=textFields[4].getText();
        if (s1.length()<2){
            return false;
        }
        String s2=s1.substring(0,2);
        for (int i=0;i<Constent.LOCATIONS.length;i++){
            if (s2.equals(Constent.LOCATIONS[i])){
                return true;
            }
        }
        return false;
    }

    private boolean checkCounterID(){
        String s=textFields[5].getText();
        try{
            int x=Integer.parseInt(s);
            return (x>0);
        } catch (NumberFormatException e){
            return false;
        }
    }

    private boolean checkOrderID(){
        String s=orderT.getText();
        if (s.length()!=Constent.ORDER_ID_LENGTH){
            return false;
        }
        return isDigit(s);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==calfeebt){
            if (checkCalFeeCondition()){
                if (carriageService!=null){
                    TransferReceiptVO vo= getTempVO();
                    double fee=0;
                    try {
                        fee = carriageService.calCarriage(vo);
                        textFields[6].setText(fee+"");
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    } catch (ClassNotFoundException e1) {
                        System.out.println(e1.getMessage());
                    } catch (IOException e1) {
                        System.out.println(e1.getMessage());
                    } catch (NotBoundException e1) {
                        System.out.println(e1.getMessage());
                    }
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==addbt){
            if (checkOrderID()){
                String [] data={orderT.getText()};
                defaultTableModel.addRow(data);
                orderT.setText("");
            }
            else {
                new TranslucentFrame(this, "订单号必须为"+Constent.ORDER_ID_LENGTH+"位整数", Color.RED);
            }
        }
        else if(e.getSource()==backbt){
            transferPanel.showList();
        }
        else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row>=0) {
                defaultTableModel.removeRow(row);
            }
        }
        else if (e.getSource()==submitbt){
            if (checkAll()){
                if (transferBLService!=null){
                    TransferReceiptVO vo= getFinalVO();
                    try {
                        transferBLService.submit(vo);
                        refresh();
                        new TranslucentFrame(transferPanel, MessageType.SUBMIT_SUCCESS, Color.GREEN);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (SQLException e1) {
                        System.out.println("中转单sql："+e1.getMessage());
                    } catch (MalformedURLException e1) {
                        System.out.println(e1.getMessage());
                    } catch (NotBoundException e1) {
                        System.out.println(e1.getMessage());
                    }
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==cancelbt){
            refresh();
        }
    }

    public void focusGained(FocusEvent e) {
        if (e.getSource()==textFields[1]){
            String hubID=parent.getUserIdentity().getId().substring(0,4);
            Date today=new Date();
            if (textFields[1].getText().equals(hubID+Constent.RECIEPT_NUM_FORMAT.format(today)+"+7位数字")){
                textFields[1].setText(hubID+Constent.RECIEPT_NUM_FORMAT.format(today));
            }
        }
    }

    public void focusLost(FocusEvent e) {

    }

    private boolean isDigit(String s){
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    private TransferReceiptVO getTempVO(){
        String fromLoc=textFields[3].getText();
        String toLoc=textFields[4].getText();
        int vehicleNum=0;
        for (; vehicleNum<3;vehicleNum++){
            if (vehicles[vehicleNum].isSelected())
                break;
        }
        Vehicle vehicle=Vehicle.values()[vehicleNum];
        return new TransferReceiptVO(vehicle, null, null, null, fromLoc, toLoc, 0, null, 0.0, ReceiptState.SUBMITTED);
    }

    /**
     * 提交单据时完整的vo，包含运费等所有信息
     * @return
     */
    private TransferReceiptVO getFinalVO(){
        int vehicleNum=0;
        for (; vehicleNum<3;vehicleNum++){
            if (vehicles[vehicleNum].isSelected())
                break;
        }
        Vehicle vehicle=Vehicle.values()[vehicleNum];
        Date time=null;
        double fee=0;
        try{
            time=Constent.DATE_FORMAT.parse(textFields[0].getText());
            fee=Double.parseDouble(textFields[6].getText());
        } catch (ParseException e) {
            new TranslucentFrame(this, "日期格式必须为：yyyy-MM-dd HH:mm:ss", Color.RED);
        } catch (NumberFormatException e){
            new TranslucentFrame(this, "运费必须为正数", Color.RED);
        }
        String transID=textFields[1].getText();
        String vehicleID=textFields[2].getText();
        String fromLoc=textFields[3].getText();
        String toLoc=textFields[4].getText();
        int counterID=Integer.parseInt(textFields[5].getText());
        ArrayList<String> orderIDs=new ArrayList<String>();

        //从表格中读取所有单号
        {
            int row = table.getRowCount();
            for (int i=0;i<row;i++) {
                orderIDs.add((String)table.getValueAt(i, 0));
            }
        }

        TransferReceiptVO vo = new TransferReceiptVO(vehicle, time, transID, vehicleID, fromLoc, toLoc,
                counterID, orderIDs, fee, ReceiptState.SUBMITTED);

        return vo;
    }

    private void initUI(){
        labels[0]=new MyLabel("中转方式");
        labels[1]=new MyLabel("中转日期");
        labels[2]=new MyLabel("中转单编号");
        labels[3]=new MyLabel("班次/车牌号");
        labels[4]=new MyLabel("出发地");
        labels[5]=new MyLabel("目的地");
        labels[6]=new MyLabel("货柜号");
        labels[7]=new MyLabel("运费");

        for(int i=0;i<7;i++){
            textFields[i]=new MyTextField();
        }

        vehicles[0]=new JRadioButton("汽车");
        vehicles[1]=new JRadioButton("火车");
        vehicles[2]=new JRadioButton("飞机");
        transferWay = new JPanel();
        transferWay.setOpaque(false);
        transferWay.setLayout(new GridLayout(1,3));
        transferWay.add(vehicles[0]);
        transferWay.add(vehicles[1]);
        transferWay.add(vehicles[2]);
        ButtonGroup btgroup=new ButtonGroup();
        for(JRadioButton bt:vehicles){
            btgroup.add(bt);
        }
        vehicles[0].setSelected(true);

        String [] names={"本次装箱所有托运单号"};

        defaultTableModel=new MyDefaultTableModel(names, 0);
        table=new JTable(defaultTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(300,450));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;

        gbc.gridx=gbc.gridy=0;
        this.add(backbt, gbc);

        gbc.fill=GridBagConstraints.BOTH;

        for(gbc.gridx=0,gbc.gridy=1;gbc.gridy<=8;gbc.gridy++){
            this.add(labels[gbc.gridy-1],gbc);
        }

        gbc.gridy=1;
        gbc.gridx=1;
        this.add(transferWay,gbc);


        gbc.weightx=1.0;
        for(gbc.gridx=1,gbc.gridy=2;gbc.gridy<=8;gbc.gridy++){
            this.add(textFields[gbc.gridy-2],gbc);
        }

        gbc.weightx=0.0;
        gbc.gridx=2;
        gbc.gridy=8;
        gbc.fill=GridBagConstraints.NONE;
        this.add(calfeebt,gbc);


        gbc.weightx=1.0;
        gbc.gridx=3;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.gridheight=5;
        gbc.fill=GridBagConstraints.BOTH;
        this.add(new JScrollPane(table),gbc);

        gbc.gridheight=1;
        gbc.gridy=6;
        this.add(orderT,gbc);

        gbc.weightx=0.0;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridy++;
        this.add(addbt,gbc);
        gbc.gridx++;
        this.add(deletebt,gbc);

        gbc.gridx=0;
        gbc.gridy=9;
        gbc.gridwidth=5;
        this.add(new JSeparator(),gbc);
        gbc.gridx=2;
        gbc.gridy++;
        gbc.gridwidth=1;
        this.add(submitbt,gbc);
        gbc.gridx=3;
        this.add(cancelbt,gbc);
    }

    private void setPresentTime(){
        String time= Constent.DATE_FORMAT.format(new Date());
        textFields[0].setText(time);
    }
}
