package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Harry on 2015/11/29.
 */
public class SalaryListPanel extends JPanel implements ActionListener {
    MainFrame parent;
    MyButton confirmbt=new MyButton("确认");
    MyButton cancelbt=new MyButton("取消");

    DefaultTableModel defaultTableModel;
    JTable table;

    public SalaryListPanel(MainFrame par){
        this.parent=par;

        String [] names={"员工职位","月基本工资(元)","提成(元/次)","备注"};

        //todo 读取数据后初始化表格data
        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new JTable(defaultTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RowSorter<TableModel> sorter=new TableRowSorter<TableModel>();
        table.setRowSorter(sorter);
        table.setPreferredSize(new Dimension(500,400));
        table.setPreferredScrollableViewportSize(new Dimension(500,400));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridwidth=6;
        gbc.gridheight=10;
        this.add(new JScrollPane(table),gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridheight=1;
        gbc.gridwidth=1;

        gbc.gridy=13;
        gbc.gridx=3;
        this.add(confirmbt,gbc);
        gbc.gridx=3;
        this.add(cancelbt,gbc);


        confirmbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            //todo 调用bl方法

        }
        else if (e.getSource()==cancelbt){
            //todo
        }
    }
    

}
