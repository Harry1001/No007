package presentation.contentpanel;

import MainFrame.MainFrame;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/27.
 */
public class ChargeReceiptPanel extends JPanel implements ActionListener{
    MainFrame parent;

    MyLabel timeL=new MyLabel("收款日期");
    MyLabel moneyL=new MyLabel("收款金额");
    MyLabel courierL=new MyLabel("收款快递员");

    MyTextField timeT=new MyTextField(25);
    MyTextField moneyT=new MyTextField(25);
    MyTextField courierT=new MyTextField(25);
    MyTextField orderNumT=new MyTextField(25);

    MyButton appendbt=new MyButton("添加");
    MyButton deletebt=new MyButton("删除");
    MyButton submitbt=new MyButton("提交");
    MyButton cleanbt=new MyButton("清空输入");

    MyDefaultTableModel defaultTableModel;
    JTable table;



    public ChargeReceiptPanel(MainFrame par){
        this.parent=par;
        String [] names={"订单条形码号"};
        String [][] data={};
        defaultTableModel=new MyDefaultTableModel(data, names);
        table=new JTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;

        this.add(timeL,gbc);
        gbc.gridy=1;
        this.add(moneyL,gbc);
        gbc.gridy=2;
        this.add(courierL,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        this.add(timeT,gbc);
        gbc.gridy=1;
        this.add(moneyT,gbc);
        gbc.gridy=2;
        this.add(courierT,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.gridheight=3;
        table.setPreferredScrollableViewportSize(new Dimension(300,200));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(new JScrollPane(table), gbc);

        gbc.gridy=3;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        this.add(orderNumT,gbc);
        gbc.gridy=4;
        gbc.gridwidth=1;
        this.add(appendbt,gbc);
        gbc.gridx=3;
        this.add(deletebt,gbc);

        gbc.gridy=7;
        gbc.gridx=1;
        this.add(submitbt,gbc);
        gbc.gridx=2;
        this.add(cleanbt,gbc);

        appendbt.addActionListener(this);
        deletebt.addActionListener(this);
        submitbt.addActionListener(this);
        cleanbt.addActionListener(this);
    }

    private void initBL(){

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==appendbt){
            String [] s={orderNumT.getText()};
            defaultTableModel.addRow(s);
           // System.out.println(defaultTableModel.getRowCount());
        }
        else if (e.getSource()==deletebt){
            int[] rows=table.getSelectedRows();
            for(int i:rows){
                defaultTableModel.removeRow(i);
            }
          //  System.out.println(defaultTableModel.getRowCount());
        }
        else if (e.getSource()==submitbt){

        }
        else if (e.getSource()==cleanbt){

        }

    }
}
