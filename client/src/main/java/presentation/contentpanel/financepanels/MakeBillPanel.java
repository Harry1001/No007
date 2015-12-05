package presentation.contentpanel.financepanels;

import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTable;
import presentation.commoncontainer.MyTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/4.
 */
public class MakeBillPanel extends JPanel implements ActionListener {

    private Frame parent;
    private MyLabel yearL = new MyLabel("保存年份");
    private MyTextField yearT = new MyTextField();
    private MyButton submitbt=new MyButton("提交");

    public MakeBillPanel(Frame par){
        this.parent=par;
        init();

    }

    private void init(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
       // gbc.weightx=1.0;
        //gbc.weighty=1.0;

        gbc.gridx=gbc.gridy=0;
        this.add(yearL,gbc);
        gbc.gridx++;
        this.add(yearT,gbc);
        gbc.gridy++;
        this.add(submitbt,gbc);
        refresh();
    }


    public void refresh(){
        yearT.setText("");
    }

    public void actionPerformed(ActionEvent e) {

    }
}
