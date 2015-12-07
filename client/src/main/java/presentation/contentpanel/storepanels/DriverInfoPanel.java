package presentation.contentpanel.storepanels;

import javax.swing.*;

import presentation.commoncontainer.TimePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/27.
 */
public class DriverInfoPanel extends JPanel implements ActionListener{
    Dialog parent;
    JLabel[] labels=new JLabel[7];
    JTextField [] textFields=new JTextField[5];
    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    public DriverInfoPanel(Dialog parent) {
        this.parent = parent;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        String[] names={"司机编号","姓名","出生日期","身份证号","手机号","性别","行驶证期限"};
        for(int i=0;i<7;i++){
            labels[i]=new JLabel(names[i]);
        }
        for(int i=0;i<5;i++){
            textFields[i]=new JTextField(25);
        }
        TimePanel p1=new TimePanel();
        TimePanel p2=new TimePanel();

        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<7;gbc.gridy++){
            this.add(labels[gbc.gridy],gbc);
        }

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(textFields[0],gbc);
        gbc.gridy++;
        this.add(textFields[1],gbc);
        gbc.gridy++;
        this.add(p1,gbc);
        gbc.gridy++;
        this.add(textFields[2],gbc);
        gbc.gridy++;
        this.add(textFields[3],gbc);
        gbc.gridy++;
        this.add(textFields[4],gbc);
        gbc.gridy++;
        this.add(p2,gbc);

        gbc.gridx=0;
        gbc.gridy=8;
        this.add(submitbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            //todo
        }
        else if (e.getSource()==cancelbt){
            parent.dispose();
        }
    }

    class datepanel extends JPanel{
        JLabel l1=new JLabel("年");
        JLabel l2=new JLabel("月");
        JLabel l3=new JLabel("日");
        JTextField t1=new JTextField(8);
        JTextField t2=new JTextField(4);
        JTextField t3=new JTextField(4);

        public datepanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.add(t1);
            this.add(l1);
            this.add(t2);
            this.add(l2);
            this.add(t3);
            this.add(l3);
        }


    }

}
