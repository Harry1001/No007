package presentation;

import presentation.Images.Images;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/23.
 */
public class LoginPanel extends JPanel {
    public LoginPanel(){

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc =new GridBagConstraints();

        this.setPreferredSize(new Dimension(800,600));

        JLabel picLabel=new JLabel(Images.LOGIN_IMAGE);

        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        JLabel numLabel=new JLabel("工号");
        JLabel passLabel=new JLabel("密码");
        JTextField numText=new JTextField(15);
        JPasswordField passwordField=new JPasswordField(15);
        JButton logButton=new JButton("登录");

        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(10,10,10,10);
        c.fill=GridBagConstraints.BOTH;

        panel.add(numLabel,c);
        c.gridx=1;
        panel.add(numText,c);
        c.gridx=0;
        c.gridy=1;
        panel.add(passLabel,c);
        c.gridx=1;
        panel.add(passwordField,c);
        c.gridy=2;
        c.fill=GridBagConstraints.NONE;
        panel.add(logButton,c);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setPreferredSize(new Dimension(300, 200));

        gbc.gridx=0;
        gbc.gridy=1;

        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);
        this.add(picLabel,gbc);

        gbc.gridx=4;

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(10,100,10,10);
        gbc.anchor=GridBagConstraints.WEST;
        this.add(panel,gbc);

        JButton b=new JButton("查询物流信息");
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(b, gbc);
    }


}
