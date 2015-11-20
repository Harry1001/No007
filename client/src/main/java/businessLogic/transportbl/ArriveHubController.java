package businessLogic.transportbl;

import businessLogicService.transportblservice.ArriveHubBLService;
import vo.receiptvo.HubArrivalReceiptVO;

public class ArriveHubController implements ArriveHubBLService{
	
	ArriveHubBL arrivehubbl=new ArriveHubBL();
	
	public boolean verify(HubArrivalReceiptVO vo) {		
		return arrivehubbl.verify(vo);
	}

	public void submit(HubArrivalReceiptVO vo) {
		arrivehubbl.submit(vo);
	}

}
