package presentation.contentpanel.hubpanels;

import MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Harry on 2015/11/28.
 */
public class TransferPanel extends JPanel {

    private MainFrame parent;
    private TransferListPanel transferListPanel;
    private TransferReceiptPanel transferReceiptPanel;

    public TransferPanel(MainFrame par) {
        this.parent=par;
        this.setOpaque(false);
        initInstance();
        initContentPane();
    }

    public void showList(){
        ((CardLayout)this.getLayout()).show(this, "one");
    }

    public void showTransferReceipt(){
        ((CardLayout)this.getLayout()).show(this, "two");
    }

    private void initInstance(){
        transferListPanel=new TransferListPanel(parent, this);
        transferReceiptPanel=new TransferReceiptPanel(parent, this);
    }

    private void initContentPane(){
        this.setLayout(new CardLayout());
        this.add("one", transferListPanel);
        this.add("two", transferReceiptPanel);
    }


}
