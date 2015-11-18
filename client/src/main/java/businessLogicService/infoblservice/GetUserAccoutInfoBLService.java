package businessLogicService.infoblservice;

import vo.infovo.UserAccountVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface GetUserAccoutInfoBLService {

    /**
     * 获取用户账户信息列表，用于期初建账
     * @return
     */
    public ArrayList<UserAccountVO> getInfoList();
}
