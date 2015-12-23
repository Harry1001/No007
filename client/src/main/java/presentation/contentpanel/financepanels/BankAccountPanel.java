package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.BankAccountBLService;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commoncontainer.ErrorDialog;
import vo.infovo.BankAccountVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Harry on 2015/12/4.
 */
public class BankAccountPanel extends JPanel implements ActionListener{
    private MainFrame parent;
    private MyDefaultTableModel defaultTableModel;
    private MyTable table;
    private MyButton addbt=new MyButton("新增账户");
    private MyButton deletebt=new MyButton("删除账户");

    private BankAccountBLService bankAccountBLService;

    protected Vector<String> names=new Vector<String>();

    public BankAccountPanel(MainFrame par){
        this.parent=par;
        initUI();

        addbt.addActionListener(this);
        deletebt.addActionListener(this);

        initBL();
        refresh();
    }

    private void initBL(){
        try {
            bankAccountBLService= BLFactory.getBankAccountBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    private void initUI(){
        names.add("帐号");
        names.add("金额");
        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;
        gbc.weighty=1.0;

        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=3;
        this.add(new JScrollPane(table),gbc);

        gbc.weightx=gbc.weighty=0.0;
        gbc.gridwidth=1;
        gbc.gridy++;
        this.add(addbt, gbc);
        gbc.gridx++;
        this.add(deletebt,gbc);
    }

    public void refresh(){
        try {
            ArrayList<BankAccountVO> vos=bankAccountBLService.getBankAccountList();
           // defaultTableModel.getDataVector().clear();
            Vector<Vector> data=new Vector<Vector>();
            for(BankAccountVO vo: vos){
                String acc=vo.getAccountUser();
                BigDecimal balance=vo.getBalance();
                Vector<Object> item=new Vector<Object>();
                item.add(acc);
                item.add(balance);
                data.add(item);
                //defaultTableModel.addRow(data);
            }
            defaultTableModel.setDataVector(data, names);
            table.revalidate();
            table.updateUI();
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (SQLException e) {
            new ErrorDialog(parent, "SQLException");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            if (bankAccountBLService!=null){
                JDialog dialog=new JDialog(parent,"新建银行账户", true);
                dialog.getContentPane().add(new BankAccountAddPanel(parent, dialog, this, bankAccountBLService));
                dialog.setLocationRelativeTo(parent);
                dialog.pack();
                dialog.setVisible(true);
            }
            else {
                initBL();
            }
        }
        else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row>=0){
                if (bankAccountBLService!=null){
                    String account=(String)table.getValueAt(row, 0);
                    try {
                        bankAccountBLService.deleteBankAccount(account);
                        refresh();
                    } catch (RemoteException e1) {
                        new ErrorDialog(parent, "服务器连接超时");
                    } catch (SQLException e1) {
                        new ErrorDialog(parent, "SQLException");
                    }
                }
                else{
                    initBL();
                }
            }
        }
    }
}
