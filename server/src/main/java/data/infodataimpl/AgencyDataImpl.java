package data.infodataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.infodataservice.AgencyDataService;
import database.AgencyDBManager;
import myexceptions.InfoBLException;
import po.infopo.AgencyPO;

public class AgencyDataImpl extends UnicastRemoteObject implements AgencyDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AgencyDBManager agencyDBManager;
	
	public AgencyDataImpl() throws RemoteException {
		super();
		this.agencyDBManager = new AgencyDBManager();
	}

	public ArrayList<AgencyPO> getList() throws RemoteException, SQLException {
		ArrayList<AgencyPO> agencyPOs = agencyDBManager.getall();
		return agencyPOs;
	}

	public void addItem(AgencyPO item) throws RemoteException, InfoBLException, SQLException {
		if(!isExist(item.getAgencyID())) {
			agencyDBManager.add(item);			
		}
		else throw new InfoBLException("该机构编号已存在");
	}

	public void deleteItem(String id) throws RemoteException, SQLException {
		agencyDBManager.delete(id);		
	}

	public void update(String id, AgencyPO item) throws RemoteException, InfoBLException, SQLException {
		if(!isExist(item.getAgencyID()) || id.equals(item.getAgencyID())) {
			agencyDBManager.delete(id);
			agencyDBManager.add(item);					
		}
		else {
			throw new InfoBLException("该机构编号已存在");
		}
	}
	
	public String getAgengcy(String agencyID) throws RemoteException, SQLException {
		return agencyDBManager.getAgengcy(agencyID);
	}
	
	private boolean isExist(String id) throws SQLException {
		String aString = agencyDBManager.getAgengcy(id);
		if(aString == null) return false;
		else return true;
	}

}
