package presentation.contentpanel;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.strategyblservice.CalCarriageService;
import businessLogicService.transportblservice.EntruckBLService;
import constent.Constent;
import myexceptions.TransportBLException;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.loginvo.LoginResultVO;
import vo.receiptvo.EntruckReceiptVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 新建装车单
 */
public class EntruckPanel extends JPanel implements ActionListener, FocusListener {

    EntruckBLService entruckBLService;
    CalCarriageService calCarriageService;

    MainFrame parent;

    MyLabel timeL=new MyLabel("装车日期");
    MyLabel numL=new MyLabel("汽运编号");
    MyLabel destiL=new MyLabel("到达地");
    MyLabel truckIDL=new MyLabel("车辆代号");
    MyLabel feeL=new MyLabel("运费");

    MyTextField timeT=new MyTextField();
    MyTextField numT=new MyTextField();
    MyTextField destiT=new MyTextField();
    MyTextField truckIDT=new MyTextField();
    MyTextField feeT=new MyTextField();
    MyTextField orderNumText=new MyTextField();

    MyButton feebt=new MyButton("CalFee");
    MyButton submitbt=new MyButton("Submit");
    MyButton cancelbt=new MyButton("Refresh");
    MyButton appendbt=new MyButton("Add");
    MyButton deletebt=new MyButton("Delete");

    MyDefaultTableModel defaultTableModel;
    JTable table;//此处表格和人员管理等表格大小不同，故不使用MyTable

    public EntruckPanel(MainFrame par){
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"装车单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        initUI();
        setHotKey();

        setPresentTime();

        feebt.addActionListener(this);
        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
        appendbt.addActionListener(this);
        deletebt.addActionListener(this);
        orderNumText.addActionListener(this);

        timeT.addFocusListener(this);
        numT.addFocusListener(this);
        destiT.addFocusListener(this);
        truckIDT.addFocusListener(this);
        feeT.addFocusListener(this);

        numT.addFocusListener(this);
        truckIDT.addFocusListener(this);

        initBL();
        refresh();
    }

    private void setHotKey(){
        feebt.setMnemonic('F');
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('R');
        appendbt.setMnemonic('A');
        deletebt.setMnemonic('D');
    }

