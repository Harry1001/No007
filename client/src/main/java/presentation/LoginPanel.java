package presentation;

import presentation.Images.Images;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/23.
 */
public class LoginPanel extends JPanel {
    public LoginPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel picLabel=new JLabel(new ImageIcon(Images.LOGIN_IMAGE));

        JPanel panel=new JPanel(new GridLayout(3,2,10,10));
        JLabel numLabel=new JLabel("工号");
        JLabel passLabel=new JLabel("密码");
        JTextField numText=new JTextField(10);
        JPasswordField passwordField=new JPasswordField(10);
        JButton logButton=new JButton("登录");

        panel.add(numLabel);
        panel.add(numText);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(logButton);

        this.add(picLabel);
        this.add(panel);
    }


}
