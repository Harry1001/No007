package businessLogic.transportbl;

import businessLogicService.transportblservice.ArrivaStoreBLService;
import vo.receiptvo.StoreArrivalReceiptVO;

public class ArriveStoreController implements ArrivaStoreBLService{

	ArriveStoreBL arrivestorebl=new ArriveStoreBL();
	
	public boolean verify(StoreArrivalReceiptVO vo) {
		return arrivestorebl.verify(vo);
	}

	public void submit(StoreArrivalReceiptVO vo) {
		arrivestorebl.submit(vo);
	}

}
