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


    public void addAgency(InfoVO infoItem) throws RemoteException, InfoBLException{
        AgencyVO vo=(AgencyVO)infoItem;

            super.add(new AgencyPO(vo));


        //todo 需要在界面层提示
        System.out.println("已包含该机构");

    }


    public void deleteAgency(String id) throws RemoteException{
        super.delete(InfoType.AGENCY, id);
    }


    public void modifyAgency(String id, InfoVO infoItem) throws RemoteException, InfoBLException{

        AgencyVO vo=(AgencyVO)infoItem;
       // if(!vo.getAgencyID().equals(id)){//修改了原来的编号
        //    if(containsInfo(vo.getAgencyID())){
       //         System.out.println("已包含该机构");
        //        return false;
       //     }
        //}
        //机构编号没变
       super.modify(id, new AgencyPO(vo));

       // return addInfo(infoItem);
    }


}
