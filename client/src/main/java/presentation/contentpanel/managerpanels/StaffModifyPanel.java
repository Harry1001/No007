package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.StaffBLService;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.ErrorDialog;
import presentation.commoncontainer.TranslucentFrame;
import typeDefinition.Job;
import typeDefinition.MessageType;
import vo.infovo.StaffVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Harry on 2015/12/19.
 */
public class StaffModifyPanel extends StaffInfoPanel {

    private String originID;
    private int frequecy;

    public StaffModifyPanel(MainFrame parent, JDialog dialog, StaffBLService bl, StaffListPanel panel, StaffVO vo) {
        super(parent, dialog, bl, panel);
        originID=vo.getStaffID();
        frequecy=vo.getWorkFrequency();
        initData(vo);
    }

    private void initData(StaffVO vo ){
        String id=vo.getStaffID();
        String name=vo.getName();
        String gender=vo.getGender();
        Date birthday=vo.getBirthday();
        Job job=vo.getPosition();
        idT.setText(id);
        nameT.setText(name);
        p1.setDate(birthday);
        genderC.setSelectedItem(gender);
        jobC.setSelectedIndex(job.ordinal()-1);//@see StaffInfoPanel 127行为什么-1
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                try {
                    String id=idT.getText();
                    String name=nameT.getText();
                    String gender=genderC.getSelectedItem().toString();
                    Date birthday=p1.getDate();
                    Job position=Job.values()[jobC.getSelectedIndex()+1];

                    StaffVO vo=new StaffVO(id, name, gender, birthday, position, 0, this.frequecy);
                    staffBLService.modifyStaff(originID, vo);
                    staffListPanel.refreshList();
                    new TranslucentFrame(staffListPanel, MessageType.MODIFY_SUCCESS, Color.GREEN);
                    dialog.dispose();
                } catch (TimeFormatException e1) {
                    new TranslucentFrame(staffListPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(staffListPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println("修改人员信息sql："+e1.getMessage());
                } catch (InfoBLException e1) {
                    new TranslucentFrame(staffListPanel, e1.getMessage(), Color.RED);
                }
            }
        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
