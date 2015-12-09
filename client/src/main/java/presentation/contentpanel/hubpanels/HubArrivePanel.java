package presentation.contentpanel.hubpanels;

import javax.swing.*;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.transportblservice.ArriveHubBLService;
import constent.Constent;
import myexceptions.TransportBLException;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.PackArrivalState;
import vo.receiptvo.HubArrivalReceiptVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Harry on 2015/11/28.
 */
public class HubArrivePanel extends JPanel implements ActionListener{
    MainFrame parent;

    JLabel orderL=new JLabel("中转中心到达单编号");
    JLabel hubIDL=new JLabel("中转中心编号");
    JLabel timeL=new JLabel("到达日期");
    JLabel numL=new JLabel("中转/装车单编号");
    JLabel fromL=new JLabel("出发地");
    JLabel stateL=new JLabel("货物到达状态");

    JTextField orderT=new JTextField(25);
    JTextField hubIDT=new JTextField(25);
    JTextField timeT=new JTextField(25);
    JTextField numT=new JTextField(25);
    JTextField fromT=new JTextField(25);
    JComboBox<String> stateC;

    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");//设置时间格式
    
    ArriveHubBLService arriveHubBLService;
    
    public HubArrivePanel(MainFrame par){
        this.parent=par;
        String[] s={"完整", "损坏", "丢失"};
        stateC=new JComboBox(s);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        this.add(orderL,gbc);
        gbc.gridy=1;
        this.add(hubIDL,gbc);
        gbc.gridy=2;
        this.add(timeL,gbc);
        gbc.gridy=3;
        this.add(numL,gbc);
        gbc.gridy=4;
        this.add(fromL,gbc);
        gbc.gridy=5;
        this.add(stateL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(orderT,gbc);
        gbc.gridy=1;
        this.add(hubIDT,gbc);
        gbc.gridy=2;
        this.add(timeT, gbc);
        gbc.gridy=3;
        this.add(numT,gbc);
        gbc.gridy=4;
        this.add(fromT,gbc);
        gbc.gridy=5;
        this.add(stateC,gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.gridx=0;
        gbc.gridy=7;
        this.add(submitbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
        
        initBL();
    }

	private void initBL() {
		arriveHubBLService=BLFactory.getArriveHubBLService();		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submitbt){
			boolean isChecked=false;
			try {
				isChecked=checkAllFormat();
			} catch (TransportBLException e1) {
				new ErrorDialog(parent,e1.getMessage());
			}
			if(isChecked){
			String order=orderT.getText();
			String hub=hubIDT.getText();
		    String time=timeT.getText();
		    String num=numT.getText();
		    String from=fromT.getText();
		    String s=stateC.getSelectedItem().toString();
			PackArrivalState state;
			if(s.equals("完整"))
				state=PackArrivalState.GOOD;
			else if(s.equals("损坏"))
				state=PackArrivalState.DEMAGED;
			else
				state=PackArrivalState.MISSED;
			Date date=null;
			try {
				date = df.parse(time);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			HubArrivalReceiptVO vo=new HubArrivalReceiptVO(order,hub,date,num,from,state);
			try {
				arriveHubBLService.verify(vo);
				arriveHubBLService.submit(vo);
				refresh();
			} catch (TransportBLException e1) {
				new ErrorDialog(parent,e1.getMessage());
			} catch (RemoteException e1) {
				new ErrorDialog(parent, "服务器连接超时");
			} catch (MalformedURLException e1) {
				new ErrorDialog(parent, "MalformedURLException");
			} catch (NotBoundException e1) {
				new ErrorDialog(parent, "NotBoundException");
			} catch (SQLException e1) {
				new ErrorDialog(parent, "SQLException");
			}
			}
		}else{
			refresh();
		}
		
	}

	private void refresh() {
		orderT.setText("");
	    hubIDT.setText("");
	    timeT.setText("");
	    numT.setText("");
	    fromT.setText("");
	    stateC.setSelectedIndex(0);
	}

	private boolean checkAllFormat() throws TransportBLException {		
		if(!checkHubID(hubIDT.getText()))
			throw new TransportBLException("中转中心编号必须为4位数");
		if(!checkNumID(numT.getText()))
			throw new TransportBLException("中转单编号必须为19位数");
		if(!checkDate(timeT.getText()))
			throw new TransportBLException("到达日期必须为2015-01-01格式");
		return true;
	}

	private boolean checkHubID(String s) {
		if (s.length()!=Constent.HUB_ID_LENGTH)
	        return false;
	    for (int i=0;i<Constent.HUB_ID_LENGTH;i++){
	        if (s.charAt(i)<'0'||s.charAt(i)>'9')
	           return false;
	    }
	    return true;
	}

	private boolean checkNumID(String s) {
		if (s.length()!=Constent.Transfer_ID_LENGTH)
	        return false;
	    for (int i=0;i<Constent.Transfer_ID_LENGTH;i++){
	        if (s.charAt(i)<'0'||s.charAt(i)>'9')
	           return false;
	    }
	    return true;		
	}
	
	private boolean checkDate(String s){
		Date date=null;
		try {
			date = df.parse(s);
		} catch (ParseException e1) {
			return false;
		}
		return true;
	}
}
