package presentation.contentpanel.managerpanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/28.
 */
public class StaffInfoPanel extends JPanel implements ActionListener{
    Dialog parent;
    JLabel[] labels=new JLabel[5];
    JTextField idT=new JTextField(20);
    JTextField nameT=new JTextField(20);
    JComboBox<String> gender;
    JComboBox<String> job;
    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    public StaffInfoPanel(Dialog parent) {
        this.parent = parent;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        String[] names={"工号","姓名","性别","出生日期","职位"};
        for(int i=0;i<5;i++){
            labels[i]=new JLabel(names[i]);
        }

        String[] s1={"男","女"};
        String[] s2={"快递员","营业厅业务员","中转中心业务员","仓库管理员","财务人员","总经理","管理员"};
        gender=new JComboBox<String>(s1);
        job=new JComboBox<String>(s2);

        datepanel p1=new datepanel();


        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<5;gbc.gridy++){
            this.add(labels[gbc.gridy],gbc);
        }

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(idT,gbc);
        gbc.gridy++;
        this.add(nameT,gbc);
        gbc.gridy++;
        this.add(gender,gbc);
        gbc.gridy++;
        this.add(p1,gbc);
        gbc.gridy++;
        this.add(job,gbc);

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

    class datepanel extends JPanel{
        JLabel l1=new JLabel("年");
        JLabel l2=new JLabel("月");
        JLabel l3=new JLabel("日");
        JTextField t1=new JTextField(8);
        JTextField t2=new JTextField(4);
        JTextField t3=new JTextField(4);

        public datepanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.add(t1);
            this.add(l1);
            this.add(t2);
            this.add(l2);
            this.add(t3);
            this.add(l3);
        }


    }
}
