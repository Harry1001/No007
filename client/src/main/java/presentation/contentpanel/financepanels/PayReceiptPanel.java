package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.financeblservice.FinanceBLService;
import businessLogicService.infoblservice.BankAccountBLService;
import constent.Constent;
import javafx.scene.layout.BorderRepeat;
import myexceptions.TimeFormatException;
import presentation.Images.Images;
import presentation.commoncontainer.*;
import typeDefinition.FeeType;
import typeDefinition.MessageType;
import typeDefinition.ReceiptState;
import vo.infovo.BankAccountVO;
import vo.receiptvo.PayReceiptVO;
import vo.salaryfeevo.SalaryFeeVO;

import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.border.Border;
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
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Harry on 2015/12/4.
 */
public class PayReceiptPanel extends JPanel implements ActionListener, FocusListener {

    FinanceBLService financeBLService;
    BankAccountBLService bankAccountBLService;

    MainFrame parent;
    OutcomePanel outcomePanel;

    MyLabel timeL=new MyLabel("付款日期");
    MyLabel feeL=new MyLabel("付款金额");
    MyLabel personL=new MyLabel("付款人");
    MyLabel accountL=new MyLabel("付款帐号");
    MyLabel itemL=new MyLabel("付款条目");
    MyLabel receiptIDL =new MyLabel("付款单编号");
    TimePanel timeP=new TimePanel();
    MyTextField feeT=new MyTextField();
    MyTextField personT=new MyTextField();
    JComboBox<String> accountT=new JComboBox<String>();
    MyTextField receiptIDT =new MyTextField();
    MyButton submitbt=new MyButton("提交(S)");
    MyButton cancelbt=new MyButton("取消(C)");
    MyButton backbt=new MyButton("返回(B)", Images.BACK_IMAGE);
    MyButton salarybt=new MyButton("生成工资(G)");

    JRadioButton salary = new JRadioButton("工资 ");
    JRadioButton rent = new JRadioButton("租金 ");
    JRadioButton carriage = new JRadioButton("运费 ");
    JRadioButton bonus = new JRadioButton("奖金 ");

    public PayReceiptPanel(MainFrame par, OutcomePanel outcomePanel){
        this.parent=par;
        this.outcomePanel=outcomePanel;
        initBL();

        initUI();
        sethotKey();
        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
        backbt.addActionListener(this);
        salarybt.addActionListener(this);

        receiptIDT.addFocusListener(this);
        feeT.addFocusListener(this);
        personT.addFocusListener(this);

        refresh();
    }

    private void sethotKey(){
        backbt.setMnemonic('B');
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('C');
        salarybt.setMnemonic('G');
    }

    private void initUI(){
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(salary);
        btgroup.add(rent);
        btgroup.add(carriage);
        btgroup.add(bonus);
        salary.setSelected(true);

        JPanel btpanel=new JPanel();
        btpanel.setOpaque(false);
        btpanel.setLayout(new BoxLayout(btpanel,BoxLayout.X_AXIS));
        btpanel.add(salary);
        btpanel.add(rent);
        btpanel.add(carriage);
        btpanel.add(bonus);

        try {
            ArrayList<BankAccountVO> bankAccountVOs=bankAccountBLService.getBankAccountList();
            for (BankAccountVO vo:bankAccountVOs){
                accountT.addItem(vo.getAccountUser());
            }
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.RED);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(0,10,10,10);
        gbc.weightx=0.5;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.CENTER;

        gbc.gridx=gbc.gridy=0;
        this.add(backbt, gbc);
        gbc.gridy++;
        this.add(timeL,gbc);
        gbc.gridy++;
        this.add(feeL,gbc);
        gbc.gridy++;
        this.add(personL,gbc);
        gbc.gridy++;
        this.add(accountL,gbc);
        gbc.gridy++;
        this.add(itemL,gbc);
        gbc.gridy++;
        this.add(receiptIDL,gbc);

        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=2;
        this.add(timeP,gbc);
        gbc.gridy++;
        this.add(feeT,gbc);
        gbc.gridy++;
        this.add(personT,gbc);
        gbc.gridy++;
        this.add(accountT,gbc);
        gbc.gridy++;
        this.add(btpanel,gbc);
        gbc.gridy++;
        this.add(receiptIDT,gbc);

        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridy++;
        this.add(submitbt,gbc);
        gbc.gridx=0;
        this.add(salarybt,gbc);
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridx=2;
        this.add(cancelbt,gbc);

        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"付款单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
    }

