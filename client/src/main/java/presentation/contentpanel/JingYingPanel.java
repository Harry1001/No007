package presentation.contentpanel;

import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/5.
 */
public class JingYingPanel extends JPanel implements ActionListener{

    private Dialog parent;
    private MyTable chargeTable;
    private MyDefaultTableModel chargeModel;
    private MyTable payTable;
    private MyDefaultTableModel payModel;
    private MyButton confirmbt;

    public JingYingPanel(Dialog par){
        this.parent=par;
        init();
    }

    private void init(){
        String[] chargeNames={"收款快递员","收款日期","收款金额"};
        String[] payNames={"付款人","付款日期","付款金额","付款帐号","付款条目"};
        chargeModel=new MyDefaultTableModel(chargeNames,0);
        payModel=new MyDefaultTableModel(payNames,0);
        chargeTable=new MyTable(chargeModel);
        payTable=new MyTable(payModel);
        //todo 修改表格尺寸

        chargeTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"收款单",
               TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));
        payTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"付款单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));

        confirmbt=new MyButton("确认");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;

        this.add(new JScrollPane(chargeTable),gbc);
        gbc.gridy++;
        this.add(new JScrollPane(payTable),gbc);
        gbc.gridy++;
        this.add(confirmbt,gbc);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
