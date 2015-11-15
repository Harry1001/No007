package po.infopo;

import po.infopo.InfoPO;
import typeDefinition.InfoType;

public class BankAccountPO extends InfoPO {

	private static final long serialVersionUID = 1L;

	private String accountUser;
	
	public BankAccountPO(String accountUser) {
		super(InfoType.BANKACCOUNT);
		// TODO Auto-generated constructor stub
		this.accountUser=accountUser;
	}

	public String getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}

}