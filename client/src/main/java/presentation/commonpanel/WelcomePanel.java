package presentation.commonpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/5.
 */
public class WelcomePanel extends JPanel{

    public WelcomePanel() {
        JLabel welcome=new JLabel("请点击左侧按钮选择功能",JLabel.CENTER);
        welcome.setFont(new Font("宋体",Font.BOLD,40));
        welcome.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.add(welcome);
    }
}
