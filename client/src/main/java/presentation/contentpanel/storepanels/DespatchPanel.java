package presentation.contentpanel.storepanels;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.StaffBLService;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.transportblservice.DespatchBLService;
import constent.Constent;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
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
 * 新建派件单
 */
public class DespatchPanel extends JPanel implements ActionListener{
    MyLabel timeL;
    MyLabel numL;
    MyLabel courierL;

    MyTextField timeT;
    MyTextField numT;
    MyTextField courierT;

    MyButton submitBT;
    MyButton cancelBT;

    MainFrame parent;

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");//设置时间格式

    DespatchBLService despatchBLService;
    LogisticBLService logisticBLService;
    StaffBLService staffBLService;
    
    public DespatchPanel(MainFrame par){
        this.parent=par;

        initUI();

        setHotKey();

        submitBT.addActionListener(this);
        cancelBT.addActionListener(this);

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"派件单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));

        initBL();
        
    }

    private void initUI(){
        timeL=new MyLabel("到达日期");
        numL=new MyLabel("订单条形码号");
        courierL=new MyLabel("派送员");

        timeT=new MyTextField();
        numT=new MyTextField();
        courierT=new MyTextField();

        //设置时间框里自动生成系统时间
        timeT.setText(df.format(new Date()));

        submitBT=new MyButton("提交(S)");
        cancelBT=new MyButton("取消(R)");

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
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
    }

    private void setHotKey(){
        submitBT.setMnemonic('S');
        cancelBT.setMnemonic('R');
    }

    
    private void initBL() {
    	despatchBLService=BLFactory.getDespatchBLService();
        try {
            staffBLService=BLFactory.getStaffBLService();
            logisticBLService=BLFactory.getLogisticBLService();
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.GREEN);
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        }
	}

    private void refresh() {
    	timeT.setText(df.format(new Date()));
        numT.setText("");
        courierT.setText("");
    }

	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitBT){
        	if ((despatchBLService!=null)&&(logisticBLService!=null)){
                Date date = null;
                try {
                    date = df.parse(timeT.getText());
                } catch (ParseException e1) {
                    new TranslucentFrame(this, "到达日期必须为2015-01-01格式", Color.RED);
                }
                String num=numT.getText();
                boolean isTrue=checkOrderID(num);
                if (!isTrue){
                    new TranslucentFrame(this, "订单条形码号必须是"+Constent.ORDER_ID_LENGTH+"位数字", Color.RED);
                }
                String courier=courierT.getText()+"";
                DespatchReceiptVO vo=new DespatchReceiptVO(date,num,courier);
                if(date!=null&&isTrue){
                    try {
                        despatchBLService.submit(vo);
                        logisticBLService.update(parent.getUserIdentity().getId(), vo);
                        refresh();
                        new TranslucentFrame(this, MessageType.SUBMIT_SUCCESS, Color.GREEN);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (MalformedURLException e1) {
                        System.out.println(e1.getMessage());
                    } catch (NotBoundException e1) {
                        System.out.println(e1.getMessage());
                    } catch (SQLException e1) {
                        System.out.println("派件单sql："+e1.getMessage());
                    }
                }
            }
            else {
                initBL();
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
