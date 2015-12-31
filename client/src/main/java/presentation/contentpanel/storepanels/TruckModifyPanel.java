package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.TruckBLService;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.ErrorDialog;
import presentation.commoncontainer.TranslucentFrame;
import typeDefinition.MessageType;
import vo.infovo.TruckVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Harry on 2015/12/9.
 */
public class TruckModifyPanel extends TruckInfoPanel {

    protected String originID;

    public TruckModifyPanel(MainFrame par, JDialog dialog, TruckListPanel listPanel, TruckBLService blService,
                            TruckVO vo) {
        super(par, dialog, listPanel, blService);
        this.originID=vo.getTruckID();
        initData(vo);
    }


    private void initData(TruckVO vo){
        textFields[0].setText(vo.getTruckID());
        textFields[1].setText(vo.getEngineID());
        textFields[2].setText(vo.getLicenceID());
        textFields[3].setText(vo.getChassisID());
        textFields[4].setText(vo.getServeTime()+"");
        timePanel.setDate(vo.getBuyTime());

    }

    @Override
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
                    truckBLService.modifyTruck(originID, vo);
                    listPanel.refreshList();
                    dialog.dispose();
                    new TranslucentFrame(listPanel, MessageType.MODIFY_SUCCESS, Color.GREEN);
                } catch (TimeFormatException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(listPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (InfoBLException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);
                }
            }
        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }


    }
}
