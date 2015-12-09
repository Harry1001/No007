package presentation.contentpanel.storepanels;

import javax.swing.*;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.transportblservice.ArriveStoreBLService;
import myexceptions.TransportBLException;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.PackArrivalState;
import vo.receiptvo.StoreArrivalReceiptVO;

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
 * Created by Harry on 2015/11/27.
 */
public class StoreArrivePanel extends JPanel implements ActionListener{

    MainFrame parent;

    JLabel orderL=new JLabel("营业厅到达单编号");
    JLabel timeL=new JLabel("到达日期");
    JLabel numL=new JLabel("中转/装车单编号");
    JLabel fromL=new JLabel("出发地");
    JLabel stateL=new JLabel("货物到达状态");

    JTextField orderT=new JTextField(25);
    JTextField timeT=new JTextField(25);
    JTextField numT=new JTextField(25);
    JTextField fromT=new JTextField(25);

    JComboBox stateC;

    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");//设置时间格式
    
    ArriveStoreBLService arriveStore;
    
    public StoreArrivePanel(MainFrame par){
        this.parent=par;
        String[] s={"完整", "损坏", "丢失"};
        stateC=new JComboBox(s);

      //设置时间框里自动生成系统时间
        timeT.setText(df.format(new Date()));
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        this.add(orderL,gbc);
        gbc.gridy=1;
        this.add(timeL,gbc);
        gbc.gridy=2;
        this.add(numL,gbc);
        gbc.gridy=3;
        this.add(fromL,gbc);
        gbc.gridy=4;
        this.add(stateL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(orderT,gbc);
        gbc.gridy=1;
        this.add(timeT, gbc);
        gbc.gridy=2;
        this.add(numT,gbc);
        gbc.gridy=3;
        this.add(fromT,gbc);
        gbc.gridy=4;
        this.add(stateC,gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.gridx=0;
        gbc.gridy=6;
        this.add(submitbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);
        
        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
        
        initBL();
    }

	private void initBL() {
		arriveStore=BLFactory.getArriveStoreBLService();		
	}

	private void refresh() {
		orderT.setText("");
		timeT.setText(df.format(new Date()));
        numT.setText("");
        fromT.setText("");
        stateC.setSelectedIndex(0);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submitbt){
			Date date = null;
			try {
				date = df.parse(timeT.getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String order=orderT.getText();
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
			StoreArrivalReceiptVO vo=new StoreArrivalReceiptVO(order,date,num,from,state);
			boolean isTrue=false;
			try {
				isTrue=arriveStore.verify(vo);
			} catch (TransportBLException e1) {
				new ErrorDialog(parent, e1.getMessage());
			}
			if(isTrue){
				try {
					arriveStore.submit(vo);
					refresh();
				} catch (RemoteException e1) {
					new ErrorDialog(parent,"服务器连接超时");
				} catch (MalformedURLException e1) {
					new ErrorDialog(parent,"URL格式错误");
				} catch (NotBoundException e1) {
					new ErrorDialog(parent,"服务器端没有此内容");
				} catch (SQLException e1) {
					new ErrorDialog(parent,"数据库异常");
				}
			}
		}
		else{
			refresh();
		}		
	}
}
