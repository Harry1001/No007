package presentation.contentpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/28.
 */
public class UserAccountInfoPanel extends JPanel implements ActionListener {

    Dialog parent;
    JLabel idL=new JLabel("工号");
    JLabel jobL=new JLabel("职位");
    JLabel passwordL=new JLabel("密码");
    JTextField idT=new JTextField(15);
    JComboBox<String> jobC;
    JPasswordField passwordField=new JPasswordField(15);
    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    public UserAccountInfoPanel( Dialog parent) {
        this.parent = parent;

        String[] s={"快递员","营业厅业务员","中转中心业务员","仓库管理员","财务人员","总经理","管理员"};
        jobC=new JComboBox<String>(s);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        gbc.gridx=0;
        gbc.gridy=0;
        this.add(idL,gbc);
        gbc.gridy++;
        this.add(passwordL,gbc);
        gbc.gridy++;
        this.add(jobL,gbc);
        gbc.gridy++;

        gbc.gridx=1;
        gbc.anchor=GridBagConstraints.CENTER;

        this.add(submitbt,gbc);
        gbc.anchor=GridBagConstraints.EAST;
        gbc.gridx=1;
        this.add(cancelbt,gbc);
        gbc.gridy--;
        gbc.anchor=GridBagConstraints.WEST;
        this.add(jobC,gbc);
        gbc.gridy--;
        this.add(passwordField,gbc);
        gbc.gridy--;
        this.add(idT,gbc);

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
