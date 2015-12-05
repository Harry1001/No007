package presentation.contentpanel.senderpanels;

import MainFrame.MainFrame;
import presentation.commoncontainer.*;
import presentation.uifactory.UIFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Harry on 2015/11/25.
 */
public class LogisticPanel extends JPanel implements ActionListener{

    MainFrame parent;
    MyLabel label;
    MyButton button;
    MyTextField textField;
    MyTable table;
    MyDefaultTableModel defaultTableModel;
    MyButton backbt;

    String [] name = {"时间","到达地点"};

    GridBagConstraints gbc;

    public LogisticPanel(MainFrame par){
        this.parent=par;
        label=new MyLabel("请输入10位订单号:");
        button=new MyButton("查询");
        backbt=new MyButton("返回");
        textField=new MyTextField();

        this.setLayout(new GridBagLayout());
        gbc =new GridBagConstraints();

        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;

        gbc.gridx=0;
        this.add(label, gbc);
        gbc.gridx=1;
        this.add(textField,gbc);
        gbc.gridx=2;
        this.add(button,gbc);
        gbc.gridx=3;
        this.add(backbt,gbc);

        defaultTableModel=new MyDefaultTableModel(name, 0);
        table=new MyTable(defaultTableModel);
        //table.setPreferredSize(new Dimension(400,200));
        JScrollPane s=new JScrollPane(table);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);


        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=4;
        gbc.gridheight=3;
        this.add(s, gbc);

        button.addActionListener(this);
        backbt.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        //todo
        if (e.getSource()==button){
            String [] data={"2010/10/10","dsfadssdfadsfasfdsaadsf"};

            defaultTableModel.addRow(data);
        } else if (e.getSource()==backbt){
            UIFactory.showLoginPanel(parent);
        }

    }
}
