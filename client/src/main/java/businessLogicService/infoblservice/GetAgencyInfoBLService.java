package businessLogicService.infoblservice;

import vo.infovo.AgencyVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface GetAgencyInfoBLService {

    /**
     * 获取机构信息列表，用于期初建账
     * @return
     */
    public ArrayList<AgencyVO> getInfoList();
}
