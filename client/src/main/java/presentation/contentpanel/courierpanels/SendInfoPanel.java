package presentation.contentpanel.courierpanels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/2.
 */
public class SendInfoPanel extends JPanel implements ActionListener {
    Frame parent;
    JLabel[] labels=new JLabel[16];
    JTextField[] texts=new JTextField[16];
    JLabel inputL=new JLabel("请输入订单号");
    JTextField inputT=new JTextField(15);
    JButton confirmbt=new JButton("确认");

    public SendInfoPanel(Frame par){

        this.parent=par;
        labels[0]=new JLabel("姓名");
        labels[1]=new JLabel("住址");
        labels[2]=new JLabel("单位");
        labels[3]=new JLabel("手机");
        labels[4]=new JLabel("姓名");
        labels[5]=new JLabel("住址");
        labels[6]=new JLabel("单位");
        labels[7]=new JLabel("手机");
        labels[8]=new JLabel("原件数");
        labels[9]=new JLabel("实际重量");
        labels[10]=new JLabel("体积");
        labels[11]=new JLabel("内件品名");
        labels[12]=new JLabel("快递类型");
        labels[13]=new JLabel("包装类型");
        labels[14]=new JLabel("订单条形码");
        labels[15]=new JLabel("报价");

        for(int i=0;i<16;i++){
            texts[i]=new JTextField(15);
            texts[i].setEditable(false);
        }


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
        gbc.anchor=GridBagConstraints.EAST;
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
        for(int i=0;i<4;i++){
            gbc.gridy=i;
            panel3.add(texts[i+12],gbc);
        }

        this.setLayout(new GridBagLayout());
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        this.add(inputL,gbc);
        gbc.gridx=1;
        this.add(inputT,gbc);
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridx=2;
        this.add(confirmbt,gbc);

        gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.gridx=0;
        gbc.gridy=1;
        this.add(panel1,gbc);
        gbc.gridx=2;
        this.add(panel2,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=4;
        this.add(panel3,gbc);

        confirmbt.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        //todo
    }
}
