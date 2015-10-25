package typeDefinition;

/**
 *	单据类型的定义。分别对应
 *	寄件单，派件单，装车单，中转单，营业厅到达单，中转中心到达单，入库单，出库单，收款单，付款单, 收件单
 */
public enum ReceiptType {
	SEND, DESPATCH, ENTRUCK, TRANSFER, STOREARRIVAL, HUBARRIVAL, DEPOTIN, DEPOTOUT,
	CHARGE, PAY, RECEIVE
}
