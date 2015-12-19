package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogic.strategybl.StrategyBL;
import businessLogicService.strategyblservice.SalaryStrategyBLService;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commonpanel.ErrorDialog;
import vo.strategyvo.SalaryVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Harry on 2015/11/29.
 */
public class SalaryListPanel extends JPanel implements ActionListener {
    protected MainFrame parent;
    protected MyButton confirmbt=new MyButton("确认");
    protected MyButton cancelbt=new MyButton("取消");

    protected EditableTableModel defaultTableModel;
    protected MyTable table;

    protected SalaryStrategyBLService salaryService;

    protected String [] names={"员工职位","月基本工资(元)","提成(元/次)"};


    public SalaryListPanel(MainFrame par){
        this.parent=par;

        //todo 读取数据后初始化表格data
        defaultTableModel=new EditableTableModel(names,0);
        table=new MyTable(defaultTableModel);
        table.setRowSorter(null);//工资表格不可排序

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridwidth=6;
        gbc.gridheight=10;
        this.add(new JScrollPane(table),gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridheight=1;
        gbc.gridwidth=1;

        gbc.gridy=13;
        gbc.gridx=3;
        this.add(confirmbt,gbc);
        gbc.gridx=3;
        this.add(cancelbt,gbc);


        confirmbt.addActionListener(this);
        cancelbt.addActionListener(this);

        initBL();
        initData();
    }

    /**
     * 载入数据
     */
    protected void initData(){
        try {
            SalaryVO vo = salaryService.getSalary();
            Object [][] rowData= new Object[8][3];//8代表共有八种职位
            rowData[0][0]="快递员";
            rowData[0][1]=vo.getMailerBS();
            rowData[0][2]=vo.getMailerAl();
            rowData[1][0]="司机";
            rowData[1][1]=vo.getDriverBS();
            rowData[1][2]=vo.getDriverAl();
            rowData[2][0]="总经理";
            rowData[2][1]=vo.getManagerBS();
            rowData[2][2]=0;
            rowData[3][0]="财务人员";
            rowData[3][1]=vo.getAccountantBS();
            rowData[3][2]=0;
            rowData[4][0]="营业厅业务员";
            rowData[4][1]=vo.getStoresalesmanBS();
            rowData[4][2]=0;
            rowData[5][0]="中转中心业务员";
            rowData[5][1]=vo.getHubsalesmanBS();
            rowData[5][2]=0;
            rowData[6][0]="仓库管理员";
            rowData[6][1]=vo.getStorekeeperBS();
            rowData[6][2]=0;
            rowData[7][0]="管理员";
            rowData[7][1]=vo.getAdministerBS();
            rowData[7][2]=0;

            defaultTableModel.setDataVector(rowData, names);
            table.revalidate();
            table.updateUI();
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (SQLException e) {
            new ErrorDialog(parent, "SQLException");
        }
    }


    /**
     * 创建逻辑层引用
     */
    protected void initBL(){
        try {
            salaryService = BLFactory.getSalaryBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            if (salaryService!=null){
                int mail=(Integer)table.getValueAt(0,1);
                int driver=(Integer)table.getValueAt(1,1);
                int manager=(Integer)table.getValueAt(2,1);
                int finance=(Integer)table.getValueAt(3,1);
                int store=(Integer)table.getValueAt(4,1);
                int hub=(Integer)table.getValueAt(5,1);
                int depot=(Integer)table.getValueAt(6,1);
                int admin=(Integer)table.getValueAt(7,1);

                int mailAl=(Integer)table.getValueAt(0,2);
                int driverAl=(Integer)table.getValueAt(1,2);

                SalaryVO vo = new SalaryVO(finance, admin, driver,hub,mail,manager,depot,store,driverAl,mailAl);
                try {
                    salaryService.setSalary(vo);
                    initData();
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                }
            }
            else {
                initBL();
            }
        }
        else if (e.getSource()==cancelbt){//点击取消则重新载入数据
            initData();
        }
    }

    /**
     * 内部类，是的表格可编辑
     */
    private class EditableTableModel extends DefaultTableModel{
        public EditableTableModel() {
        }

        public EditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public EditableTableModel(Vector columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public EditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        public EditableTableModel(Vector data, Vector columnNames) {
            super(data, columnNames);
        }

        public EditableTableModel(int rowCount, int columnCount) {
            super(rowCount, columnCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            if (column==0){
                return false;
            }else {
                return true;
            }
        }
    }

}
