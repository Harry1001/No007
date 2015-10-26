package vo;

import javax.sound.midi.MidiDevice.Info;

import typeDefinition.InfoType;

public class BankAccountVO extends InfoVO{

	private static final long serialVersionUID = 1L;

	private String accountUser;
	
	public BankAccountVO(String accountUser) {
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
	
	public BankAccountVO(InfoType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
