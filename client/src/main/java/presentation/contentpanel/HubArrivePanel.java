package presentation.contentpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/28.
 */
public class HubArrivePanel extends JPanel {
    Frame parent;

    JLabel orderL=new JLabel("中转中心到达单编号");
    JLabel hubIDL=new JLabel("中转中心编号");
    JLabel timeL=new JLabel("到达日期");
    JLabel numL=new JLabel("中转/装车单编号");
    JLabel fromL=new JLabel("出发地");
    JLabel stateL=new JLabel("货物到达状态");

    JTextField orderT=new JTextField(25);
    JTextField hubIDT=new JTextField(25);
    JTextField timeT=new JTextField(25);
    JTextField numT=new JTextField(25);
    JTextField fromT=new JTextField(25);
    JComboBox<String> stateC;

    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    public HubArrivePanel(Frame par){
        this.parent=par;
        String[] s={"完整", "损坏", "丢失"};
        stateC=new JComboBox(s);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        this.add(orderL,gbc);
        gbc.gridy=1;
        this.add(hubIDL,gbc);
        gbc.gridy=2;
        this.add(timeL,gbc);
        gbc.gridy=3;
        this.add(numL,gbc);
        gbc.gridy=4;
        this.add(fromL,gbc);
        gbc.gridy=5;
        this.add(stateL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(orderT,gbc);
        gbc.gridy=1;
        this.add(hubIDT,gbc);
        gbc.gridy=2;
        this.add(timeT, gbc);
        gbc.gridy=3;
        this.add(numT,gbc);
        gbc.gridy=4;
        this.add(fromT,gbc);
        gbc.gridy=5;
        this.add(stateC,gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.gridx=0;
        gbc.gridy=7;
        this.add(submitbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);

    }
}
