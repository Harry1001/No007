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
 * Created by Harry on 2015/11/28.
 */
public class RecordListPanel extends JPanel implements ActionListener{
    Frame parent;

    DefaultTableModel defaultTableModel;
    JTable table;
    JButton refreshbt=new JButton("刷新日志");

    public RecordListPanel(Frame par) {

        this.parent = par;

        String[] names = {"操作时间", "操作人", "操作概要简述"};

        defaultTableModel = new MyDefaultTableModel(names, 0);
        table = new JTable(defaultTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>();
        table.setRowSorter(sorter);
        table.setPreferredSize(new Dimension(500, 400));
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        table.getColumnModel().getColumn(2).setPreferredWidth(300);//设置操作简述一列较宽

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridwidth = 2;
        this.add(new JScrollPane(table), gbc);

        gbc.gridwidth = 1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(refreshbt,gbc);

        refreshbt.addActionListener(this);
    }

    public void refreshData(){
        //todo
    }

    public void actionPerformed(ActionEvent e) {
        refreshData();
    }

    class MyDefaultTableModel extends DefaultTableModel{
        public MyDefaultTableModel(int rowCount, int columnCount) {
            super(rowCount, columnCount);
        }

        public MyDefaultTableModel() {
        }

        public MyDefaultTableModel(Vector columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public MyDefaultTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public MyDefaultTableModel(Vector data, Vector columnNames) {
            super(data, columnNames);
        }

        public MyDefaultTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
