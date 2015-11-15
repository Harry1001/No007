package vo.infovo;

import typeDefinition.InfoType;

public class BankAccountVO extends InfoVO {

	private static final long serialVersionUID = 1L;

	private String accountUser;
	private double balance ;
	
	public BankAccountVO(String accountUser, double balance) {
		super(InfoType.BANKACCOUNT);
		// TODO Auto-generated constructor stub
		this.accountUser=accountUser;
		this.balance=balance;
	}

	public String getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
