package presentation.contentpanel.managerpanels;

import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/29.
 */
public class FeeStrategyPanel extends JavaPanel implements ActionListener {

    Frame parent;
    JPanel paypanel=new JPanel();
    JPanel chargepanel= new JPanel();
    JLabel[] labels=new JLabel[6];
    JTextField[] textFields=new JTextField[6];
    JButton confirmbt=new JButton("确认");
    JButton cancelbt=new JButton("取消");

    public FeeStrategyPanel(Frame par){
        this.parent=par;

        labels[0]=new JLabel("飞机");
        labels[1]=new JLabel("火车");
        labels[2]=new JLabel("汽车");
        labels[3]=new JLabel("经济快递");
        labels[4]=new JLabel("标准快递");
        labels[5]=new JLabel("特快专递");

        for(int i=0;i<6;i++){
            textFields[i]=new JTextField(15);
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;

        paypanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,3),
                "支出价格/距离策略(元/吨/公里)"));
        chargepanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,3),
                "收入价格/距离策略(元/公斤/公里)"));

        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<3;gbc.gridy++){
            paypanel.add(labels[gbc.gridy],gbc);
            chargepanel.add(labels[gbc.gridy+3],gbc);
        }
        gbc.gridx=1;
        for(gbc.gridy=0;gbc.gridy<3;gbc.gridy++){
            paypanel.add(textFields[gbc.gridy],gbc);
            chargepanel.add(textFields[gbc.gridy+3],gbc);
        }

        gbc.gridx=gbc.gridy=0;
        gbc.gridwidth=2;
        this.add(paypanel,gbc);
        gbc.gridy=1;
        this.add(chargepanel,gbc);
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridy=3;
        this.add(confirmbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);

        confirmbt.addActionListener(this);
        cancelbt.addActionListener(this);

        refreshData();
    }

    public void refreshData(){
        //todo 从数据层读取数据并刷新，rmi异常在这里catch
    }


    public void actionPerformed(ActionEvent e) {

    }
}
