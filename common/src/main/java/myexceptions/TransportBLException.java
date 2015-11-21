package myexceptions;

public class TransportBLException extends Exception{

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
