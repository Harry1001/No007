package presentation.contentpanel;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.strategyblservice.CalCarriageService;
import businessLogicService.transportblservice.EntruckBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commonpanel.ErrorDialog;
import vo.loginvo.LoginResultVO;
import vo.receiptvo.EntruckReceiptVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Harry on 2015/11/27.
 */
public class EntruckPanel extends JPanel implements ActionListener {

    EntruckBLService entruckBLService;
    CalCarriageService calCarriageService;

    MainFrame parent;

    MyLabel timeL=new MyLabel("装车日期");
    MyLabel numL=new MyLabel("汽运编号");
    MyLabel destiL=new MyLabel("到达地");
    MyLabel truckIDL=new MyLabel("车辆代号");
    MyLabel feeL=new MyLabel("运费");
    //MyLabel orderNumL=new MyLabel("本次装箱所有订单条形码号");

    MyTextField timeT=new MyTextField(15);
    MyTextField numT=new MyTextField(15);
    MyTextField destiT=new MyTextField(15);
    MyTextField truckIDT=new MyTextField(15);
    MyTextField feeT=new MyTextField(15);
    MyTextField orderNumText=new MyTextField(15);

    MyButton feebt=new MyButton("生成运费");
    MyButton submitbt=new MyButton("提交");
    MyButton cancelbt=new MyButton("取消");
    MyButton appendbt=new MyButton("添加");
    MyButton deletebt=new MyButton("删除");

    MyDefaultTableModel defaultTableModel;
    JTable table;//此处表格和人员管理等表格大小不同，故不使用MyTable

    public EntruckPanel(MainFrame par){
        this.parent=par;

        initUI();
        setPresentTime();

        feebt.addActionListener(this);
        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
        appendbt.addActionListener(this);
        deletebt.addActionListener(this);

        initBL();
    }

    /**
     * 创建逻辑层引用
     */
    private void initBL(){

        try {
            entruckBLService= BLFactory.getEntruckBLService();
            calCarriageService=BLFactory.getCalCarriageService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
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
        gbc.gridy=6;
        this.add(orderNumText,gbc);

        gbc.gridx=4;
        gbc.gridy=6;
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
            new ErrorDialog(parent, "服务器连接超时");
        } catch (SQLException e) {
            new ErrorDialog(parent, "SQLException");
        }
    }

    private String getMyLocation(){
        LoginResultVO vo=parent.getUserIdentity();
        String cityID=vo.getId().substring(0,3);//员工编号的前3位是城市区号（总经理、财务、管理员除外）
        return Constent.getLocationByCityID(cityID);
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
        numT.setText("");
        destiT.setText("");
        truckIDT.setText("");
        feeT.setText("");
        orderNumText.setText("");
        defaultTableModel.getDataVector().clear();
        table.revalidate();
        table.updateUI();
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==feebt){
            calCarriage();
        } else if (e.getSource()==submitbt){
            if (entruckBLService!=null){
                //todo

                refresh();
            } else{
                initBL();
            }
        } else if (e.getSource()==cancelbt){
            refresh();
        } else if (e.getSource()==appendbt){
            if (checkOrderNum()){
                String[] id={orderNumText.getText()};
                defaultTableModel.addRow(id);
                orderNumText.setText("");
            }
        } else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row>=0){//如果没选择任何行为-1
                defaultTableModel.removeRow(row);
            }
        }
    }
}
