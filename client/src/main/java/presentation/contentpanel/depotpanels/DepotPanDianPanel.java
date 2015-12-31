package presentation.contentpanel.depotpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.commodityblservice.CommodityBLService;
import constent.Constent;
import presentation.commoncontainer.*;
import typeDefinition.Location;
import typeDefinition.MessageType;
import vo.commodityvo.CommodityVO;

import javax.naming.NamingException;
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
 * Created by Harry on 2015/12/2.
 */
public class DepotPanDianPanel extends JPanel implements ActionListener{
    MainFrame parent;
    MyButton refreshbt=new MyButton("刷新(R)");
    CommodityBLService commodityBLService;

    MyDefaultTableModel defaultTableModel;
    MyTable table;

    Vector<String> names=new Vector<String>();

    public DepotPanDianPanel(MainFrame par) {

        this.parent=par;
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"库存盘点",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        
        String [] namestr={"快递编号","入库日期","目的地","区号","排号","架号","位号"};
        for (int i=0;i<namestr.length;i++){
            names.add(namestr[i]);
        }

        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=gbc.weighty=1.0;

        gbc.gridwidth=5;
        gbc.gridx=gbc.gridy=0;
        this.add(new JScrollPane(table),gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.weightx=gbc.weighty=0.0;
        gbc.gridwidth=1;
        gbc.gridx=4;
        gbc.gridy=2;
        this.add(refreshbt,gbc);

        setHotKey();


        refreshbt.addActionListener(this);
        initBL();
    }

    private void setHotKey(){
        refreshbt.setMnemonic('R');
    }

    private void initBL(){
        try {
            commodityBLService= BLFactory.getCommodityBLService();
        } catch (RemoteException e) {
            new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
        } catch (MalformedURLException e) {
            new ErrorDialog(parent, "MalformedURLException");
        } catch (NotBoundException e) {
            new ErrorDialog(parent, "NotBoundException");
        } catch (NamingException e) {
            new ErrorDialog(parent, "NamingException");
        }
    }

    private void refreshData(){
        if (commodityBLService!=null){
            String hubID=parent.getUserIdentity().getId().substring(0,4);
            try{
                ArrayList<CommodityVO> commodityVOs=commodityBLService.getList(hubID);
                Vector<Vector> data=new Vector<Vector>();
                for (CommodityVO vo: commodityVOs){
                    String orderID=vo.getExpressNumber();
                    String time= Constent.DATE_FORMAT.format(vo.getInTime());
                    String destination=vo.getDestination();
                    Location loc=vo.getStoreloc();
                    Vector<Object> item=new Vector<Object>();
                    item.add(orderID);
                    item.add(time);
                    item.add(destination);
                    item.add(loc.getRegionID());
                    item.add(loc.getRowID());
                    item.add(loc.getShelfID());
                    item.add(loc.getPostID());
                    data.add(item);
                }
                defaultTableModel.setDataVector(data,names);
                table.revalidate();
                table.updateUI();

            } catch (RemoteException e) {
                new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
            } catch (SQLException e) {
                System.out.println("库存盘点sql："+e.getMessage());
                new ErrorDialog(parent, "SQLException");
            } catch (NamingException e) {
                new ErrorDialog(parent, "NamingException");
            }
        }
        else {
            initBL();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==refreshbt){
            refreshData();
            new TranslucentFrame(this, "刷新成功", Color.GREEN);
        }
    }

}
