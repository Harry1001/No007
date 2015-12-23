package presentation.commonpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/26.
 */
public class HeadPanel extends JPanel {
    JLabel chinese;
    JLabel english;

    public HeadPanel(){
        chinese=new JLabel("  快 递 物 流 系 统  ", JLabel.HORIZONTAL);
        chinese.setFont(new Font("楷体", Font.ITALIC, 48));
        chinese.setForeground(Color.BLUE);
        english=new JLabel("Express And Logistic System",JLabel.CENTER);
        english.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        english.setForeground(Color.BLUE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);

        gbc.gridy=0;
        this.add(chinese,gbc);

        gbc.gridy=1;
        this.add(english,gbc);

        this.setOpaque(false);

        this.setBorder(BorderFactory.createLineBorder(Color.CYAN, 4));
        this.setPreferredSize(new Dimension(600, 70));
    }
}
