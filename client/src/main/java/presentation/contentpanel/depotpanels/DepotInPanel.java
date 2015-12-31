package presentation.contentpanel.depotpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.commodityblservice.CommodityBLService;
import constent.Constent;
import presentation.commoncontainer.*;
import typeDefinition.Location;
import typeDefinition.MessageType;
import vo.receiptvo.DepotInReceiptVO;

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
import java.util.Date;
import java.text.ParseException;

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

    MyButton submitbt=new MyButton("提交(S)");
    MyButton cancelbt=new MyButton("取消(C)");

    public DepotInPanel(MainFrame par){
        this.parent=par;
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"库存入库",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
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

        setHotKey();
        
        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);

        initFocus();

        setPresentTime();
        initBL();
        refresh();
    }

    private void setHotKey(){
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('C');
    }

	private void initBL() {
		try {
			commodityBLService=BLFactory.getCommodityBLService();
		} catch (MalformedURLException e) {
			new ErrorDialog(parent, "MalformedURLException");
		} catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
		} catch (NamingException e) {
			new ErrorDialog(parent, "NamingException");
		} catch (NotBoundException e) {
			new ErrorDialog(parent, "NotBoundException");
		}
	}




	private boolean checkAllFormat() {
		if(!checkPackID(packIDT.getText())) {
            new TranslucentFrame(this, "快递编号必须为"+Constent.ORDER_ID_LENGTH+"位数字", Color.RED);
            return false;
        }

        if (!checkLoc(destiT.getText())){
            new TranslucentFrame(this, "目的地前两位必须为城市名", Color.RED);
            return false;
        }

        if (!checkTime(timeT.getText())){
            new TranslucentFrame(this, "时间格式为：yyyy-MM-dd HH:mm:ss", Color.RED);
            return false;
        }

		if(!checkNumber(quhaoT.getText())) {
            new TranslucentFrame(this, "区号必须为正整数", Color.RED);
            return false;
        }

        if(!checkNumber(paihaoT.getText())) {
            new TranslucentFrame(this, "排号必须为正整数", Color.RED);
            return false;
        }

        if(!checkNumber(jiahaoT.getText())) {
            new TranslucentFrame(this, "架号必须为正整数", Color.RED);
            return false;
        }

        if(!checkNumber(weihaoT.getText())) {
            new TranslucentFrame(this, "位号必须为正整数", Color.RED);
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

        packIDT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        destiT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        timeT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        quhaoT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        paihaoT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        jiahaoT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        weihaoT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
    }

    public void initFocus(){
        packIDT.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                if (checkPackID(packIDT.getText())){
                    packIDT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                else {
                    packIDT.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });

        destiT.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                if (checkLoc(destiT.getText())){
                    destiT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                else {
                    destiT.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });

        timeT.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                if (checkTime(timeT.getText())){
                    timeT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                else {
                    timeT.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });

        quhaoT.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                if (checkNumber(quhaoT.getText())){
                    quhaoT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                else {
                    quhaoT.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });

        paihaoT.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                if (checkNumber(paihaoT.getText())){
                    paihaoT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                else {
                    paihaoT.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });

        jiahaoT.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                if (checkNumber(jiahaoT.getText())){
                    jiahaoT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                else {
                    jiahaoT.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });

        weihaoT.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                if (checkNumber(weihaoT.getText())){
                    weihaoT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                else {
                    weihaoT.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });
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
                        new TranslucentFrame(this, MessageType.SUBMIT_SUCCESS, Color.GREEN);
                    } catch (ParseException e1) {
                        new TranslucentFrame(this, "时间格式为：yyyy-MM-dd HH:mm:ss", Color.RED);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (SQLException e1) {
                        System.out.println("库存入库："+e1.getMessage());
                        String s=e1.getMessage();
                        if ((s.length()>=15)&&(s.substring(0,15).equals("Duplicate entry"))){
                            new TranslucentFrame(this, "该入库单已存在", Color.RED);
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
