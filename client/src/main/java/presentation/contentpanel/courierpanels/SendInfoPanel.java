package presentation.contentpanel.courierpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.transportblservice.SendBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.ErrorDialog;
import vo.receiptvo.SendReceiptVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/12/2.
 */
public class SendInfoPanel extends JPanel implements ActionListener {
    MainFrame parent;
    MyLabel[] labels=new MyLabel[16];
    MyTextField[] texts=new MyTextField[16];
    MyLabel inputL=new MyLabel("请输入订单号");
    MyTextField inputT=new MyTextField(15);
    MyButton confirmbt=new MyButton("确认");

    SendBLService sendBLService;

    public SendInfoPanel(MainFrame par){

        this.parent=par;
        labels[0]=new MyLabel("姓名");
        labels[1]=new MyLabel("住址");
        labels[2]=new MyLabel("单位");
        labels[3]=new MyLabel("手机");
        labels[4]=new MyLabel("姓名");
        labels[5]=new MyLabel("住址");
        labels[6]=new MyLabel("单位");
        labels[7]=new MyLabel("手机");
        labels[8]=new MyLabel("原件数");
        labels[9]=new MyLabel("实际重量");
        labels[10]=new MyLabel("体积");
        labels[11]=new MyLabel("内件品名");
        labels[12]=new MyLabel("快递类型");
        labels[13]=new MyLabel("包装类型");
        labels[14]=new MyLabel("订单条形码");
        labels[15]=new MyLabel("报价");

        for(int i=0;i<16;i++){
            texts[i]=new MyTextField(15);
            texts[i].setEditable(false);
        }


        JPanel panel1=new JPanel(new GridBagLayout());
        panel1.setOpaque(false);
        panel1.setBorder(BorderFactory.createTitledBorder("寄件人信息"));
        JPanel panel2=new JPanel(new GridBagLayout());
        panel2.setOpaque(false);
        panel2.setBorder(BorderFactory.createTitledBorder("收件人信息"));
        JPanel panel3=new JPanel(new GridBagLayout());
        panel3.setOpaque(false);
        panel3.setBorder(BorderFactory.createTitledBorder("托运货物信息"));

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(5,5,5,5);
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.gridx=0;

        for(int i=0;i<4;i++){
            gbc.gridy=i;
            panel1.add(labels[i],gbc);
            panel2.add(labels[i+4],gbc);
            panel3.add(labels[i+8],gbc);
        }

        gbc.gridx=1;
        for(int i=0;i<4;i++){
            gbc.gridy=i;
            panel1.add(texts[i],gbc);
            panel2.add(texts[i+4],gbc);
            panel3.add(texts[i+8],gbc);
        }

        gbc.gridx=2;
        for(int i=0;i<4;i++){
            gbc.gridy=i;
            panel3.add(labels[i+12],gbc);
        }

        gbc.gridx=3;
        for(int i=0;i<4;i++){
            gbc.gridy=i;
            panel3.add(texts[i+12],gbc);
        }

        gbc.gridy=0;
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        this.add(inputL,gbc);
        gbc.gridx=1;
        this.add(inputT,gbc);
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridx=2;
        this.add(confirmbt,gbc);

        gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.gridx=0;
        gbc.gridy=1;
        this.add(panel1,gbc);
        gbc.gridx=2;
        this.add(panel2,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=4;
        this.add(panel3,gbc);

        confirmbt.addActionListener(this);
        initBL();
    }

    /**
     * 检查订单号是否合法
     * @param s
     * @return
     */
    private boolean checkOrderID(String s){
        if (s.length()!= Constent.ORDER_ID_LENGTH)
            return false;
        for (int i=0;i<Constent.ORDER_ID_LENGTH;i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9')
                return false;
        }
        return true;
    }

    /**
     * 建立逻辑层引用
     */
    private void initBL(){
        sendBLService= BLFactory.getSendBLService();
    }

    private void loadData(SendReceiptVO vo){
        texts[0].setText(vo.getSenderName());
        texts[1].setText(vo.getSenderLoc());
        texts[2].setText(vo.getSenderUnit());
        texts[3].setText(vo.getSenderPhone());
        texts[4].setText(vo.getReceiverName());
        texts[5].setText(vo.getReceiverLoc());
        texts[6].setText(vo.getReceiverUnit());
        texts[7].setText(vo.getReceiverPhone());
        texts[8].setText(vo.getNumber()+"");
        texts[9].setText(vo.getWeight() + "");
        texts[10].setText(vo.getVolume() + "");
        texts[11].setText(vo.getName());
        texts[12].setText(vo.getExpressType());
        texts[13].setText(vo.getPack());
        texts[14].setText(vo.getExpressNumber());
        texts[15].setText(vo.getMoney()+"");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            if (sendBLService!=null){
                String id=inputT.getText();
                if (checkOrderID(id)){
                    try {
                        SendReceiptVO vo = sendBLService.getSendReceipt(id);
                        if (vo!=null)
                            loadData(vo);
                        else
                            new ErrorDialog(parent, "此订单不存在");
                    } catch (RemoteException e1) {
                        new ErrorDialog(parent, "服务器连接超时");
                    } catch (SQLException e1) {
                        System.out.println("查询寄件单信息sql："+e1.getMessage());
                        new ErrorDialog(parent, "SQLException");
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    } catch (NotBoundException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    new ErrorDialog(parent, "订单号必须为"+Constent.ORDER_ID_LENGTH+"位数字");
                }
            }
            else {
                initBL();
            }
        }
    }
}
