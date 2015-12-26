package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.strategyblservice.SalaryStrategyBLService;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.strategyvo.SalaryVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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
    protected MyButton confirmbt=new MyButton("OK");
    protected MyButton cancelbt=new MyButton("Refresh");
    protected MyLabel tipL=new MyLabel("双击表格中的单元格可以修改薪水");

    protected EditableTableModel defaultTableModel;
    protected MyTable table;

    protected SalaryStrategyBLService salaryService;

    protected String [] names={"员工职位","月基本工资(元)","提成(元/次)"};


    public SalaryListPanel(MainFrame par){
        this.parent=par;

        initUI();
        setHotKey();

        confirmbt.addActionListener(this);
        cancelbt.addActionListener(this);

        initBL();
        initData();
    }

    private void setHotKey(){
        confirmbt.setMnemonic('O');
        cancelbt.setMnemonic('R');
    }

    private void initUI(){
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"薪水策略",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));


        defaultTableModel=new EditableTableModel(names,0);
        table=new MyTable(defaultTableModel);
        table.setRowSorter(null);//工资表格不可排序
        table.setPreferredScrollableViewportSize(new Dimension(500,150));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridx=gbc.gridy=0;
        gbc.weightx=gbc.weighty=1.0;
        gbc.gridwidth=3;
        gbc.gridheight=1;
        this.add(new JScrollPane(table),gbc);

        gbc.weightx=gbc.weighty=0.0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.WEST;
        gbc.gridy++;
        this.add(tipL, gbc);

        gbc.gridwidth=1;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridy=2;
        gbc.gridx=0;
        this.add(confirmbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);

        gbc.gridx=0;
        gbc.gridy++;
        this.add(new BlankBlock(), gbc);
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
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * 创建逻辑层引用
     */
    protected void initBL(){
        try {
            salaryService = BLFactory.getSalaryBLService();
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            if (salaryService!=null){

                try {
                    int mail = Integer.parseInt(table.getValueAt(0, 1).toString());
                    int driver = Integer.parseInt( table.getValueAt(1, 1).toString());
                    int manager = Integer.parseInt(table.getValueAt(2, 1).toString());
                    int finance = Integer.parseInt(table.getValueAt(3, 1).toString());
                    int store = Integer.parseInt(table.getValueAt(4, 1).toString());
                    int hub = Integer.parseInt(table.getValueAt(5, 1).toString());
                    int depot = Integer.parseInt(table.getValueAt(6, 1).toString());
                    int admin = Integer.parseInt(table.getValueAt(7, 1).toString());

                    int mailAl = Integer.parseInt(table.getValueAt(0, 2).toString());
                    int driverAl = Integer.parseInt(table.getValueAt(1, 2).toString());

                    SalaryVO vo = new SalaryVO(finance, admin, driver, hub, mail, manager, depot, store, driverAl, mailAl);
                    salaryService.setSalary(vo);
                    initData();
                    new TranslucentFrame(this, MessageType.MODIFY_SUCCESS, Color.GREEN);
                } catch (NumberFormatException e1){
                    new TranslucentFrame(this, "工资必须为正整数", Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println("salary sql"+e1.getMessage());
                }
            }
            else {
                initBL();
            }
        }
        else if (e.getSource()==cancelbt){//点击取消则重新载入数据
            initData();
            new TranslucentFrame(this, "刷新成功", Color.GREEN);
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
            return column!=0;
        }
    }

}
