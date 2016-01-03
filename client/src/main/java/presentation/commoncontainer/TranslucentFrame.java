package presentation.commoncontainer;

import MainFrame.MainFrame;
import com.sun.awt.AWTUtilities;
import constent.Constent;
import typeDefinition.MessageType;

import javax.swing.*;
import java.awt.*;

/**
 * 逐渐自动隐藏的提示框
 */
public class TranslucentFrame implements Runnable {
    JFrame frame=new JFrame();
    JLabel messageLabel=new JLabel();

    private JPanel mainPanel;


    public TranslucentFrame(JPanel panel, MessageType type, Color color){
        this(panel, Constent.TIP_MESSAGE[type.ordinal()], color);
    }

    public TranslucentFrame(JPanel panel, String message, Color color){
        this.mainPanel=panel;

        initLabel(message, color);

        //运行线程
        Thread thread=new Thread(this);
        thread.start();
    }

    private void initLabel(String message, Color color){
        messageLabel.setText(message);
        messageLabel.setBackground(color);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("", Font.BOLD, 23));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(true);
    }


    /**
     * 设置渐隐窗口的相对位置
     */
    private void initLocation(){
        frame.setLocationRelativeTo(mainPanel);
        int x=frame.getX();
        int y=frame.getY();
        int width=mainPanel.getWidth();
        int height=mainPanel.getHeight();
        x=x-width/2;
        y=y+height/2-50;
        height=50;
        frame.setBounds(x,y,width,height);
    }

    private void print(){
        initLocation();
        frame.add(messageLabel);
        frame.setUndecorated(true); // 去掉窗口的装饰
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE); //窗体样式
//      frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG ); //窗体样式
        AWTUtilities.setWindowOpacity(frame, 0.01f);//初始化透明度
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);//窗体置顶
    }


    /**
     * 窗体逐渐变清晰
     *
     */
    public void show(){
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {}
            AWTUtilities.setWindowOpacity(frame, i*0.02f);
        }
    }

    /**
     * 窗体逐渐变淡直至消失
     *
     */
    public void hide(){
        float opacity=100;
        while(true){
            if(opacity<2){
                break;
            }
            opacity=opacity-2;
            AWTUtilities.setWindowOpacity(frame, opacity/100);
            try {
                Thread.sleep(10);
            } catch (Exception e1) {}
        }
        frame.dispose();
        //System.dispose();
    }

    public void run(){
        print();
        show();
        try {
            Thread.sleep(700);
        } catch (Exception e) {}
        hide();
    }
}
