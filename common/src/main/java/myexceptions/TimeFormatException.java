package myexceptions;

/**
 * Created by Harry on 2015/12/4.
 */
public class TimeFormatException extends Exception {
    private String message;

    public TimeFormatException ( String s ) {
        this.message=s;
    }

    public String getMessage(){
        return message;
    }
}
