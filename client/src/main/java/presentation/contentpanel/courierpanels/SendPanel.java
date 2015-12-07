package presentation.contentpanel.courierpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.strategyblservice.CalExpressfeeService;
import businessLogicService.transportblservice.SendBLService;
import constent.Constent;
import myexceptions.TransportBLException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commonpanel.ErrorDialog;
import vo.receiptvo.SendReceiptVO;

import javax.swing.*;
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

/**
 * Created by Harry on 2015/11/25.
 */
public class SendPanel extends JPanel implements ActionListener, FocusListener{

    private SendBLService sendBLService;
    private CalExpressfeeService calExpressfeeService;
    MainFrame parent;
    MyLabel[] labels=new MyLabel[16];
    MyTextField[] texts=new MyTextField[14];
    JComboBox comboBox1;
    JComboBox comboBox2;
    MyButton calFeebt;
    MyButton submitbt;

    public SendPanel(MainFrame par){

        this.parent=par;
        labels[0]=new MyLabel("姓名");
        labels[1]=new MyLabel("住址");
        labels[2]=new MyLabel("单位");
        labels[3]=new MyLabel("手机");
        labels[4]=new MyLabel("姓名");
        labels[5]=new MyLabel("住址");
        labels[6]=new MyLabel("单位");
        labels[7]=new MyLabel("手机");
        labels[8]=new MyLabel("原件数");
        labels[9]=new MyLabel("实际重量");
        labels[10]=new MyLabel("体积");
        labels[11]=new MyLabel("内件品名");
        labels[12]=new MyLabel("快递类型");
        labels[13]=new MyLabel("包装类型");
        labels[14]=new MyLabel("订单条形码");
        labels[15]=new MyLabel("报价");

        for(int i=0;i<14;i++){
            texts[i]=new MyTextField(15);
        }

        String[] s1={"经济快递","标准快递","特快快递"};
        String[] s2={"快递袋","纸箱","木箱"};
        comboBox1=new JComboBox(s1);
        comboBox2=new JComboBox(s2);

        JPanel panel1=new JPanel(new GridBagLayout());
        panel1.setBorder(BorderFactory.createTitledBorder("寄件人信息"));
        JPanel panel2=new JPanel(new GridBagLayout());
        panel2.setBorder(BorderFactory.createTitledBorder("收件人信息"));
        JPanel panel3=new JPanel(new GridBagLayout());
        panel3.setBorder(BorderFactory.createTitledBorder("托运货物信息"));

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=0;

        for(int i=0;i<4;i++){
            gbc.gridy=i;
            panel1.add(labels[i],gbc);
            panel2.add(labels[i+4],gbc);
            panel3.add(labels[i+8],gbc);
        }

        gbc.gridx=1;
        for(int i=0;i<4;i++){
            gbc.gridy=i;
            panel1.add(texts[i],gbc);
            panel2.add(texts[i+4],gbc);
            panel3.add(texts[i+8],gbc);
        }

        gbc.gridx=2;
        for(int i=0;i<4;i++){
            gbc.gridy=i;
            panel3.add(labels[i+12],gbc);
        }
        gbc.gridx=3;
        gbc.gridy=0;
        panel3.add(comboBox1,gbc);
        gbc.gridy++;
        panel3.add(comboBox2,gbc);
        gbc.gridy++;
        panel3.add(texts[12],gbc);
        gbc.gridy++;
        panel3.add(texts[13],gbc);

        this.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        this.add(panel1,gbc);
        gbc.gridx=1;
        //gbc.insets=new Insets(10,20,10,10);
        this.add(panel2,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;

        this.add(panel3,gbc);

        calFeebt=new MyButton("计算运费");
        submitbt=new MyButton(" 提交");
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.gridwidth=1;
        this.add(calFeebt,gbc);
        gbc.gridx=1;
        this.add(submitbt,gbc);

        calFeebt.addActionListener(this);
        submitbt.addActionListener(this);
        texts[1].addFocusListener(this);
        texts[5].addFocusListener(this);

        initBL();
    }

    private void initBL(){
        sendBLService= BLFactory.getSendBLService();
        try {
            calExpressfeeService=BLFactory.getCalExpressfeeService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==calFeebt){
            String fromLoc=texts[1].getText();
            String toLoc=texts[5].getText();
            double weight;
            double volumn;
            String expressType=comboBox1.getSelectedItem().toString();
            String wrapType=comboBox2.getSelectedItem().toString();

            if (checkLocation(fromLoc)&& checkLocation(toLoc)){
                try {
                    weight=Double.parseDouble(texts[9].getText());
                    volumn=Double.parseDouble(texts[10].getText());
                    if (weight<=0||volumn<=0){
                        new ErrorDialog(parent, "重量或体积必须是正整数或小数");
                    }

                    SendReceiptVO vo=new SendReceiptVO("",fromLoc,"","","",toLoc,"","",1,weight,volumn,"",
                            expressType,wrapType,"",0.0,new Date());

                    double fee=calExpressfeeService.calExpressFee(vo);
                    texts[13].setText(fee+"");

                }catch (NumberFormatException e1){
                    new ErrorDialog(parent, "重量或体积必须是正整数或小数");
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "sql exception");
                }

            } else {
                new ErrorDialog(parent, "地址前两位必须为市名");
            }



        } else if (e.getSource()==submitbt){
            try {
                checkAllFormat();
                SendReceiptVO vo=new SendReceiptVO(texts[0].getText(),texts[1].getText(),texts[2].getText(),
                        texts[3].getText(),texts[4].getText(),texts[5].getText(),texts[6].getText(),
                        texts[7].getText(),Integer.parseInt(texts[8].getText()),Double.parseDouble(texts[9].getText()),
                        Double.parseDouble(texts[10].getText()),texts[11].getText(),comboBox1.getSelectedItem().toString(),
                        comboBox2.getSelectedItem().toString(), texts[12].getText(),Double.parseDouble(texts[13].getText()),
                        new Date());
                sendBLService.submit(vo);
                refresh();
            } catch (TransportBLException e1) {
                new ErrorDialog(parent, e1.getMessage());
            } catch (RemoteException e1) {
                new ErrorDialog(parent, "服务器连接超时");
            } catch (MalformedURLException e1) {
                new ErrorDialog(parent, "MalformedURLException");
            } catch (SQLException e1) {
                new ErrorDialog(parent, "SQLException");
            } catch (NotBoundException e1) {
                new ErrorDialog(parent, "NotBoundException");
            }


        }
    }

