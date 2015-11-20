package businessLogic.receiptbl;

import businessLogicService.receiptblservice.ReceiptBLService;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.ReceiptVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class ReceiptController implements ReceiptBLService {

    private ReceiptBL receiptBL=new ReceiptBL();

    public ArrayList<? extends ReceiptVO> getListByTime(myTime fromTime, myTime toTime, ReceiptType type) {
        return receiptBL.getListByTime(fromTime, toTime, type);
    }

    public void createReceipt(ReceiptVO item) {
        receiptBL.createReceipt(item);
    }
}
