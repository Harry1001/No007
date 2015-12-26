package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.financeblservice.FinanceBLService;
import businessLogicService.infoblservice.AgencyBLService;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import constent.Constent;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.*;
import presentation.commoncontainer.ErrorDialog;
import typeDefinition.MessageType;
import vo.infovo.AgencyVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Harry on 2015/12/4.
 */
public class IncomePanel extends JPanel implements ActionListener{

    private FinanceBLService financeBLService;
    private AgencyBLService agencyBLService;

    private MainFrame parent;
    private MyDefaultTableModel defaultTableModel;
    private MyTable table;
    //private MyButton totalbt;
    private MyButton timebt;
    private TimePanel fromTimeP;
    private TimePanel toTimeP;
    private MyLabel fromTimeL;
    private MyLabel toTimeL;
    private MyLabel storeL;
    private JComboBox<String> storelist;
    private MyLabel totalMoneyL=new MyLabel("总收入: ");
    private MyLabel totalMoneyNum=new MyLabel("0.00");

    private Vector<String> names;

    public IncomePanel(MainFrame par){
        this.parent=par;
        this.setOpaque(false);

        initBL();

        initUI();
        setHotKey();
        timebt.addActionListener(this);
    }

    private void initBL(){
        try {
            agencyBLService=BLFactory.getAgencyBLService();
            financeBLService= BLFactory.getFinanceBLService();
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        } catch (NamingException e) {
            new ErrorDialog(parent, "NamingException");
        }
    }

    /**
     * 初始化所有组件
     */
    private void initUI(){
        initStoreList();
        names=new Vector<String>();
        names.add("收款快递员工号");
        names.add("收款日期");
        names.add("收款金额");

        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(270, 100));
        //totalbt=new MyButton("合计");
        timebt=new MyButton("OK");
        fromTimeP=new TimePanel();
        toTimeP=new TimePanel();
        fromTimeL=new MyLabel("起始时间");
        toTimeL=new MyLabel("截止时间");
        storeL=new MyLabel("营业厅编号");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;

        gbc.weightx=1.0;

        gbc.gridx=gbc.gridy=0;
        this.add(fromTimeL,gbc);
        gbc.gridx=1;
        this.add(fromTimeP,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        this.add(toTimeL,gbc);
        gbc.gridx=1;
        this.add(toTimeP,gbc);
        gbc.gridy=2;
        gbc.gridx=0;
        this.add(storeL,gbc);
        gbc.gridx=1;

        this.add(storelist, gbc);
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=2;
        this.add(timebt,gbc);

        gbc.gridy=3;
        gbc.gridx=0;
        gbc.gridwidth=3;
        gbc.fill=GridBagConstraints.BOTH;
        this.add(new JScrollPane(table),gbc);
        gbc.gridwidth=1;
        gbc.gridy=4;
        gbc.gridx=0;
        gbc.fill=GridBagConstraints.NONE;
        this.add(totalMoneyL,gbc);
        gbc.gridx=1;
        this.add(totalMoneyNum, gbc);

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"收入管理",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));

    }

    private void setHotKey(){
        timebt.setMnemonic('O');
    }

    private void initStoreList(){
        if (agencyBLService!=null){
            try {
                ArrayList<AgencyVO> agencyVOs=agencyBLService.getAgencyList();
                Vector<String> v = new Vector<String>();
                for (AgencyVO vo : agencyVOs){
                    if (vo.getAgencyType().equals("营业厅")){
                        v.add(vo.getAgencyID());
                    }
                }
                storelist=new JComboBox<String>(v);
            } catch (RemoteException e) {
                new TranslucentFrame(this, "服务器连接超时，载入营业厅列表失败", Color.RED);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            initBL();
        }

    }

    public void refresh(){
        //todo
    }

    public void actionPerformed(ActionEvent e) {
        if(financeBLService!=null){
            if (e.getSource() == timebt){
                //todo
                String storeNum=(String)storelist.getSelectedItem();
                double totalMoney=0.0;
                try {
                    Date fromtime=fromTimeP.getDate();
                    Date totime=toTimeP.getDate();
                    ArrayList<ChargeReceiptVO> vos= financeBLService.checkStore(fromtime, totime, storeNum);
                    defaultTableModel.getDataVector().clear();
                    Vector<Vector> data=new Vector<Vector>();
                    for (ChargeReceiptVO vo: vos){
                        String courierID=vo.getCourier();
                        String time= Constent.DATE_FORMAT.format(vo.getChargeTime());
                        double fee=vo.getFee();
                        Vector<Object> item=new Vector<Object>();
                        item.add(courierID);
                        item.add(time);
                        item.add(fee);
                        data.add(item);
                        totalMoney+=vo.getFee();
                    }
                    defaultTableModel.setDataVector(data, names);
                    totalMoneyNum.setText(totalMoney+"");
                    table.revalidate();
                    table.updateUI();
                } catch (TimeFormatException e1) {
                    new TranslucentFrame(this, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(this, MessageType.RMI_LAG, Color.RED);
                } catch (SQLException e1) {
                    System.out.println("成本管理sql:"+e1.getMessage());

                } catch (MalformedURLException e1) {
                    System.out.println(e1.getMessage());
                } catch (NotBoundException e1) {
                    System.out.println(e1.getMessage());
                }

            }
        }
        else {
            initBL();
        }
    }
}
