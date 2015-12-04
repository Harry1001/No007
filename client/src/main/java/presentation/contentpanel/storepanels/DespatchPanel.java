package presentation.contentpanel.storepanels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Harry on 2015/11/27.
 */
public class DespatchPanel extends JPanel implements ActionListener{
    JLabel timeL;
    JLabel numL;
    JLabel courierL;

    JTextField timeT;
    JTextField numT;
    JTextField courierT;

    JButton submitBT;
    JButton cancelBT;

    Frame parent;

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间格式

    public DespatchPanel(Frame par){
        this.parent=par;

        timeL=new JLabel("到达日期");
        numL=new JLabel("订单条形码号");
        courierL=new JLabel("派送员");

        timeT=new JTextField(25);
        numT=new JTextField(25);
        courierT=new JTextField(25);

        //设置时间框里自动生成系统时间
        timeT.setText(df.format(new Date()));

        submitBT=new JButton("提交");
        cancelBT=new JButton("清空输入");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        this.add(timeL,gbc);
        gbc.gridy=1;
        this.add(numL,gbc);
        gbc.gridy=2;
        this.add(courierL,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        this.add(timeT,gbc);
        gbc.gridy=1;
        this.add(numT,gbc);
        gbc.gridy=2;
        this.add(courierT,gbc);

        gbc.fill=GridBagConstraints.NONE;
        gbc.gridx=0;
        gbc.gridy=4;
        this.add(submitBT,gbc);
        gbc.gridx=1;
        this.add(cancelBT,gbc);

        submitBT.addActionListener(this);
        cancelBT.addActionListener(this);

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"派件单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitBT){
            //todo 提交单据
        }
        else {//取消按钮
            timeT.setText(df.format(new Date()));
            numT.setText("");
            courierT.setText("");
        }
    }
}
