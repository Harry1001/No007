package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/24.
 */
public class StaffListPanel extends JPanel implements ActionListener{
    MainFrame parent;
    MyButton addbt=new MyButton("新增");
    MyButton deletebt=new MyButton("删除");
    MyButton modifybt=new MyButton("修改");

    MyDefaultTableModel defaultTableModel;
    MyTable table;

    public StaffListPanel(MainFrame par) {

        this.parent=par;

        String [] names={"工号","姓名","性别","出生年月","职位"};

        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);
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
        gbc.gridheight=10;
        this.add(new JScrollPane(table),gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        gbc.gridx=0;
        gbc.gridy=13;
        this.add(addbt,gbc);
        gbc.gridx=1;
        this.add(deletebt,gbc);
        gbc.gridx=2;
        this.add(modifybt,gbc);

        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            JDialog dialog=new JDialog(parent,"新增人员信息",true);
            dialog.getContentPane().add(new StaffInfoPanel(dialog));
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
