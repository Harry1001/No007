package presentation.contentpanel.financepanels;

import presentation.commoncontainer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/4.
 */
public class IncomePanel extends JPanel implements ActionListener{
    private Frame parent;
    private MyDefaultTableModel defaultTableModel;
    private MyTable table;
    private MyButton totalbt;
    private MyButton timebt;
    private TimePanel fromTimeP;
    private TimePanel toTimeP;
    private MyLabel fromTimeL;
    private  MyLabel toTimeL;

    public IncomePanel(Frame par){
        this.parent=par;
        initialize();
    }

    /**
     * 初始化所有组件
     */
    private void initialize(){
        String[] names={"收款快递员工号","收款日期","收款金额"};
        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);
        totalbt=new MyButton("合计");
        timebt=new MyButton("确认");
        fromTimeP=new TimePanel();
        toTimeP=new TimePanel();
        fromTimeL=new MyLabel("起始时间");
        toTimeL=new MyLabel("截止时间");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;

        gbc.gridx=gbc.gridy=0;
        this.add(fromTimeL,gbc);
        gbc.gridx=1;
        this.add(fromTimeP,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        this.add(toTimeL,gbc);
        gbc.gridx=1;
        this.add(toTimeP,gbc);
        gbc.gridx=2;
        this.add(timebt,gbc);
        gbc.gridy=2;
        gbc.gridx=0;
        gbc.gridwidth=4;
        this.add(new JScrollPane(table),gbc);
        gbc.gridwidth=1;
        gbc.gridy=3;
        gbc.gridx=2;
        this.add(totalbt,gbc);
    }

    public void refresh(){
        //todo
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timebt){
            //todo
        } else if (e.getSource() == totalbt){

        }
    }
}