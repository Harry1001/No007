package businessLogic.strategybl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import businessLogic.recordbl.RecordBL;
import businessLogicService.strategyblservice.DistanceService;
import dataService.StrategyDataService;
import dataService._RMI;
import po.strategypo.DistancePO;
import vo.recordvo.RecordVO;
import vo.strategyvo.DistanceVO;

public class DistanceStrategyBL implements DistanceService{

	StrategyDataService sd;

	public DistanceStrategyBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_strategy";
		sd=(StrategyDataService)Naming.lookup(url);
	}

	RecordBL rb=new RecordBL();


	//==============================待删除=============================
	public void initDistance() throws IOException, SQLException {
		Vector<String> city=new Vector<String>();
		city.add("北京");
		city.add("上海");
		city.add("广州");
		city.add("南京");
		Vector<Vector<Object>> data=new Vector<Vector<Object>>();
		Vector<Object> v1=new Vector<Object>();
		v1.add(new String("北京"));
		v1.add(new Double(0.0));
		v1.add(new Double(1064.7));
		v1.add(new Double(1888.8));
		v1.add(new Double(900.0));
		Vector<Object> v2=new Vector<Object>();
		v1.add(new String("上海"));
		v1.add(new Double(1064.7));
		v1.add(new Double(0.0));
		v1.add(new Double(1213));
		v1.add(new Double(266));
		Vector<Object> v3=new Vector<Object>();
		v1.add(new String("广州"));
		v1.add(new Double(1888.8));
		v1.add(new Double(1213));
		v1.add(new Double(0));
		v1.add(new Double(1132));
		Vector<Object> v4=new Vector<Object>();
		v1.add(new String("南京"));
		v1.add(new Double(900));
		v1.add(new Double(266));
		v1.add(new Double(1132));
		v1.add(new Double(0));
		data.add(v1);
		data.add(v2);
		data.add(v3);
		data.add(v4);
		DistanceVO vo=new DistanceVO(city, data);
		setDistance(vo);
	}
	//=============================================================

	public void setDistance(DistanceVO vo) throws SQLException, FileNotFoundException, IOException{

		DistancePO po=new DistancePO(vo);
		sd.updateDistanceStrategy(po);
		RecordVO rvo=new RecordVO(new Date(),"总经理","制定/修改城市间距离");
		rb.add(rvo);
	}

	public double getDistance(String city1, String city2) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		double dis=sd.getDistance(city1, city2);
		return dis;
	}

	//界面层显示距离策略
	public DistanceVO getAll() throws FileNotFoundException, ClassNotFoundException, IOException{
		DistancePO po=sd.getDistanceStrategy();
		DistanceVO vo=new DistanceVO(po);
		return vo;
	}
}
