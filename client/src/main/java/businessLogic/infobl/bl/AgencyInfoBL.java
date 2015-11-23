package businessLogic.infobl.bl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import myexceptions.InfoBLException;
import po.infopo.AgencyPO;
import typeDefinition.InfoType;
import vo.infovo.AgencyVO;
import vo.infovo.InfoVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class AgencyInfoBL extends InfoBL  {

    private ArrayList<AgencyPO> agencyPOs;

    public AgencyInfoBL(){
        this(new InfoDataImpl());
    }

    public AgencyInfoBL(InfoDataService infoData) {
        super(infoData);
        //agencyPOs=(ArrayList<AgencyPO>)super.getList(InfoType.AGENCY);
    }

    public ArrayList<AgencyVO> getAgencyList() throws RemoteException{

        agencyPOs=(ArrayList<AgencyPO>)super.getList(InfoType.AGENCY);//界面层每次修改完都要refresh一下重新getInfoList，也要刷新一下po
        ArrayList<AgencyVO> agencyVOs=new ArrayList<AgencyVO>();

        for(AgencyPO po:agencyPOs){
            agencyVOs.add(new AgencyVO(po));
        }

        return agencyVOs;
    }


    public void addAgency(AgencyVO vo) throws RemoteException, InfoBLException{
            super.add(new AgencyPO(vo));
    }


    public void deleteAgency(String id) throws RemoteException{
        super.delete(InfoType.AGENCY, id);
    }


    public void modifyAgency(String id, AgencyVO vo) throws RemoteException, InfoBLException{


       super.modify(id, new AgencyPO(vo));


    }


}
