package businessLogicService.transportblservice;

import vo.receiptvo.ReceiptVO;

public interface TransportBLService {
	/**
	 * 检查输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(ReceiptVO vo);
	/**
	 * 单据提交等待审批
	 */
	public void submit(ReceiptVO vo);
	/**
	 * 输入参数获得运费
	 */
	public double calFee(ReceiptVO vo);
}
