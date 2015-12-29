package presentation.contentpanel.storepanels;

import MainFrame.MainFrame;
import businessLogicService.infoblservice.TruckBLService;
import constent.Constent;
import myexceptions.InfoBLException;
import myexceptions.TimeFormatException;
import presentation.commoncontainer.*;
import typeDefinition.MessageType;
import vo.infovo.TruckVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Harry on 2015/11/27.
 */
public class TruckInfoPanel extends JPanel implements ActionListener {

    MainFrame parent;
    JDialog dialog;
    TruckBLService truckBLService;
    TruckListPanel listPanel;

    MyLabel truckIDL=new MyLabel("车辆代号");
    MyLabel engineIDL=new MyLabel("发动机号");
    MyLabel chepaiL=new MyLabel("车牌号");
    MyLabel dipanL=new MyLabel("底盘号");
    MyLabel goumaiL=new MyLabel("购买时间");
    MyLabel fuyiL=new MyLabel("服役时间");
    MyButton submitbt=new MyButton("Submit");
    MyButton cancelbt=new MyButton("Cancel");

    MyTextField[] textFields=new MyTextField[5];
    TimePanel timePanel=new TimePanel();

    public TruckInfoPanel(MainFrame par, JDialog dialog, TruckListPanel listPanel, TruckBLService blService){
        this.parent=par;
        this.dialog=dialog;
        this.listPanel=listPanel;
        this.truckBLService=blService;

        this.setLayout(new GridBagLayout());
       // this.setBackground(Color.WHITE);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);

        gbc.gridx=gbc.gridy=0;
        this.add(truckIDL,gbc);
        gbc.gridy=1;
        this.add(engineIDL,gbc);
        gbc.gridy++;
        this.add(chepaiL,gbc);
        gbc.gridy++;
        this.add(dipanL,gbc);
        gbc.gridy++;
        this.add(fuyiL,gbc);
        gbc.gridy++;
        this.add(goumaiL,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        for(int i=0;i<5;i++){
            textFields[i]=new MyTextField();
            gbc.gridy=i;
            this.add(textFields[i],gbc);
        }

        gbc.gridy++;
        this.add(timePanel, gbc);

        gbc.gridx=0;
        gbc.gridy=7;
        this.add(submitbt,gbc);
        gbc.gridx++;
        this.add(cancelbt,gbc);

        setHotKey();

        submitbt.addActionListener(this);
        cancelbt.addActionListener(this);
    }

    private void setHotKey(){
        submitbt.setMnemonic('S');
        cancelbt.setMnemonic('C');
    }

    private boolean isDigit(String s){
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    private boolean checkTruckID(){
        String id=textFields[0].getText();
        if (id.length()!= Constent.TRUCK_ID_LENGTH){
            return false;
        }
        return isDigit(id);
    }

    private boolean checkEngineID(){
        String engineID=textFields[1].getText();
        return !engineID.isEmpty();//由于不知道发动机号的格式会不会随机型变化而变化，故只能测试是否为空
    }

    private boolean checkChePai(){
        String chePai=textFields[2].getText();
        return !chePai.isEmpty();
    }

    private boolean checkDiPan(){
        String diPan=textFields[3].getText();
        return !diPan.isEmpty();
    }

    private boolean checkServeTime(){
        String serveTime=textFields[4].getText();
        try{
            int t=Integer.parseInt(serveTime);
            return (t>0);
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean checkBuyTime(){
        try{
            Date buytime=timePanel.getDate();
            return buytime.before(new Date());//时间必须在当前系统时间以前
        } catch (TimeFormatException e) {
            return false;
        }
    }

    protected boolean checkAll(){
        if (!checkTruckID()){
            new TranslucentFrame(listPanel, "车辆代号必须为"+Constent.TRUCK_ID_LENGTH+"位数字", Color.RED);
            return false;
        }

        if (!checkEngineID()){
            new TranslucentFrame(listPanel, "发动机号不得为空", Color.RED);
            return false;
        }

        if (!checkChePai()){
            new TranslucentFrame(listPanel, "车牌号不得为空", Color.RED);
            return false;
        }

        if (!checkDiPan()){
            new TranslucentFrame(listPanel, "底盘号不得为空", Color.RED);
            return false;
        }

        if (!checkServeTime()){
            new TranslucentFrame(listPanel, "服役时间必须为正整数", Color.RED);
            return false;
        }

        if (!checkBuyTime()){
            new TranslucentFrame(listPanel, "请填写正确的时间格式，时间必须早于当前系统时间", Color.RED);
            return false;
        }

        return true;
    }

    protected void refresh(){
        for (int i=0;i<5;i++){
            textFields[i].setText("");
        }
        timePanel.makeEmpty();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitbt){
            if (checkAll()){
                try {
                    String id = textFields[0].getText();
                    String chepai = textFields[1].getText();
                    String engine = textFields[2].getText();
                    String dipan = textFields[3].getText();
                    int fuyiTime = Integer.parseInt(textFields[4].getText());
                    Date buyTime = timePanel.getDate();

                    TruckVO vo = new TruckVO(id, chepai, engine, dipan, buyTime, fuyiTime);
                    truckBLService.addTruck(vo);
                    listPanel.refreshList();
                    refresh();
                    new TranslucentFrame(listPanel, MessageType.SUBMIT_SUCCESS, Color.GREEN);
                } catch (TimeFormatException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);
                } catch (RemoteException e1) {
                    new TranslucentFrame(listPanel, MessageType.RMI_LAG, Color.ORANGE);
                } catch (SQLException e1) {
                    new ErrorDialog(parent, "SQLException");
                } catch (InfoBLException e1) {
                    new TranslucentFrame(listPanel, e1.getMessage(), Color.RED);
                }
            }
        }
        else if (e.getSource()==cancelbt){
            dialog.dispose();
        }
    }
}
