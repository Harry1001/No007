package presentation.contentpanel;

import vo.financevo.FinanceVO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/12/23.
 */
public class BankInfoPanel extends JPanel {
    private FinanceVO financeVO;
    private JTextArea textArea;

    public BankInfoPanel(FinanceVO vo){
        this.financeVO=vo;
        initUI(vo);
    }

    private void initUI(FinanceVO vo){
        this.textArea=new JTextArea(15, 25);
        textArea.setFont(new Font("微软雅黑",Font.PLAIN,20));
        textArea.append(vo.printStaff());
        textArea.append("\n");
        textArea.append(vo.printAgency());
        textArea.append("\n");
        textArea.append(vo.printBankAccount());
        textArea.append("\n");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        this.add(new JScrollPane(textArea));
    }
}
