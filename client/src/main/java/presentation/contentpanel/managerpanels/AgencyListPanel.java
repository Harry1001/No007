package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.AgencyBLService;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commonpanel.ErrorDialog;
import vo.infovo.AgencyVO;

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
 * 人员信息管理列表界面
 */
public class AgencyListPanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private MyButton addbt=new MyButton("新增");
    private MyButton deletebt=new MyButton("删除");
    private MyButton modifybt=new MyButton("修改");

    private MyDefaultTableModel defaultTableModel;
    private MyTable table;

    private  Vector<String> names=new Vector<String>();

    private AgencyBLService agencyBLService;

    public AgencyListPanel(MainFrame par) {

        this.parent=par;

        initNames();
        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridwidth=5;
        gbc.gridheight=10;
        this.add(new JScrollPane(table),gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        gbc.gridx=0;
        gbc.gridy=13;
        this.add(addbt,gbc);
        gbc.gridx=1;
        this.add(deletebt,gbc);
        gbc.gridx=2;
        this.add(modifybt,gbc);

        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);

        initBL();
        refreshList();
    }

    /**
     * 初始化列名称
     */
    private void initNames(){
        String [] strings={"机构编号","名称","机构类型","位置","面积","土地租金"};
        for(int i=0;i<strings.length;i++){
            names.add(strings[i]);
        }
    }

    /**
     * 初始化逻辑层引用
     */
    private void initBL(){
        try {
            agencyBLService= BLFactory.getAgencyBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "网络连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    /**
     * 从数据库读取数据刷新列表
     */
    public void refreshList(){
        try {
            ArrayList<AgencyVO> agencyVOs = agencyBLService.getAgencyList();
            System.out.println(""+agencyVOs.size());
            Vector<Vector> data = new Vector<Vector>();
            Vector<Object> item;
            for (AgencyVO vo: agencyVOs){
                item=new Vector<Object>();
                item.add(vo.getAgencyID());
                item.add(vo.getAgencyName());
                item.add(vo.getAgencyType());
                item.add(vo.getLocation());
                item.add(vo.getArea());
                item.add(vo.getRent());
                data.add(item);
            }
            defaultTableModel.setDataVector(data,names);
            table.validate();
            table.updateUI();
            System.out.println(""+defaultTableModel.getRowCount());
        } catch (RemoteException e) {
            new ErrorDialog(parent, "网络连接超时");
        } catch (SQLException e) {
            new ErrorDialog(parent, "SQLException");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            JDialog dialog=new JDialog(parent,"新增机构信息",false);
            dialog.getContentPane().add(new AgencyInfoPanel(dialog, parent, this, agencyBLService));
            dialog.setLocationRelativeTo(parent);
            dialog.pack();
            dialog.setVisible(true);
        }
        else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row==-1){//没有选择任何行
                new ErrorDialog(parent, "请选择一行待删除条目");
            } else {//选择了待删除的行
                String id= (String)table.getValueAt(row, 0);
                try {
                    agencyBLService.deleteAgency(id);
                    refreshList();
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "网络连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                }
            }
        }
        else if (e.getSource()==modifybt){
            int row=table.getSelectedRow();
            if (row==-1){//没有选择任何行
                new ErrorDialog(parent, "请选择一行待修改条目");
            } else {//选择了待修改的行
                String id=(String)table.getValueAt(row, 0);
                String name=(String) table.getValueAt(row, 1)//todo
                AgencyVO vo=new AgencyVO();
                JDialog dialog=new JDialog(parent,"新增机构信息",false);
                dialog.getContentPane().add(new AgencyInfoPanel(dialog, parent, this, agencyBLService));
                dialog.setLocationRelativeTo(parent);
                dialog.pack();
                dialog.setVisible(true);
            }
        }
    }
}
