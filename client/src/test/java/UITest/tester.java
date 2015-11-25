package UITest;

import presentation.*;
import presentation.Images.Images;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/23.
 */
public class tester {
    public static void main(String [] argv){
        JFrame frame=new JFrame();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();

        gbc.weighty=0.0;
        gbc.weightx=0.0;

        //frame.getContentPane().add(new ReceivePanel(frame),gbc);
        JPanel userp=new UserPanel("董本超","快递员");
        JLabel headl=new HeadLabel();
        JPanel contentp=new JPanel(new CardLayout());
        contentp.setPreferredSize(new Dimension(600,500));



        JPanel guidep=new CourierGuidePanel(contentp, frame);

        Container contentPane=frame.getContentPane();

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.gridx=0;
        gbc.gridy=0;
        contentPane.add(userp,gbc);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=1;
        gbc.gridy=0;
        contentPane.add(headl,gbc);

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=1;
        contentPane.add(guidep,gbc);

        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        contentPane.add(contentp,gbc);

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
