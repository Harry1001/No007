package businessLogic.recordbl;

import java.rmi.RemoteException;

import businessLogicService.recordblservice.RecordBLService;
import data.RecordDataImpl;
import dataService.RecordDataService;
import po.recordpo.RecordPO;
import vo.recordvo.RecordVO;

public class RecordBL implements RecordBLService{

	RecordDataService rd=new RecordDataImpl();
	public RecordVO lookup() throws RemoteException {
		// TODO Auto-generated method stub
		RecordPO po=rd.getRecord();
		RecordVO vo=new RecordVO(po);
		return vo;
	}

	public void add(RecordVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		RecordPO po=new RecordPO(vo);
		rd.record(po);
	}

}
