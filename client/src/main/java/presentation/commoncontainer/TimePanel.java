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
    private JTextField yearT=new MyTextField(4);
    private JTextField monthT=new MyTextField(2);
    private JTextField dateT=new MyTextField(2);

    public TimePanel(){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(yearT);
        this.add(yearL);
        this.add(monthT);
        this.add(monthL);
        this.add(dateT);
        this.add(dateL);
        this.setOpaque(false);
    }

    public Date getDate() throws TimeFormatException{
        try{
            int year=Integer.parseInt(yearT.getText());
            int month=Integer.parseInt(monthT.getText());
            int date=Integer.parseInt(dateT.getText());

            Calendar c= Calendar.getInstance();
            c.set(year, month-1, date);
            return c.getTime();

        }catch (NumberFormatException e){
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

    public void setDate(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DATE);
        yearT.setText(year+"");
        monthT.setText(month+"");
        dateT.setText(day+"");
    }


}
