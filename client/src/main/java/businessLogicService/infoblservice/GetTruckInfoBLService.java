package businessLogicService.infoblservice;

import vo.infovo.TruckVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface GetTruckInfoBLService {

    /**
     * 获取车辆信息列表，用于期初建账
     * @return
     */
    public ArrayList<TruckVO> getInfoList();
}
