package presentation.contentpanel.managerpanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.AgencyBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.infovo.AgencyVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/11/28.
 */
public class AgencyInfoPanel extends JPanel implements ActionListener{
    protected AgencyBLService agencyBLService;
    protected MainFrame parent;
    protected AgencyListPanel listPanel;
    protected JDialog dialog;
    protected MyLabel[] labels=new MyLabel[6];
    protected MyTextField[] textFields=new MyTextField[5];
    protected JComboBox<String> type;

    protected MyButton submitbt=new MyButton("提交(S)");
    protected MyButton cancelbt=new MyButton("取消(C)");

    public AgencyInfoPanel(JDialog dialog, MainFrame parent, AgencyListPanel panel, AgencyBLService agencyBLService) {
        this.agencyBLService=agencyBLService;
        this.parent = parent;
        this.listPanel=panel;
        this.dialog=dialog;

        initUI();
        setHotKey();

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    private void setHotKey(){
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('C');
    }

    /**
     * 设置组件位置
     */
    protected void initUI(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        String [] names={"机构编号","名称","机构类型","位置","面积","土地租金"};
        for(int i=0;i<6;i++){
            labels[i]=new MyLabel(names[i]);
        }
        for (int i=0;i<5;i++){
            textFields[i]=new MyTextField();
        }

        String[] s={"营业厅","中转中心"};

        type=new JComboBox<String>(s);

        for(gbc.gridx=0,gbc.gridy=0;gbc.gridy<6;gbc.gridy++){
            this.add(labels[gbc.gridy],gbc);
        }

        gbc.gridx=1;
        gbc.gridy=0;
        this.add(textFields[0],gbc);
        gbc.gridy++;
        this.add(textFields[1],gbc);
        gbc.gridy++;
        this.add(type,gbc);
        gbc.gridy++;
        this.add(textFields[2],gbc);
        gbc.gridy++;
        this.add(textFields[3],gbc);
        gbc.gridy++;
        this.add(textFields[4],gbc);

        gbc.gridx=0;
        gbc.gridy=6;
        this.add(submitbt,gbc);
        gbc.gridx=1;
        this.add(cancelbt,gbc);
    }

    /**
     * 检查所有输入
     * @return
     */
    protected boolean checkAll(){
        if (!checkAgencyID()){
            new TranslucentFrame(listPanel, "机构编号格式错误：中转中心4位，营业厅6位", Color.RED);
            return false;
        }

        if (!checkArea()){
            new TranslucentFrame(listPanel, "面积必须为正整数", Color.RED);
            return false;
        }

        if (!checkRent()){
            new TranslucentFrame(listPanel, "租金必须为正整数", Color.RED);
            return false;
        }

        return true;
    }

    /**
     * 检查租金格式
     * @return
     */
    protected boolean checkRent(){
        String s=textFields[4].getText();
        try{
            int rent=Integer.parseInt(s);
            if (rent<=0) return false;
            return true;
        }catch (NumberFormatException e1){
            return false;
        }
    }

    /**
     * 检查面积格式
     * @return
     */
    protected boolean checkArea(){
        String s=textFields[3].getText();
        try{
            int area=Integer.parseInt(s);
            if (area<=0) return false;
            return true;
        }catch (NumberFormatException e1){
            return false;
        }
    }

    /**
     * 检查机构编号格式
     * @return
     */
    protected boolean checkAgencyID(){
        String id=textFields[0].getText();
        if (type.getSelectedIndex()==0){//营业厅
            if (id.length()!= Constent.STORE_ID_LENGTH){
                return false;
            } else {
                return isDigit(id);
            }
        } else {//中转中心
            if (id.length()!= Constent.HUB_ID_LENGTH){
                return false;
            } else {
                return isDigit(id);
            }
        }
    }

    /**
     * 检查字符串是否为数字
     * @param s
     * @return
     */
    protected boolean isDigit(String s){
        if (s.length()<=0)
            return false;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9')
                return false;
        }
        return true;
    }

    protected void refresh(){
        for (int i=0;i<textFields.length;i++){
            textFields[i].setText("");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                AgencyVO vo =new AgencyVO(textFields[1].getText(), type.getSelectedItem().toString(),
                        textFields[0].getText(), textFields[2].getText(), Integer.parseInt(textFields[3].getText()),
                        Integer.parseInt(textFields[4].getText()));
                try {
                    agencyBLService.addAgency(vo);
                    listPanel.refreshList();
                    refresh();
                    new TranslucentFrame(listPanel, MessageType.ADD_SUCCESS, Color.GREEN);
                } catch (InfoBLException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(listPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    System.out.println("agency sql:"+e1.getMessage());
                }
            }
        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
