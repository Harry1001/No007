package businessLogicService.infoblservice;


import vo.infovo.DriverVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface GetDriverInfoBLService {

    /**
     * 获取司机信息列表，用于期初建账
     * @return
     */
    public ArrayList<DriverVO> getInfoList();
}
