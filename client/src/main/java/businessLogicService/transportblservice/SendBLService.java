package businessLogicService.transportblservice;

import myexceptions.TransportBLException;
import vo.receiptvo.SendReceiptVO;

public interface SendBLService {
	/**
	 * 检查寄件单输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(SendReceiptVO vo) throws TransportBLException;
	/**
	 * 寄件单提交等待审批
	 */
	public void submit(SendReceiptVO vo);
	/**
	 * 输入参数获得寄件费
	 */
	public double calFee(SendReceiptVO vo);
}
