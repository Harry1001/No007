package presentation.contentpanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Harry on 2015/11/25.
 */
public class SendPanel extends JPanel {

    Frame parent;
    JLabel[] labels=new JLabel[16];
    JTextField[] texts=new JTextField[14];
    JComboBox comboBox1;
    JComboBox comboBox2;
    JButton calFeebt;
    JButton submitbt;

    public SendPanel(Frame par){

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

        for(int i=0;i<14;i++){
            texts[i]=new JTextField(15);
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

        calFeebt=new JButton("计算运费");
        submitbt=new JButton(" 提交");
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.gridwidth=1;
        this.add(calFeebt,gbc);
        gbc.gridx=1;
        this.add(submitbt,gbc);

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"寄件单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));
    }
}
