package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.StaffBLService;
import constent.Constent;
import presentation.commoncontainer.*;
import typeDefinition.Job;
import typeDefinition.MessageType;
import vo.infovo.StaffVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Harry on 2015/11/24.
 */
public class StaffListPanel extends JPanel implements ActionListener{
    private MainFrame parent;
    private MyButton addbt=new MyButton("新建(N)");
    private MyButton deletebt=new MyButton("删除(D)");
    private MyButton modifybt=new MyButton("修改(M)");

    private MyDefaultTableModel defaultTableModel;
    private MyTable table;

    private Vector<String> names=new Vector<String>();
    private StaffBLService staffBLService;

    public StaffListPanel(MainFrame par) {

        this.parent=par;

        initUI();
        setHotKey();
        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);

        initBL();
        refreshList();
    }

    private void setHotKey(){
        addbt.setMnemonic('N');
        deletebt.setMnemonic('D');
        modifybt.setMnemonic('M');
    }

    private void initUI(){
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"人员管理",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        initNames();

        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=gbc.weighty=1.0;

        gbc.gridwidth=5;
        gbc.gridheight=10;
        this.add(new JScrollPane(table),gbc);

        gbc.weightx=gbc.weighty=0.0;
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

        gbc.gridx=0;
        gbc.gridy++;
        this.add(new BlankBlock(),gbc);
    }

    /**
     * 初始化列名称
     */
    private void initNames(){
        String [] strings={"工号","姓名","性别","出生年月","职位", "提成次数"};
        for(int i=0;i<strings.length;i++){
            names.add(strings[i]);
        }
    }

    /**
     * 初始化逻辑层引用
     */
    private void initBL(){
        try {
            staffBLService= BLFactory.getStaffBLService();
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 从数据库读取数据刷新列表
     */
    public void refreshList(){
        if (staffBLService!=null){
            try {
                ArrayList<StaffVO> staffVOs = staffBLService.getStaffList();
                //System.out.println("staffVOs:"+staffVOs.size());
                Vector<Vector> data = new Vector<Vector>();
                Vector<Object> item;
                for (StaffVO vo: staffVOs){
                    item=new Vector<Object>();
                    item.add(vo.getStaffID());
                    item.add(vo.getName());
                    item.add(vo.getGender());
                    item.add(Constent.BIRTHDAY_FORMAT.format(vo.getBirthday()));
                    item.add(Constent.JOB_STRING[vo.getPosition().ordinal()]);
                    item.add(vo.getWorkFrequency());
                    data.add(item);
                }
                //System.out.println("vector<vector> size:"+data.size());
                defaultTableModel.setDataVector(data,names);
                table.revalidate();
                table.updateUI();
                //System.out.println(""+defaultTableModel.getRowCount());
            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
            } catch (SQLException e) {
                System.out.println("刷新人员列表sql："+e.getMessage());
            }
        }
        else {
            initBL();
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            if (staffBLService!=null){
                JDialog dialog=new JDialog(parent,"新增人员信息",true);
                dialog.getContentPane().add(new StaffInfoPanel(parent, dialog, staffBLService, this));
                dialog.setLocationRelativeTo(parent);
                dialog.setLocation(dialog.getX()/3, dialog.getY()/3);
                dialog.pack();
                dialog.setVisible(true);
            }
            else {
                initBL();
            }
        }
        else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row==-1){//没有选择任何行
                new TranslucentFrame(this, "请选择一行待删除信息", Color.RED);
            } else {//选择了待删除的行
                if (staffBLService!=null){
                    String id= (String)table.getValueAt(row, 0);
                    try {
                        staffBLService.deleteStaff(id);
                        refreshList();
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
        else if (e.getSource()==modifybt){
            int row=table.getSelectedRow();
            if (row==-1){
                new TranslucentFrame(this, "请选择待修改行", Color.RED);
            }
            else {
                if (staffBLService!=null){
                    String id=(String)table.getValueAt(row, 0);
                    String name=(String)table.getValueAt(row, 1);
                    String gender=(String)table.getValueAt(row, 2);
                    int frequency=(Integer)table.getValueAt(row, 5);
                    Job job;
                    String jobStr=(String)table.getValueAt(row, 4);
                    int i=0;
                    for (;i<Constent.JOB_STRING.length;i++){
                        if (jobStr.equals(Constent.JOB_STRING[i])){
                            break;
                        }
                    }
                    job=Job.values()[i];
                    Date birthday=null;
                    try {
                        birthday=Constent.BIRTHDAY_FORMAT.parse((String)table.getValueAt(row,3));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                    StaffVO vo=new StaffVO(id, name, gender, birthday, job, 0, frequency);

                    JDialog dialog=new JDialog(parent,"修改人员信息",true);
                    dialog.getContentPane().add(new StaffModifyPanel(parent, dialog, staffBLService, this, vo));
                    dialog.setLocationRelativeTo(parent);
                    dialog.setLocation(dialog.getX()/3, dialog.getY()/3);
                    dialog.pack();
                    dialog.setVisible(true);
                }
                else {
                    initBL();
                }
            }
        }
    }
}
