package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.BankAccountBLService;
import myexceptions.InfoBLException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commonpanel.ErrorDialog;
import vo.infovo.BankAccountVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/12/19.
 */
public class BankAccountAddPanel extends JPanel implements ActionListener {
    private MyLabel accountL;
    private MyLabel balanceL;
    private MyTextField accountT;
    private MyTextField balanceT;
    private MyButton confirmbt;
    private MyButton cancelbt;

    private  MainFrame parent;
    private JDialog dialog;
    private BankAccountPanel bankAccountPanel;
    private BankAccountBLService bankAccountBLService;

    public BankAccountAddPanel(MainFrame par, JDialog dialog, BankAccountPanel panel, BankAccountBLService service){
        this.parent=par;
        this.dialog=dialog;
        this.bankAccountPanel=panel;
        this.bankAccountBLService=service;

        initUI();
        confirmbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    private void initUI(){
        accountL=new MyLabel("账户名");
        accountT=new MyTextField(25);
        balanceL=new MyLabel("当前余额");
        balanceT=new MyTextField(25);
        confirmbt=new MyButton("确认");
        cancelbt=new MyButton("取消");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=gbc.gridy=0;
        this.add(accountL,gbc);
        gbc.gridy++;
        this.add(balanceL,gbc);
        gbc.gridy++;
        this.add(confirmbt,gbc);
        gbc.gridx++;
        this.add(cancelbt,gbc);
        gbc.gridy--;
        this.add(balanceT,gbc);
        gbc.gridy--;
        this.add(accountT,gbc);
    }

    private boolean checkAccount(){
        String s=accountT.getText();
        if (s.isEmpty())
            return false;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    private boolean checkBalance(){
        String s=balanceT.getText();
        try{
            BigDecimal bd=new BigDecimal(s);
            if (bd.compareTo(new BigDecimal(0))<0){
                return false;
            }
            else return true;
        }catch (NumberFormatException e){
            return  false;
        }
    }

    private boolean checkAll(){
        if (!checkAccount()){
            new ErrorDialog(parent, "请输入正确的银行帐号");
            return false;
        }

        if (!checkBalance()){
            new ErrorDialog(parent, "余额必须为非负数");
            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            if (checkAll()){
                String acc=accountT.getText();
                BigDecimal bd=new BigDecimal(balanceT.getText());
                BankAccountVO vo=new BankAccountVO(acc, bd);
                try {
                    bankAccountBLService.addBankAccount(vo);
                    bankAccountPanel.refresh();
                    dialog.dispose();
                } catch (InfoBLException e1) {
                    new ErrorDialog(parent, e1.getMessage());
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                }

            }

        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
