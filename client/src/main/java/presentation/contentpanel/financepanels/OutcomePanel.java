package presentation.contentpanel.financepanels;

import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.TimePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/4.
 */
public class OutcomePanel extends JPanel implements ActionListener{
    Frame parent;
    MyLabel timeL=new MyLabel("付款日期");
    MyLabel feeL=new MyLabel("付款金额");
    MyLabel personL=new MyLabel("付款人");
    MyLabel accountL=new MyLabel("付款帐号");
    MyLabel itemL=new MyLabel("付款条目");
    MyLabel additionL=new MyLabel("备注");
    TimePanel timeP=new TimePanel();
    MyTextField feeT=new MyTextField();
    MyTextField personT=new MyTextField();
    MyTextField accountT=new MyTextField();
    MyTextField additionT=new MyTextField(25);
    MyButton submitbt=new MyButton("提交");

    JRadioButton salary = new JRadioButton("工资");
    JRadioButton rent = new JRadioButton("租金");
    JRadioButton carriage = new JRadioButton("运费");
    JRadioButton bonus = new JRadioButton("奖金");

    public OutcomePanel(Frame par){
        this.parent=par;
        initialize();
    }

    private void initialize(){
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(salary);
        btgroup.add(rent);
        btgroup.add(carriage);
        btgroup.add(bonus);
        salary.setSelected(true);

        JPanel btpanel=new JPanel();
        btpanel.setLayout(new BoxLayout(btpanel,BoxLayout.X_AXIS));
        btpanel.add(salary);
        btpanel.add(rent);
        btpanel.add(carriage);
        btpanel.add(bonus);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;
       // gbc.weighty=1.0;

        gbc.gridx=gbc.gridy=0;
        this.add(timeL,gbc);
        gbc.gridy++;
        this.add(feeL,gbc);
        gbc.gridy++;
        this.add(personL,gbc);
        gbc.gridy++;
        this.add(accountL,gbc);
        gbc.gridy++;
        this.add(itemL,gbc);
        gbc.gridy++;
        this.add(additionL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(timeP,gbc);
        gbc.gridy++;
        this.add(feeT,gbc);
        gbc.gridy++;
        this.add(personT,gbc);
        gbc.gridy++;
        this.add(accountT,gbc);
        gbc.gridy++;
        this.add(btpanel,gbc);
        gbc.gridy++;
        this.add(additionT,gbc);

        gbc.anchor=GridBagConstraints.EAST;
        gbc.gridy++;
        this.add(submitbt,gbc);
    }

    public void refresh(){
        //todo
    }

    public void actionPerformed(ActionEvent e) {
        
    }
}
