package presentation.commoncontainer;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 *
 * Created by Harry on 2015/12/4.
 */
public class MyDefaultTableModel extends DefaultTableModel {
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

    /**
     * 将表格设置为不可编辑
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
