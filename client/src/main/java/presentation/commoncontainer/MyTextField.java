package presentation.commoncontainer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/4.
 */
public class MyTextField extends JTextField {
    public MyTextField() {
       this(15);
    }

    public MyTextField(int rows){
        this.setColumns(rows);
        initialize();
    }

    private void initialize(){
        this.setFont(new Font("",Font.PLAIN,15));
    }
}
