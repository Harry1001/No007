package presentation.commoncontainer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/5.
 */
public class MyToggleButton extends JToggleButton {



    public MyToggleButton(String text, Icon icon) {
        super(text, icon);
        initbt();
    }

    private void initbt(){
        this.setPreferredSize(new Dimension(170,60));
        this.setMinimumSize(new Dimension(170,60));
        this.setFont(new Font("",Font.PLAIN,20));
    }
}
