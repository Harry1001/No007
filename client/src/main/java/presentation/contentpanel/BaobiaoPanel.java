package presentation.contentpanel;

import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.TimePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/4.
 */
public class BaobiaoPanel extends JPanel {
    private Frame parent;
    private JRadioButton chengben=new JRadioButton("成本收益表");
    private JRadioButton jingying=new JRadioButton("经营情况表");
    private JRadioButton zhangmu=new JRadioButton("账目查询");
    private MyLabel fromTimeL=new MyLabel("开始日期");
    private MyLabel toTimeL=new MyLabel("截至日期");
    private MyLabel yearL=new MyLabel("年份");
    private TimePanel fromTimeP=new TimePanel();
    private TimePanel toTimeP=new TimePanel();
    private MyTextField yearT=new MyTextField();
    private MyButton confirmbt=new MyButton("确认");

    public BaobiaoPanel(Frame par){
        this.parent=par;
        init();
        refresh();
    }

    private void init(){
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(chengben);
        btgroup.add(jingying);
        btgroup.add(zhangmu);
        chengben.setSelected(true);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        //gbc.weightx=1.0;
       // gbc.weighty=1.0;

        this.add(chengben,gbc);
        gbc.gridy=1;
        this.add(jingying,gbc);
        gbc.gridy++;
        gbc.gridx=1;
        this.add(fromTimeL,gbc);
        gbc.gridx++;
        this.add(fromTimeP,gbc);
        gbc.gridy++;
        gbc.gridx--;
        this.add(toTimeL,gbc);
        gbc.gridx++;
        this.add(toTimeP,gbc);
        gbc.gridy++;
        gbc.gridx=0;
        this.add(zhangmu,gbc);
        gbc.gridy++;
        gbc.gridx=1;
        this.add(yearL,gbc);
        gbc.gridx++;
        this.add(yearT,gbc);
        gbc.gridy++;
        this.add(confirmbt,gbc);
    }

    public void refresh(){
        fromTimeP.makeEmpty();
        toTimeP.makeEmpty();
        yearT.setText("");
    }

}
