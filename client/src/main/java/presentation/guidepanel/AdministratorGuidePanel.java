package presentation.guidepanel;

import presentation.Images.Images;
import presentation.contentpanel.administratorpanels.UserAccountListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/28.
 */
public class AdministratorGuidePanel extends JPanel implements ActionListener {

    private static final int BTNUMBER=1;
    private JPanel content;
    private Frame parent;
    JToggleButton[] bts=new JToggleButton [BTNUMBER];

    public AdministratorGuidePanel(JPanel content, Frame par){
        this.content=content;
        this.parent=par;

        JLabel title=new JLabel("导航栏");
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        title.setFont(new Font("宋体",Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);

        bts[0] =new JToggleButton("用户账户管理", Images.USER_ACCOUNT_IMAGE);


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

        content.add("one",new UserAccountListPanel(parent));

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==bts[0]){
            ((CardLayout)content.getLayout()).show(content,"one");
        }
    }
}
