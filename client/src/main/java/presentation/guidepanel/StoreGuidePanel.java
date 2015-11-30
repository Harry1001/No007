package presentation.guidepanel;

import presentation.Images.Images;
import presentation.contentpanel.TruckListPanel;
import presentation.contentpanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/26.
 */
public class StoreGuidePanel extends JPanel implements ActionListener{
    private static final int BTNUMBER=6;
    private JPanel content;
    private Frame parent;
    JToggleButton[] bts=new JToggleButton [BTNUMBER];

    public StoreGuidePanel(JPanel content, Frame par){
        this.content=content;
        this.parent=par;

        JLabel title=new JLabel("导航栏");
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        title.setFont(new Font("宋体",Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);

        bts[0] =new JToggleButton("新建派件单", Images.DESPATCH_IMAGE);
        bts[1] =new JToggleButton("新建装车单", Images.ENTRUCK_IMAGE);
        bts[2]=new JToggleButton("新建营业厅到达单", Images.STORE_ARRIVE_IMAGE);
        bts[3]=new JToggleButton("新建收款单", Images.CHARGE_IMAGE);
        bts[4]=new JToggleButton("车辆信息管理", Images.TRUCK_IMAGE);
        bts[5]=new JToggleButton("司机信息管理", Images.DRIVER_IMAGE);
        ButtonGroup btgroup=new ButtonGroup();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.weightx=1.0;
        gbc.weighty=0.0;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.fill=GridBagConstraints.HORIZONTAL;

        gbc.ipady=30;
        this.add(title,gbc);
        gbc.ipady=0;

        Font font=new Font("",Font.PLAIN,20);
        for(int i=0;i<BTNUMBER;i++){
            bts[i].setFont(font);
            bts[i].setPreferredSize(new Dimension(170,60));
            btgroup.add(bts[i]);
            gbc.gridy++;
            this.add(bts[i],gbc);
            bts[i].addActionListener(this);
        }

        content.add("one",new DespatchPanel(parent));
        content.add("two",new EntruckPanel(parent));
        content.add("three",new StoreArrivePanel(parent));
        content.add("four",new ChargeReceiptPanel(parent));
        content.add("five",new TruckListPanel(parent));
        content.add("six",new DriverListPanel(parent));

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
}
