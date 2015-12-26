package presentation.contentpanel;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.financeblservice.FinanceBLService;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.*;
import presentation.contentpanel.storepanels.BankInfoPanel;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.receiptvo.ReceiptVO;

import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Harry on 2015/12/4.
 */
public class BaobiaoPanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private JRadioButton chengben=new JRadioButton("成本收益表");
    private JRadioButton jingying=new JRadioButton("经营情况表");
    private JRadioButton zhangmu=new JRadioButton("账目查询");
    private MyLabel fromTimeL=new MyLabel("开始日期");
    private MyLabel toTimeL=new MyLabel("截至日期");
    private MyLabel yearL=new MyLabel("年份");
    private TimePanel fromTimeP=new TimePanel();
    private TimePanel toTimeP=new TimePanel();
    private MyTextField yearT=new MyTextField();
    private MyButton confirmbt=new MyButton("确认");

    private FinanceBLService financeBLService;

    public BaobiaoPanel(MainFrame par){
    	chengben.setFont(new Font("微软雅黑",Font.PLAIN,16));
    	jingying.setFont(new Font("微软雅黑",Font.PLAIN,16));
    	zhangmu.setFont(new Font("微软雅黑",Font.PLAIN,16));
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"报表查询",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        
        initUI();
        refresh();
        confirmbt.addActionListener(this);

        initBL();
    }

    private void initBL(){
        try {
            financeBLService= BLFactory.getFinanceBLService();
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

    private void initUI(){
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(chengben);
        btgroup.add(jingying);
        btgroup.add(zhangmu);
        chengben.setSelected(true);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        //gbc.weightx=1.0;
       // gbc.weighty=1.0;

        this.add(chengben,gbc);
        gbc.gridy=1;
        this.add(jingying,gbc);
        gbc.gridy++;
        gbc.gridx=1;
        this.add(fromTimeL,gbc);
        gbc.gridx++;
        this.add(fromTimeP,gbc);
        gbc.gridy++;
        gbc.gridx--;
        this.add(toTimeL,gbc);
        gbc.gridx++;
        this.add(toTimeP,gbc);
        gbc.gridy++;
        gbc.gridx=0;
        this.add(zhangmu,gbc);
        gbc.gridy++;
        gbc.gridx=1;
        this.add(yearL,gbc);
        gbc.gridx++;
        this.add(yearT,gbc);
        gbc.gridy++;
        this.add(confirmbt,gbc);
    }

    public void refresh(){
        fromTimeP.makeEmpty();
        toTimeP.makeEmpty();
        yearT.setText("");
    }

    private int getYear() throws NumberFormatException{
        String s=yearT.getText();
        int year=Integer.parseInt(s);
        return year;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            if (financeBLService!=null){
                if (chengben.isSelected()){
                    try {
                        ProfitVO vo=financeBLService.checkProfit();
                        new ContentDialog(parent, "成本收益表", new ChenBenPanel(vo));
                    } catch (RemoteException e1) {
                        new ErrorDialog(parent, "服务器连接超时");
                    }
                }
                else if (jingying.isSelected()){
                    try{
                        Date fromtime=fromTimeP.getDate();
                        Date totime=toTimeP.getDate();
                        ArrayList<ReceiptVO> list=financeBLService.seeRecord(fromtime, totime);
                        new ContentDialog(parent, "经营情况表", new JingYingPanel(list));
                    } catch (TimeFormatException e1) {
                        new ErrorDialog(parent, e1.getMessage());
                    } catch (MalformedURLException e1) {
                        new ErrorDialog(parent, "MalformedURLException");
                    } catch (RemoteException e1) {
                        new ErrorDialog(parent, "服务器连接超时");
                    } catch (SQLException e1) {
                        System.out.println("查询经营情况表sql："+e1.getMessage());
                        new ErrorDialog(parent, "SQLException");
                    } catch (NotBoundException e1) {
                        new ErrorDialog(parent, "NotBoundException");
                    }
                }
                else if (zhangmu.isSelected()){
                    //todo
                    try{
                        int year=getYear();
                        FinanceVO vo=financeBLService.getCredit(year);
                        new ContentDialog(parent, "账户信息", new BankInfoPanel(vo));
                    } catch (NumberFormatException e1){
                        new ErrorDialog(parent, "请输入正确的年份");
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (RemoteException e1){
                        new ErrorDialog(parent, "服务器连接超时");
                    } catch (FileNotFoundException e1){
                        new ErrorDialog(parent, "账目信息不存在！");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            else {
                initBL();
            }
        }
    }
}
