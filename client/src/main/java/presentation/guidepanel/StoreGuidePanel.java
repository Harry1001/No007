package presentation.guidepanel;

import presentation.Images.Images;
import presentation.commoncontainer.MyToggleButton;
import presentation.contentpanel.storepanels.DespatchPanel;
import presentation.contentpanel.storepanels.StoreArrivePanel;
import presentation.contentpanel.storepanels.TruckListPanel;
import presentation.contentpanel.*;
import presentation.contentpanel.storepanels.DriverListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/26.
 */
public class StoreGuidePanel extends GuidePanel implements ActionListener{
    private static final int BTNUMBER=6;

    MyToggleButton[] bts=new MyToggleButton [BTNUMBER];

    public StoreGuidePanel(JPanel content, JFrame par){
        super(content,par);

        bts[0] =new MyToggleButton("新建派件单", Images.DESPATCH_IMAGE);
        bts[1] =new MyToggleButton("新建装车单", Images.ENTRUCK_IMAGE);
        bts[2]=new MyToggleButton("新建营业厅到达单", Images.STORE_ARRIVE_IMAGE);
        bts[3]=new MyToggleButton("新建收款单", Images.CHARGE_IMAGE);
        bts[4]=new MyToggleButton("车辆信息管理", Images.TRUCK_IMAGE);
        bts[5]=new MyToggleButton("司机信息管理", Images.DRIVER_IMAGE);

        for(int i=0;i<BTNUMBER;i++){
            bts[i].addActionListener(this);
        }

        this.setLayout(new GridBagLayout());
        addbts(bts);
        initContentPanels();

    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==bts[0]){
            ((CardLayout)content.getLayout()).show(content,"one");
        }
        else if (e.getSource()==bts[1]){
            ((CardLayout)content.getLayout()).show(content,"two");
        }
        else if (e.getSource()==bts[2]){
            ((CardLayout)content.getLayout()).show(content,"three");
        }
        else if (e.getSource()==bts[3]){
            ((CardLayout)content.getLayout()).show(content,"four");
        }
        else if (e.getSource()==bts[4]){
            ((CardLayout)content.getLayout()).show(content,"five");
        }
        else if (e.getSource()==bts[5]){
            ((CardLayout)content.getLayout()).show(content,"six");
        }

    }

    @Override
    protected void initContentPanels() {
        content.add("one",new DespatchPanel(parent));
        content.add("two",new EntruckPanel(parent));
        content.add("three",new StoreArrivePanel(parent));
        content.add("four",new ChargeReceiptPanel(parent));
        content.add("five",new TruckListPanel(parent));
        content.add("six",new DriverListPanel(parent));
    }
}
