package presentation.contentpanel.storepanels;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.transportblservice.DespatchBLService;
import constent.Constent;
import myexceptions.TransportBLException;
import presentation.commonpanel.ErrorDialog;
import vo.receiptvo.DespatchReceiptVO;

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
public class DespatchPanel extends JPanel implements ActionListener{
    JLabel timeL;
    JLabel numL;
    JLabel courierL;

    JTextField timeT;
    JTextField numT;
    JTextField courierT;

    JButton submitBT;
    JButton cancelBT;

    MainFrame parent;

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");//设置时间格式

    DespatchBLService despatchBLService;
    
    public DespatchPanel(MainFrame par){
        this.parent=par;

        timeL=new JLabel("到达日期");
        numL=new JLabel("订单条形码号");
        courierL=new JLabel("派送员");

        timeT=new JTextField(25);
        numT=new JTextField(25);
        courierT=new JTextField(25);

        //设置时间框里自动生成系统时间
        timeT.setText(df.format(new Date()));

        submitBT=new JButton("提交");
        cancelBT=new JButton("清空输入");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        this.add(timeL,gbc);
        gbc.gridy=1;
        this.add(numL,gbc);
        gbc.gridy=2;
        this.add(courierL,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        this.add(timeT,gbc);
        gbc.gridy=1;
        this.add(numT,gbc);
        gbc.gridy=2;
        this.add(courierT,gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.gridx=0;
        gbc.gridy=4;
        this.add(submitBT,gbc);
        gbc.gridx=1;
        this.add(cancelBT,gbc);

        submitBT.addActionListener(this);
        cancelBT.addActionListener(this);

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"派件单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));
        
        initBL();
    }

    
    private void initBL() {
    	despatchBLService=BLFactory.getDespatchBLService();		
	}

    private void refresh() {
    	timeT.setText(df.format(new Date()));
        numT.setText("");
        courierT.setText("");
    }

	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitBT){
        	Date date = null;
			try {
				date = df.parse(timeT.getText());
			} catch (ParseException e1) {
				new ErrorDialog(parent, "到达日期必须为2015-01-01格式");
			}
        	String num=numT.getText();
        	boolean isTrue=checkOrderID(num);
        	String courier=courierT.getText();
        	DespatchReceiptVO vo=new DespatchReceiptVO(date,num,courier);
        	if(date!=null&&isTrue){
        		try {
					despatchBLService.submit(vo);
					refresh();
				} catch (RemoteException e1) {
					new ErrorDialog(parent,"服务器连接超时");
				} catch (MalformedURLException e1) {
					new ErrorDialog(parent,"MalformedURLException");
				} catch (NotBoundException e1) {
					new ErrorDialog(parent,"NotBoundException");
				} catch (SQLException e1) {
					new ErrorDialog(parent,"数据库异常");
				}
        	}
        }
        else {//取消按钮
        	refresh();
        }
    }

	private boolean checkOrderID(String s) {
		if (s.length()!=Constent.ORDER_ID_LENGTH)
	        return false;
	    for (int i=0;i<Constent.ORDER_ID_LENGTH;i++){
	        if (s.charAt(i)<'0'||s.charAt(i)>'9')
	           return false;
	    }
	    return true;
	}
	
}
