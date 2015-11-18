package businessLogicService.receiptblservice;

import vo.receiptvo.ReceiptVO;

/**
 * Created by Harry on 2015/11/18.
 */
public interface CreateReceiptBLService {

    /**
     * 给其他业务逻辑对象提供创建单据的接口，比如FinanceBL创建付款单，SendBL创建寄件单
     * @param item
     */
    public void createReceipt(ReceiptVO item);
}
