package typeDefinition;

/*货运状态，按顺序为
 * 收件、到达寄件人营业厅、到达寄件人中转中心、
 * 到达收件人中转中心、到达收件人营业厅、派件中
 */
public enum LogisticState {
	RECEIVE,ASSTORE,ASHUB,ARHUB,ARSTORE,DESPATCH
}