    /**
     * 创建逻辑层引用
     */
    private void initBL(){

        try {
            entruckBLService= BLFactory.getEntruckBLService();
            calCarriageService=BLFactory.getCalCarriageService();
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 初始化界面
     */
    private void initUI(){
        String [] names={"本次装箱所有订单条形码号"};
        defaultTableModel=new MyDefaultTableModel(names,0);

        table=new JTable(defaultTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(300,200));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.NORTH;

        this.add(timeL, gbc);
        gbc.gridy=1;
        this.add(numL,gbc);
        gbc.gridy=2;
        this.add(destiL,gbc);
        gbc.gridy=3;
        this.add(truckIDL,gbc);
        gbc.gridy=4;
        this.add(feeL, gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(timeT,gbc);
        gbc.gridy=1;
        this.add(numT,gbc);
        gbc.gridy=2;
        this.add(destiT,gbc);
        gbc.gridy=3;
        this.add(truckIDT,gbc);
        gbc.gridy=4;
        this.add(feeT,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        gbc.gridwidth=4;
        gbc.gridheight=6;
        this.add(new JScrollPane(table),gbc);

        gbc.gridheight=1;
        gbc.gridwidth=2;
        gbc.gridy=5;
        this.add(orderNumText,gbc);

        gbc.gridx=4;
        gbc.gridy=5;
        gbc.gridwidth=1;
        //gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(appendbt,gbc);
        gbc.gridx=5;
        this.add(deletebt,gbc);

        gbc.gridx=1;
        gbc.gridy=5;
        gbc.anchor=GridBagConstraints.CENTER;
        this.add(feebt,gbc);
        gbc.gridy=9;
        gbc.gridx=1;
        this.add(submitbt,gbc);
        gbc.gridx=2;
        this.add(cancelbt,gbc);
    }

    private void calCarriage(){
        EntruckReceiptVO vo=new EntruckReceiptVO(null,null,null,null,null,0.0);//计算装车单运费时不需要太多信息，null就可以
        try {
            if (calCarriageService!=null){
                double fee=calCarriageService.calCarriage(vo);
                feeT.setText(fee+"");
            } else {
                initBL();
            }

        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * 根据登录帐号的id信息得到所在营业厅的编号或中转中心编号+00
     * @return
     */
    private String getMyStoreID(){
        LoginResultVO vo=parent.getUserIdentity();
        String storeID=vo.getId().substring(0,6);//营业厅业务员编号的前6位是营业厅编号
        return storeID;
    }

    /**
     * 检查时间格式
     * @return
     */
    private boolean checkTime(){
        String s=timeT.getText();
        try{
            Constent.DATE_FORMAT.parse(s);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 检查地址输入是个否规范
     * @return
     */
    private boolean checkDestination(){
        String s=destiT.getText();
        if (s.length()<2)
            return false;
        s=s.substring(0,2);
        for (String loc: Constent.LOCATIONS){
            if (loc.equals(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * 清空输入
     */
    private void refresh(){
        setPresentTime();
        numT.setText(getMyStoreID()+Constent.RECIEPT_NUM_FORMAT.format(new Date())+"+5位编号待输入");
        destiT.setText("");
        truckIDT.setText(getMyStoreID()+"+3位数字待输入");
        feeT.setText("");
        orderNumText.setText("");
        defaultTableModel.getDataVector().clear();
        table.revalidate();
        table.updateUI();
        timeT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        numT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        destiT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        truckIDT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        feeT.setBorder(BorderFactory.createLoweredSoftBevelBorder());

    }

    private void setPresentTime(){
        timeT.setText(Constent.DATE_FORMAT.format(new Date()));
    }

    /**
     * 检查订单条形码号是否符合规范
     * @return
     */
    private boolean checkOrderNum(){
        String orderID=orderNumText.getText();
        if (orderID.length()!=Constent.ORDER_ID_LENGTH){
            return false;
        }
        for (int i=0;i<Constent.ORDER_ID_LENGTH;i++){
            if (orderID.charAt(i)<'0'||orderID.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    private boolean checkQiYunNum(){
        String s=numT.getText();
        if (s.length()!=Constent.QIYUN_ID_LENGTH){
            return false;
        }
        for (int i=0;i<Constent.QIYUN_ID_LENGTH;i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        String ss=s.substring(0, 14);
        if (!ss.equals(getMyStoreID()+Constent.RECIEPT_NUM_FORMAT.format(new Date()))){//检查汽运编号的前14位为营业厅编号+日期
            return false;
        }
        return true;
    }

    private boolean checkTruckID(){
        String s=truckIDT.getText();
        if (s.length()!=Constent.TRUCK_ID_LENGTH){
            return false;
        }
        for (int i=0;i<Constent.TRUCK_ID_LENGTH;i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    private boolean checkFee(){
        String s=feeT.getText();
        if (s.length()<=0){
            return false;
        }
        try{
            double f=Double.parseDouble(s);
            if (f<=0){
                return false;
            }
        } catch (NumberFormatException e1){
            return false;
        }
        return true;
    }

    private void checkAll() throws TransportBLException{
        if (!checkTime()){
            throw new TransportBLException("时间格式必须为 yyyy-MM-dd HH:MM:ss");
        }

        if (!checkQiYunNum()){
            throw new TransportBLException("汽运编号格式为：营业厅编号(6位)或中转中心编号(4位+00)+日期(8位)+5为数字");
        }

        if (!checkDestination()){
            throw new TransportBLException("地址前两位必须为城市民称");
        }

        if (!checkTruckID()){
            throw new TransportBLException("车辆代号为：营业厅编号(6为)+3位数字");
        }

        if (!checkFee()){
            throw new TransportBLException("运费必须为正数");
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==feebt){
            calCarriage();
        } else if (e.getSource()==submitbt){
            if (entruckBLService!=null){

                try{
                    checkAll();
                    Date date=Constent.DATE_FORMAT.parse(timeT.getText());
                    String num=numT.getText();
                    String desti=destiT.getText();
                    String truckID=truckIDT.getText();
                    double fee=Double.parseDouble(feeT.getText());
                    ArrayList<String> orderList=new ArrayList<String>();
                    for (int i=0;i<defaultTableModel.getRowCount();i++){
                        orderList.add((String)table.getValueAt(i, 0));
                    }

                    EntruckReceiptVO vo=new EntruckReceiptVO(date,num,desti,truckID,orderList, fee);
                    entruckBLService.submit(vo);
                    refresh();
                    new TranslucentFrame(this, MessageType.SUBMIT_SUCCESS, Color.GREEN);
                } catch (TransportBLException e1) {
                    new TranslucentFrame(this, e1.getMessage(), Color.RED);
                } catch (ParseException e1) {
                    System.out.println(e1.getMessage());
                } catch (RemoteException e1) {
                    new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                } catch (MalformedURLException e1) {
                    System.out.println(e1.getMessage());
                } catch (NotBoundException e1) {
                    System.out.println(e1.getMessage());
                }
            } else{
                initBL();
            }
        } else if (e.getSource()==cancelbt){
            refresh();
        } else if (e.getSource()==appendbt){
            appendOrder();
        } else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row>=0){//如果没选择任何行为-1
                defaultTableModel.removeRow(row);
            }
        }
        else if (e.getSource()==orderNumText){
            appendOrder();
        }
    }

    private void appendOrder(){
        if (checkOrderNum()){
            String[] id={orderNumText.getText()};
            defaultTableModel.addRow(id);
            orderNumText.setText("");
        } else{
            new TranslucentFrame(this, "订单条形码号必须为"+Constent.ORDER_ID_LENGTH+"位数字", Color.RED);
        }
    }

    public void focusGained(FocusEvent e) {
        if (e.getSource()==numT){
            String s=numT.getText();
            String ss=getMyStoreID()+Constent.RECIEPT_NUM_FORMAT.format(new Date());
            if (s.equals(ss+"+5位编号待输入")){
                numT.setText(ss);
            }
        }
        else if (e.getSource()==truckIDT){
            String s=truckIDT.getText();
            String ss=getMyStoreID();
            if (s.equals(ss+"+3位数字待输入")){
                truckIDT.setText(ss);
            }
        }
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource()==timeT){
            if (checkTime()){
                timeT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else {
                timeT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else if(e.getSource()==numT){
            if (checkQiYunNum()){
                numT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else {
                numT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else if(e.getSource()==destiT){
            if (checkDestination()){
                destiT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else {
                destiT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else if(e.getSource()==truckIDT){
            if (checkTruckID()){
                truckIDT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else {
                truckIDT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else if(e.getSource()==feeT){
            if (checkFee()){
                feeT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else {
                feeT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
    }
}
