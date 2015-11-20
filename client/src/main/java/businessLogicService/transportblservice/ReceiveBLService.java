package businessLogicService.transportblservice;

import vo.receiptvo.ReceiveReceiptVO;

public interface ReceiveBLService {
	/**
	 * 检查收件单输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(ReceiveReceiptVO vo);
	/**
	 * 收件单提交等待审批
	 */
	public void submit(ReceiveReceiptVO vo);

}
