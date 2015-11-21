package businessLogicService.transportblservice;

import myexceptions.TransportBLException;
import vo.receiptvo.TransferReceiptVO;

public interface TransferBLService {
	/**
	 * 检查中转单输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(TransferReceiptVO vo) throws TransportBLException;
	/**
	 * 中转单提交等待审批
	 */
	public void submit(TransferReceiptVO vo);
	/**
	 * 输入参数获得运费
	 */
	public double calFee(TransferReceiptVO vo);
}
