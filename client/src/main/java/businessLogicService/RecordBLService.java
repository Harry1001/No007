package businessLogicService;

import vo.RecordVO;

public interface RecordBLService {

	public RecordVO lookup();
	
	public void add(RecordVO vo);
	
	
}
