package businessLogicService.infoblservice;

import vo.infovo.StaffVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface GetStaffInfoBLService {

    /**
     * 获得人员信息列表，用于期初建账
     * @return
     */
    public ArrayList<StaffVO> getInfoList();
}
