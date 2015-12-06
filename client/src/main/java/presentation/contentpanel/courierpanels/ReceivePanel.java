package presentation.contentpanel.courierpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.transportblservice.ReceiveBLService;
import myexceptions.TransportBLException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commonpanel.ErrorDialog;
import vo.receiptvo.ReceiveReceiptVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 快递员新建收件单面板
 */
public class ReceivePanel extends JPanel implements ItemListener, ActionListener {

    private ReceiveBLService receiveBLService;
    MainFrame parent;
    MyLabel l1;
    MyLabel l2;
    MyLabel l3;
    MyLabel l4;
    MyLabel l5;
    JRadioButton rbt1;
    JRadioButton rbt2;
    MyTextField t1;
    MyTextField t2;
    MyTextField t3;
    MyTextField t4;

    MyButton submitbt;

    public ReceivePanel(MainFrame par) {
        this.parent=par;
        l1=new MyLabel("是否为代收");
        l2=new MyLabel("收件人姓名");
        l3=new MyLabel("收件人电话");
        l4=new MyLabel("收件编号");
        l5=new MyLabel("收件时间");
        t1=new MyTextField(15);
        t2=new MyTextField(15);
        t3=new MyTextField(15);
        t4=new MyTextField(15);
        rbt1=new JRadioButton("是");
        rbt2=new JRadioButton("否");
        submitbt=new MyButton("提交");
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(rbt1);
        btgroup.add(rbt2);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=0;
        gbc.gridy=0;
        this.add(l1,gbc);
        gbc.gridx=1;
        this.add(rbt1,gbc);
        gbc.gridx=2;
        this.add(rbt2,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        this.add(l2,gbc);
        gbc.gridy=2;
        this.add(l3,gbc);
        gbc.gridy=3;
        this.add(l4,gbc);
        gbc.gridy=4;
        this.add(l5,gbc);

        gbc.gridx=1;
        gbc.gridwidth=2;
        gbc.gridy=1;
        this.add(t1,gbc);
        gbc.gridy=2;
        this.add(t2,gbc);
        gbc.gridy=3;
        this.add(t3,gbc);
        gbc.gridy=4;
        this.add(t4,gbc);

        gbc.gridx=2;
        gbc.gridy=5;
        gbc.gridwidth=1;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.NONE;
        this.add(submitbt,gbc);

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"收件单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));

        rbt1.setSelected(true);
        rbt1.addItemListener(this);
        rbt2.addItemListener(this);
        submitbt.addActionListener(this);

        setDefaultTime();
        initBL();

    }

    private void initBL(){
        receiveBLService= BLFactory.getReceiveBLService();
    }

    private void setDefaultTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间格式
        t4.setText(df.format(new Date()));
        t4.setEditable(false);//时间框不可编辑
    }

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==e.SELECTED){
            if (e.getSource()==rbt1){//代收
                t2.setEditable(true);
            }
            else if (e.getSource()==rbt2){
                t2.setText("");
                t2.setEditable(false);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){

            ReceiveReceiptVO vo=new ReceiveReceiptVO(t3.getText(), t1.getText(), new Date());//todo 此处直接取系统时间

            try {
                receiveBLService.verify(vo);
                receiveBLService.submit(vo);
            } catch (TransportBLException e1) {
                new ErrorDialog(parent, e1.getMessage());
            } catch (RemoteException e1) {
                new ErrorDialog(parent, "服务器连接超时");
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            }


        }
    }
}
