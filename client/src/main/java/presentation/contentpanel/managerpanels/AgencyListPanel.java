package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.AgencyBLService;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.infovo.AgencyVO;

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
 * 人员信息管理列表界面
 */
public class AgencyListPanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private MyButton addbt=new MyButton("New");
    private MyButton deletebt=new MyButton("Delete");
    private MyButton modifybt=new MyButton("Modify");

    private MyDefaultTableModel defaultTableModel;
    private MyTable table;

    private  Vector<String> names=new Vector<String>();

    private AgencyBLService agencyBLService;

    public AgencyListPanel(MainFrame par) {

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
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"机构管理",
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
        this.add(new BlankBlock(), gbc);
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
        if (agencyBLService!=null){
            try {
                ArrayList<AgencyVO> agencyVOs = agencyBLService.getAgencyList();
                //System.out.println("agencyVOs:"+agencyVOs.size());
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
                //System.out.println(""+defaultTableModel.getRowCount());
            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            initBL();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addbt){
            if (agencyBLService!=null){
                JDialog dialog=new JDialog(parent,"新增机构信息",true);
                dialog.getContentPane().add(new AgencyInfoPanel(dialog, parent, this, agencyBLService));
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
                new TranslucentFrame(this, "请选择一行待删除条目", Color.RED);
            } else {//选择了待删除的行
                if (agencyBLService!=null){
                    String id= (String)table.getValueAt(row, 0);
                    try {
                        agencyBLService.deleteAgency(id);
                        refreshList();
                        new TranslucentFrame(this, MessageType.DELETE_SUCCESS, Color.GREEN);
                    } catch (RemoteException e1) {
                        new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    }
                }
                else {
                    initBL();
                }
            }
        }
        else if (e.getSource()==modifybt){
            int row=table.getSelectedRow();
            if (row==-1){//没有选择任何行
                new TranslucentFrame(this, "请选择一行待修改条目", Color.RED);
            } else {//选择了待修改的行
                if (agencyBLService!=null){
                    String id=(String)table.getValueAt(row, 0);
                    String name=(String) table.getValueAt(row, 1);
                    String type=(String) table.getValueAt(row, 2);
                    String loc=(String) table.getValueAt(row, 3);
                    int area=(Integer) table.getValueAt(row, 4);
                    int rent=(Integer) table.getValueAt(row, 5);

                    AgencyVO vo=new AgencyVO(name, type, id, loc, area, rent);
                    JDialog dialog=new JDialog(parent,"修改机构信息",false);
                    dialog.getContentPane().add(new AgencyModifyPanel(dialog, parent, this, agencyBLService, vo));
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
