package presentation.commonpanel;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.loginblservice.LoginBLService;
import constent.Constent;
import presentation.Images.Images;
import presentation.commoncontainer.*;
import presentation.uifactory.UIFactory;
import typeDefinition.Job;
import typeDefinition.MessageType;
import vo.loginvo.LoginInputVO;
import vo.loginvo.LoginResultVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Harry on 2015/11/23.
 */
public class LoginPanel extends JPanel implements ActionListener, FocusListener{

    private MainFrame parent;
    MyLabel numLabel=new MyLabel("工号");
    MyLabel passLabel=new MyLabel("密码");
    MyTextField numText=new MyTextField();
    JPasswordField passwordField=new JPasswordField();
    MyButton logButton=new MyButton("登录");
    MyButton logisticbt=new MyButton("查询物流信息");
    public LoginPanel(MainFrame par){
        this.parent=par;
        this.setLayout(new GridBagLayout());
        //this.setOpaque(false);//将login panel设为透明
        GridBagConstraints gbc =new GridBagConstraints();

        this.setPreferredSize(new Dimension(1000,700));

        //JLabel picLabel=new JLabel(Images.LOGIN_IMAGE);
        
        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();

        MyLabel label=new MyLabel("用户登录");
        JSeparator seph=new JSeparator();
        seph.setVisible(true);

        panel.setOpaque(false);
        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(10,10,10,10);
        c.fill=GridBagConstraints.BOTH;
        c.weightx=c.weighty=0.7;

        c.gridwidth=2;
        panel.add(label,c);
        c.gridy=1;
        panel.add(seph,c);

        c.weightx=c.weighty=0.0;
        c.gridy=2;
        c.gridwidth=1;
        panel.add(numLabel,c);
        c.gridx=1;
        c.weightx=c.weighty=0.7;
        panel.add(numText,c);
        c.gridx=0;
        c.gridy=3;
        c.weightx=c.weighty=0.0;
        panel.add(passLabel,c);
        c.gridx=1;
        c.weightx=c.weighty=0.7;
        panel.add(passwordField,c);
        c.gridy=4;
        c.fill=GridBagConstraints.NONE;
        c.weightx=c.weighty=0.0;
        panel.add(logButton,c);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setSize(new Dimension(250, 200));

        gbc.gridx=0;
        gbc.gridy=1;

        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);
        BlankBlock blankBlock=new BlankBlock();
        blankBlock.setSize(800, 50);
        this.add(blankBlock,gbc);

        gbc.gridx=2;

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(10,200,10,10);
        gbc.ipadx=70;
       // gbc.anchor=GridBagConstraints.WEST;
        this.add(panel,gbc);

        gbc.ipadx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(logisticbt, gbc);

        setHotKey();

        //numText.setText("请输入"+ Constent.USER_ID_LENGTH+"位数字");

        //注册监听事件
        logButton.addActionListener(this);
        logisticbt.addActionListener(this);
        numText.addFocusListener(this);
        passwordField.addActionListener(this);

        //this.getRootPane().setDefaultButton(logButton);//设置默认按钮
    }

    private void setHotKey(){
        logButton.setMnemonic('L');
        logisticbt.setMnemonic('I');
    }

    protected void paintComponent(Graphics g){
    	g.drawImage(Images.LOGIN_IMAGE, WIDTH, HEIGHT, this);
    }
    
    private void loginEvent(){
        LoginBLService loginBLService = BLFactory.getLoginBLService();
        LoginResultVO loginResult= null;
        try {
            loginResult = loginBLService.getPermission(new LoginInputVO(numText.getText(),new String(passwordField.getPassword())));
            if (loginResult.getJob()== Job.NOTFOUND){
                new TranslucentFrame(this, "用户名或密码错误", Color.RED);
            } else {
                UIFactory.showContentPanel(parent,loginResult);
            }
        } catch (RemoteException e1) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.RED);
        } catch (MalformedURLException e1) {
            System.out.println(e1.getMessage());
        } catch (NotBoundException e1) {
            System.out.println(e1.getMessage());
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==logButton){
            loginEvent();
        }else if (e.getSource()==logisticbt){
            UIFactory.showLogisticPanel(parent);
        } else if (e.getSource()==passwordField){
            loginEvent();
        }
    }

    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {
        if (e.getSource()==numText){
            if (validateID()){
                numText.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }else {
                numText.setBorder(BorderFactory.createLineBorder(Color.RED));
                new TranslucentFrame(this, "请输入"+Constent.USER_ID_LENGTH+"位数字帐号",Color.ORANGE);
            }
        }
    }

    /**
     * 验证工号位数是否正确
     * @return
     */
    private boolean validateID(){
        String id=numText.getText();
        if (id.length()!=Constent.USER_ID_LENGTH){
            return false;
        }
        for(int i=0;i<Constent.USER_ID_LENGTH;i++){
            if (id.charAt(i)<'0'||id.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }
}
