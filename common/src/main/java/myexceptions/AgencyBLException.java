package myexceptions;

/**
 * Created by Harry on 2015/11/20.
 */
public class AgencyBLException extends Exception {
    private String message;

    public AgencyBLException(String value){
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
