package po.infopo;

import po.infopo.InfoPO;
import typeDefinition.InfoType;
import vo.infovo.BankAccountVO;

public class BankAccountPO extends InfoPO {

	private static final long serialVersionUID = 1L;

	private String accountUser;
	private double balance;
	
	public BankAccountPO(String accountUser, double balance) {
		super(InfoType.BANKACCOUNT);
		// TODO Auto-generated constructor stub
		this.accountUser=accountUser;
		this.balance=balance;
	}

	public BankAccountPO(BankAccountVO vo){
		this(vo.getAccountUser(),vo.getBalance());
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
