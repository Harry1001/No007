package presentation.contentpanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by Harry on 2015/11/27.
 */
public class EntruckPanel extends JPanel {

    Frame parent;

    JLabel timeL=new JLabel("装车日期");
    JLabel numL=new JLabel("汽运编号");
    JLabel destiL=new JLabel("到达地");
    JLabel truckIDL=new JLabel("车辆代号");
    JLabel feeL=new JLabel("运费");
    //JLabel orderNumL=new JLabel("本次装箱所有订单条形码号");

    JTextField timeT=new JTextField(15);
    JTextField numT=new JTextField(15);
    JTextField destiT=new JTextField(15);
    JTextField truckIDT=new JTextField(15);
    JTextField feeT=new JTextField(15);
    //JTextArea orderNumArea=new JTextArea();
    JTextField orderNumText=new JTextField(15);

    JButton feebt=new JButton("生成运费");
    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");
    JButton appendbt=new JButton("添加");
    JButton deletebt=new JButton("删除");

    DefaultTableModel defaultTableModel;
    JTable table;

    public EntruckPanel(Frame par){
        this.parent=par;

        String [] names={"本次装箱所有订单条形码号"};
        defaultTableModel=new DefaultTableModel(names,0);

        table=new JTable(defaultTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(300,200));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.NORTH;

        this.add(timeL, gbc);
        gbc.gridy=1;
        this.add(numL,gbc);
        gbc.gridy=2;
        this.add(destiL,gbc);
        gbc.gridy=3;
        this.add(truckIDL,gbc);
        gbc.gridy=4;
        this.add(feeL, gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(timeT,gbc);
        gbc.gridy=1;
        this.add(numT,gbc);
        gbc.gridy=2;
        this.add(destiT,gbc);
        gbc.gridy=3;
        this.add(truckIDT,gbc);
        gbc.gridy=4;
        this.add(feeT,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        gbc.gridwidth=4;
        gbc.gridheight=6;
        this.add(new JScrollPane(table),gbc);

        gbc.gridheight=1;
        gbc.gridwidth=2;
        gbc.gridy=6;
        this.add(orderNumText,gbc);

        gbc.gridx=4;
        gbc.gridy=6;
        gbc.gridwidth=1;
        //gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(appendbt,gbc);
        gbc.gridx=5;
        this.add(deletebt,gbc);

        gbc.gridx=1;
        gbc.gridy=5;
        gbc.anchor=GridBagConstraints.CENTER;
        this.add(feebt,gbc);
        gbc.gridy=9;
        gbc.gridx=1;
        this.add(submitbt,gbc);
        gbc.gridx=2;
        this.add(cancelbt,gbc);
    }
}
