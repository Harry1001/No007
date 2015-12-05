package presentation.commoncontainer;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * Created by Harry on 2015/12/4.
 */
public class MyTable extends JTable {
    public MyTable(TableModel dm) {
        super(dm);
        initialize();
    }

    private void initialize(){
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>();
        this.setRowSorter(sorter);
        this.setPreferredSize(new Dimension(500, 300));
        this.setPreferredScrollableViewportSize(new Dimension(500, 300));
        this.setMinimumSize(new Dimension(300,240));
    }
}
