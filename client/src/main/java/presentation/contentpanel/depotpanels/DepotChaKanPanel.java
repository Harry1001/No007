package presentation.contentpanel.depotpanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.commodityblservice.CommodityBLService;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.commodityvo.CheckResultVO;

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
import java.util.Date;

/**
 * Created by Harry on 2015/12/5.
 */
public class DepotChaKanPanel extends JPanel implements ActionListener{

    private CommodityBLService commodityBLService;

    private MainFrame parent;
    private TimePanel fromTimePanel;
    private TimePanel toTimePanel;
    private MyLabel fromTimeL;
    private MyLabel toTimeL;
    private MyLabel inNumL;
    private MyLabel outNumL;
    private MyLabel inNum;
    private MyLabel outNum;
    private MyButton confirmbt;

    public DepotChaKanPanel(MainFrame par){
        this.parent=par;
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"库存查看",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
        initUI();
        setHotKey();

        confirmbt.addActionListener(this);

        initBL();
    }

    private void setHotKey(){
        confirmbt.setMnemonic('O');
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirmbt){
            if (commodityBLService!=null){
                try{
                    Date from=fromTimePanel.getDate();
                    Date to=toTimePanel.getDate();
                    if (from.before(to)){
                        String hubID=parent.getUserIdentity().getId().substring(0,4);
                        CheckResultVO resultVO=commodityBLService.getList(hubID, from, to);
                        inNum.setText(" "+resultVO.getDepotinnum());
                        outNum.setText(" "+resultVO.getDepotoutnum());
                        new TranslucentFrame(this, "数据已更新", Color.GREEN);
                    }
                    else {
                        new TranslucentFrame(this, "开始时间必须早于结束时间", Color.RED);
                    }
                } catch (TimeFormatException e1) {
                    new TranslucentFrame(this, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(this, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println("库存查看sql："+e1.getMessage());
                    new ErrorDialog(parent, "SQLException");
                } catch (MalformedURLException e1) {
                    new ErrorDialog(parent, "MalformedURLException");
                } catch (NotBoundException e1) {
                    new ErrorDialog(parent, "NotBoundException");
                } catch (NamingException e1) {
                    new ErrorDialog(parent, "NamingException");
                }
            }
            else {
                initBL();
            }
        }
    }

    private void initUI(){
        fromTimeL=new MyLabel("开始时间: ");
        toTimeL=new MyLabel("结束时间: ");
        inNumL=new MyLabel("入库数量: ");
        outNumL=new MyLabel("出库数量: ");
        inNum=new MyLabel("");
        outNum=new MyLabel("");
        fromTimePanel=new TimePanel();
        toTimePanel=new TimePanel();
        confirmbt=new MyButton("确认(O)");

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=gbc.weighty=1.0;

        gbc.gridx=gbc.gridy=0;
        this.add(fromTimeL, gbc);
        gbc.gridy++;
        this.add(toTimeL,gbc);
        gbc.gridy++;
        this.add(inNumL,gbc);
        gbc.gridy++;
        this.add(outNumL,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        this.add(fromTimePanel,gbc);
        gbc.gridy++;
        this.add(toTimePanel,gbc);
        gbc.gridy++;
        this.add(inNum,gbc);
        gbc.gridy++;
        this.add(outNum, gbc);
        gbc.gridx=2;
        gbc.gridy=1;
        this.add(confirmbt,gbc);
    }
}
