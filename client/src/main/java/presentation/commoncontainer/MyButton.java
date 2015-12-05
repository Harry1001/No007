package presentation.commoncontainer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/4.
 */
public class MyButton extends JButton {
    public MyButton(String text) {
        this.setText(text);
        initialize();
    }

    private void initialize(){
        this.setFont(new Font("",Font.PLAIN,15));
       // this.setPreferredSize(new Dimension(50,30));
       // this.setMaximumSize(new Dimension(80,50));
       // this.setMinimumSize(new Dimension(30,18));
    }
}
