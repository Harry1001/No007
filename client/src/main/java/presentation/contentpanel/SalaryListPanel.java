package presentation.contentpanel;

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
    Frame parent;
    JButton confirmbt=new JButton("确认");

    DefaultTableModel defaultTableModel;
    JTable table;

    public SalaryListPanel(Frame par){
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
        gbc.gridx=4;
        this.add(confirmbt,gbc);


        confirmbt.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            //todo 调用bl方法

        }

    }

    class MyDefaultTableModel extends DefaultTableModel{
        public MyDefaultTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public MyDefaultTableModel() {
        }

        public MyDefaultTableModel(int rowCount, int columnCount) {
            super(rowCount, columnCount);
        }

        public MyDefaultTableModel(Vector columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public MyDefaultTableModel(Vector data, Vector columnNames) {
            super(data, columnNames);
        }

        public MyDefaultTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        public boolean isCellEditable(int row, int column){
            return column==0?false:true;
        }
    }

}
