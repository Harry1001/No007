package presentation.contentpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/28.
 */
public class AgencyInfoPanel extends JPanel implements ActionListener{
    Dialog parent;
    JLabel[] labels=new JLabel[6];
    JTextField[] textFields=new JTextField[5];
    JComboBox<String> type;

    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    public AgencyInfoPanel(Dialog parent) {
        this.parent = parent;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        String [] names={"机构编号","名称","机构类型","位置","面积","土地租金"};
        for(int i=0;i<6;i++){
            labels[i]=new JLabel(names[i]);
        }

        String[] s={"营业厅","中转中心"};

        type=new JComboBox<String>(s);

        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<6;gbc.gridy++){
            this.add(labels[gbc.gridy],gbc);
        }

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(textFields[0],gbc);
        gbc.gridy++;
        this.add(textFields[1],gbc);
        gbc.gridy++;
        this.add(type,gbc);
        gbc.gridy++;
        this.add(textFields[2],gbc);
        gbc.gridy++;
        this.add(textFields[3],gbc);
        gbc.gridy++;
        this.add(textFields[4],gbc);

        gbc.gridx=0;
        gbc.gridy=6;
        this.add(submitbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            //todo
        }
        else if (e.getSource()==cancelbt){
            parent.dispose();
        }
    }
}
