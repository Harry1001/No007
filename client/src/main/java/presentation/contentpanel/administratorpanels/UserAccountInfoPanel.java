package presentation.contentpanel.administratorpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.UserAccoutBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import presentation.commoncontainer.*;
import typeDefinition.Job;
import typeDefinition.MessageType;
import vo.infovo.UserAccountVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/11/28.
 */
public class UserAccountInfoPanel extends JPanel implements ActionListener {

    protected UserAccoutBLService userAccoutBLService;
    JDialog dialog;
    UserAccountListPanel listPanel;
    MainFrame parent;
    MyLabel idL=new MyLabel("工号");
    MyLabel nameL=new MyLabel("姓名");
    MyLabel jobL=new MyLabel("职位");
    MyLabel passwordL=new MyLabel("密码");
    MyTextField idT=new MyTextField(15);
    MyTextField nameT=new MyTextField(15);
    JComboBox<String> jobC;
    JPasswordField passwordField=new JPasswordField(15);
    MyButton submitbt=new MyButton("提交(S)");
    MyButton cancelbt=new MyButton("取消(C)");

    public UserAccountInfoPanel( MainFrame parent,JDialog dialog, UserAccountListPanel listPanel,
                                 UserAccoutBLService userAccoutBL) {
        this.parent = parent;
        this.userAccoutBLService=userAccoutBL;
        this.dialog=dialog;
        this.listPanel=listPanel;

        jobC=new JComboBox<String>(Constent.USER_ACCOUNR_JOB);

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        gbc.gridx=0;
        gbc.gridy=0;
        this.add(idL,gbc);
        gbc.gridy++;
        this.add(passwordL,gbc);
        gbc.gridy++;
        this.add(nameL,gbc);
        gbc.gridy++;
        this.add(jobL,gbc);
        gbc.gridy++;


        gbc.gridx=0;
        gbc.anchor=GridBagConstraints.CENTER;

        this.add(submitbt,gbc);
        //gbc.anchor=GridBagConstraints.EAST;
        gbc.gridx=1;
        this.add(cancelbt,gbc);
        gbc.gridy--;
        gbc.anchor=GridBagConstraints.WEST;
        this.add(jobC,gbc);
        gbc.gridy--;
        this.add(nameT,gbc);
        gbc.gridy--;
        this.add(passwordField,gbc);
        gbc.gridy--;
        this.add(idT,gbc);

        setHotKey();

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    private void setHotKey(){
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('C');
    }

    protected boolean checkID(){
        String id=idT.getText();
        if( id.length()!= Constent.USER_ID_LENGTH){
            return false;
        }
        for (int i=0;i<Constent.USER_ID_LENGTH;i++){
            if (id.charAt(i)<'0'||id.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    protected boolean checkPassword(){
        String pass=new String(passwordField.getPassword());
        if (pass.length()<8){
            return false;
        }
        return true;
    }

    protected boolean checkName(){
        String s=nameT.getText();
        if (s.length()<=0){
            return  false;
        }
        return true;
    }

    protected boolean checkFormat(){

        if(!checkID()){
            new TranslucentFrame(listPanel, "请输入"+Constent.USER_ID_LENGTH+"位数字工号", Color.RED);
            return false;
        }

        if (!checkPassword()){
            new TranslucentFrame(listPanel, "密码位数不可小于8", Color.RED);
            return false;
        }

        if (!checkName()){
            new TranslucentFrame(listPanel, "姓名不可为空", Color.RED);
            return  false;
        }
        return true;
    }

    protected void refresh(){
        idT.setText("");
        nameT.setText("");
        jobC.setSelectedIndex(0);
        passwordField.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkFormat()){
                UserAccountVO vo = new UserAccountVO(idT.getText(), nameT.getText(),
                        Job.values()[jobC.getSelectedIndex()+1], new String(passwordField.getPassword()));//此处job加一是因为下拉框中没有寄件人
                try {
                    userAccoutBLService.addUserAccount(vo);
                    listPanel.refreshList();
                    refresh();
                    new TranslucentFrame(listPanel, MessageType.ADD_SUCCESS, Color.GREEN);
                } catch (InfoBLException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(listPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println("增加账户sql异常："+e1.getMessage());
                    new ErrorDialog(parent, "数据库异常");
                }
            }

        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
