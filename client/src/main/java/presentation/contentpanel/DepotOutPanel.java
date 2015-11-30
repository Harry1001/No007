package presentation.contentpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/28.
 */
public class DepotOutPanel extends JPanel {
    Frame parent;

    JLabel packIDL=new JLabel("快递编号");
    JLabel destiL=new JLabel("目的地");
    JLabel timeL=new JLabel("出库日期");
    JLabel vehicleL=new JLabel("装运形式");
    JLabel transIDL=new JLabel("中转单/汽运编号");

    JTextField packIDT=new JTextField(25);
    JTextField destiT=new JTextField(25);
    JTextField timeT=new JTextField(25);
    JTextField transIDT=new JTextField(25);
    JRadioButton rbt1=new JRadioButton("飞机");
    JRadioButton rbt2=new JRadioButton("火车");
    JRadioButton rbt3=new JRadioButton("汽车");

    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    public DepotOutPanel(Frame par){
        this.parent=par;

        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(rbt1);
        btgroup.add(rbt2);
        btgroup.add(rbt3);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        this.add(packIDL,gbc);
        gbc.gridy++;
        this.add(destiL,gbc);
        gbc.gridy++;
        this.add(timeL,gbc);
        gbc.gridy++;
        this.add(transIDL,gbc);
        gbc.gridy++;
        this.add(vehicleL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(packIDT,gbc);
        gbc.gridy++;
        this.add(destiT,gbc);
        gbc.gridy++;
        this.add(timeT,gbc);
        gbc.gridy++;
        this.add(transIDT,gbc);
        gbc.gridy++;
        gbc.gridwidth=1;
        this.add(rbt1,gbc);
        gbc.gridx++;
        this.add(rbt2,gbc);
        gbc.gridx++;
        this.add(rbt3,gbc);

        gbc.gridx=1;
        gbc.gridy=6;
        this.add(submitbt,gbc);
        gbc.gridx++;
        this.add(cancelbt,gbc);
    }
}
