package businessLogicService.transportblservice;

import vo.receiptvo.HubArrivalReceiptVO;

public interface ArriveHubBLService {
	/**
	 * 检查中转中心到达单输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(HubArrivalReceiptVO vo);
	/**
	 * 中转中心到达单提交等待审批
	 */
	public void submit(HubArrivalReceiptVO vo);
}
