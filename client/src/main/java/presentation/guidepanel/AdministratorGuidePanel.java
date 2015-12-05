package presentation.guidepanel;

import presentation.Images.Images;
import presentation.commoncontainer.MyToggleButton;
import presentation.contentpanel.administratorpanels.UserAccountListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/28.
 */
public class AdministratorGuidePanel extends GuidePanel implements ActionListener {

    private static final int BTNUMBER=1;

    MyToggleButton[] bts=new MyToggleButton [BTNUMBER];

    public AdministratorGuidePanel(JPanel content, JFrame par){
        super(content, par);

        bts[0] =new MyToggleButton("用户账户管理", Images.USER_ACCOUNT_IMAGE);

        this.setLayout(new GridBagLayout());
        addbts(bts);
        initContentPanels();

    }

    @Override
    protected void initContentPanels() {
        content.add("one",new UserAccountListPanel(parent));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==bts[0]){
            ((CardLayout)content.getLayout()).show(content,"one");
        }
    }
}
