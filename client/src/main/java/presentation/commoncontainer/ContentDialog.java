package presentation.commoncontainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Harry on 2015/12/23.
 */
public class ContentDialog extends JDialog implements ActionListener {
    private JFrame parent;
    private MyButton confirmbt;

    public ContentDialog(JFrame parent, String title, JPanel panel) {
        super(parent, title, false);
        this.parent = parent;
        confirmbt = new MyButton("OK");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = gbc.gridy = 0;
        this.getContentPane().add(panel, gbc);
        gbc.gridy++;
        this.getContentPane().add(confirmbt, gbc);
        initLocation();
        confirmbt.addActionListener(this);

        setHotKey();
        this.pack();
        this.setVisible(true);
    }

    private void setHotKey(){
        confirmbt.setMnemonic('O');
        this.getRootPane().setDefaultButton(confirmbt);
    }

    private void initLocation(){
        this.setLocationRelativeTo(parent);
        int x=this.getX();
        int y=this.getY();
        x/=5;
        y/=5;
        this.setLocation(x,y);
    }

    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
