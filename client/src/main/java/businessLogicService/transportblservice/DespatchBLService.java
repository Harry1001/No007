package businessLogicService.transportblservice;

import myexceptions.TransportBLException;
import vo.receiptvo.DespatchReceiptVO;

public interface DespatchBLService {
	/**
	 * 检查派件单输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(DespatchReceiptVO vo) throws TransportBLException;
	/**
	 * 派件单提交等待审批
	 */
	public void submit(DespatchReceiptVO vo);

}
