package presentation.contentpanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Harry on 2015/11/25.
 */
public class ReceivePanel extends JPanel implements ItemListener{
    Frame parent;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JRadioButton rbt1;
    JRadioButton rbt2;
    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;

    JButton submitbt;

    public ReceivePanel(Frame par) {
        this.parent=par;
        l1=new JLabel("是否为代收");
        l2=new JLabel("收件人姓名");
        l3=new JLabel("收件人电话");
        l4=new JLabel("收件编号");
        l5=new JLabel("收件时间");
        t1=new JTextField(15);
        t2=new JTextField(15);
        t3=new JTextField(15);
        t4=new JTextField(15);
        rbt1=new JRadioButton("是");
        rbt2=new JRadioButton("否");
        submitbt=new JButton("提交");
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(rbt1);
        btgroup.add(rbt2);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=0;
        gbc.gridy=0;
        this.add(l1,gbc);
        gbc.gridx=1;
        this.add(rbt1,gbc);
        gbc.gridx=2;
        this.add(rbt2,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        this.add(l2,gbc);
        gbc.gridy=2;
        this.add(l3,gbc);
        gbc.gridy=3;
        this.add(l4,gbc);
        gbc.gridy=4;
        this.add(l5,gbc);

        gbc.gridx=1;
        gbc.gridwidth=2;
        gbc.gridy=1;
        this.add(t1,gbc);
        gbc.gridy=2;
        this.add(t2,gbc);
        gbc.gridy=3;
        this.add(t3,gbc);
        gbc.gridy=4;
        this.add(t4,gbc);

        gbc.gridx=2;
        gbc.gridy=5;
        gbc.gridwidth=1;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.NONE;
        this.add(submitbt,gbc);

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"收件单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));

        rbt1.setSelected(true);
        rbt1.addItemListener(this);
        rbt2.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==e.SELECTED){
            if (e.getSource()==rbt1){//代收
                t2.setEditable(true);
            }
            else {
                t2.setText("");
                t2.setEditable(false);
            }
        }
    }
}
