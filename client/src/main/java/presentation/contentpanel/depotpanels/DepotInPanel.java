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
    MyLabel hubIDL=new MyLabel("中转中心编号");

    MyTextField packIDT=new MyTextField(25);
    MyTextField destiT=new MyTextField(25);
    MyTextField timeT=new MyTextField(25);
    MyTextField hubIDT=new MyTextField(25);
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
        gbc.gridy++;
        this.add(hubIDL,gbc);
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
        gbc.gridy++;
        this.add(hubIDT,gbc);

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




	private void checkAllFormat() throws TransportBLException {
		if(!checkPackID(packIDT.getText()))
			throw new TransportBLException("快递编号必须为数字");
		if(!checkNumber(quhaoT.getText()))
			throw new TransportBLException("区号必须为正整数");
		if(!checkNumber(paihaoT.getText()))
			throw new TransportBLException("排号必须为正整数");
		if(!checkNumber(jiahaoT.getText()))
			throw new TransportBLException("架号必须为正整数");
		if(!checkNumber(weihaoT.getText()))
			throw new TransportBLException("位号必须为正整数");
		if(!checkHubID(hubIDT.getText()))
			throw new TransportBLException("中转中心编号必须为4位数字");
	}

	private boolean checkPackID(String s) {
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
        if (num>0) return true;
        return false;
    }
	
	private boolean checkHubID(String s){
        if (s.length()!=Constent.HUB_ID_LENGTH) 
        	return false;
        for (int i=0;i<Constent.HUB_ID_LENGTH;i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9')
                return false;
        }
        return true;
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
        quhaoT.setText("");
        paihaoT.setText("");
        jiahaoT.setText("");
        weihaoT.setText("");
        hubIDT.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                try {
                    String packID=packIDT.getText();
                    Date time= Constent.DATE_FORMAT.parse(timeT.getText());
                    String desti=destiT.getText();
                    int quhao=Integer.parseInt(quhaoT.getText());
                    int paihao=Integer.parseInt(paihaoT.getText());
                    int jiahao=Integer.parseInt(jiahaoT.getText());
                    int weihao=Integer.parseInt(weihaoT.getText());
                    String hubID=hubIDT.getText();
                    Location loc=new Location(hubID, quhao,paihao,jiahao,weihao);
                    DepotInReceiptVO vo=new DepotInReceiptVO(packID,time, desti, loc);
                    commodityBLService.submitIn(vo);
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
        } else if (e.getSource()==cancelbt){//取消按钮清空输入

            refresh();
        }
    }

}
