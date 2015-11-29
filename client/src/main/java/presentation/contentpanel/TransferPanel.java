package presentation.contentpanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by Harry on 2015/11/28.
 */
public class TransferPanel extends JPanel {

    private String[] positions;//todo 将来可以添加，所以地点的值需要从数据层读取

    Frame parent;

    JLabel[] labels=new JLabel[8];
    JTextField [] textFields=new JTextField[7];
    JRadioButton[] vehicles=new JRadioButton[3];
    JButton calfeebt=new JButton("计算运费");
    JButton addbt=new JButton("添加");
    JButton deletebt=new JButton("删除");
    JButton submitbt=new JButton("提交");
    JButton cancelbt=new JButton("取消");
    JComboBox<String> fromCombo;
    JComboBox<String> toCombo;
    DefaultTableModel defaultTableModel;
    JTable table;
    JTextField orderT=new JTextField(25);

    public TransferPanel(Frame par) {
        this.parent=par;

        labels[0]=new JLabel("中转方式");
        labels[1]=new JLabel("中转日期");
        labels[2]=new JLabel("中转单编号");
        labels[3]=new JLabel("班次/车牌号");
        labels[4]=new JLabel("运费");
        labels[5]=new JLabel("出发地");
        labels[6]=new JLabel("目的地");
        labels[7]=new JLabel("货柜号");

        for(int i=0;i<7;i++){
            textFields[i]=new JTextField(25);
        }

        positions= new String[]{"北京","上海","南京"};//todo 需要从数据库中读取
        fromCombo=new JComboBox<String>(positions);
        toCombo=new JComboBox<String>(positions);

        vehicles[0]=new JRadioButton("飞机");
        vehicles[1]=new JRadioButton("火车");
        vehicles[2]=new JRadioButton("汽车");
        ButtonGroup btgroup=new ButtonGroup();
        for(JRadioButton bt:vehicles){
            btgroup.add(bt);
        }
        vehicles[1].setSelected(true);

        String [] names={"本次装箱所有托运单号"};

        defaultTableModel=new DefaultTableModel(names, 0);
        table=new JTable(defaultTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(300,200));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.NONE;

        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<5;gbc.gridy++){
            this.add(labels[gbc.gridy],gbc);
        }

        gbc.gridy=0;
        for( gbc.gridx=1;gbc.gridx<4;gbc.gridx++){
            this.add(vehicles[gbc.gridx-1],gbc);
        }

        for(gbc.gridx=1,gbc.gridy=1;gbc.gridy<5;gbc.gridy++){
            this.add(textFields[gbc.gridy-1],gbc);
        }

        gbc.gridx=2;
        gbc.gridy=4;
        this.add(calfeebt,gbc);

        for(gbc.gridx=3,gbc.gridy=1;gbc.gridy<4;gbc.gridy++){
            this.add(labels[gbc.gridy+4],gbc);
        }

        gbc.gridx=4;
        gbc.gridy=1;
        gbc.anchor=GridBagConstraints.WEST;
        this.add(fromCombo,gbc);
        gbc.gridx=5;
        this.add(textFields[4],gbc);
        gbc.gridx=4;
        gbc.gridy=2;
        this.add(toCombo,gbc);
        gbc.gridx=5;
        this.add(textFields[5],gbc);

        gbc.gridx=5;
        gbc.gridy=3;
        this.add(textFields[6],gbc);

        gbc.gridx=0;
        gbc.gridy=6;
        gbc.gridwidth=3;
        gbc.gridheight=4;
        this.add(new JScrollPane(table),gbc);
        gbc.gridx=3;
        gbc.gridy=6;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        this.add(orderT,gbc);

        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridy=7;
        this.add(addbt,gbc);
        gbc.gridy=8;
        this.add(deletebt,gbc);

        gbc.gridx=0;
        gbc.gridy=10;
        gbc.gridwidth=6;
        this.add(new JSeparator(),gbc);
        gbc.gridx=2;
        gbc.gridy++;
        gbc.gridwidth=1;
        this.add(submitbt,gbc);
        gbc.gridx=3;
        this.add(cancelbt,gbc);
    }
}
