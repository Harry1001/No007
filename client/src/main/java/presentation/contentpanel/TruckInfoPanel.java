package presentation.contentpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/11/27.
 */
public class TruckInfoPanel extends JPanel implements ActionListener {

    Dialog parent;

    JLabel truckIDL=new JLabel("车辆代号");
    JLabel engineIDL=new JLabel("发动机号");
    JLabel chepaiL=new JLabel("车牌号");
    JLabel dipanL=new JLabel("底盘号");
    JLabel goumaiL=new JLabel("购买时间");
    JLabel fuyiL=new JLabel("服役时间");
    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");

    JTextField[] textFields=new JTextField[6];

    public TruckInfoPanel(Dialog par){
        this.parent=par;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        this.add(truckIDL,gbc);
        gbc.gridy=1;
        this.add(engineIDL,gbc);
        gbc.gridy++;
        this.add(chepaiL,gbc);
        gbc.gridy++;
        this.add(dipanL,gbc);
        gbc.gridy++;
        this.add(goumaiL,gbc);
        gbc.gridy++;
        this.add(fuyiL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        for(int i=0;i<6;i++){
            textFields[i]=new JTextField(25);
            gbc.gridy=i;
            this.add(textFields[i],gbc);
        }

        gbc.gridx=0;
        gbc.gridy=7;
        this.add(submitbt,gbc);
        gbc.gridx++;
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
