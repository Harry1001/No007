package presentation.commonpanel;

import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/5.
 */
public class ErrorDialog extends JDialog implements ActionListener {

    private JFrame parent;
    private MyLabel label;
    private MyButton confirmbt;
    public ErrorDialog(JFrame parent, String message) {
        super(parent,"错误提示",true);
        this.parent=parent;
        label=new MyLabel(message);
        confirmbt=new MyButton("确认");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.getContentPane().add(label);
        this.getContentPane().add(confirmbt);
        this.setLocationRelativeTo(parent);
        confirmbt.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
