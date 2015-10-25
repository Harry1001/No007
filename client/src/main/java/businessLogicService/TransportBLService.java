package businessLogicService;

import vo.ReceiptVO;
import businessLogic.BLImpl;

public interface TransportBLService {
	/*
	 * 检查输入内容的正确性以决定页面是否跳转
	 */
	public boolean verify(ReceiptVO vo);
	/*
	 * 单据提交等待审批
	 */
	public void submit(ReceiptVO vo);
	/*
	 * 监听者调用此方法向被监听者注册，在转运更新时通知改变物流信息
	 */
	public void register(BLImpl listener);
	/*
	 * 输入参数获得运费
	 */
	public void cal(ReceiptVO vo);
}
