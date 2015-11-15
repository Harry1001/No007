package businessLogic.recordbl;

import vo.recordvo.RecordVO;

public class RecordController {
	RecordBL recordbl=new RecordBL();
	public RecordVO lookup() {
		// TODO Auto-generated method stub
		RecordVO rvo=new RecordVO(null, null, null);
		rvo=recordbl.lookup();
		return rvo;
	}

	public void add(RecordVO vo) {
		// TODO Auto-generated method stub
		recordbl.add(vo);
	}
}
