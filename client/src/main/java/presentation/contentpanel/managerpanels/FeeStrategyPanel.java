package presentation.contentpanel.managerpanels;


import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.strategyblservice.FeeStrategyBLService;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.strategyvo.CarriageFeeVO;
import vo.strategyvo.ExpressFeeVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/11/29.
 */
public class FeeStrategyPanel extends JPanel implements ActionListener {

    protected MainFrame parent;
    protected JPanel paypanel=new JPanel();
    protected JPanel chargepanel= new JPanel();
    protected MyLabel[] labels=new MyLabel[6];
    protected MyTextField[] textFields=new MyTextField[6];
    protected MyButton confirmbt=new MyButton("确定(O)");
    protected MyButton cancelbt=new MyButton("取消(C)");

    protected FeeStrategyBLService feeService;

    public FeeStrategyPanel(MainFrame par){
        this.parent=par;

        initUI();
        setHotKey();
        confirmbt.addActionListener(this);
        cancelbt.addActionListener(this);

        initBL();
        refreshData();
    }

    private void setHotKey(){
        confirmbt.setMnemonic('O');
        cancelbt.setMnemonic('C');
    }

    private void initBL(){
        try {
            feeService=BLFactory.getFeeBLService();
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initUI(){
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"价格距离策略",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));

        labels[0]=new MyLabel("汽车");
        labels[1]=new MyLabel("火车");
        labels[2]=new MyLabel("飞机");
        labels[3]=new MyLabel("经济快递");
        labels[4]=new MyLabel("标准快递");
        labels[5]=new MyLabel("特快快递");

        for(int i=0;i<6;i++){
            textFields[i]=new MyTextField(15);
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        paypanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3),
                "支出价格/距离策略(元/吨/公里)"));
        chargepanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,3),
                "收入价格/距离策略(元/公斤/公里)"));

        chargepanel.setLayout(new GridBagLayout());
        paypanel.setLayout(new GridBagLayout());
        paypanel.setOpaque(false);
        chargepanel.setOpaque(false);

        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<3;gbc.gridy++){
            paypanel.add(labels[gbc.gridy],gbc);
            chargepanel.add(labels[gbc.gridy+3],gbc);
        }
        gbc.gridx=1;
        for(gbc.gridy=0;gbc.gridy<3;gbc.gridy++){
            paypanel.add(textFields[gbc.gridy],gbc);
            chargepanel.add(textFields[gbc.gridy+3],gbc);
        }

        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=2;
        this.add(paypanel,gbc);
        gbc.gridy=1;
        this.add(chargepanel,gbc);
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridy=3;
        this.add(confirmbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);
    }

    /**
     * 载入数据
     */
    public void refreshData(){
        try {
            CarriageFeeVO carriageFeeVO=feeService.getCarriageFee();
            ExpressFeeVO expressFeeVO=feeService.getExpressFee();
            textFields[0].setText(""+carriageFeeVO.getPlanePrice());
            textFields[1].setText(""+carriageFeeVO.getTrainPrice());
            textFields[2].setText(""+carriageFeeVO.getBusPrice());
            textFields[3].setText(""+expressFeeVO.getEcoPrice());
            textFields[4].setText(""+expressFeeVO.getStdPrice());
            textFields[5].setText(""+expressFeeVO.getSpePrice());

        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkInput(){
        for (int i=0;i<6;i++){
            String s=textFields[i].getText();
            if (!checkNumber(s)){
                return false;
            }
        }
        return true;
    }

    private boolean checkNumber(String s){
        try{
            double n= Double.parseDouble(s);
            return (n>=0.0);
        }catch (NumberFormatException e){
            return false;
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            if (checkInput()){
                if (feeService!=null){
                    double bus=Double.parseDouble(textFields[0].getText());
                    double train=Double.parseDouble(textFields[1].getText());
                    double plane=Double.parseDouble(textFields[2].getText());
                    double economic=Double.parseDouble(textFields[3].getText());
                    double standard=Double.parseDouble(textFields[4].getText());
                    double special=Double.parseDouble(textFields[5].getText());

                    CarriageFeeVO carriageFeeVO=new CarriageFeeVO(plane, train, bus);
                    ExpressFeeVO expressFeeVO=new ExpressFeeVO(economic, standard, special);
                    try {
                        feeService.setCarriage(carriageFeeVO);
                        feeService.setExpressFee(expressFeeVO);
                        new TranslucentFrame(this, MessageType.MODIFY_SUCCESS, Color.GREEN);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    }
                }
                else {
                    initBL();
                }
            } else {
                new TranslucentFrame(this, "所有输入必须为正数", Color.RED);
            }
        } else if (e.getSource()==cancelbt){
            refreshData();//不保存，重新载入数据
            new TranslucentFrame(this, "刷新成功", Color.GREEN);
        }
    }
}
