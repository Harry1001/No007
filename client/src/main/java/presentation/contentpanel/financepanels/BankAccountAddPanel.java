package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.BankAccountBLService;
import myexceptions.InfoBLException;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
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
        setHotKey();
        confirmbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    private void setHotKey(){
        confirmbt.setMnemonic('O');
        cancelbt.setMnemonic('C');
        dialog.getRootPane().setDefaultButton(confirmbt);
    }

    private void initUI(){
        accountL=new MyLabel("账户名");
        accountT=new MyTextField(25);
        balanceL=new MyLabel("当前余额");
        balanceT=new MyTextField(25);
        confirmbt=new MyButton("OK");
        cancelbt=new MyButton("Cancel");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridx=gbc.gridy=0;
        this.add(accountL,gbc);
        gbc.gridy++;
        this.add(balanceL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        this.add(accountT,gbc);
        gbc.gridy++;
        this.add(balanceT,gbc);
        gbc.gridwidth=1;
        gbc.gridy++;
        gbc.gridx=2;
        this.add(cancelbt,gbc);
        gbc.gridx--;
        this.add(confirmbt,gbc);

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
            new TranslucentFrame(bankAccountPanel, "请输入正确的银行帐号", Color.RED);
            return false;
        }

        if (!checkBalance()){
            new TranslucentFrame(bankAccountPanel, "余额必须为非负数", Color.RED);
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
                    new TranslucentFrame(bankAccountPanel, MessageType.ADD_SUCCESS, Color.GREEN);
                    dialog.dispose();
                } catch (InfoBLException e1) {
                    new TranslucentFrame(bankAccountPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(bankAccountPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }

            }

        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
