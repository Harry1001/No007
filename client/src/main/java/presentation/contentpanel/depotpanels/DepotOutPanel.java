package presentation.contentpanel.depotpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.commodityblservice.CommodityBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.Vehicle;
import vo.receiptvo.DepotOutReceiptVO;

import javax.naming.NamingException;
import javax.swing.*;
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
 * Created by Harry on 2015/11/28.
 */
public class DepotOutPanel extends JPanel implements ActionListener {

    CommodityBLService commodityBLService;

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

    MyButton submitbt=new MyButton("提交");
    MyButton cancelbt=new MyButton("取消");

    public DepotOutPanel(MainFrame par){
        this.parent=par;

        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(rbt1);
        btgroup.add(rbt2);
        btgroup.add(rbt3);

        this.setLayout(new GridBagLayout());
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

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
        setPresentTime();
        initBL();
    }

    private void initBL(){
        try {
            commodityBLService= BLFactory.getCommodityBLService();
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
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

    private boolean checkAll(){
        //todo 待实现
        return  true;
    }

    /**
     * 清空输入
     */
    private void refresh(){
        packIDT.setText("");
        destiT.setText("");
        setPresentTime();
        rbt1.setSelected(true);
        transIDT.setText("");
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

                try {
                    String packID=packIDT.getText();
                    Date time= Constent.DATE_FORMAT.parse(timeT.getText());
                    String desti=destiT.getText();
                    String transID=transIDT.getText();
                    Vehicle vehicle=getSelectedVehicle();
                    DepotOutReceiptVO vo=new DepotOutReceiptVO(packID,time,desti,vehicle,transID);
                    commodityBLService.submitOut(vo);
                    refresh();
                } catch (ParseException e1) {
                    new ErrorDialog(parent, "请不要改变默认时间格式");
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (MalformedURLException e1) {
                    new ErrorDialog(parent, "MalformedURLException");
                } catch (NotBoundException e1) {
                    new ErrorDialog(parent, "NotBoundException");
                } catch (NamingException e1) {
                    new ErrorDialog(parent, "NamingException");
                }

            }
        } else if (e.getSource()==cancelbt){
            refresh();
        }
    }
}
