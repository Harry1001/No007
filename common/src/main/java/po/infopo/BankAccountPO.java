package po.infopo;

import typeDefinition.InfoType;
import vo.infovo.BankAccountVO;

import java.math.BigDecimal;

public class BankAccountPO extends InfoPO {

	private static final long serialVersionUID = 1L;

	private String accountUser;
	private BigDecimal balance;
	
	public BankAccountPO(String accountUser, BigDecimal balance) {
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
