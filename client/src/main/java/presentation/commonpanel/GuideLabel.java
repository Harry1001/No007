package presentation.commonpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/5.
 */
public class GuideLabel extends JLabel {

    public GuideLabel(){
        this.setText("导航栏");
        this.setFont(new Font("宋体",Font.BOLD, 30));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setPreferredSize(new Dimension(250,70));
        this.setMinimumSize(new Dimension(250,70));
        this.setOpaque(false);
    }
}
