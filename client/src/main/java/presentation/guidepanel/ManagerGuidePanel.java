package presentation.guidepanel;

import MainFrame.MainFrame;
import presentation.Images.Images;
import presentation.commoncontainer.MyToggleButton;
import presentation.contentpanel.BaobiaoPanel;
import presentation.contentpanel.RecordListPanel;
import presentation.contentpanel.managerpanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/24.
 */
public class ManagerGuidePanel extends GuidePanel implements ActionListener {
    public static final int BTNUMBER=7;

    MyToggleButton[] bts=new MyToggleButton[BTNUMBER];

    public ManagerGuidePanel(JPanel content, MainFrame par){

        super(content,par);

        bts[0] =new MyToggleButton("审批单据", Images.RECEIPT_IMAGE);
        bts[1] =new MyToggleButton("薪水策略", Images.SALARY_IMAGE);
        bts[2]=new MyToggleButton("价格/距离策略", Images.MONEY_DISTANCE_IMAGE);
        bts[3]=new MyToggleButton("人员管理", Images.STAFF_IMAGE);
        bts[4]=new MyToggleButton("机构管理", Images.AGENCY_IMAGE);
        bts[5]=new MyToggleButton("系统日志", Images.RECORD_IMAGE);
        bts[6]=new MyToggleButton("报表查询", Images.BAOBIAO_IMAGE);

        for(int i=0;i<BTNUMBER;i++){
            bts[i].addActionListener(this);
        }

        this.setLayout(new GridBagLayout());
        addbts(bts);
        initContentPanels();
    }

    protected void initContentPanels(){
        content.add("one",new ShenPiPanel(parent));
        content.add("two",new SalaryListPanel(parent));
        content.add("three",new FeeStrategyPanel(parent));
        content.add("four",new StaffListPanel(parent));
        content.add("five",new AgencyListPanel(parent));
        content.add("six",new RecordListPanel(parent));
        content.add("seven",new BaobiaoPanel(parent));

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

        } else if (e.getSource()==bts[6]){
            ((CardLayout)content.getLayout()).show(content, "seven");
        }
    }
}
