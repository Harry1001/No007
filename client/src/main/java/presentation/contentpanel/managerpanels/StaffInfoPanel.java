package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.StaffBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.*;
import typeDefinition.Job;
import typeDefinition.MessageType;
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
    protected MyTextField idT=new MyTextField();
    protected MyTextField nameT=new MyTextField();
    protected JComboBox<String> genderC;
    protected JComboBox<String> jobC;
    protected TimePanel p1;
    protected MyButton submitbt=new MyButton("提交(S)");
    protected MyButton cancelbt=new MyButton("取消(C)");

    public StaffInfoPanel(MainFrame parent, JDialog dialog, StaffBLService bl, StaffListPanel panel) {
        this.parent = parent;
        this.dialog=dialog;
        this.staffBLService=bl;
        this.staffListPanel=panel;

        initUI();
        setHotKey();

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    private void setHotKey(){
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('C');
    }

    private void initUI(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        String[] names={"工号","姓名","性别","出生日期","职位"};
        for(int i=0;i<5;i++){
            labels[i]=new MyLabel(names[i]);
        }

        String[] s1={"男","女"};
        genderC =new JComboBox<String>(s1);
        jobC =new JComboBox<String>(Constent.USER_ACCOUNR_JOB);

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
    }

    protected void refresh(){
        nameT.setText("");
        idT.setText("");
        p1.makeEmpty();
    }

    protected boolean checkID(){
        String s=idT.getText();
        if (s.length()!=Constent.USER_ID_LENGTH){
            return false;
        }
        for (int i=0;i<Constent.USER_ID_LENGTH;i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    protected boolean checkName(){
        String s=nameT.getText();
        return !s.isEmpty();
    }

    protected boolean checkAll(){
        if (!checkID()){
            new TranslucentFrame(staffListPanel, "工号必须为"+Constent.USER_ID_LENGTH+"位数字", Color.RED);
            return false;
        }

        if (!checkName()){
            new TranslucentFrame(staffListPanel, "姓名不可为空", Color.RED);
            return false;
        }

        return true;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                try {
                    String id=idT.getText();
                    String name=nameT.getText();
                    String gender=genderC.getSelectedItem().toString();
                    Date birthday=p1.getDate();
                    Job position=Job.values()[jobC.getSelectedIndex()+1];//此处+1很重要，因为该下拉选框里没有寄件人，导致在数组中次序偏差了1

                    StaffVO vo=new StaffVO(id, name, gender, birthday, position, 0, 0);
                    staffBLService.addStaff(vo);
                    staffListPanel.refreshList();
                    refresh();
                    new TranslucentFrame(staffListPanel, MessageType.ADD_SUCCESS, Color.GREEN);
                } catch (TimeFormatException e1) {
                    new TranslucentFrame(staffListPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(staffListPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println("新增人员sql："+e1.getMessage());
                } catch (InfoBLException e1) {
                    new TranslucentFrame(staffListPanel, e1.getMessage(), Color.RED);
                }
            }
        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
    
}
