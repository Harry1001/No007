package presentation.commoncontainer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/4.
 */
public class MyLabel extends JLabel {

    public MyLabel(String text) {
        super(text);
        initialize();
    }

    private void initialize(){
        this.setFont(new Font("微软雅黑",Font.PLAIN,20));
        this.setHorizontalAlignment(CENTER);
    }
}
