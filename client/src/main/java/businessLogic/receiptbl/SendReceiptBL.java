package businessLogic.receiptbl;

import businessLogicService.receiptblservice.ReceiptBLService;
import dataService.ReceiptDataService;
import typeDefinition.myTime;
import vo.receiptvo.ReceiptVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class SendReceiptBL extends ReceiptBL {
    public SendReceiptBL(ReceiptDataService receiptData) {
        super(receiptData);
    }

    public ArrayList<ReceiptVO> getListByTime(myTime fromTime, myTime toTime){

        return null;
    }

    public void createReceipt(ReceiptVO item) {

    }
}
