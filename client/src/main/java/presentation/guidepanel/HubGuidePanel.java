package presentation.guidepanel;

import presentation.Images.Images;
import presentation.commoncontainer.MyToggleButton;
import presentation.contentpanel.*;
import presentation.contentpanel.hubpanels.HubArrivePanel;
import presentation.contentpanel.hubpanels.TransferPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/28.
 */
public class HubGuidePanel extends GuidePanel implements ActionListener {
    private static final int BTNUMBER=3;

    MyToggleButton[] bts=new MyToggleButton [BTNUMBER];

    public HubGuidePanel(JPanel content, JFrame par){
        super(content,par);

        bts[0] =new MyToggleButton("中转中心到达单", Images.HUB_ARRIVE_IMAGE);
        bts[1] =new MyToggleButton("中转单", Images.TRANSFER_IMAGE);
        bts[2]=new MyToggleButton("装车单", Images.ENTRUCK_IMAGE);

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
        else if (e.getSource()==bts[2]){
            ((CardLayout)content.getLayout()).show(content, "three");

        }
    }

    @Override
    protected void initContentPanels() {
        content.add("one", new HubArrivePanel(parent));
        content.add("two",new TransferPanel(parent));
        content.add("three", new EntruckPanel(parent));
    }
}
