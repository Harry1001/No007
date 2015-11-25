package typeDefinition;

/*
 * 单据的状态定义，包括
 * 草稿状态，提交状态，审批通过，审批不通过
 */
public enum ReceiptState {
	RAW, SUBMITTED, APPROVED, UNAPPROVED
}