package po;

import typeDefinition.InfoType;

public class BankAccountPO extends InfoPO{

	private static final long serialVersionUID = 1L;

	private String accountUser;
	
	public BankAccountPO(InfoType type,String accountUser) {
		super(type);
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
