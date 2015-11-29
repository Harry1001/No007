package po.recordpo;

import java.util.Date;

import vo.recordvo.RecordVO;

public class RecordPO {

	private String operation;//操作名称
	private Date opeTime;//发生时间
	private String operator;//操作人员
	
	public RecordPO(Date opeTime,String operator,String operation){
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

	public Date getOpeTime() {
		return opeTime;
	}

	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}
	
	public String getOperator(){
		return operator;
	}
	
	public void setOperator(String operator){
		this.operator=operator;
	}
}
