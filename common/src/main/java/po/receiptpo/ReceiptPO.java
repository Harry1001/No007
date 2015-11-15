package po.receiptpo;

import java.io.Serializable;
import typeDefinition.ReceiptType;

/**
 * 单据的持久化数据
 * 此类为父类，其子类对应具体的单据
 */
public abstract class ReceiptPO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//单据的类型，新建子类单据时必须制定单据类型
	private ReceiptType type;
	
	public ReceiptPO(ReceiptType type){
		this.type=type;
	}

	public ReceiptType getType() {
		return type;
	}


}
