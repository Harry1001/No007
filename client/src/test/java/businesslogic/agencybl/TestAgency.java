package businesslogic.agencybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogic.infobl.bl.AgencyInfoBL;
import myexceptions.InfoBLException;
import vo.infovo.AgencyVO;

public class TestAgency {

	public static void main(String args[]) {
		AgencyVO vo = new AgencyVO("南京大学", "Transfer", "0251", "南京大学中转中心", 2, 5000);
		try {
			AgencyInfoBL agencyInfoBL = new AgencyInfoBL();
			try {
				agencyInfoBL.addAgency(vo);
			} catch (InfoBLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
