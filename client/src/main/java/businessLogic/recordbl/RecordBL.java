package businessLogic.recordbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import businessLogicService.recordblservice.RecordBLService;
import dataService.RecordDataService;
import dataService._RMI;
import po.recordpo.RecordPO;
import vo.recordvo.RecordVO;

public class RecordBL implements RecordBLService{

	RecordDataService rd;
	public RecordBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_record";
		rd=(RecordDataService)Naming.lookup(url);
	}
	public ArrayList<RecordVO> lookup() throws RemoteException, SQLException {
		
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
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public void add(RecordVO vo) throws RemoteException, SQLException {
		
		RecordPO po=new RecordPO(vo);
		rd.record(po);
	}

}
