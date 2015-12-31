package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import blfactory.BLFactory;
import businessLogicService.infoblservice.DriverBLService;
import businessLogicService.recordblservice.RecordBLService;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.ErrorDialog;
import presentation.commoncontainer.TranslucentFrame;
import typeDefinition.MessageType;
import vo.infovo.DriverVO;
import vo.recordvo.RecordVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Harry on 2015/12/17.
 */
public class DriverModifyPanel extends DriverInfoPanel {

    private String originID;

    public DriverModifyPanel(MainFrame par, JDialog dialog, DriverListPanel listPanel, DriverBLService blService, DriverVO vo) {
        super(par, dialog, listPanel, blService);
        this.originID=vo.getDriverID();
        initData(vo);
    }

    private void initData(DriverVO vo){
        textFields[0].setText(vo.getDriverID());
        textFields[1].setText(vo.getName());
        birthday.setDate(vo.getBirthday());
        textFields[2].setText(vo.getIDNum());
        textFields[3].setText(vo.getPhoneNum());
        genderC.setSelectedItem(vo.getGender());
        limitTime.setDate(vo.getLicenseLimit());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                try {
                    DriverVO vo = new DriverVO(textFields[0].getText(), textFields[1].getText(), birthday.getDate(),
                            textFields[2].getText(), textFields[3].getText(), genderC.getSelectedItem().toString(),
                            limitTime.getDate());
                    driverBLService.modifyDriver(originID, vo);
                    listPanel.refreshList();
                    RecordVO rvo=new RecordVO(new Date(),parent.getUserIdentity().getName(),"修改司机信息:"+vo.getName());
                    RecordBLService rb= BLFactory.getRecordBLService();
                    rb.add(rvo);
                    dialog.dispose();
                    new TranslucentFrame(listPanel, MessageType.MODIFY_SUCCESS, Color.GREEN);
                } catch (TimeFormatException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(listPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (InfoBLException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);//数据库中已经包含该司机编号
                } catch (MalformedURLException e1) {
                    new ErrorDialog(parent, "MalformedURLException");
                } catch (NotBoundException e1) {
                    new ErrorDialog(parent, "NotBoundException");
                }
            }
        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
