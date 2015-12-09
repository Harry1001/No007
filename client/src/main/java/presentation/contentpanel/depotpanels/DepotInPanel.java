package presentation.contentpanel.depotpanels;

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

/**
 * Created by Harry on 2015/11/28.
 */
public class DepotInPanel extends JPanel implements ActionListener{
    MainFrame parent;
    JLabel packIDL=new JLabel("快递编号");
    JLabel destiL=new JLabel("目的地");
    JLabel timeL=new JLabel("入库日期");
    JLabel quhaoL=new JLabel("区号");
    JLabel paihaoL=new JLabel("排号");
    JLabel jiahaoL=new JLabel("架号");
    JLabel weihaoL=new JLabel("位号");
    JLabel hubIDL=new JLabel("中转中心编号");

    JTextField packIDT=new JTextField(25);
    JTextField destiT=new JTextField(25);
    JTextField timeT=new JTextField(25);
    JTextField hubIDT=new JTextField(25);
    JTextField quhaoT=new JTextField(5);
    JTextField paihaoT=new JTextField(5);
    JTextField jiahaoT=new JTextField(5);
    JTextField weihaoT=new JTextField(5);

    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");//设置时间格式

    CommodityBLService commodityBLService;
    
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submitbt) {
			try {
				checkAllFormat();
			} catch (TransportBLException e2) {
				new ErrorDialog(parent, e2.getMessage());
			}
			int regionID=Integer.parseInt(quhaoT.getText());
			int rowID=Integer.parseInt(paihaoT.getText());
			int shelfID=Integer.parseInt(jiahaoT.getText());
			int postID=Integer.parseInt(weihaoT.getText());
			Location loc=new Location(packIDT.getText(), regionID, rowID, shelfID, postID);
			Date date=null;
			try {
				date = df.parse(timeT.getText());
				DepotInReceiptVO vo=new DepotInReceiptVO(packIDT.getText(),date,destiT.getText(),loc);
				commodityBLService.submitIn(vo);
				refresh();
			} catch (ParseException e1) {
				new ErrorDialog(parent, "到达日期必须为2015-01-01格式");
			} catch (RemoteException e1) {
                new ErrorDialog(parent, "服务器连接超时");
			} catch (MalformedURLException e1) {
                new ErrorDialog(parent, "MalformedURLException");
			} catch (NamingException e1) {
				new ErrorDialog(parent, "NamingException");
			} catch (SQLException e1) {
				new ErrorDialog(parent, "SQLException");
			} catch (NotBoundException e1) {
				new ErrorDialog(parent, "NotBoundException");
			}			
		}else{
			refresh();
		}	
	}

	private void refresh() {
		packIDT.setText("");
	    destiT.setText("");
	    timeT.setText("");
	    hubIDT.setText("");
	    quhaoT.setText("");
	    paihaoT.setText("");
	    jiahaoT.setText("");
	    weihaoT.setText("");
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
	
}
