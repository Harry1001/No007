package po;

public class FinancePO {

	private String agency;
	private String staff;
	private String truck;
	private int commodity;
	private String bankAccountName;
	private double balance;
	private double income;
	private double outcome;
	
	public FinancePO(String agency,String staff,String truck,int commodity,
			String bankAccountName,double balance,double income,double outcome){
		this.agency=agency;
		this.staff=staff;
		this.truck=truck;
		this.commodity=commodity;
		this.bankAccountName=bankAccountName;
		this.balance=balance;
		this.income=income;
		this.outcome=outcome;
	}
	
}
