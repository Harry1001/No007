package MainFrame;

import businessLogic.strategybl.DistanceStrategyBL;
import presentation.uifactory.UIFactory;
import vo.loginvo.LoginResultVO;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;


/**
 * Created by Harry on 2015/11/23.
 */
public class MainFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginResultVO userIdentity=null;

    public static void main(String [] argv){
        /*
        //
        try {
            DistanceStrategyBL bl=new DistanceStrategyBL();
            bl.initDistance();
        } catch (MalformedURLException e) {
            System.out.println("挂了1");
        } catch (NotBoundException e) {
            System.out.println("挂了3");
        } catch (SQLException e) {
            System.out.println("挂了4: "+e.getMessage());
        } catch (RemoteException e) {
            System.out.println("挂了2");
        }catch (IOException e) {
            System.out.println("挂了5");
        }
        //
        */
        
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
