package presentation.contentpanel.depotpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.commodityblservice.CommodityBLService;
import constent.Constent;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.Location;
import vo.commodityvo.CommodityVO;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/12/2.
 */
public class DepotPanDianPanel extends JPanel implements ActionListener{
    MainFrame parent;
    MyButton refreshbt=new MyButton("刷新数据");
    CommodityBLService commodityBLService;

    MyDefaultTableModel defaultTableModel;
    MyTable table;

    public DepotPanDianPanel(MainFrame par) {

        this.parent=par;

        String [] names={"快递编号","入库日期","目的地","区号","排号","架号","位号"};

        defaultTableModel=new MyDefaultTableModel(names,0);
        table=new MyTable(defaultTableModel);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridwidth=5;
        gbc.gridx=gbc.gridy=0;
        this.add(new JScrollPane(table),gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.EAST;

        gbc.gridwidth=1;
        gbc.gridx=4;
        gbc.gridy=2;
        this.add(refreshbt,gbc);


        refreshbt.addActionListener(this);
        initBL();
    }

    private void initBL(){
        try {
            commodityBLService= BLFactory.getCommodityBLService();
        } catch (RemoteException e) {
            new ErrorDialog(parent, "服务器连接超时");
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
                defaultTableModel.getDataVector().clear();
                for (CommodityVO vo: commodityVOs){
                    String orderID=vo.getExpressNumber();
                    String time= Constent.DATE_FORMAT.format(vo.getInTime());
                    String destination=vo.getDestination();
                    Location loc=vo.getStoreloc();

                    Object[] data={orderID, time, destination, loc.getRegionID(), loc.getRowID(), loc.getShelfID(), loc.getPostID()};
                    defaultTableModel.addRow(data);
                }

            } catch (RemoteException e) {
                new ErrorDialog(parent, "服务器连接超时");
            } catch (SQLException e) {
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
        }
    }

}
