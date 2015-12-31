package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.AgencyBLService;
import myexceptions.InfoBLException;
import presentation.commoncontainer.ErrorDialog;
import presentation.commoncontainer.TranslucentFrame;
import typeDefinition.MessageType;
import vo.infovo.AgencyVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/12/8.
 */
public class AgencyModifyPanel extends AgencyInfoPanel {

    private String originID;

    public AgencyModifyPanel(JDialog dialog, MainFrame parent, AgencyListPanel panel,
                             AgencyBLService agencyBLService, AgencyVO vo) {
        super(dialog, parent, panel, agencyBLService);
        this.originID=vo.getAgencyID();
        initData(vo);
    }

    /**
     * 显示修改的原始数据
     */
    protected void initData(AgencyVO vo){
        textFields[0].setText(vo.getAgencyID());
        textFields[1].setText(vo.getAgencyName());
        type.setSelectedIndex((vo.getAgencyType().equals("营业厅")?0:1));
        textFields[2].setText(vo.getLocation());
        textFields[3].setText(vo.getArea()+"");
        textFields[4].setText(vo.getRent()+"");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                AgencyVO vo =new AgencyVO(textFields[1].getText(), type.getSelectedItem().toString(),
                        textFields[0].getText(), textFields[2].getText(), Integer.parseInt(textFields[3].getText()),
                        Integer.parseInt(textFields[4].getText()));
                try {
                    agencyBLService.modifyAgency(originID, vo);
                    listPanel.refreshList();
                    dialog.dispose();
                    new TranslucentFrame(listPanel, MessageType.MODIFY_SUCCESS, Color.GREEN);
                } catch (InfoBLException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(listPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println("agency sql:"+e1.getMessage());
                }
            }
        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
