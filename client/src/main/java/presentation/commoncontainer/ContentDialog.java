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
        super(parent, title, true);
        this.parent = parent;
        confirmbt = new MyButton("确认");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = gbc.gridy = 0;
        this.getContentPane().add(panel, gbc);
        gbc.gridy++;
        this.getContentPane().add(confirmbt, gbc);
        this.setLocationRelativeTo(parent);
        confirmbt.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