    private void refresh(){
        for (int i=0;i<14;i++){
            texts[i].setText("");
        }
        comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
    }

    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {
        if (e.getSource()==texts[1]){
            if (checkLocation(texts[1].getText())){
                texts[1].setBorder(BorderFactory.createLineBorder(Color.GREEN));

            }else {
                texts[1].setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }else if (e.getSource()==texts[5]){
            if (checkLocation(texts[5].getText())){
                texts[5].setBorder(BorderFactory.createLineBorder(Color.GREEN));

            }else {
                texts[5].setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
    }

    private boolean checkAllFormat() throws TransportBLException {
        if (!checkLocation(texts[1].getText())) throw new TransportBLException("寄件人地址前两位必须为市名");

        if (!checkLocation(texts[5].getText())) throw new TransportBLException("收件人地址前两位必须为市名");

        if (!checkPhone(texts[3].getText())) throw new TransportBLException("寄件人手机号必须为11位数");

        if (!checkPhone(texts[7].getText())) throw new TransportBLException("收件人手机号必须为11位数");

        if (!checkNumber(texts[8].getText())) throw new TransportBLException("原件数必须为正整数");

        if (!checkWeightOrVolumn(texts[9].getText())) throw new TransportBLException("重量必须为正整数或正小数");

        if (!checkWeightOrVolumn(texts[10].getText())) throw new TransportBLException("体积必须为正整数或正小数");

        if (!checkOrderID(texts[12].getText())) throw new TransportBLException("订单条形码必须为10为数字");

        if (!checkFee(texts[13].getText())) throw new TransportBLException("运费必须为正数");

        return true;
    }

    private boolean checkFee(String s){
        double num;
        try{
            num=Double.parseDouble(s);
        }catch (NumberFormatException e1){
            return false;
        }
        if (num>0) return true;
        return false;
    }

    private boolean checkOrderID(String s){
        if (s.length()!=Constent.ORDER_ID_LENGTH)
            return false;
        for (int i=0;i<Constent.ORDER_ID_LENGTH;i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9')
                return false;
        }
        return true;
    }

    private boolean checkWeightOrVolumn(String s){
        double num;
        try{
            num=Double.parseDouble(s);
        }catch (NumberFormatException e1){
            return false;
        }
        if (num>0) return true;
        return false;
    }

    /**
     * 检查地址前两位是否合法，合法才能计算运费
     * @param s
     * @return
     */
    private boolean checkLocation(String s){
        if (s.length()<2)
            return false;
        s=s.substring(0,2);
        for (String loc: Constent.LOCATIONS){
            if (loc.equals(s)){
                return true;
            }
        }
        return false;
    }

    private boolean checkPhone(String s){
        if (s.length()!=Constent.PHONE_LENGTH) return false;
        for (int i=0;i<Constent.PHONE_LENGTH;i++){
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
        if (num>0) return true;
        return false;

    }
}
