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
        agencyPOs=(ArrayList<AgencyPO>)super.getList(InfoType.AGENCY);
    }

    @Override
    public ArrayList<? extends InfoVO> getInfoList() {
        ArrayList<AgencyVO> agencyVOs=new ArrayList<AgencyVO>();

        for(AgencyPO po:agencyPOs){
            agencyVOs.add(new AgencyVO(po));
        }

        return agencyVOs;
    }

    @Override
    public boolean addInfo(InfoVO infoItem) {
        return false;
    }

    @Override
    public void deleteInfo(String id) {

    }

    @Override
    public boolean modifyInfo(String id, InfoVO infoItem) {
        return false;
    }
}
