package presentation.commoncontainer;

import myexceptions.TimeFormatException;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Harry on 2015/12/4.
 */
public class TimePanel extends JPanel {
    private MyLabel yearL=new MyLabel("年");
    private MyLabel monthL=new MyLabel("月");
    private MyLabel dateL=new MyLabel("日");
    private JTextField yearT=new MyTextField(8);
    private JTextField monthT=new MyTextField(4);
    private JTextField dateT=new MyTextField(4);

    public TimePanel(){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(yearT);
        this.add(yearL);
        this.add(monthT);
        this.add(monthL);
        this.add(dateT);
        this.add(dateL);
    }

    public Date getDate() throws TimeFormatException{
        try{
            int year=Integer.parseInt(yearT.getText());
            int month=Integer.parseInt(monthT.getText());
            int date=Integer.parseInt(dateT.getText());
            return new Date(year-1900,month-1,date);
        }catch (Exception e){
            throw new TimeFormatException("日期格式错误");
        }
    }

    public void setPresentTime(){
        Calendar ca = Calendar.getInstance();
        yearT.setText(""+ca.get(Calendar.YEAR));
        monthT.setText(""+ca.get(Calendar.MONTH));
        dateT.setText(""+ca.get(Calendar.DATE));
    }

    public void makeEmpty(){
        yearT.setText("");
        monthT.setText("");
        dateT.setText("");
    }


}
