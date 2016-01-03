package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.BankAccountBLService;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.infovo.BankAccountVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Harry on 2015/12/4.
 */
public class BankAccountPanel extends JPanel implements ActionListener{
    private MainFrame parent;
    private MyDefaultTableModel defaultTableModel;
    private MyTable table;
    private MyButton addbt=new MyButton("新建(N)");
    private MyButton deletebt=new MyButton("删除(D)");
    private MyButton refreshbt=new MyButton("刷新(R)");

    private BankAccountBLService bankAccountBLService;

    protected Vector<String> names=new Vector<String>();

    public BankAccountPanel(MainFrame par){
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"银行账户",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        
        initUI();
        setHotKey();

        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        refreshbt.addActionListener(this);

        initBL();
        refresh();
    }

    private void setHotKey(){
        addbt.setMnemonic('N');
        deletebt.setMnemonic('D');
        refreshbt.setMnemonic('R');
    }

    private void initBL(){
        try {
            bankAccountBLService= BLFactory.getBankAccountBLService();
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
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
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=4;
        this.add(new JScrollPane(table),gbc);

        //gbc.weightx=gbc.weighty=0.0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridwidth=1;
        gbc.gridy++;
        gbc.gridx=1;
        this.add(addbt, gbc);
        gbc.gridx++;
        this.add(deletebt,gbc);
        gbc.gridx++;
        this.add(refreshbt, gbc);

        gbc.gridy++;
        gbc.gridx=1;
        this.add(new BlankBlock(), gbc);
    }

    public void refresh(){
        try {

            DecimalFormat df=new DecimalFormat("#.00");

            ArrayList<BankAccountVO> vos=bankAccountBLService.getBankAccountList();
           // defaultTableModel.getDataVector().clear();
            Vector<Vector> data=new Vector<Vector>();
            for(BankAccountVO vo: vos){
                String acc=vo.getAccountUser();
                BigDecimal balance=vo.getBalance();
                Vector<Object> item=new Vector<Object>();
                item.add(acc);
                item.add(df.format(balance));
                data.add(item);
                //defaultTableModel.addRow(data);
            }
            defaultTableModel.setDataVector(data, names);
            table.revalidate();
            table.updateUI();
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            if (bankAccountBLService!=null){
                JDialog dialog=new JDialog(parent,"新建银行账户", true);
                dialog.getContentPane().add(new BankAccountAddPanel(parent, dialog, this, bankAccountBLService));
                dialog.setLocationRelativeTo(parent);
                dialog.setLocation(dialog.getX()/2, dialog.getY()/2);
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
                        new TranslucentFrame(this, MessageType.DELETE_SUCCESS, Color.GREEN);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    }
                }
                else{
                    initBL();
                }
            }
        }
        else if (e.getSource()==refreshbt){
            refresh();
            new TranslucentFrame(this, "刷新成功", Color.GREEN);
        }
    }
}
