package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.AgencyBLService;

import javax.swing.*;

/**
 * Created by Harry on 2015/12/8.
 */
public class AgencyModifyPanel extends AgencyInfoPanel {

    private String originID;

    public AgencyModifyPanel(JDialog dialog, MainFrame parent, AgencyListPanel panel, AgencyBLService agencyBLService) {
        super(dialog, parent, panel, agencyBLService);
    }
}
