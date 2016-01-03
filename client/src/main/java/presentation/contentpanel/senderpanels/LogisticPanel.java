package presentation.contentpanel.senderpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.logisticblservice.LogisticBLService;
import constent.Constent;
import presentation.Images.Images;
import presentation.commoncontainer.*;
import presentation.commoncontainer.ErrorDialog;
import presentation.uifactory.UIFactory;
import typeDefinition.MessageType;
import vo.logisticvo.LogisticVO;

import javax.swing.*;
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
 * 物流信息查询界面
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

    Vector<String> names=new Vector<String>();

    GridBagConstraints gbc;

    public LogisticPanel(MainFrame par){
        this.setOpaque(false);
        names.add("时间");
        names.add("到达地点");
        this.parent=par;
        label=new MyLabel("请输入10位订单号:");
        button=new MyButton("搜索(S)");
        backbt=new MyButton("返回(B)", Images.BACK_IMAGE);
        textField=new MyTextField();

        this.setLayout(new GridBagLayout());
        gbc =new GridBagConstraints();

        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.weightx=gbc.weighty=0.0;

        gbc.gridx=gbc.gridy=0;
        this.add(backbt, gbc);
        gbc.gridx++;
        gbc.gridy++;
        this.add(label, gbc);
        gbc.gridx++;
        this.add(textField,gbc);
        gbc.gridx++;
        this.add(button,gbc);



        defaultTableModel=new MyDefaultTableModel(names, 0);
        table=new MyTable(defaultTableModel);
        //table.setRowSorter(null);
        JScrollPane s=new JScrollPane(table);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);


        gbc.gridx=1;
        gbc.gridy++;
        gbc.gridwidth=3;
        gbc.gridheight=1;
        this.add(s, gbc);

        setHotKey();

        button.addActionListener(this);
        backbt.addActionListener(this);

        initBL();
    }

    private void setHotKey(){
        button.setMnemonic('S');
        backbt.setMnemonic('B');
    }

    private void initBL(){
        try {
            logisticBLService= BLFactory.getLogisticBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
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
                if (len<=0){
                    new TranslucentFrame(this, "无此订单信息", Color.RED);
                }
                else {
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
                }
            } catch (RemoteException e1) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
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
