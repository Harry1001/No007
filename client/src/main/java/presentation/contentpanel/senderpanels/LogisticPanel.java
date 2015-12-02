package presentation.contentpanel.senderpanels;

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

    Frame parent;
    JLabel label;
    JButton button;
    JTextField textField;
    JTable table;
    DefaultTableModel defaultTableModel;

    String [] name = {"时间","到达地点"};
    String [][] data={};

    GridBagConstraints gbc;

    public LogisticPanel(Frame par){
        this.parent=par;
        label=new JLabel("请输入10位订单号:");
        button=new JButton("查询");
        textField=new JTextField(20);

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

        defaultTableModel=new DefaultTableModel(data,name);
        table=new JTable(defaultTableModel);
        //table.setPreferredSize(new Dimension(400,200));
        JScrollPane s=new JScrollPane(table);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);


        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=3;
        gbc.gridheight=3;
        this.add(s, gbc);

        button.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        //todo
       String [] data={"2010/10/10","dsfadssdfadsfasfdsaadsf"};

       defaultTableModel.addRow(data);
    }
}
