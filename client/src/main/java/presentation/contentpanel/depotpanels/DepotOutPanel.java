package presentation.contentpanel.depotpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.commodityblservice.CommodityBLService;
import businessLogicService.receiptblservice.SendReceiptBLService;
import constent.Constent;
import po.receiptpo.SendReceiptPO;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import typeDefinition.Vehicle;
import vo.loginvo.LoginResultVO;
import vo.receiptvo.DepotOutReceiptVO;

import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Harry on 2015/11/28.
 */
public class DepotOutPanel extends JPanel implements ActionListener, FocusListener {

    CommodityBLService commodityBLService;
    SendReceiptBLService sendReceiptBLService;

    MainFrame parent;
    MyLabel packIDL=new MyLabel("快递编号");
    MyLabel destiL=new MyLabel("目的地");
    MyLabel timeL=new MyLabel("出库日期");
    MyLabel vehicleL=new MyLabel("装运形式");
    MyLabel transIDL=new MyLabel("中转单/汽运编号");

    MyTextField packIDT=new MyTextField(25);
    MyTextField destiT=new MyTextField(25);
    MyTextField timeT=new MyTextField(25);
    MyTextField transIDT=new MyTextField(25);
    JRadioButton rbt1=new JRadioButton("汽车");
    JRadioButton rbt2=new JRadioButton("火车");
    JRadioButton rbt3=new JRadioButton("飞机");

    MyButton submitbt=new MyButton("提交(S)");
    MyButton cancelbt=new MyButton("取消(C)");

    public DepotOutPanel(MainFrame par){
        this.parent=par;
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"库存出库",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(rbt1);
        btgroup.add(rbt2);
        btgroup.add(rbt3);

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        gbc.gridx=gbc.gridy=0;
        this.add(packIDL,gbc);
        gbc.gridy++;
        this.add(destiL,gbc);
        gbc.gridy++;
        this.add(timeL,gbc);
        gbc.gridy++;
        this.add(transIDL,gbc);
        gbc.gridy++;
        this.add(vehicleL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(packIDT,gbc);
        gbc.gridy++;
        this.add(destiT,gbc);
        gbc.gridy++;
        this.add(timeT,gbc);
        gbc.gridy++;
        this.add(transIDT,gbc);
        gbc.gridy++;
        gbc.gridwidth=1;
        this.add(rbt1,gbc);
        gbc.gridx++;
        this.add(rbt2,gbc);
        gbc.gridx++;
        this.add(rbt3,gbc);

        gbc.gridx=1;
        gbc.gridy=6;
        this.add(submitbt,gbc);
        gbc.gridx++;
        this.add(cancelbt,gbc);

        setHotKey();

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);

        packIDT.addFocusListener(this);
        transIDT.addFocusListener(this);
        destiT.addFocusListener(this);
        timeT.addFocusListener(this);

        refresh();
        initBL();
    }

    private void setHotKey(){
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('C');
    }

    private void initBL(){
        try {
            commodityBLService= BLFactory.getCommodityBLService();
            sendReceiptBLService=BLFactory.getSendReceiptBLService();
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        } catch (NamingException e) {
            new ErrorDialog(parent, "NamingException");
        }
    }

    /**
     * 自动设置当前时间
     */
    private void setPresentTime(){
        timeT.setText(Constent.DATE_FORMAT.format(new Date()));
    }

