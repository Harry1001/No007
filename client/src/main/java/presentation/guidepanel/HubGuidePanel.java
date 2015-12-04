package presentation.guidepanel;

import presentation.Images.Images;
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
public class HubGuidePanel extends JPanel implements ActionListener {
    private static final int BTNUMBER=3;
    private JPanel content;
    private Frame parent;
    JToggleButton[] bts=new JToggleButton [BTNUMBER];

    public HubGuidePanel(JPanel content, Frame par){
        this.content=content;
        this.parent=par;
        JLabel title=new JLabel("导航栏");
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        title.setFont(new Font("宋体",Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);


        bts[0] =new JToggleButton("中转中心到达单", Images.HUB_ARRIVE_IMAGE);
        bts[1] =new JToggleButton("中转单", Images.TRANSFER_IMAGE);
        bts[2]=new JToggleButton("装车单", Images.ENTRUCK_IMAGE);

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

        JLabel welcome=new JLabel("请点击左侧按钮选择功能",JLabel.CENTER);
        welcome.setFont(new Font("宋体",Font.BOLD,40));
        welcome.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        content.add("zero", welcome);
        content.add("one", new HubArrivePanel(parent));
        content.add("two",new TransferPanel(parent));
        content.add("three", new EntruckPanel(parent));

        ((CardLayout)content.getLayout()).show(content,"zero");
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

}
