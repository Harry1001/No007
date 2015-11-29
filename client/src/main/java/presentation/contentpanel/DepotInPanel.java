package presentation.contentpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/28.
 */
public class DepotInPanel extends JPanel {
    Frame parent;
    JLabel packIDL=new JLabel("快递编号");
    JLabel destiL=new JLabel("目的地");
    JLabel timeL=new JLabel("入库日期");
    JLabel quhaoL=new JLabel("区号");
    JLabel paihaoL=new JLabel("排号");
    JLabel jiahaoL=new JLabel("架号");
    JLabel weihaoL=new JLabel("位号");
    JLabel hubIDL=new JLabel("中转中心编号");

    JTextField packIDT=new JTextField(25);
    JTextField destiT=new JTextField(25);
    JTextField timeT=new JTextField(25);
    JTextField hubIDT=new JTextField(25);
    JTextField quhaoT=new JTextField(5);
    JTextField paihaoT=new JTextField(5);
    JTextField jiahaoT=new JTextField(5);
    JTextField weihaoT=new JTextField(5);

    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    public DepotInPanel(Frame par){
        this.parent=par;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        this.add(packIDL,gbc);
        gbc.gridy++;
        this.add(destiL,gbc);
        gbc.gridy++;
        this.add(timeL,gbc);
        gbc.gridy++;
        this.add(hubIDL,gbc);
        gbc.gridy++;
        this.add(quhaoL,gbc);
        gbc.gridy++;
        this.add(jiahaoL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(packIDT,gbc);
        gbc.gridy++;
        this.add(destiT,gbc);
        gbc.gridy++;
        this.add(timeT,gbc);
        gbc.gridy++;
        this.add(hubIDT,gbc);

        gbc.gridwidth=1;
        gbc.gridy++;
        this.add(quhaoT,gbc);
        gbc.gridy++;
        this.add(jiahaoT,gbc);

        gbc.gridx++;
        this.add(weihaoL,gbc);
        gbc.gridy--;
        this.add(paihaoL,gbc);

        gbc.gridx++;
        this.add(paihaoT,gbc);
        gbc.gridy++;
        this.add(weihaoT,gbc);

        gbc.gridx=1;
        gbc.gridy=7;
        this.add(submitbt,gbc);
        gbc.gridx++;
        this.add(cancelbt,gbc);
    }

}
