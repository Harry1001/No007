package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.financeblservice.FinanceBLService;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.ErrorDialog;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/12/4.
 */
public class MakeBillPanel extends JPanel implements ActionListener {

    private MainFrame parent;
    private MyLabel yearL = new MyLabel("保存年份");
    private MyTextField yearT = new MyTextField();
    private MyButton submitbt=new MyButton("提交");

    private FinanceBLService financeBLService;

    public MakeBillPanel(MainFrame par){
        this.parent=par;
        initUI();

        initBL();
    }

    private void initUI(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
       // gbc.weightx=1.0;
        //gbc.weighty=1.0;

        gbc.gridx=gbc.gridy=0;
        this.add(yearL,gbc);
        gbc.gridx++;
        this.add(yearT,gbc);
        gbc.gridy++;
        this.add(submitbt,gbc);
        refresh();
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


    public void refresh(){
        yearT.setText("");
    }

    private boolean checkYear(){
        String s=yearT.getText();
        try{
            int year=Integer.parseInt(s);
            return year>0;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (financeBLService!=null){
                if (checkYear()){
                    int year=Integer.parseInt(yearT.getText());
                    try {
                        financeBLService.makeCredit(year);
                        refresh();
                    } catch (RemoteException e1) {
                        new ErrorDialog(parent, "服务器连接超时");
                    } catch (NamingException e1) {
                        new ErrorDialog(parent, "NamingException");
                    } catch (SQLException e1) {
                        new ErrorDialog(parent, "SQLException");
                    } catch (MalformedURLException e1) {
                        new ErrorDialog(parent, "MalformedURLException");
                    } catch (NotBoundException e1) {
                        new ErrorDialog(parent, "NotBoundException");
                    }
                }
            }
            else{
                initBL();
            }
        }
    }
}
