package presentation.contentpanel.depotpanels;

import presentation.contentpanel.managerpanels.StaffInfoPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Harry on 2015/12/2.
 */
public class DepotPanDianPanel extends JPanel implements ActionListener{
    Frame parent;
    JButton refreshbt=new JButton("刷新数据");

    DefaultTableModel defaultTableModel;
    JTable table;

    public DepotPanDianPanel(Frame par) {

        this.parent=par;

        String [] names={"快递编号","入库日期","目的地","区号","排号","架号","位号"};

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

        gbc.gridwidth=5;
        gbc.gridx=gbc.gridy=0;
        this.add(new JScrollPane(table),gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;

        gbc.gridwidth=1;
        gbc.gridx=4;
        gbc.gridy=2;
        this.add(refreshbt,gbc);


        refreshbt.addActionListener(this);

    }

    public void refreshData(){
        //todo
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==refreshbt){
            refreshData();
        }
    }

    class MyDefaultTableModel extends DefaultTableModel{
        public MyDefaultTableModel() {
        }

        public MyDefaultTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public MyDefaultTableModel(Vector columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public MyDefaultTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        public MyDefaultTableModel(Vector data, Vector columnNames) {
            super(data, columnNames);
        }

        public MyDefaultTableModel(int rowCount, int columnCount) {
            super(rowCount, columnCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
