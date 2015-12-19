package presentation.contentpanel;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.recordblservice.RecordBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commonpanel.ErrorDialog;
import vo.recordvo.RecordVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/28.
 */
public class RecordListPanel extends JPanel implements ActionListener{

    RecordBLService recordBLService;

    MainFrame parent;
    MyDefaultTableModel defaultTableModel;
    MyTable table;
    MyButton refreshbt=new MyButton("刷新日志");

    public RecordListPanel(MainFrame par) {

        this.parent = par;

        String[] names = {"操作时间", "操作人", "操作概要简述"};

        defaultTableModel = new MyDefaultTableModel(names, 0);
        table = new MyTable(defaultTableModel);

        table.getColumnModel().getColumn(2).setPreferredWidth(300);//设置操作简述一列较宽

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridwidth = 2;
        this.add(new JScrollPane(table), gbc);

        gbc.gridwidth = 1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(refreshbt,gbc);

        refreshbt.addActionListener(this);
    }

    private void initBL(){
        try {
            recordBLService=BLFactory.getRecordBLService();
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        }
    }

    public void refreshData(){
        if (recordBLService!=null){
            try {
                ArrayList<RecordVO> vos = recordBLService.lookup();
                defaultTableModel.getDataVector().clear();
                for (RecordVO vo:vos){
                    String date= Constent.DATE_FORMAT.format(vo.getOpeTime());
                    String operator=vo.getOperator();
                    String operation=vo.getOperation();
                    String[] data={date, operator, operation};
                    defaultTableModel.addRow(data);
                }
                table.revalidate();
                table.updateUI();
            } catch (RemoteException e) {
                new ErrorDialog(parent, "服务器连接超时");
            } catch (SQLException e) {
                new ErrorDialog(parent, "SQLException");
            }
        }
        else {
            initBL();
        }
    }

    public void actionPerformed(ActionEvent e) {
        refreshData();
    }
    
}
