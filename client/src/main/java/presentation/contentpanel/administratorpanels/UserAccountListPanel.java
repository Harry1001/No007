package presentation.contentpanel.administratorpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.UserAccoutBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commonpanel.ErrorDialog;
import vo.infovo.UserAccountVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;
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
    private MyButton addbt=new MyButton("新增");
    private MyButton deletebt=new MyButton("删除");
    private MyButton modifybt=new MyButton("修改");
    private MyButton refreshbt=new MyButton("刷新");
    private UserAccoutBLService userAccoutBLService;//逻辑层引用

    private MyDefaultTableModel defaultTableModel;
    private MyTable table;

    private Vector names;


    public UserAccountListPanel(MainFrame par) {

        this.parent=par;

        initUI();

        //为组件添加监听
        addbt.addActionListener(this);
        deletebt.addActionListener(this);
        modifybt.addActionListener(this);
        refreshbt.addActionListener(this);

        initBL();

        refreshList();
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
            new ErrorDialog(parent, "服务器连接超时");
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
        gbc.gridx=4;
        gbc.anchor=GridBagConstraints.EAST;
        this.add(refreshbt,gbc);
    }

    /**
     * 刷新账户列表，进入界面时以及新增、删除、修改后均需调用
     */
    private void refreshList(){
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
                new ErrorDialog(parent, "服务器连接超时");
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
            JDialog dialog=new JDialog(parent,"新增用户信息",true);
            dialog.getContentPane().add(new UserAccountInfoPanel(parent, userAccoutBLService));
            dialog.setLocationRelativeTo(parent);
            dialog.pack();
            dialog.show();
        } else if (e.getSource()==deletebt){
            int row=table.getSelectedRow();
        } else if (e.getSource()==modifybt){

        } else if (e.getSource()==refreshbt){

        }
    }
}
