package businessLogic.recordbl;

import businessLogicService.recordblservice.RecordBLService;
import po.recordpo.RecordPO;
import vo.recordvo.RecordVO;

public class RecordBL implements RecordBLService{

	public RecordVO lookup() {
		// TODO Auto-generated method stub
		
		return null;
	}

	public void add(RecordVO vo) {
		// TODO Auto-generated method stub
		RecordPO po=new RecordPO(vo);
	}

}
