package presentation.contentpanel.depotpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.commodityblservice.CommodityBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.Location;
import vo.receiptvo.DepotInReceiptVO;

import javax.naming.NamingException;
import javax.swing.*;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.commodityblservice.CommodityBLService;
import constent.Constent;
import myexceptions.TransportBLException;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.Location;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.DepotInReceiptVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Harry on 2015/11/28.
 */

public class DepotInPanel extends JPanel implements ActionListener {

    CommodityBLService commodityBLService;

    MainFrame parent;
    MyLabel packIDL=new MyLabel("快递编号");
    MyLabel destiL=new MyLabel("目的地");
    MyLabel timeL=new MyLabel("入库日期");
    MyLabel quhaoL=new MyLabel("区号");
    MyLabel paihaoL=new MyLabel("排号");
    MyLabel jiahaoL=new MyLabel("架号");
    MyLabel weihaoL=new MyLabel("位号");
    //MyLabel hubIDL=new MyLabel("中转中心编号");

    MyTextField packIDT=new MyTextField(25);
    MyTextField destiT=new MyTextField(25);
    MyTextField timeT=new MyTextField(25);
    //MyTextField hubIDT=new MyTextField(25);
    MyTextField quhaoT=new MyTextField(5);
    MyTextField paihaoT=new MyTextField(5);
    MyTextField jiahaoT=new MyTextField(5);
    MyTextField weihaoT=new MyTextField(5);

    MyButton submitbt=new MyButton("提交");
    MyButton cancelbt=new MyButton("取消");

    public DepotInPanel(MainFrame par){
        this.parent=par;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        gbc.gridx=gbc.gridy=0;
        this.add(packIDL,gbc);
        gbc.gridy++;
        this.add(destiL,gbc);
        gbc.gridy++;
        this.add(timeL,gbc);
        //gbc.gridy++;
        //this.add(hubIDL,gbc);
        gbc.gridy++;
        this.add(quhaoL,gbc);
        gbc.gridy++;
        this.add(jiahaoL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(packIDT,gbc);
        gbc.gridy++;
        this.add(destiT,gbc);
        gbc.gridy++;
        this.add(timeT,gbc);
        //gbc.gridy++;
        //this.add(hubIDT,gbc);

        gbc.gridwidth=1;
        gbc.gridy++;
        this.add(quhaoT,gbc);
        gbc.gridy++;
        this.add(jiahaoT,gbc);

        gbc.gridx++;
        this.add(weihaoL,gbc);
        gbc.gridy--;
        this.add(paihaoL,gbc);

        gbc.gridx++;
        this.add(paihaoT,gbc);
        gbc.gridy++;
        this.add(weihaoT,gbc);

        gbc.gridx=1;
        gbc.gridy=7;
        this.add(submitbt,gbc);
        gbc.gridx++;
        this.add(cancelbt,gbc);
        
        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
        setPresentTime();
        initBL();
        refresh();
    }

	private void initBL() {
		try {
			commodityBLService=BLFactory.getCommodityBLService();
		} catch (MalformedURLException e) {
			new ErrorDialog(parent, "MalformedURLException");
		} catch (RemoteException e) {
			new ErrorDialog(parent, "服务器连接超时");
		} catch (NamingException e) {
			new ErrorDialog(parent, "NamingException");
		} catch (NotBoundException e) {
			new ErrorDialog(parent, "NotBoundException");
		}
	}




	private boolean checkAllFormat() {
		if(!checkPackID(packIDT.getText())) {
            new ErrorDialog(parent, "快递编号必须为"+Constent.ORDER_ID_LENGTH+"位数字");
            return false;
        }

        if (!checkLoc(destiT.getText())){
            new ErrorDialog(parent, "目的地前两位必须为城市名");
            return false;
        }

        if (!checkTime(timeT.getText())){
            new ErrorDialog(parent, "时间格式为：yyyy-MM-dd HH:mm:ss");
            return false;
        }

		if(!checkNumber(quhaoT.getText())) {
            new ErrorDialog(parent, "区号必须为正整数");
            return false;
        }

        if(!checkNumber(paihaoT.getText())) {
            new ErrorDialog(parent, "排号必须为正整数");
            return false;
        }

        if(!checkNumber(jiahaoT.getText())) {
            new ErrorDialog(parent, "架号必须为正整数");
            return false;
        }

        if(!checkNumber(weihaoT.getText())) {
            new ErrorDialog(parent, "位号必须为正整数");
            return false;
        }

        return true;
	}

	private boolean checkPackID(String s) {
        if (s.length()!=Constent.ORDER_ID_LENGTH){
            return false;
        }
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9')
                return false;
        }
        return true;
	}

	private boolean checkNumber(String s) {
        int num;
        try{
            num=Integer.parseInt(s);
        }catch (NumberFormatException e1){
            return false;
        }
        return (num>0);
    }

    private boolean checkTime(String s){
        try{
            Constent.DATE_FORMAT.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkLoc(String s){
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
	
	//private boolean checkHubID(String s){
    //    if (s.length()!=Constent.HUB_ID_LENGTH)
    //    	return false;
    //    for (int i=0;i<Constent.HUB_ID_LENGTH;i++){
    //        if (s.charAt(i)<'0'||s.charAt(i)>'9')
    //            return false;
    //    }
    //    return true;
    //}


    /**
     * 自动设置当前时间
     */
    private void setPresentTime(){
        timeT.setText(Constent.DATE_FORMAT.format(new Date()));
    }


    /**
     * 清空输入
     */
    private void refresh(){
        packIDT.setText("");
        destiT.setText("");
        setPresentTime();
        quhaoT.setText("");
        paihaoT.setText("");
        jiahaoT.setText("");
        weihaoT.setText("");
        //hubIDT.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAllFormat()){
                if (commodityBLService!=null){
                    try {
                        String packID=packIDT.getText();
                        Date time= Constent.DATE_FORMAT.parse(timeT.getText());
                        String desti=destiT.getText();
                        int quhao=Integer.parseInt(quhaoT.getText());
                        int paihao=Integer.parseInt(paihaoT.getText());
                        int jiahao=Integer.parseInt(jiahaoT.getText());
                        int weihao=Integer.parseInt(weihaoT.getText());
                        //String hubID=hubIDT.getText();
                        String hubID=parent.getUserIdentity().getId().substring(0,4);//当前账户工号前4位即为中转中心编号
                        Location loc=new Location(hubID, quhao,paihao,jiahao,weihao);
                        DepotInReceiptVO vo=new DepotInReceiptVO(packID,time, desti, loc);
                        commodityBLService.submitIn(vo);
                        refresh();
                    } catch (ParseException e1) {
                        new ErrorDialog(parent, "时间格式为：yyyy-MM-dd HH:mm:ss");
                    } catch (RemoteException e1) {
                        new ErrorDialog(parent, "服务器连接超时");
                    } catch (SQLException e1) {
                        System.out.println("库存入库："+e1.getMessage());
                        String s=e1.getMessage();
                        if ((s.length()>=15)&&(s.substring(0,15).equals("Duplicate entry"))){
                            new ErrorDialog(parent, "该入库单已存在");
                        }
                        else {
                            new ErrorDialog(parent, "SQLException");
                        }

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
        }
        else if (e.getSource()==cancelbt){//取消按钮清空输入
            refresh();
        }
    }

}
