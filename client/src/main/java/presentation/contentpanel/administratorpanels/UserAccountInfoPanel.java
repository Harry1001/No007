package presentation.contentpanel.administratorpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.UserAccoutBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commonpanel.ErrorDialog;
import typeDefinition.Job;
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

    private UserAccoutBLService userAccoutBLService;
    MainFrame parent;
    MyLabel idL=new MyLabel("工号");
    MyLabel nameL=new MyLabel("姓名");
    MyLabel jobL=new MyLabel("职位");
    MyLabel passwordL=new MyLabel("密码");
    MyTextField idT=new MyTextField(15);
    MyTextField nameT=new MyTextField(15);
    JComboBox<String> jobC;
    JPasswordField passwordField=new JPasswordField(15);
    MyButton submitbt=new MyButton("提交");
    MyButton cancelbt=new MyButton("取消");

    public UserAccountInfoPanel( MainFrame parent, UserAccoutBLService userAccoutBL) {
        this.parent = parent;
        this.userAccoutBLService=userAccoutBL;

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
        this.add(nameL,gbc);
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
        this.add(nameT,gbc);
        gbc.gridy--;
        this.add(passwordField,gbc);
        gbc.gridy--;
        this.add(idT,gbc);

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    private boolean checkFormat(){
        String ids= idT.getText();
        if(ids.length()!= Constent.USER_ID_LENGTH){
            new ErrorDialog(parent, "请输入"+Constent.USER_ID_LENGTH+"位数字工号");
            return false;
        }
        for (int i=0;i<Constent.USER_ID_LENGTH;i++){
            if (ids.charAt(i)<'0'||ids.charAt(i)>'9'){
                new ErrorDialog(parent, "请输入"+Constent.USER_ID_LENGTH+"位数字工号");
                return false;
            }
        }

        if (passwordField.getPassword().length<8){
            new ErrorDialog(parent, "密码位数不可小于8");
            return false;
        }

        if (nameT.getText().length()<=0){
            new ErrorDialog(parent, "姓名不可为空");
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            //todo
            if (checkFormat()){

                UserAccountVO vo = new UserAccountVO(idT.getText(), nameT.getText(),
                        Job.values()[jobC.getSelectedIndex()], new String(passwordField.getPassword()));
                try {
                    userAccoutBLService.addUserAccount(vo);
                } catch (InfoBLException e1) {
                    new ErrorDialog(parent, e1.getMessage());
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "数据库异常");
                }
            }

        }
        else if (e.getSource()==cancelbt){
            parent.dispose();
        }
    }
}
