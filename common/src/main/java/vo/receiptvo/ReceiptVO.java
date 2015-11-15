package vo.receiptvo;

import java.io.Serializable;

import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;

/**
 * 单据的传输数据
 * 此类为父类，其子类对应具体的单据
 */

public abstract class ReceiptVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//单据的类型，新建子类单据时必须制定单据类型
		private ReceiptType type;
		//private ReceiptState state;
		
		public ReceiptVO(ReceiptType type){
			this.type=type;
			//this.state = ReceiptState.RAW;
		}
		public ReceiptType getType() {
			return type;
		}


}
