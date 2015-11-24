package presentation;

import presentation.Images.Images;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harry on 2015/11/24.
 */
public class ManagerGuidePanel extends JPanel{
    public static final int BTNUMBER=7;

    public ManagerGuidePanel(){
        JLabel title=new JLabel("导航栏");
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        title.setFont(new Font("宋体",Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);

        JToggleButton[] bts=new JToggleButton [BTNUMBER];

        bts[0] =new JToggleButton("审批单据", Images.RECEIPT_IMAGE);
        bts[1] =new JToggleButton("薪水策略", Images.SALARY_IMAGE);
        bts[2]=new JToggleButton("价格/距离策略", Images.MONEY_DISTANCE_IMAGE);
        bts[3]=new JToggleButton("人员管理", Images.STAFF_IMAGE);
        bts[4]=new JToggleButton("机构管理", Images.AGENCY_IMAGE);
        bts[5]=new JToggleButton("系统日志", Images.RECORD_IMAGE);
        bts[6]=new JToggleButton("报表查询", Images.BAOBIAO_IMAGE);

        ButtonGroup btgroup=new ButtonGroup();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;


        this.add(title,gbc);
        for(int i=0;i<BTNUMBER;i++){
            bts[i].setPreferredSize(new Dimension(170,60));
            btgroup.add(bts[i]);
            gbc.gridy++;
            this.add(bts[i],gbc);
        }

    }
}
