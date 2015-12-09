package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.financeblservice.FinanceBLService;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.TimePanel;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.FeeType;
import vo.receiptvo.PayReceiptVO;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Harry on 2015/12/4.
 */
public class OutcomePanel extends JPanel implements ActionListener{

    FinanceBLService financeBLService;

    MainFrame parent;
    MyLabel timeL=new MyLabel("付款日期");
    MyLabel feeL=new MyLabel("付款金额");
    MyLabel personL=new MyLabel("付款人");
    MyLabel accountL=new MyLabel("付款帐号");
    MyLabel itemL=new MyLabel("付款条目");
    MyLabel additionL=new MyLabel("备注");
    TimePanel timeP=new TimePanel();
    MyTextField feeT=new MyTextField();
    MyTextField personT=new MyTextField();
    MyTextField accountT=new MyTextField();
    MyTextField additionT=new MyTextField(25);
    MyButton submitbt=new MyButton("提交");

    JRadioButton salary = new JRadioButton("工资");
    JRadioButton rent = new JRadioButton("租金");
    JRadioButton carriage = new JRadioButton("运费");
    JRadioButton bonus = new JRadioButton("奖金");

    public OutcomePanel(MainFrame par){
        this.parent=par;
        initUI();
        initBL();
    }

    private void initUI(){
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(salary);
        btgroup.add(rent);
        btgroup.add(carriage);
        btgroup.add(bonus);
        salary.setSelected(true);

        JPanel btpanel=new JPanel();
        btpanel.setLayout(new BoxLayout(btpanel,BoxLayout.X_AXIS));
        btpanel.add(salary);
        btpanel.add(rent);
        btpanel.add(carriage);
        btpanel.add(bonus);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;
       // gbc.weighty=1.0;

        gbc.gridx=gbc.gridy=0;
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
        this.add(additionL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
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
        this.add(additionT,gbc);

        gbc.anchor=GridBagConstraints.EAST;
        gbc.gridy++;
        this.add(submitbt,gbc);

        submitbt.addActionListener(this);
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
        feeT.setText("");
        personT.setText("");
        accountT.setText("");
        additionT.setText("");
        timeP.setPresentTime();
        salary.setSelected(true);
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

    private boolean checkAll(){
        //todo
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                try {
                    Date time=timeP.getDate();
                    double fee=Double.parseDouble(feeT.getText());
                    String man=personT.getText();
                    String acc=accountT.getText();
                    FeeType feeType=getFeeType();

                    PayReceiptVO vo = new PayReceiptVO(time, fee, man, acc, feeType);
                    financeBLService.submitOut(vo);
                    refresh();
                } catch (TimeFormatException e1) {
                    new ErrorDialog(parent, e1.getMessage());
                } catch (NumberFormatException e1){
                    new ErrorDialog(parent, "金额必须为正数");
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (MalformedURLException e1) {
                    new ErrorDialog(parent, "MalformedURLException");
                } catch (NotBoundException e1) {
                    new ErrorDialog(parent, "NotBoundException");
                }
            }
        }
    }
}
