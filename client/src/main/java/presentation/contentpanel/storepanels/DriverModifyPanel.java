package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.DriverBLService;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commonpanel.ErrorDialog;
import vo.infovo.DriverVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

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
                    dialog.dispose();
                } catch (TimeFormatException e1) {
                    new ErrorDialog(parent, "时间格式错误");
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (InfoBLException e1) {
                    new ErrorDialog(parent, e1.getMessage());//数据库中已经包含该司机编号
                }
            }
        }
        else if (e.getSource()==cancelbt){
            parent.dispose();
        }
    }
}
