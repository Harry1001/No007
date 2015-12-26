package presentation.contentpanel;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.financeblservice.FinanceBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import myexceptions.TransportBLException;
import presentation.commoncontainer.*;
import vo.receiptvo.ChargeReceiptVO;

import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Harry on 2015/11/27.
 */
public class ChargeReceiptPanel extends JPanel implements ActionListener{
    MainFrame parent;

    MyLabel timeL=new MyLabel("收款日期");
    MyLabel moneyL=new MyLabel("收款金额");
    MyLabel courierL=new MyLabel("收款快递员工号");

    MyTextField timeT=new MyTextField();
    MyTextField moneyT=new MyTextField();
    MyTextField courierT=new MyTextField();
    MyTextField orderNumT=new MyTextField();

    MyButton appendbt=new MyButton("添加");
    MyButton deletebt=new MyButton("删除");
    MyButton submitbt=new MyButton("提交");
    MyButton cleanbt=new MyButton("清空输入");

    MyDefaultTableModel defaultTableModel;
    JTable table;

    FinanceBLService financeBLService;

    public ChargeReceiptPanel(MainFrame par){
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"收款单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        initUI();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//单选模式

        appendbt.addActionListener(this);
        deletebt.addActionListener(this);
        submitbt.addActionListener(this);
        cleanbt.addActionListener(this);

        initBL();
        setPresentTime();
    }

    private void initBL(){
        try {
            financeBLService=BLFactory.getFinanceBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        } catch (NamingException e) {
            new ErrorDialog(parent, "NamingException");
        }
    }

    private void setPresentTime(){
        String time= Constent.BIRTHDAY_FORMAT.format(new Date());
        timeT.setText(time);
    }

    private boolean checkOrderID(){
        String id=orderNumT.getText();
        if (id.length()!=Constent.ORDER_ID_LENGTH){
            return false;
        }
        for (int i=0;i<Constent.ORDER_ID_LENGTH;i++){
            if (id.charAt(i)<'0'||id.charAt(i)>'9') {
                return false;
            }
        }
        return true;
    }

    private boolean checkTime(){
        String time=timeT.getText();
        try{
            Constent.BIRTHDAY_FORMAT.parse(time);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkMoney(){
        String money=moneyT.getText();
        try{
            double fee=Double.parseDouble(money);
            if (fee<=0){
                return  false;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private boolean checkCourier(){
        String person=courierT.getText();
        if (person.length()<=0){
            return false;
        }
        return true;
    }

    private void checkAll() throws TransportBLException {
        if (!checkCourier()) throw new TransportBLException("快递员信息不可为空");

        if (!checkMoney()) throw new TransportBLException("收款金额必须为正数");

        if (!checkTime()) throw new TransportBLException("时间格式为: yy-MM-dd");

    }

    /**
     * 清空输入
     */
    private void refresh(){
        setPresentTime();
        moneyT.setText("");
        courierT.setText("");
        orderNumT.setText("");
        defaultTableModel.getDataVector().clear();
        table.revalidate();
        table.updateUI();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==appendbt){
            if (checkOrderID()){
                String [] s={orderNumT.getText()};
                defaultTableModel.addRow(s);
            }
            else {
                new ErrorDialog(parent, "订单号必须为"+Constent.ORDER_ID_LENGTH+"位整数");
            }
           // System.out.println(defaultTableModel.getRowCount());
        }
        else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row>-1) {
                defaultTableModel.removeRow(row);
            }

          //  System.out.println(defaultTableModel.getRowCount());
        }
        else if (e.getSource()==submitbt){
            if (financeBLService!=null){
                try {
                    checkAll();
                    Date time=Constent.BIRTHDAY_FORMAT.parse(timeT.getText());
                    double money=Double.parseDouble(moneyT.getText());
                    String courier=courierT.getText();
                    ArrayList<String> orderIDs=new ArrayList<String>();
                    {
                        int row=table.getRowCount();
                        for (int i=0;i<row;i++){
                            orderIDs.add((String)table.getValueAt(i, 0));
                        }
                    }
                    ChargeReceiptVO vo=new ChargeReceiptVO(time, money, courier, orderIDs);
                    financeBLService.submitIn(vo);
                    refresh();
                } catch (ParseException e1) {
                    new ErrorDialog(parent, "时间格式为: yy-MM-dd");
                } catch (TransportBLException e1) {
                    new ErrorDialog(parent, e1.getMessage());
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (MalformedURLException e1) {
                    new ErrorDialog(parent, "MalformedURLException");
                } catch (NotBoundException e1) {
                    new ErrorDialog(parent, "NotBoundException");
                } catch (InfoBLException e1) {
                    new TranslucentFrame(this, e1.getMessage(), Color.RED);
                }

            }
            else {
                initBL();
            }
        }
        else if (e.getSource()==cleanbt){
            refresh();
        }

    }

    private void initUI(){
        String [] names={"订单条形码号"};
        String [][] data={};
        defaultTableModel=new MyDefaultTableModel(data, names);
        table=new JTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;

        this.add(timeL,gbc);
        gbc.gridy=1;
        this.add(moneyL,gbc);
        gbc.gridy=2;
        this.add(courierL,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        this.add(timeT,gbc);
        gbc.gridy=1;
        this.add(moneyT,gbc);
        gbc.gridy=2;
        this.add(courierT,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.gridheight=3;
        table.setPreferredScrollableViewportSize(new Dimension(300,200));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(new JScrollPane(table), gbc);

        gbc.gridy=3;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.fill=GridBagConstraints.BOTH;
        this.add(orderNumT,gbc);
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridy=4;
        gbc.gridwidth=1;
        this.add(appendbt,gbc);
        gbc.gridx=3;
        this.add(deletebt,gbc);

        gbc.gridy=5;
        gbc.gridx=0;
        gbc.gridwidth=4;
        this.add(new JSeparator(), gbc);

        gbc.gridwidth=1;
        gbc.gridy=7;
        gbc.gridx=1;
        this.add(submitbt,gbc);
        gbc.gridx=2;
        this.add(cleanbt,gbc);
    }
}
