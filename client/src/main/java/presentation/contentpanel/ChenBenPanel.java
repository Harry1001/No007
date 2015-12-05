package presentation.contentpanel;

import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/5.
 */
public class ChenBenPanel extends JPanel implements ActionListener{

    private Dialog parent;
    private MyLabel incomeL=new MyLabel("总收入");
    private MyLabel outcomeL=new MyLabel("总支出");
    private MyLabel profitL=new MyLabel("总利润");
    private MyTextField incomeT=new MyTextField();
    private MyTextField outcomeT=new MyTextField();
    private MyTextField profitT=new MyTextField();
    private MyButton confirmbt=new MyButton("确认");

    public ChenBenPanel(Dialog par){
        this.parent=par;
        init();
    }

    private void init(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;
        gbc.weighty=1.0;

        this.add(incomeL,gbc);
        gbc.gridy++;
        this.add(outcomeL,gbc);
        gbc.gridy++;
        this.add(profitL,gbc);
        gbc.gridx++;
        gbc.gridy=0;
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

    public void actionPerformed(ActionEvent e) {

    }
}
