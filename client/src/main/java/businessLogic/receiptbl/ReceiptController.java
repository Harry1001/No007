package businessLogic.receiptbl;

import businessLogicService.receiptblservice.ReceiptBLService;
import typeDefinition.ReceiptType;
import vo.receiptvo.ReceiptVO;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class ReceiptController implements ReceiptBLService {

    private ReceiptBL receiptBL=new ReceiptBL();

    public ArrayList<? extends ReceiptVO> getListByTime(Date fromTime,
                                                        Date toTime, ReceiptType type) throws RemoteException {
        return receiptBL.getListByTime(fromTime, toTime, type);
    }

    public void createReceipt(ReceiptVO item) throws RemoteException {
        receiptBL.createReceipt(item);
    }
}
