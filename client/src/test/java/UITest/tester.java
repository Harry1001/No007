package UITest;

import presentation.LoginPanel;

import javax.swing.*;

/**
 * Created by Harry on 2015/11/23.
 */
public class tester {
    public static void main(String [] argv){
        JFrame frame=new JFrame();
        frame.getContentPane().add(new LoginPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
