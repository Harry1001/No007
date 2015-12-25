package presentation.contentpanel.managerpanels;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

/**
 * Created by Harry on 2015/12/5.
 */
public class ShenPiPanel extends JPanel {
    private Frame parent;

    public ShenPiPanel(Frame par){
        this.parent=par;
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(ALLBITS),"审批单据",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 25)));
    }

    //todo
}
