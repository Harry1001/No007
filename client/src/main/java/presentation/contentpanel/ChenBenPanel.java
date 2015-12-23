package presentation.contentpanel;

import MainFrame.MainFrame;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import vo.financevo.ProfitVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/5.
 */
public class ChenBenPanel extends JPanel {

    private MyLabel titileL=new MyLabel("截至当前为止公司财政情况为：");
    private MyLabel incomeL=new MyLabel("总收入");
    private MyLabel outcomeL=new MyLabel("总支出");
    private MyLabel profitL=new MyLabel("总利润");
    private MyTextField incomeT=new MyTextField();
    private MyTextField outcomeT=new MyTextField();
    private MyTextField profitT=new MyTextField();
    private MyButton confirmbt=new MyButton("确认");

    public ChenBenPanel( ProfitVO vo){
        initUI();

        initData(vo);
    }

    private void initData(ProfitVO vo){
        incomeT.setText(vo.income+"");
        outcomeT.setText(vo.outcome+"");
        profitT.setText(vo.profit+"");
    }

    private void initUI(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;
        gbc.weighty=1.0;

        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=2;
        this.add(titileL,gbc);
        gbc.gridwidth=1;
        gbc.gridy=1;
        this.add(incomeL,gbc);
        gbc.gridy++;
        this.add(outcomeL,gbc);
        gbc.gridy++;
        this.add(profitL,gbc);
        gbc.gridx++;
        gbc.gridy=1;
        this.add(incomeT,gbc);
        gbc.gridy++;
        this.add(outcomeT,gbc);
        gbc.gridy++;
        this.add(profitT,gbc);
        gbc.gridy++;
        this.add(confirmbt,gbc);

        //设置成本收益表的文本框不可被编辑
        incomeT.setEditable(false);
        outcomeT.setEditable(false);
        profitT.setEditable(false);
    }

}
