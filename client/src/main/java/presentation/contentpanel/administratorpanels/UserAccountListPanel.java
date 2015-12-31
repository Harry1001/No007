package presentation.contentpanel.administratorpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.UserAccoutBLService;
import constent.Constent;
import presentation.commoncontainer.*;
import typeDefinition.Job;
import typeDefinition.MessageType;
import vo.infovo.UserAccountVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;

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
 * 管理员管理用户账户信息的列表面板
 *
 */
public class UserAccountListPanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private MyButton addbt=new MyButton("新增(N)");
    private MyButton deletebt=new MyButton("删除(D)");
    private MyButton modifybt=new MyButton("修改(M)");
    private MyButton refreshbt=new MyButton("刷新(R)");
    private UserAccoutBLService userAccoutBLService;//逻辑层引用

    private MyDefaultTableModel defaultTableModel;
    private MyTable table;

    private Vector names;


    public UserAccountListPanel(MainFrame par) {

        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"用户账户管理",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        
        initUI();

        setHotKey();

        //为组件添加监听
        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);
        refreshbt.addActionListener(this);

        initBL();

        refreshList();
    }

    private void setHotKey(){
        addbt.setMnemonic('N');
        deletebt.setMnemonic('D');
        modifybt.setMnemonic('M');
        refreshbt.setMnemonic('R');
    }

    /**
     * 创建逻辑层引用，并对可能产生的异常进行处理
     */
    private void initBL(){
        try {
            userAccoutBLService=BLFactory.getUserAccountBLService();
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "URL格式错误");
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "URL未绑定");
        }
    }

    /**
     * 创建并布局面板上所有的组件位置
     */
    private void initUI(){


        names=new Vector();
        names.add(new String ("工号"));
        names.add(new String ("姓名"));
        names.add(new String ("职位"));
        names.add(new String ("密码"));

        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
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
        gbc.gridx=4;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(refreshbt,gbc);
    }

    /**
     * 刷新账户列表，进入界面时以及新增、删除、修改后均需调用
     */
    protected void refreshList(){
        if (userAccoutBLService!=null){
            try {
                ArrayList<UserAccountVO> userAccountVOs=userAccoutBLService.getUserAccountList();
                Vector<Vector> data=new Vector<Vector>();
                for(UserAccountVO vo : userAccountVOs){
                    Vector v = new Vector();
                    v.add(new String(vo.getUserID()));
                    v.add(new String (""+vo.getName()));
                    v.add(Constent.JOB_STRING[vo.getPosition().ordinal()]);
                    v.add(vo.getPassword());

                    data.add(v);
                }
                defaultTableModel.setDataVector(data, names);
                table.revalidate();
                table.updateUI();
            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
            } catch (SQLException e) {
                new ErrorDialog(parent, "数据库异常");
            }
        }
        else {
            initBL();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            if (userAccoutBLService!=null) {
                JDialog dialog=new JDialog(parent,"新增用户信息",true);
                dialog.getContentPane().add(new UserAccountInfoPanel(parent,dialog, this, userAccoutBLService));
                dialog.setLocationRelativeTo(parent);
                dialog.pack();
                dialog.setVisible(true);
            }
            else {
                initBL();
            }
        } else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
            if (row>=0) {
                if (userAccoutBLService!=null) {
                    String id=(String)table.getValueAt(row, 0);
                    try {
                        userAccoutBLService.deleteUserAccount(id);
                        refreshList();
                        new TranslucentFrame(this, MessageType.DELETE_SUCCESS, Color.GREEN);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (SQLException e1) {
                        new ErrorDialog(parent, "SQLException");
                    }
                }
                else {
                    initBL();
                }
            }
            else {
                new TranslucentFrame(this, "请选择待删除行", Color.RED);
            }
        } else if (e.getSource()==modifybt){
            int row=table.getSelectedRow();
            if (row==-1){//没有选择任何行
                new TranslucentFrame(this, "请选择待修改账户", Color.RED);
            } else {//选择了待修改的行
                if (userAccoutBLService!=null){
                    String id=(String)table.getValueAt(row, 0);
                    String name=(String) table.getValueAt(row, 1);
                    String pass=(String) table.getValueAt(row, 3);

                    String jobString = (String) table.getValueAt(row, 2);
                    int jobID=0;
                    for(;jobID<Constent.JOB_STRING.length;jobID++){
                        if (Constent.JOB_STRING[jobID].equals(jobString)){
                            break;
                        }
                    }
                    UserAccountVO vo=new UserAccountVO(id, name, Job.values()[jobID],pass);

                    JDialog dialog=new JDialog(parent,"修改账户信息",false);
                    dialog.getContentPane().add(new UserAccountModifyPanel(parent,dialog, this, userAccoutBLService, vo));
                    dialog.setLocationRelativeTo(parent);
                    dialog.pack();
                    dialog.setVisible(true);
                }
                else {
                    initBL();
                }
            }
        } else if (e.getSource()==refreshbt){
            refreshList();
            new TranslucentFrame(this, "刷新成功", Color.GREEN);
        }
    }
}
