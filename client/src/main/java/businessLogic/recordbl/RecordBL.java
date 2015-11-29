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
		
		ArrayList<RecordVO> avo=new ArrayList<RecordVO>();
		for(int i=0;i<apo.size();i++){
			avo.add(new RecordVO(apo.get(i)));
		}
		
		return avo;
	}

	/**
	 * 系统对主要操作（车辆信息变动、司机信息变动、银行账户信息变动、人员信息变动、机构信息变动、用户账户信息变动、薪水策略变动、
	 * 价格/距离策略变动）有日志记录
	 */
	public void add(RecordVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		RecordPO po=new RecordPO(vo);
		rd.record(po);
	}

}
