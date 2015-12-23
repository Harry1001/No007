package presentation.contentpanel.storepanels;

import vo.financevo.FinanceVO;

import javax.swing.*;

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
        this.textArea=new JTextArea(20, 50);
        textArea.append(vo.printStaff());
        textArea.append(vo.printAgency());
        textArea.append(vo.printBankAccount());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        this.add(new JScrollPane(textArea));
    }
}
