package businessLogicService.transportblservice;

import myexceptions.TransportBLException;
import vo.receiptvo.EntruckReceiptVO;

public interface EntruckBLService {
	/**
	 * 检查装车单输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(EntruckReceiptVO vo) throws TransportBLException;
	/**
	 * 装车单提交等待审批
	 */
	public void submit(EntruckReceiptVO vo);
	/**
	 * 输入参数获得运费
	 */
	public double calFee(EntruckReceiptVO vo);
}
