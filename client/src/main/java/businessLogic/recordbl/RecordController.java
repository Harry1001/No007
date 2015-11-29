package businessLogic.recordbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.recordvo.RecordVO;

public class RecordController {
	RecordBL recordbl=new RecordBL();
	public ArrayList<RecordVO> lookup() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<RecordVO> rvo=new ArrayList<RecordVO>();
		rvo=recordbl.lookup();
		return rvo;
	}

	/*public void add(RecordVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		recordbl.add(vo);
	}*/
}
