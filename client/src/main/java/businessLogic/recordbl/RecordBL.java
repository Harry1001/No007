package businessLogic.recordbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogicService.recordblservice.RecordBLService;
import data.RecordDataImpl;
import dataService.RecordDataService;
import po.recordpo.RecordPO;
import vo.recordvo.RecordVO;

public class RecordBL implements RecordBLService{

	RecordDataService rd=new RecordDataImpl();
	public ArrayList<RecordVO> lookup() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<RecordPO> apo=rd.getRecord();
		
		ArrayList<RecordVO> avo=new ArrayList<new RecordVO(apo)>();
		return null;
	}

	public void add(RecordVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		RecordPO po=new RecordPO(vo);
		rd.record(po);
	}

}