    private boolean isDigit(String s){
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    private boolean checkPackID(){
        String id=packIDT.getText();
        if (id.length()!=Constent.ORDER_ID_LENGTH){
            return false;
        }
        return isDigit(id);
    }

    private boolean checkLoc(){
        String s=destiT.getText();
        if (s.length()<2){
            return false;
        }
        String ss=s.substring(0,2);
        for (int i=0;i<Constent.LOCATIONS.length;i++){
            if (ss.equals(Constent.LOCATIONS[i])){
                return true;
            }
        }
        return false;
    }

    private boolean checkTime(){
        String s=timeT.getText();
        try{
            Constent.DATE_FORMAT.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkTransID(){
        String s=transIDT.getText();
        if (s.length()!=Constent.Transfer_ID_LENGTH){
            return false;
        }
        return isDigit(s);
    }

    private boolean checkAll(){
        if (!checkPackID()){
            new TranslucentFrame(this, "快递编号必须为"+Constent.ORDER_ID_LENGTH+"位数字", Color.RED);
            return false;
        }

        if (!checkTime()){
            new TranslucentFrame(this, "时间格式必须为: yyyy-MM-dd HH:mm:ss", Color.RED);
            return false;
        }

        if (!checkLoc()){
            new TranslucentFrame(this, "目的地前两位必须为城市名", Color.RED);
            return false;
        }

        if (!checkTransID()){
            new TranslucentFrame(this, "中转单编号或汽运单编号必须为"+Constent.Transfer_ID_LENGTH+"位数字", Color.RED);
            return false;
        }

        return  true;
    }


    private String getMyHubID(){
        LoginResultVO vo=parent.getUserIdentity();
        String hubID=vo.getId().substring(0,4);//中转中心业务员编号的前4位是中转中心编号
        return hubID;
    }

    /**
     * 清空输入
     */
    private void refresh(){
        packIDT.setText("");
        destiT.setText("");
        setPresentTime();
        rbt1.setSelected(true);
        String hubID=getMyHubID();
        String timeNum=Constent.RECIEPT_NUM_FORMAT.format(new Date());
        transIDT.setText(hubID+timeNum+"+7位数字");

        packIDT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        destiT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        timeT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        transIDT.setBorder(BorderFactory.createLoweredSoftBevelBorder());

    }

    private void subRefresh(){
        packIDT.setText("");
        destiT.setText("");
        setPresentTime();

        packIDT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        destiT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        timeT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
    }

    private Vehicle getSelectedVehicle(){
        if (rbt1.isSelected()){
            return Vehicle.TRUCK;
        } else if (rbt2.isSelected()){
            return Vehicle.TRAIN;
        } else {
            return Vehicle.PLANE;
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                if (commodityBLService!=null){
                    try {
                        String packID=packIDT.getText();
                        Date time= Constent.DATE_FORMAT.parse(timeT.getText());
                        String desti=destiT.getText();
                        String transID=transIDT.getText();
                        Vehicle vehicle=getSelectedVehicle();
                        DepotOutReceiptVO vo=new DepotOutReceiptVO(packID,time,desti,vehicle,transID);
                        commodityBLService.submitOut(vo);
                        subRefresh();
                        new TranslucentFrame(this, MessageType.SUBMIT_SUCCESS, Color.GREEN);
                    } catch (ParseException e1) {
                        new TranslucentFrame(this, "请不要改变默认时间格式", Color.RED);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (SQLException e1) {
                        System.out.println("库存出库："+e1.getMessage());

                    } catch (MalformedURLException e1) {
                        new ErrorDialog(parent, "MalformedURLException");
                    } catch (NotBoundException e1) {
                        new ErrorDialog(parent, "NotBoundException");
                    } catch (NamingException e1) {
                        new ErrorDialog(parent, "NamingException");
                    }
                }
                else {
                    initBL();
                }
            }
        } else if (e.getSource()==cancelbt){
            refresh();
        }
    }

    public void focusGained(FocusEvent e) {
        if (e.getSource()==transIDT){
            String hubID=getMyHubID();
            String timeNum=Constent.RECIEPT_NUM_FORMAT.format(new Date());
            if (transIDT.getText().equals(hubID+timeNum+"+7位数字")){
                transIDT.setText(hubID+timeNum);
            }
        }
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource()==packIDT){
            if (checkPackID()){
                //packIDT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                if (sendReceiptBLService!=null){
                    try {
                        SendReceiptPO po = sendReceiptBLService.getSendReceipt(packIDT.getText());
                        if (po!=null){
                            packIDT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                            destiT.setText(po.getReceiverLoc());
                            destiT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                        }
                        else {
                            packIDT.setBorder(BorderFactory.createLineBorder(Color.RED));
                            destiT.setText("");
                            destiT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
                        }
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else{
                    initBL();
                }
            }
            else{
                packIDT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else if (e.getSource()==destiT){
            if (checkLoc()){
                destiT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else{
                destiT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else if (e.getSource()==timeT){
            if (checkTime()){
                timeT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else{
                timeT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else if(e.getSource()==transIDT){
            if (checkTransID()){
                transIDT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else{
                transIDT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
    }
}
