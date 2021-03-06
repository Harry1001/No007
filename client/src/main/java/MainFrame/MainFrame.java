package MainFrame;

import businessLogic.strategybl.DistanceStrategyBL;
import dataService._RMI;
import presentation.Images.Images;
import presentation.uifactory.UIFactory;
import vo.loginvo.LoginResultVO;

import javax.swing.*;
import java.awt.*;
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

    public MainFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        this.userIdentity = userIdentity;
    }

    public MainFrame(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String [] argv){
        /*
        //
        try {
            DistanceStrategyBL bl=new DistanceStrategyBL();
            bl.initDistance();
            System.out.println("距离初始化成功");
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

        _RMI.setIP();
        MainFrame frame=new MainFrame("快递物流系统");
        JPanel bkPanel=(JPanel)frame.getContentPane();
        bkPanel.setOpaque(false);
        JLabel bkLabel=new JLabel(Images.BACKGROUND_IMAGE);
        bkLabel.setBounds(0,0,Images.BACKGROUND_IMAGE.getIconWidth(),Images.BACKGROUND_IMAGE.getIconHeight());

        frame.getLayeredPane().setLayout(null);
        frame.getLayeredPane().add(bkLabel, new Integer(Integer.MIN_VALUE));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
