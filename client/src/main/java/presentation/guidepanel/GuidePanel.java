package presentation.guidepanel;

import MainFrame.MainFrame;
import presentation.commoncontainer.MyToggleButton;
import presentation.commonpanel.GuideLabel;
import presentation.commonpanel.WelcomePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/5.
 */
public abstract class GuidePanel extends JPanel {

    protected JPanel content;
    protected MainFrame parent;
    private GridBagConstraints gbc;
    private ButtonGroup btgroup=new ButtonGroup();

    public GuidePanel(JPanel content, MainFrame par){
        this.content=content;
        this.setOpaque(false);
        this.parent=par;
        this.gbc=new GridBagConstraints();
        gbc.weightx=1.0;
        gbc.weighty=0.0;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.fill=GridBagConstraints.HORIZONTAL;

        //加入“导航栏”label
        gbc.ipady=30;
        this.add(new GuideLabel(),gbc);
        gbc.ipady=0;

        gbc.gridy=1;
        showWelcome();
    }

    /**
     * 显示“点击左侧按钮选择功能”的欢迎界面
     */
    private void showWelcome(){
        content.add("zero", new WelcomePanel());
        ((CardLayout)content.getLayout()).show(content,"zero");
    }

    /**
     * 为每个人员的具体guidepanel子类添加功能按钮提供接口
     */
    public void addbt(MyToggleButton bt){
        add(bt,gbc);
        gbc.gridy++;
        btgroup.add(bt);
    }

    public void addbts(MyToggleButton[] bts){
        for(MyToggleButton bt:bts){
            addbt(bt);
        }
    }

    /**
     * 向内容面板里加入待切换的panel，根据具体子类决定加哪些panel
     */
    protected abstract void initContentPanels();
}
