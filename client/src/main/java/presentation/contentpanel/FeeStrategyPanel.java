package presentation.contentpanel;

import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/29.
 */
public class FeeStrategyPanel extends JavaPanel implements ActionListener {

    Frame parent;
    JPanel paypanel;
    JPanel chargepanel;
    JLabel[] labels=new JLabel[6];
    JTextField[] textFields=new JTextField[6];
    JButton confirmbt=new JButton("确认");
    JButton cancelbt=new JButton("取消");

    public FeeStrategyPanel(Frame par){
        this.parent=par;

        labels[0]=new JLabel("飞机");
        labels[1]=new JLabel("火车");
        labels[2]=new JLabel("汽车");
        labels[3]=new JLabel("经济快递");
        labels[4]=new JLabel("标准快递");
        labels[5]=new JLabel("特快专递");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();

        paypanel.setLayout(new GridLayout(3,2));
        chargepanel.setLayout(new GridLayout(3,2));
    }


    public void actionPerformed(ActionEvent e) {

    }
}
