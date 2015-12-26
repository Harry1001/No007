package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.financeblservice.FinanceBLService;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;

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

/**
 * Created by Harry on 2015/12/4.
 */
public class MakeBillPanel extends JPanel implements ActionListener {

    private MainFrame parent;
    private MyLabel yearL = new MyLabel("保存年份");
    private JComboBox<Integer> yearT = new JComboBox<Integer>();
    private MyButton submitbt=new MyButton("Submit");

    private FinanceBLService financeBLService;

    public MakeBillPanel(MainFrame par){
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"期初建账",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        
        initUI();
        setHotKey();

        submitbt.addActionListener(this);
        initBL();
    }

    private void setHotKey(){
        submitbt.setMnemonic('S');
    }

    private void initUI(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
       // gbc.weightx=1.0;
        //gbc.weighty=1.0;
        yearT.addItem(2015);
        yearT.addItem(2016);
        yearT.addItem(2017);
        yearT.addItem(2018);
        yearT.addItem(2019);

        gbc.gridx=gbc.gridy=0;
        this.add(yearL,gbc);
        gbc.gridx++;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.ipadx=100;

        this.add(yearT,gbc);
        gbc.ipadx=0;
        gbc.ipady=0;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        this.add(submitbt,gbc);
        refresh();
    }

    private void initBL(){
        try {
            financeBLService= BLFactory.getFinanceBLService();
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }


    public void refresh(){
        yearT.setSelectedIndex(0);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (financeBLService!=null){

                    int year=(Integer)yearT.getSelectedItem();
                    try {
                        financeBLService.makeCredit(year);
                        refresh();
                        new TranslucentFrame(this, "期初建账成功", Color.GREEN);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (NamingException e1) {
                        System.out.println(e1.getMessage());
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    } catch (MalformedURLException e1) {
                        System.out.println(e1.getMessage());
                    } catch (NotBoundException e1) {
                        System.out.println(e1.getMessage());
                    }

            }
            else{
                initBL();
            }
        }
    }
}
