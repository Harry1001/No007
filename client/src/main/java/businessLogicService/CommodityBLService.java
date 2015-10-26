package businessLogicService;

import java.util.ArrayList;

import typeDefinition.myTime;
import vo.CommodityVO;
import vo.ReceiptVO;

public interface CommodityBLService {

	public void submit(ReceiptVO vo);
	
	public ArrayList<CommodityVO> getList(String TransferNum);
	
	public ArrayList<CommodityVO> getList(String TransferNum,myTime fromTime,myTime toTime);
	
	public ArrayList<CommodityVO> getTotal();
	
	public void renew();
	
}
