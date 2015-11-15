package businessLogicService.recordblservice;

import vo.recordvo.RecordVO;

public interface RecordBLService {

	public RecordVO lookup();
	
	public void add(RecordVO vo);
	
	
}
