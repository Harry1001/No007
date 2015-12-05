package presentation.uifactory;

import MainFrame.MainFrame;
import presentation.commonpanel.HeadPanel;
import presentation.commonpanel.LoginPanel;
import presentation.commonpanel.UserPanel;
import presentation.contentpanel.senderpanels.LogisticPanel;
import presentation.guidepanel.*;
import vo.loginvo.LoginResultVO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/5.
 */
public class UIFactory {
    public static void showLoginPanel(MainFrame frame){
        frame.setUserIdentity(null);
        Container contentPane=frame.getContentPane();
        contentPane.removeAll();//清除所有组件
        frame.setLayout(new BorderLayout());
        contentPane.add(new LoginPanel(frame));

        frame.revalidate();
    }

    public static void showContentPanel(MainFrame frame, LoginResultVO result){
        frame.setUserIdentity(result);
        Container contentPane=frame.getContentPane();
        contentPane.removeAll();//清除所有组件

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.gridx=0;
        gbc.gridy=0;
        contentPane.add(new UserPanel(frame, result),gbc);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=1;
        gbc.gridy=0;
        contentPane.add(new HeadPanel(),gbc);

        JPanel contentp=new JPanel(new CardLayout());
        contentp.setPreferredSize(new Dimension(800,500));

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=1;
        contentPane.add(createGuidePanel(result,contentp,frame),gbc);

        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        contentPane.add(contentp,gbc);
        frame.revalidate();
    }

    public static void showLogisticPanel(MainFrame frame){
        frame.setUserIdentity(null);
        Container contentPane=frame.getContentPane();
        contentPane.removeAll();//清除所有组件
        frame.setLayout(new BorderLayout());
        contentPane.add(new LogisticPanel(frame));

        frame.revalidate();
    }

    /**
     * 根据不同的职位创建不同的guidepanel
     * @param vo 登录结果，内部包含权限信息
     * @return
     */
    private static GuidePanel createGuidePanel(LoginResultVO vo, JPanel content, JFrame par){
        switch (vo.getJob()){

            case COURIER:return new CourierGuidePanel(content,par);

            case STORESALESMAN:return new StoreGuidePanel(content,par);

            case HUBSALESMAN:return new HubGuidePanel(content,par);

            case ACCOUNTANT:return new FinanceGuidePanel(content,par);

            case STOREKEEPER:return new DepotGuidePanel(content,par);

            case MANAGER:return new ManagerGuidePanel(content,par);

            case ADMINISTRATOR:return new AdministratorGuidePanel(content,par);

            default:return null;
        }
    }
}
