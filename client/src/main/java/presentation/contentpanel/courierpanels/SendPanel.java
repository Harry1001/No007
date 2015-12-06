package presentation.contentpanel.courierpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.strategyblservice.CalExpressfeeService;
import businessLogicService.transportblservice.SendBLService;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/25.
 */
public class SendPanel extends JPanel implements ActionListener {

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

        String[] s1={"经济快递","标准快递","次晨特快"};
        String[] s2={"塑料袋","纸盒","木盒"};
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

        initBL();
    }

    private void initBL(){
        sendBLService= BLFactory.getSendBLService();
        calExpressfeeService=BLFactory.getCalExpressfeeService();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==calFeebt){

        } else if (e.getSource()==submitbt){

        }
    }
}
