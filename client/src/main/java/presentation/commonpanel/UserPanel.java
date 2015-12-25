package presentation.commonpanel;

import MainFrame.MainFrame;
import presentation.Images.Images;
import presentation.commoncontainer.MyLabel;
import presentation.uifactory.UIFactory;
import typeDefinition.Job;
import vo.loginvo.LoginResultVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/24.
 */
public class UserPanel extends JPanel implements ActionListener{

    //顺序不可换，用于确定job
    private static final String[] jobTable={"寄件人","快递员","营业厅业务员","中转中心业务员","财务人员","仓库管理员",
            "总经理","管理员","司机"};

    private MainFrame parent;
    private String userID;
    private Job userJob;//todo job这个枚举类不方便显示，建议改成string
    private String userName;
    private JButton logoutbt;

    public UserPanel(MainFrame par, LoginResultVO result){

        this.parent=par;
        this.userID=result.getId();
        this.userJob=result.getJob();
        this.userName=result.getName();

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        GridBagConstraints gbc=new GridBagConstraints();

        JLabel pic=new JLabel(Images.USERHEAD);
        MyLabel welcome=new MyLabel("欢迎您，"+userName+"!");
        MyLabel identity=new MyLabel("当前身份："/*+jobTable[userJob.ordinal()]*/);
        MyLabel identity1=new MyLabel(jobTable[userJob.ordinal()]);
        logoutbt=new JButton("退出 ");

        gbc.insets=new Insets(10, 10,10,20);
       // gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=2;
        this.add(pic, gbc);

        gbc.gridx=1;
        gbc.gridy=0;

        this.add(welcome,gbc);
        gbc.gridy=1;
        gbc.anchor=GridBagConstraints.SOUTH;
        this.add(identity,gbc);
//-------------------------------------------------
        gbc.gridy=2;
        gbc.anchor=GridBagConstraints.SOUTH;
        this.add(identity1,gbc);
//-------------------------------------------------
        gbc.gridx=0;
        gbc.gridy=3;
        this.add(logoutbt,gbc);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setOpaque(false);

        logoutbt.addActionListener(this);
    }

    /**
     * 点击退出按钮，退出当前帐号
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        UIFactory.showLoginPanel(parent);
    }
}
