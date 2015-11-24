package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/24.
 */
public class HeadLabel extends JLabel {
    public HeadLabel(){
        super("快递物流系统", JLabel.CENTER);
        this.setForeground(Color.BLUE);
        this.setFont(new Font("楷体", Font.ITALIC, 48));
        this.setBorder(BorderFactory.createLineBorder(Color.CYAN, 4));
        this.setPreferredSize(new Dimension(600, 85));
    }
}
