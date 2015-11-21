package businessLogic.infobl.controller;

import businessLogicService.infoblservice.UserAccoutBLService;
import myexceptions.InfoBLException;
import vo.infovo.UserAccountVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/21.
 */
public class UserAccountBLController implements UserAccoutBLService {
    public ArrayList<UserAccountVO> getInfoList() throws InfoBLException {
        return null;
    }

    public void addUserAccount(UserAccountVO vo) throws InfoBLException {

    }

    public void deleteUserAccount(String id) throws InfoBLException {

    }

    public void modifyUserAccount(String id, UserAccountVO vo) throws InfoBLException {

    }
}
