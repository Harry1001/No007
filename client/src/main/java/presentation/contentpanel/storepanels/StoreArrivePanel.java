package presentation.contentpanel.storepanels;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.transportblservice.ArriveStoreBLService;
import constent.Constent;
import myexceptions.TransportBLException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.ErrorDialog;
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

    MyLabel orderL=new MyLabel("订单编号");
    MyLabel timeL=new MyLabel("到达日期");
    MyLabel numL=new MyLabel("中转/装车单编号");
    MyLabel fromL=new MyLabel("出发地");
    MyLabel stateL=new MyLabel("货物到达状态");

    MyTextField orderT=new MyTextField();
    MyTextField timeT=new MyTextField();
    MyTextField numT=new MyTextField();
    MyTextField fromT=new MyTextField();

    JComboBox stateC;

    MyButton submitbt=new MyButton("提交");
    MyButton cancelbt=new MyButton("取消");

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");//设置时间格式
    
    ArriveStoreBLService arriveStore;
	LogisticBLService logisticBLService;
    
    public StoreArrivePanel(MainFrame par){
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"营业厅到达单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
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
		try {
			logisticBLService=BLFactory.getLogisticBLService();
		} catch (MalformedURLException e) {
			new ErrorDialog(parent, "MalformedURLException");
		} catch (RemoteException e) {
			new ErrorDialog(parent, "服务器连接超时");
		} catch (NotBoundException e) {
			new ErrorDialog(parent, "NotBoundException");
		}
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
			if ( (arriveStore!=null) && (logisticBLService!=null)){
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
				boolean isChecked=false;
				try {
					isChecked=checkAllFormat();
				} catch (TransportBLException e1) {
					new ErrorDialog(parent, e1.getMessage());
				}
				if(isChecked){
					try {
						arriveStore.verify(vo);
						arriveStore.submit(vo);
						logisticBLService.update(parent.getUserIdentity().getId(), vo);
						refresh();
					} catch (RemoteException e1) {
						new ErrorDialog(parent,"服务器连接超时");
					} catch (MalformedURLException e1) {
						new ErrorDialog(parent,"MalformedURLException");
					} catch (NotBoundException e1) {
						new ErrorDialog(parent,"NotBoundException");
					} catch (SQLException e1) {
						new ErrorDialog(parent,"数据库异常");
					} catch (TransportBLException e1) {
						new ErrorDialog(parent, e1.getMessage());
					}
				}
			}
			else {
				initBL();
			}
		}
		else{
			refresh();
		}		
	}
	
	private boolean checkAllFormat() throws TransportBLException {		
		if(!checkOrderID())
			throw new TransportBLException("订单编号必须为"+Constent.ORDER_ID_LENGTH+"位数");
		if(!checkNumID())
			throw new TransportBLException("中转单编号必须为"+Constent.Transfer_ID_LENGTH+"位数");
		if(!checkDate())
			throw new TransportBLException("到达日期必须为2015-01-01格式");
		if (!checkFrom())
			throw new TransportBLException("出发地不可为空");

		return true;
	}

	private boolean checkFrom(){
		String s=fromT.getText();
		return !s.isEmpty();
	}

	private boolean checkOrderID() {
		String s=orderT.getText();
		if (s.length()!=Constent.ORDER_ID_LENGTH)
	        return false;
	    for (int i=0;i<Constent.ORDER_ID_LENGTH;i++){
	        if (s.charAt(i)<'0'||s.charAt(i)>'9')
	           return false;
	    }
	    return true;
	}

	private boolean checkNumID() {
		String s=numT.getText();
		if (s.length()!=Constent.Transfer_ID_LENGTH)
	        return false;
	    for (int i=0;i<Constent.Transfer_ID_LENGTH;i++){
	        if (s.charAt(i)<'0'||s.charAt(i)>'9')
	           return false;
	    }
	    return true;		
	}
	
	private boolean checkDate(){
		String s=timeT.getText();
		try {
			df.parse(s);
		} catch (ParseException e1) {
			return false;
		}
		return true;
	}
}
