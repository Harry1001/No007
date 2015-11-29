package vo.recordvo;

import java.util.Date;

import po.recordpo.RecordPO;

public class RecordVO {

	private String operation;//操作名称
	private Date opeTime;//发生时间
	private String operator;//操作人员
	
	public RecordVO(Date opeTime,String operator,String operation){
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
