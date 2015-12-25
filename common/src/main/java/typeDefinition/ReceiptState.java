package typeDefinition;

/*
 * 单据的状态定义，包括
 * 提交状态，审批通过，审批不通过, 已处理
 */
public enum ReceiptState {
	SUBMITTED, APPROVED, UNAPPROVED, HANDLED
}