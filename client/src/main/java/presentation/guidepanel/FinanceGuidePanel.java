package presentation.guidepanel;

import MainFrame.MainFrame;
import presentation.Images.Images;
import presentation.commoncontainer.MyToggleButton;
import presentation.contentpanel.BaobiaoPanel;
import presentation.contentpanel.RecordListPanel;
import presentation.contentpanel.financepanels.BankAccountPanel;
import presentation.contentpanel.financepanels.IncomePanel;
import presentation.contentpanel.financepanels.MakeBillPanel;
import presentation.contentpanel.financepanels.PayReceiptPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/5.
 */
public class FinanceGuidePanel extends GuidePanel implements ActionListener{

    public static final int BTNUMBER=6;

    MyToggleButton[] bts=new MyToggleButton[BTNUMBER];

    public FinanceGuidePanel(JPanel content, MainFrame par){

        super(content,par);

        bts[0] =new MyToggleButton("收入管理", Images.SHOURU_IMAGE);
        bts[1] =new MyToggleButton("成本管理", Images.CHENBEN_IMAGE);
        bts[2]=new MyToggleButton("期初建账", Images.QICHU_IMAGE);
        bts[3]=new MyToggleButton("报表查询", Images.BAOBIAO_IMAGE);
        bts[4]=new MyToggleButton("银行账户管理", Images.BANK_ACCOUNT_IMAGE);
        bts[5]=new MyToggleButton("系统日志", Images.RECORD_IMAGE);

        for(int i=0;i<BTNUMBER;i++){
            bts[i].addActionListener(this);
        }

        this.setLayout(new GridBagLayout());
        addbts(bts);
        initContentPanels();
    }
    @Override
    protected void initContentPanels() {
        content.add("one",new IncomePanel(parent));
        content.add("two",new PayReceiptPanel(parent));
        content.add("three",new MakeBillPanel(parent));
        content.add("four",new BaobiaoPanel(parent));
        content.add("five",new BankAccountPanel(parent));
        content.add("six",new RecordListPanel(parent));
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bts[0]){
            ((CardLayout)content.getLayout()).show(content, "one");

        } else if(e.getSource()==bts[1]){
            ((CardLayout)content.getLayout()).show(content, "two");

        } else if (e.getSource()==bts[2]){
            ((CardLayout)content.getLayout()).show(content, "three");

        } else if (e.getSource()==bts[3]){
            ((CardLayout)content.getLayout()).show(content, "four");

        }else if(e.getSource()==bts[4]){
            ((CardLayout)content.getLayout()).show(content, "five");

        } else if (e.getSource()==bts[5]){
            ((CardLayout)content.getLayout()).show(content, "six");

        }
    }
}
