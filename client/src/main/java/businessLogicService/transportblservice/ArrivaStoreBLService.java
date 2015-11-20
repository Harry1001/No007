package businessLogicService.transportblservice;

import vo.receiptvo.StoreArrivalReceiptVO;

public interface ArrivaStoreBLService {
	/**
	 * 检查营业厅到达单输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(StoreArrivalReceiptVO vo);
	/**
	 * 营业厅到达单提交等待审批
	 */
	public void submit(StoreArrivalReceiptVO vo);
}
