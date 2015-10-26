package vo;

import typeDefinition.myTime;

public class RecordVO {

	private String operation;//操作名称
	private myTime opeTime;//发生时间
	
	public RecordVO(String operation,myTime opeTime){
		this.operation=operation;
		this.opeTime=opeTime;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public myTime getOpeTime() {
		return opeTime;
	}

	public void setOpeTime(myTime opeTime) {
		this.opeTime = opeTime;
	}
	
}
