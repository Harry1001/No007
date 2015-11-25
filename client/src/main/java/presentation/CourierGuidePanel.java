package presentation;

import presentation.Images.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/25.
 */
public class CourierGuidePanel extends JPanel implements ActionListener{

    private static final int BTNUMBER=3;
    private JPanel content;
    private Frame parent;
    JToggleButton[] bts=new JToggleButton [BTNUMBER];

    public CourierGuidePanel(JPanel content, Frame par){
        this.content=content;
        this.parent=par;
        JLabel title=new JLabel("导航栏");
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        title.setFont(new Font("宋体",Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);


        bts[0] =new JToggleButton("新建寄件单", Images.SEND_IMAGE);
        bts[1] =new JToggleButton("新建收件单", Images.RECEIVE_IMAGE);
        bts[2]=new JToggleButton("查询订单信息", Images.RECORD_IMAGE);
        bts[0].addActionListener(this);
        bts[1].addActionListener(this);
        bts[2].addActionListener(this);

        ButtonGroup btgroup=new ButtonGroup();


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.weightx=1.0;
        gbc.weighty=0.0;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.fill=GridBagConstraints.HORIZONTAL;


        this.add(title,gbc);
        for(int i=0;i<BTNUMBER;i++){
            bts[i].setPreferredSize(new Dimension(170,60));
            btgroup.add(bts[i]);
            gbc.gridy++;
            this.add(bts[i],gbc);
        }

        JLabel welcome=new JLabel("请点击左侧按钮选择功能",JLabel.CENTER);
        welcome.setFont(new Font("宋体",Font.BOLD,40));
        welcome.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        content.add("zero", welcome);
        content.add("one", new SendPanel(parent));
        content.add("two",new ReceivePanel(parent));
        content.add("three", new LogisticPanel(parent));

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
