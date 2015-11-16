package po.recordpo;

import typeDefinition.myTime;
import vo.recordvo.RecordVO;

public class RecordPO {

	private String operation;//操作名称
	private myTime opeTime;//发生时间
	private String operator;//操作人员
	
	public RecordPO(String operation,myTime opeTime,String operator){
		this.operation=operation;
		this.opeTime=opeTime;
		this.operator=operator;
	}
	
	public RecordPO(RecordVO vo){
		this.operation=vo.getOperation();
		this.opeTime=vo.getOpeTime();
		this.operator=vo.getOperator();
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
	
	public String getOperator(){
		return operator;
	}
	
	public void setOperator(String operator){
		this.operator=operator;
	}
}