    private void initBL(){
        try {
            bankAccountBLService=BLFactory.getBankAccountBLService();
            financeBLService= BLFactory.getFinanceBLService();
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void refresh(){
        feeT.setText("");
        personT.setText(parent.getUserIdentity().getId());

        receiptIDT.setText(Constent.RECIEPT_NUM_FORMAT.format(new Date())+"+3位编号待输入");
        timeP.setPresentTime();
        salary.setSelected(true);

        feeT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        personT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        receiptIDT.setBorder(BorderFactory.createLoweredSoftBevelBorder());
    }



    private FeeType getFeeType(){
        if (salary.isSelected()){
            return FeeType.SALARY;
        } else if (rent.isSelected()){
            return FeeType.RENT;
        } else if (carriage.isSelected()){
            return FeeType.FREIGHT;
        } else {
            return FeeType.BONUS;
        }
    }

    private boolean checkMoney(){
        String s=feeT.getText();
        try{
            double fee=Double.parseDouble(s);
            return (fee>0);
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean checkMan(){
        String s=personT.getText();
        if (s.length()!= Constent.USER_ID_LENGTH) return false;
        return isDigit(s);
    }

    private boolean checkReceiptID(){
        String s=receiptIDT.getText();
        if (s.length()!=Constent.PAY_RECEIPT_LENGTH){
            return false;
        }
        else {
            return isDigit(s);
        }

    }


    private boolean isDigit(String s){
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }


    private boolean checkAll(){
        if (!checkMoney()){
            new TranslucentFrame(this, "付款金额必须为正数", Color.RED);
            return false;
        }

        if (!checkMan()){
            new TranslucentFrame(this, "请输入正确的付款人工号", Color.RED);
            return false;
        }

        if (!checkReceiptID()){
            new TranslucentFrame(this, "付款单编号为"+Constent.PAY_RECEIPT_LENGTH+"位数字", Color.RED);
        }

        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                if (financeBLService!=null){
                    try {
                        Date time=timeP.getDate();
                        double fee=Double.parseDouble(feeT.getText());
                        String man=personT.getText();
                        String acc=accountT.getSelectedItem().toString();
                        FeeType feeType=getFeeType();
                        String receiptID=receiptIDT.getText();

                        PayReceiptVO vo = new PayReceiptVO(time, fee, man, acc, feeType, ReceiptState.SUBMITTED,receiptID);
                        financeBLService.submitOut(vo);
                        refresh();
                        new TranslucentFrame(this, MessageType.SUBMIT_SUCCESS, Color.GREEN);
                    } catch (TimeFormatException e1) {
                        new TranslucentFrame(this, e1.getMessage(), Color.RED);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.RED);
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                        if (e1.getMessage().substring(0,9).equals("Duplicate")){
                            new TranslucentFrame(this, "该单号已存在", Color.RED);
                        }

                    } catch (MalformedURLException e1) {
                        System.out.println(e1.getMessage());
                    } catch (NotBoundException e1) {
                        System.out.println(e1.getMessage());
                    }
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==cancelbt){
            refresh();
        }
        else if (e.getSource()==backbt){
            outcomePanel.showList();
        }
        else if (e.getSource()==salarybt){
            if (financeBLService!=null){
                double totalFee=0.0;
                try {
                    ArrayList<SalaryFeeVO> salaryFeeVOs=financeBLService.calSalary();
                    for (SalaryFeeVO vo:salaryFeeVOs){
                        totalFee+=vo.getSalary();
                    }
                    feeT.setText(totalFee+"");
                } catch (RemoteException e1) {
                    new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (NotBoundException e1) {
                    e1.printStackTrace();
                }
            }
            else {
                initBL();
            }
        }
    }

    public void focusGained(FocusEvent e) {
        if (e.getSource()==receiptIDT){
            String s=receiptIDT.getText();
            if (s.equals(Constent.RECIEPT_NUM_FORMAT.format(new Date())+"+3位编号待输入")){
                receiptIDT.setText(Constent.RECIEPT_NUM_FORMAT.format(new Date()));
            }
        }
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource()==feeT){
            if (checkMoney()){
                feeT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else {
                feeT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }

        else if (e.getSource()==personT){
            if (checkMan()){
                personT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else {
                personT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }

        else if (e.getSource()==receiptIDT){
            if (checkReceiptID()){
                receiptIDT.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            else {
                receiptIDT.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
    }
}
