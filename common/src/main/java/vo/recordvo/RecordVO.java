package vo.recordvo;

import po.recordpo.RecordPO;
import typeDefinition.myTime;

public class RecordVO {

	private String operation;//操作名称
	private myTime opeTime;//发生时间
	private String operator;//操作人员
	
	public RecordVO(String operation,myTime opeTime,String operator){
		this.operation=operation;
		this.opeTime=opeTime;
		this.operator=operator;
	}

	public RecordVO(RecordPO po){
		this.operation=po.getOperation();
		this.opeTime=po.getOpeTime();
		this.operator=po.getOperator();
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
