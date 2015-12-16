package myexceptions;

public class TransportBLException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	 
	 public TransportBLException(String m){
		 this.message=m;
	 }
	 
	 public String getMessage() {
		 return message;
	 }

	 public void setMessage(String m) {
		 this.message = m;
	 }
}
