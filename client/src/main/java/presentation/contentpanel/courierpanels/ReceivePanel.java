package presentation.contentpanel.courierpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.StaffBLService;
import businessLogicService.logisticblservice.LogisticBLService;
import businessLogicService.transportblservice.ReceiveBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import myexceptions.TransportBLException;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
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
import java.text.ParseException;
import java.util.Date;

/**
 * 快递员新建收件单面板
 */
public class ReceivePanel extends JPanel implements ItemListener, ActionListener {

    private ReceiveBLService receiveBLService;
    private LogisticBLService logisticBLService;
    private StaffBLService staffBLService;
    private MainFrame parent;
    private MyLabel l1;
    private MyLabel l2;
    private MyLabel l3;
    private MyLabel l4;
    private MyLabel l5;
    private JRadioButton rbt1;
    private JRadioButton rbt2;
    private MyTextField t1;
    private MyTextField t2;
    private MyTextField t3;
    private MyTextField t4;

    private MyButton submitbt;
    private MyButton refreshbt;


    public ReceivePanel(MainFrame par) {
        this.parent=par;
        l1=new MyLabel("是否为代收");
        l2=new MyLabel("收件人姓名");
        l3=new MyLabel("代收人电话");
        l4=new MyLabel("收件编号");
        l5=new MyLabel("收件时间");
        t1=new MyTextField(15);
        t2=new MyTextField(15);
        t3=new MyTextField(15);
        t4=new MyTextField(15);
        rbt1=new JRadioButton("是");
        rbt2=new JRadioButton("否");
        submitbt=new MyButton("提交(S)");
        refreshbt=new MyButton("清空(R)");
        ButtonGroup btgroup=new ButtonGroup();
        btgroup.add(rbt1);
        btgroup.add(rbt2);

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(5,5,5,5);
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
        this.add(refreshbt,gbc);
        gbc.gridx=1;
        this.add(submitbt,gbc);

        setHotKey();

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"收件单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));

        rbt2.setSelected(true);
        t2.setEditable(false);
        rbt1.addItemListener(this);
        rbt2.addItemListener(this);
        submitbt.addActionListener(this);
        refreshbt.addActionListener(this);

        setDefaultTime();
        initBL();

    }

    private void setHotKey(){
        submitbt.setMnemonic('S');
        refreshbt.setMnemonic('R');
    }

    /**
     * 为界面层创建逻辑层引用
     */
    private void initBL(){
        receiveBLService= BLFactory.getReceiveBLService();
        try {
            staffBLService=BLFactory.getStaffBLService();
            logisticBLService=BLFactory.getLogisticBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    /**
     * 设置时间框为当前系统时间
     */
    private void setDefaultTime(){
        t4.setText(Constent.DATE_FORMAT.format(new Date()));
        //t4.setEditable(false);//时间框不可编辑
    }

    /**
     *  清空当前所有输入
     */
    private void refresh(){
        rbt2.setSelected(true);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        setDefaultTime();

    }

    /**
     * 单选按钮选择是否代收的处理，若非代收则将代收人电话清空并设置为不可输入
     */
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

    /**
     * 处理按钮点击事件
     * @param e
     */
    public void actionPerformed(ActionEvent e) {

        //提价按钮点击
        if (e.getSource()==submitbt){
            if ( (logisticBLService!=null) && (receiveBLService!=null)&&(staffBLService!=null)){
                try {
                    Date date= Constent.DATE_FORMAT.parse(t4.getText());
                    ReceiveReceiptVO vo=new ReceiveReceiptVO(t3.getText(), t1.getText(), date);
                    receiveBLService.verify(vo);
                    receiveBLService.submit(vo);
                    logisticBLService.update(parent.getUserIdentity().getId(), vo);
                    staffBLService.addWorkFrequency(parent.getUserIdentity().getId());
                    refresh();
                    new TranslucentFrame(this, MessageType.SUBMIT_SUCCESS, Color.GREEN);
                } catch (TransportBLException e1) {
                    new TranslucentFrame(this, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                } catch (MalformedURLException e1) {
                    new ErrorDialog(parent, "MalformedURLException");
                } catch (SQLException e1) {
                    System.out.println("提交收件单sql："+e1.getMessage());
                    if (e1.getMessage().substring(0,9).equals("Duplicate")){
                        new TranslucentFrame(this, "该订单已存在", Color.RED);
                    }
                } catch (NotBoundException e1) {
                    new ErrorDialog(parent, "NotBoundException");
                } catch (ParseException e1) {
                    new TranslucentFrame(this, "请不要改变默认时间格式", Color.RED);
                } catch (InfoBLException e1) {
                    new TranslucentFrame(this, e1.getMessage(), Color.RED);
                }
            }
            else {
                initBL();
            }
            //清空输入按钮点击
        } else if (e.getSource()==refreshbt){
            refresh();
        }
    }
}
