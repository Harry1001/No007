package presentation.contentpanel.financepanels;

import MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/25.
 */
public class OutcomePanel extends JPanel {
    private MainFrame parent;
    private OutcomeListPanel listPanel;
    private PayReceiptPanel payReceiptPanel;

    public OutcomePanel(MainFrame par){
        this.parent=par;

        this.setOpaque(false);
        initInstance();
        initContentPane();
        showList();
    }

    /**
     * 显示付款单列表面板
     */
    public void showList(){
        ((CardLayout)this.getLayout()).show(this, "one");
    }

    /**
     * 显示新建付款单面板
     */
    public void showPayReceipt(){
        ((CardLayout)this.getLayout()).show(this, "two");
    }

    private void initInstance(){
        this.listPanel=new OutcomeListPanel(parent, this);
        this.payReceiptPanel=new PayReceiptPanel(parent, this);
    }

    private void initContentPane(){
        this.setLayout(new CardLayout());
        this.add("one", listPanel);
        this.add("two", payReceiptPanel);
    }
}
