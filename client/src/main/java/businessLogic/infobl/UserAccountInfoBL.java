package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import typeDefinition.Job;

/**
 * Created by Harry on 2015/11/16.
 */
public class UserAccountInfoBL extends InfoBL {

    public UserAccountInfoBL(){
        this(new InfoDataImpl());
    }

    public UserAccountInfoBL(InfoDataService infoData) {
        super(infoData);
    }

    public Job verifyPassword(String id, String password){
        //todo
        return Job.COURIER;
    }
}
