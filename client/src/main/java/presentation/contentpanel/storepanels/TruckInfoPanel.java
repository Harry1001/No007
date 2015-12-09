package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.TruckBLService;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.TimePanel;
import presentation.commonpanel.ErrorDialog;
import vo.infovo.TruckVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Harry on 2015/11/27.
 */
public class TruckInfoPanel extends JPanel implements ActionListener {

    MainFrame parent;
    JDialog dialog;
    TruckBLService truckBLService;
    TruckListPanel listPanel;

    MyLabel truckIDL=new MyLabel("车辆代号");
    MyLabel engineIDL=new MyLabel("发动机号");
    MyLabel chepaiL=new MyLabel("车牌号");
    MyLabel dipanL=new MyLabel("底盘号");
    MyLabel goumaiL=new MyLabel("购买时间");
    MyLabel fuyiL=new MyLabel("服役时间");
    MyButton submitbt=new MyButton("提交");
    MyButton cancelbt=new MyButton("取消");

    MyTextField[] textFields=new MyTextField[5];
    TimePanel timePanel=new TimePanel();

    public TruckInfoPanel(MainFrame par, JDialog dialog, TruckListPanel listPanel, TruckBLService blService){
        this.parent=par;
        this.dialog=dialog;
        this.listPanel=listPanel;
        this.truckBLService=blService;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        this.add(truckIDL,gbc);
        gbc.gridy=1;
        this.add(engineIDL,gbc);
        gbc.gridy++;
        this.add(chepaiL,gbc);
        gbc.gridy++;
        this.add(dipanL,gbc);
        gbc.gridy++;
        this.add(fuyiL,gbc);
        gbc.gridy++;
        this.add(goumaiL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        for(int i=0;i<5;i++){
            textFields[i]=new MyTextField();
            gbc.gridy=i;
            this.add(textFields[i],gbc);
        }

        gbc.gridy++;
        this.add(timePanel, gbc);

        gbc.gridx=0;
        gbc.gridy=7;
        this.add(submitbt,gbc);
        gbc.gridx++;
        this.add(cancelbt,gbc);

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    protected boolean checkAll(){
        //todo
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                try {
                    String id = textFields[0].getText();
                    String chepai = textFields[1].getText();
                    String engine = textFields[2].getText();
                    String dipan = textFields[3].getText();
                    int fuyiTime = Integer.parseInt(textFields[4].getText());
                    Date buyTime = timePanel.getDate();

                    TruckVO vo = new TruckVO(id, chepai, engine, dipan, buyTime, fuyiTime);
                    truckBLService.addTruck(vo);
                    listPanel.refreshList();
                } catch (TimeFormatException e1) {
                    new ErrorDialog(parent, "时间格式错误");
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (InfoBLException e1) {
                    new ErrorDialog(parent, e1.getMessage());
                }
            }
        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
