package presentation.contentpanel.storepanels;

import javax.swing.*;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.DriverBLService;
import businessLogicService.infoblservice.TruckBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.MyButton;
import presentation.commoncontainer.MyLabel;
import presentation.commoncontainer.MyTextField;
import presentation.commoncontainer.TimePanel;
import presentation.commonpanel.ErrorDialog;
import vo.infovo.DriverVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Harry on 2015/11/27.
 */
public class DriverInfoPanel extends JPanel implements ActionListener{
    MainFrame parent;
    JDialog dialog;
    DriverListPanel listPanel;
    DriverBLService driverBLService;

    MyLabel[] labels=new MyLabel[7];
    MyTextField[] textFields=new MyTextField[4];
    JComboBox<String> genderC;
    TimePanel birthday;
    TimePanel limitTime;
    MyButton submitbt=new MyButton("提交");
    MyButton cancelbt=new MyButton("取消");

    public DriverInfoPanel(MainFrame par, JDialog dialog, DriverListPanel listPanel, DriverBLService blService) {
        this.parent = par;
        this.dialog=dialog;
        this.listPanel=listPanel;
        this.driverBLService=blService;

        initUI();
        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    private boolean checkID(){
        String id=textFields[0].getText();
        if (id.length()!= Constent.DRIVER_ID_LENGTH)
            return false;
        return isDigit(id);
    }

    private boolean checkName(){
        String name=textFields[1].getText();
        return !name.isEmpty();
    }

    private boolean checkBirthday(){
        try {
            Date time=birthday.getDate();
            return time.before(new Date());
        } catch (TimeFormatException e) {
            return false;
        }
    }

    private boolean checkPersonID(){
        String id=textFields[2].getText();
        if (id.length()!=Constent.PERSON_ID_LENGTH){
            return false;
        }

        if (!isDigit(id.substring(0,Constent.PERSON_ID_LENGTH-1))){
            return false;
        }

        return ( isDigit(id.substring(Constent.DRIVER_ID_LENGTH-1)) || (id.charAt(Constent.DRIVER_ID_LENGTH-1))=='x');
    }

    private boolean checkPhone(){
        String phone=textFields[3].getText();
        if (phone.length()!=Constent.PHONE_LENGTH){
            return false;
        }
        return isDigit(phone);
    }

    private boolean checkLimitTime(){
        try {
            Date time=limitTime.getDate();
            return true;                //时间如果在当前系统时间之前此处也不做检查，也许这个司机的行驶证就是过期了
        } catch (TimeFormatException e) {
            return false;
        }
    }

    protected boolean checkAll(){
        if (!checkID()){
            new ErrorDialog(parent, "司机编号必须为"+Constent.DRIVER_ID_LENGTH+"位数字");
            return false;
        }

        if (!checkName()){
            new ErrorDialog(parent, "名称不可为空");
            return false;
        }

        if (!checkBirthday()){
            new ErrorDialog(parent, "出生日期必须在当前时间之前");
            return false;
        }

        if (!checkPersonID()){
            new ErrorDialog(parent, "请输入正确的身份证号");
            return false;
        }

        if (!checkPhone()){
            new ErrorDialog(parent, "手机号必须为"+Constent.PHONE_LENGTH+"位数字");
            return false;
        }

        if (!checkLimitTime()){
            new ErrorDialog(parent, "请输入正确的行驶证期限");
            return false;
        }
        return true;
    }

    protected void refresh(){
        for (int i=0;i<4;i++){
            textFields[i].setText("");
        }
        birthday.makeEmpty();
        limitTime.makeEmpty();
        genderC.setSelectedIndex(0);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                try {
                    DriverVO vo = new DriverVO(textFields[0].getText(), textFields[1].getText(), birthday.getDate(),
                            textFields[2].getText(), textFields[3].getText(), genderC.getSelectedItem().toString(),
                            limitTime.getDate());
                    driverBLService.addDriver(vo);
                    listPanel.refreshList();
                    refresh();
                } catch (TimeFormatException e1) {
                    new ErrorDialog(parent, "时间格式错误");
                } catch (RemoteException e1) {
                    new ErrorDialog(parent, "服务器连接超时");
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (InfoBLException e1) {
                    new ErrorDialog(parent, e1.getMessage());//数据库中已经包含该司机编号
                }
            }
        }
        else if (e.getSource()==cancelbt){
            parent.dispose();
        }
    }

    private void initUI(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        String[] names={"司机编号","姓名","出生日期","身份证号","手机号","性别","行驶证期限"};
        String[] genders={"男","女"};
        for(int i=0;i<7;i++){
            labels[i]=new MyLabel(names[i]);
        }
        for(int i=0;i<4;i++){
            textFields[i]=new MyTextField();
        }
        birthday=new TimePanel();
        limitTime=new TimePanel();
        genderC=new JComboBox<String>(names);

        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<7;gbc.gridy++){
            this.add(labels[gbc.gridy],gbc);
        }

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(textFields[0],gbc);
        gbc.gridy++;
        this.add(textFields[1],gbc);
        gbc.gridy++;
        this.add(birthday,gbc);
        gbc.gridy++;
        this.add(textFields[2],gbc);
        gbc.gridy++;
        this.add(textFields[3],gbc);
        gbc.gridy++;
        this.add(genderC,gbc);
        gbc.gridy++;
        this.add(limitTime,gbc);

        gbc.gridx=0;
        gbc.gridy=8;
        this.add(submitbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);
    }

    private boolean isDigit(String s){
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9')
                return false;
        }
        return true;
    }
}
