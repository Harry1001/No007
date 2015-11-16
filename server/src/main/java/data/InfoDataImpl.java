package data;

import dataService.InfoDataService;
import po.infopo.InfoPO;
import typeDefinition.InfoType;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class InfoDataImpl implements InfoDataService {
    public ArrayList<InfoPO> getList(InfoType type) throws RemoteException {
        return null;
    }

    public void addItem(InfoPO item) throws RemoteException {

    }

    public void deleteItem(InfoType type, String id) throws RemoteException {

    }

    public void update(String id, InfoPO item) throws RemoteException {

    }
}
