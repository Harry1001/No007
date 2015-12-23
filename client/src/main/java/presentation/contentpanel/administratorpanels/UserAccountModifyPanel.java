package presentation.contentpanel.administratorpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.UserAccoutBLService;
import myexceptions.InfoBLException;
import presentation.commoncontainer.ErrorDialog;
import typeDefinition.Job;
import vo.infovo.UserAccountVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/12/17.
 */
public class UserAccountModifyPanel extends UserAccountInfoPanel {

    protected String originUserID;

    public UserAccountModifyPanel(MainFrame parent, JDialog dialog, UserAccountListPanel listPanel,
                                  UserAccoutBLService userAccoutBL, UserAccountVO vo) {
        super(parent, dialog, listPanel, userAccoutBL);
        this.originUserID=vo.getUserID();
        initData(vo);
    }

    private void initData(UserAccountVO vo){
        idT.setText(vo.getUserID());
        nameT.setText(vo.getName());
        passwordField.setText(vo.getPassword());
        jobC.setSelectedIndex(vo.getPosition().ordinal()-1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkFormat()){
                UserAccountVO vo = new UserAccountVO(idT.getText(), nameT.getText(),
                        Job.values()[jobC.getSelectedIndex()], new String(passwordField.getPassword()));
                try {
                    userAccoutBLService.modifyUserAccount(originUserID, vo);
                    listPanel.refreshList();
                    dialog.dispose();
                } catch (InfoBLException e1) {
                    new ErrorDialog(parent, e1.getMessage());
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "数据库异常");
                }
            }

        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
