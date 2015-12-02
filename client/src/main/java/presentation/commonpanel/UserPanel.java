package presentation.commonpanel;

import presentation.Images.Images;
import typeDefinition.Job;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/24.
 */
public class UserPanel extends JPanel {

    private String userID;
    private String userjob;//todo job这个枚举类不方便显示，建议改成string

    public UserPanel(String id, String job){

        this.userID=id;
        this.userjob=job;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();

        JLabel pic=new JLabel(Images.USERHEAD);
        JLabel welcome=new JLabel("欢迎您，"+id+"!");
        JLabel identity=new JLabel("当前身份："+job);

        JButton logout=new JButton("退出 ");

        gbc.insets=new Insets(10, 10,10,20);
       // gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=2;
        this.add(pic, gbc);

        gbc.gridx=1;
        gbc.gridy=0;

        this.add(welcome,gbc);
        gbc.gridy=1;
        gbc.anchor=GridBagConstraints.SOUTH;
        this.add(identity,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        this.add(logout,gbc);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
