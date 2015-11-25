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

        this.setPreferredSize(new Dimension(1000,700));

        JLabel picLabel=new JLabel(Images.LOGIN_IMAGE);

        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        JLabel numLabel=new JLabel("工号");
        JLabel passLabel=new JLabel("密码");
        JTextField numText=new JTextField(25);
        JPasswordField passwordField=new JPasswordField(25);
        JButton logButton=new JButton("登录");
        JLabel label=new JLabel("用户登录", JLabel.CENTER);
        JSeparator seph=new JSeparator();

        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(10,10,10,10);
        c.fill=GridBagConstraints.BOTH;

        c.gridwidth=2;
        panel.add(label,c);
        c.gridy=1;
        panel.add(seph,c);

        c.gridy=2;
        c.gridwidth=1;
        //c.insets=new Insets(10,10,10,10);
        panel.add(numLabel,c);
        c.gridx=1;
        panel.add(numText,c);
        c.gridx=0;
        c.gridy=3;
        panel.add(passLabel,c);
        c.gridx=1;
        panel.add(passwordField,c);
        c.gridy=4;
        c.fill=GridBagConstraints.NONE;
        panel.add(logButton,c);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        //panel.setPreferredSize(new Dimension(300, 200));

        gbc.gridx=0;
        gbc.gridy=1;

        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);
        this.add(picLabel,gbc);

        gbc.gridx=4;

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(10,200,10,10);
        gbc.ipadx=70;
       // gbc.anchor=GridBagConstraints.WEST;
        this.add(panel,gbc);

        JButton b=new JButton("查询物流信息");
        gbc.ipadx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(b, gbc);
    }


}
