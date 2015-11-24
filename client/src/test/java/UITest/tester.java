package UITest;

import presentation.HeadLabel;
import presentation.Images.Images;
import presentation.LoginPanel;
import presentation.ManagerGuidePanel;
import presentation.UserPanel;

import javax.swing.*;

/**
 * Created by Harry on 2015/11/23.
 */
public class tester {
    public static void main(String [] argv){
        JFrame frame=new JFrame();
        frame.getContentPane().add(new ManagerGuidePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        }catch (Exception e){
            System.out.println("look and feel set fail");
        }

        frame.pack();
        frame.setVisible(true);
    }
}
