package presentation.guidepanel;

import MainFrame.MainFrame;
import presentation.Images.Images;
import presentation.commoncontainer.MyToggleButton;
import presentation.contentpanel.depotpanels.DepotChaKanPanel;
import presentation.contentpanel.depotpanels.DepotInPanel;
import presentation.contentpanel.depotpanels.DepotOutPanel;
import presentation.contentpanel.depotpanels.DepotPanDianPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/5.
 */
public class DepotGuidePanel extends GuidePanel implements ActionListener{

    public static final int BTNUMBER=4;

    MyToggleButton[] bts=new MyToggleButton[BTNUMBER];

    public DepotGuidePanel(JPanel content, MainFrame par) {
        super(content, par);

        bts[0] =new MyToggleButton("库存入库", Images.DEPOTIN_IMAGE);
        bts[1] =new MyToggleButton("库存出库", Images.DEPOTOUT_IMAGE);
        bts[2]=new MyToggleButton("库存查看", Images.DEPOT_CHAKAN_IMAGE);
        bts[3]=new MyToggleButton("库存盘点", Images.DEPOT_PANDIAN_IMAGE);

        for(int i=0;i<BTNUMBER;i++){
            bts[i].addActionListener(this);
        }

        this.setLayout(new GridBagLayout());
        addbts(bts);
        initContentPanels();
    }

    @Override
    protected void initContentPanels() {
        content.add("one",new DepotInPanel(parent));
        content.add("two",new DepotOutPanel(parent));
        content.add("three",new DepotChaKanPanel(parent));
        content.add("four",new DepotPanDianPanel(parent));
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

        }
    }
}
