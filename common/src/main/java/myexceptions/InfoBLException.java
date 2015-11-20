package myexceptions;

/**
 * Created by Harry on 2015/11/20.
 */
public class InfoBLException extends Exception {
    private String message;

    public InfoBLException(String value){
        this.message=value;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
