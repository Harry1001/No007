package presentation.contentpanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * Created by Harry on 2015/11/28.
 */
public class RecordListPanel extends JPanel {
    Frame parent;

    DefaultTableModel defaultTableModel;
    JTable table;

    public RecordListPanel(Frame par) {

        this.parent = par;

        String[] names = {"操作时间", "操作人", "操作概要简述"};

        defaultTableModel = new DefaultTableModel(names, 0);
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

        gbc.gridwidth = 5;
        gbc.gridheight = 10;
        this.add(new JScrollPane(table), gbc);
    }
}
