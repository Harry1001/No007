package businessLogic.receiptbl;

import businessLogicService.receiptblservice.ReceiptBLService;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.receiptvo.ReceiptVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class ReceiptController implements ReceiptBLService{

    public ArrayList<ReceiptVO> getListByTime(myTime fromTime, myTime toTime, ReceiptType type) {
        return null;
    }
}
