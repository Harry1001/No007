package presentation.contentpanel.storepanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/27.
 */
public class TruckListPanel extends JPanel implements ActionListener {
    Frame parent;
    JButton addbt=new JButton("新增");
    JButton deletebt=new JButton("删除");
    JButton modifybt=new JButton("修改");

    DefaultTableModel defaultTableModel;
    JTable table;

    public TruckListPanel(Frame par){
        this.parent=par;

        String [] names={"车辆代号","发动机号","车牌号","底盘号","购买时间","服役时间"};
        String [][] data={};

        defaultTableModel=new DefaultTableModel(data,names);
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
        gbc.gridx=3;
        gbc.gridy=13;
        this.add(addbt,gbc);
        gbc.gridx=4;
        this.add(deletebt,gbc);
        gbc.gridx=5;
        this.add(modifybt,gbc);

        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            JDialog dialog=new JDialog(parent,"新增车辆信息",true);
            dialog.getContentPane().add(new TruckInfoPanel(dialog));
            dialog.setLocationRelativeTo(parent);
            dialog.pack();
            dialog.show();
        }
        else if (e.getSource()==deletebt){

        }
        else if (e.getSource()==modifybt){

        }
    }
}
