package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.StaffBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.TimePanel;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.Job;
import vo.infovo.StaffVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Harry on 2015/11/28.
 */
public class StaffInfoPanel extends JPanel implements ActionListener{
    protected StaffBLService staffBLService;
    protected StaffListPanel staffListPanel;
    protected JDialog dialog;
    protected MainFrame parent;
    protected MyLabel[] labels=new MyLabel[5];
    protected MyTextField idT=new MyTextField(20);
    protected MyTextField nameT=new MyTextField(20);
    protected JComboBox<String> genderC;
    protected JComboBox<String> jobC;
    protected TimePanel p1;
    protected MyButton submitbt=new MyButton("提交");
    protected MyButton cancelbt=new MyButton("取消");

    public StaffInfoPanel(MainFrame parent, JDialog dialog, StaffBLService bl, StaffListPanel panel) {
        this.parent = parent;
        this.dialog=dialog;
        this.staffBLService=bl;
        this.staffListPanel=panel;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        String[] names={"工号","姓名","性别","出生日期","职位"};
        for(int i=0;i<5;i++){
            labels[i]=new MyLabel(names[i]);
        }

        String[] s1={"男","女"};
        genderC =new JComboBox<String>(s1);
        jobC =new JComboBox<String>(Constent.JOB_STRING);

        p1=new TimePanel();


        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<5;gbc.gridy++){
            this.add(labels[gbc.gridy],gbc);
        }

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(idT,gbc);
        gbc.gridy++;
        this.add(nameT,gbc);
        gbc.gridy++;
        this.add(genderC,gbc);
        gbc.gridy++;
        this.add(p1,gbc);
        gbc.gridy++;
        this.add(jobC,gbc);

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
            //todo 检查输入尚未实现
            try {
                String id=idT.getText();
                String name=nameT.getText();
                String gender=genderC.getSelectedItem().toString();
                Date birthday=p1.getDate();
                Job position=Job.values()[jobC.getSelectedIndex()];

                StaffVO vo=new StaffVO(id, name, gender, birthday, position, 0, 0);
                staffBLService.addStaff(vo);
                staffListPanel.refreshList();
            } catch (TimeFormatException e1) {
                new ErrorDialog(parent, e1.getMessage());
            } catch (RemoteException e1) {
                new ErrorDialog(parent, "服务器连接超时");
            } catch (SQLException e1) {
                new ErrorDialog(parent, "SQLException");
            } catch (InfoBLException e1) {
                new ErrorDialog(parent, e1.getMessage());
            }


        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
    
}
