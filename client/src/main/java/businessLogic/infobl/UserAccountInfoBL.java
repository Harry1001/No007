package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import po.infopo.UserAccountPO;
import typeDefinition.InfoType;
import typeDefinition.Job;
import vo.infovo.InfoVO;
import vo.infovo.UserAccountVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/16.
 */
public class UserAccountInfoBL extends InfoBL {

    private ArrayList<UserAccountPO> userAccountPOs;

    public UserAccountInfoBL(){
        this(new InfoDataImpl());
    }

    public UserAccountInfoBL(InfoDataService infoData) {

        super(infoData);
        userAccountPOs=(ArrayList<UserAccountPO>)super.getList(InfoType.ACCOUNT);
    }

    @Override
    public ArrayList<? extends InfoVO> getInfoList() {
        ArrayList<UserAccountVO> userAccountVOs=new ArrayList<UserAccountVO>();

        for(UserAccountPO po:userAccountPOs){
            userAccountVOs.add(new UserAccountVO(po));
        }
        return userAccountVOs;
    }

    @Override
    public boolean addInfo(InfoVO infoItem) {
        UserAccountVO vo=(UserAccountVO)infoItem;
        if(!containsInfo(vo.getUserID())){//如果不含新加的员工
            return super.add(new UserAccountPO(vo));
        }

        //todo 需要在界面层提示
        System.out.println("已包含该账户");
        return false;
    }

    @Override
    public void deleteInfo(String id) {
        super.delete(InfoType.ACCOUNT, id);
    }

    @Override
    public boolean modifyInfo(String id, InfoVO infoItem) {
        UserAccountVO vo=(UserAccountVO)infoItem;
        if(!vo.getUserID().equals(id)){//修改了原来的工号
            if(containsInfo(vo.getUserID())){
                System.out.println("已包含该账户");
                return false;
            }
        }
        //工号没变
        deleteInfo(id);

        return addInfo(infoItem);
    }

    public Job verifyPassword(String id, String password){
        //todo
        for(UserAccountPO po:userAccountPOs){
            if(po.getUserID().equals(id)){
                return po.getPosition();
            }
        }
        return Job.NOTFOUND;
    }

    private boolean containsInfo(String id){
        for(UserAccountPO po:userAccountPOs){
            if(po.getUserID().equals(id))
                return true;
        }
        return false;
    }
}
