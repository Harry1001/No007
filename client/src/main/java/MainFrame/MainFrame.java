package MainFrame;

import presentation.uifactory.UIFactory;
import vo.loginvo.LoginResultVO;

import javax.swing.*;


/**
 * Created by Harry on 2015/11/23.
 */
public class MainFrame extends JFrame {

    private LoginResultVO userIdentity=null;

    public static void main(String [] argv){

        MainFrame frame=new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try{
            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        }catch (Exception e){
            System.out.println("look and feel set fail");
        }

        UIFactory.showLoginPanel(frame);
        frame.pack();
        frame.setVisible(true);
    }


    public LoginResultVO getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(LoginResultVO userIdentity) {
        this.userIdentity = userIdentity;
    }
}
