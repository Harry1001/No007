package presentation.contentpanel.senderpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.logisticblservice.LogisticBLService;
import constent.Constent;
import presentation.commoncontainer.*;
import presentation.commonpanel.ErrorDialog;
import presentation.uifactory.UIFactory;
import vo.logisticvo.LogisticVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Harry on 2015/11/25.
 */
public class LogisticPanel extends JPanel implements ActionListener{

    LogisticBLService logisticBLService;

    MainFrame parent;
    MyLabel label;
    MyButton button;
    MyTextField textField;
    MyTable table;
    MyDefaultTableModel defaultTableModel;
    MyButton backbt;

    Vector<String> names;

    GridBagConstraints gbc;

    public LogisticPanel(MainFrame par){
        names.add("时间");
        names.add("到达地点");
        this.parent=par;
        label=new MyLabel("请输入10位订单号:");
        button=new MyButton("查询");
        backbt=new MyButton("返回");
        textField=new MyTextField();

        this.setLayout(new GridBagLayout());
        gbc =new GridBagConstraints();

        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=gbc.weighty=0.0;
        gbc.gridx=0;
        this.add(label, gbc);
        gbc.gridx=1;
        this.add(textField,gbc);
        gbc.gridx=2;
        this.add(button,gbc);
        gbc.gridx=3;
        this.add(backbt,gbc);

        gbc.weightx=gbc.weighty=1.0;
        defaultTableModel=new MyDefaultTableModel(names, 0);
        table=new MyTable(defaultTableModel);
        //table.setRowSorter(null);
        JScrollPane s=new JScrollPane(table);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);


        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=4;
        gbc.gridheight=3;
        this.add(s, gbc);

        button.addActionListener(this);
        backbt.addActionListener(this);

        initBL();
    }

    private void initBL(){
        try {
            logisticBLService= BLFactory.getLogisticBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==button){
            String id=textField.getText();
            try {
                ArrayList<LogisticVO> logisticVOs=logisticBLService.getLogistic(id);
                int len=logisticVOs.size();
                Vector<Vector> data=new Vector<Vector>();
                for (int i=0;i<len;i++){
                    LogisticVO vo=logisticVOs.get(i);
                    Vector<String> item =new Vector<String>();
                    item.add(Constent.DATE_FORMAT.format(vo.getArrivalTime()));
                    item.add(vo.getState());
                    data.add(item);
                }
                defaultTableModel.setDataVector(data, names);
                table.revalidate();
                table.updateUI();
            } catch (RemoteException e1) {
                new ErrorDialog(parent, "服务器连接超时");
            } catch (SQLException e1) {
                new ErrorDialog(parent, "SQLException");
            }


        } else if (e.getSource()==backbt){

            //清空表格
            defaultTableModel.getDataVector().clear();
            table.updateUI();
            UIFactory.showLoginPanel(parent);
        }

    }
}
