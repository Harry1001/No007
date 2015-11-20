package vo.infovo;

import po.infopo.BankAccountPO;
import typeDefinition.InfoType;

import java.math.BigDecimal;

public class BankAccountVO extends InfoVO {

	private static final long serialVersionUID = 1L;

	private String accountUser;
	private BigDecimal balance ;
	
	public BankAccountVO(String accountUser, BigDecimal balance) {
		super(InfoType.BANKACCOUNT);
		// TODO Auto-generated constructor stub
		this.accountUser=accountUser;
		this.balance=balance;
	}

	public BankAccountVO(BankAccountPO po){
		this(po.getAccountUser(),po.getBalance());
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
