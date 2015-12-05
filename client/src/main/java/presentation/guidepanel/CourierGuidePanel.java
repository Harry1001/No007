package presentation.guidepanel;

import presentation.Images.Images;
import presentation.commoncontainer.MyToggleButton;
import presentation.contentpanel.courierpanels.SendInfoPanel;
import presentation.contentpanel.senderpanels.LogisticPanel;
import presentation.contentpanel.courierpanels.ReceivePanel;
import presentation.contentpanel.courierpanels.SendPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/25.
 */
public class CourierGuidePanel extends GuidePanel implements ActionListener{

    private static final int BTNUMBER=3;

    MyToggleButton[] bts=new MyToggleButton [BTNUMBER];

    public CourierGuidePanel(JPanel content, JFrame par){
        super(content, par);

        bts[0] =new MyToggleButton("新建寄件单", Images.SEND_IMAGE);
        bts[1] =new MyToggleButton("新建收件单", Images.RECEIVE_IMAGE);
        bts[2]=new MyToggleButton("查询订单信息", Images.RECORD_IMAGE);


        for(int i=0;i<BTNUMBER;i++){
            bts[i].addActionListener(this);
        }

        this.setLayout(new GridBagLayout());
        addbts(bts);
        initContentPanels();

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bts[0]){
            ((CardLayout)content.getLayout()).show(content, "one");

        }
        else if(e.getSource()==bts[1]){
            ((CardLayout)content.getLayout()).show(content, "two");

        }
        else {
            ((CardLayout)content.getLayout()).show(content, "three");

        }
    }

    @Override
    protected void initContentPanels() {
        content.add("one", new SendPanel(parent));
        content.add("two",new ReceivePanel(parent));
        content.add("three", new SendInfoPanel(parent));
    }
}
