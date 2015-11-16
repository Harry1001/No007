package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.AgencyPO;
import typeDefinition.InfoType;
import vo.infovo.AgencyVO;
import vo.infovo.InfoVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class AgencyInfoBL extends InfoBL {

    private ArrayList<AgencyPO> agencyPOs;

    public AgencyInfoBL(){
        this(new InfoDataImpl());
    }

    public AgencyInfoBL(InfoDataService infoData) {
        super(infoData);
        //agencyPOs=(ArrayList<AgencyPO>)super.getList(InfoType.AGENCY);
    }

    @Override
    public ArrayList<? extends InfoVO> getInfoList() {

        agencyPOs=(ArrayList<AgencyPO>)super.getList(InfoType.AGENCY);//界面层每次修改完都要refresh一下重新getInfoList，也要刷新一下po
        ArrayList<AgencyVO> agencyVOs=new ArrayList<AgencyVO>();

        for(AgencyPO po:agencyPOs){
            agencyVOs.add(new AgencyVO(po));
        }

        return agencyVOs;
    }

    @Override
    public boolean addInfo(InfoVO infoItem) {
        AgencyVO vo=(AgencyVO)infoItem;
        if(!containsInfo(vo.getAgencyID())){//如果不含新加的员工
            return super.add(new AgencyPO(vo));
        }

        //todo 需要在界面层提示
        System.out.println("已包含该机构");
        return false;
    }

    @Override
    public void deleteInfo(String id) {
        super.delete(InfoType.AGENCY, id);
    }

    @Override
    public boolean modifyInfo(String id, InfoVO infoItem) {

        AgencyVO vo=(AgencyVO)infoItem;
        if(!vo.getAgencyID().equals(id)){//修改了原来的编号
            if(containsInfo(vo.getAgencyID())){
                System.out.println("已包含该机构");
                return false;
            }
        }
        //机构编号没变
        deleteInfo(id);

        return addInfo(infoItem);
    }

    private boolean containsInfo(String id){
        for(AgencyPO po:agencyPOs){
            if(po.getAgencyID().equals(id))
                return true;
        }
        return false;
    }
}
