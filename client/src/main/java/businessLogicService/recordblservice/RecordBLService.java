package businessLogicService.recordblservice;

import vo.RecordVO;

public interface RecordBLService {

	public RecordVO lookup();
	
	public void add(RecordVO vo);
	
	
}
