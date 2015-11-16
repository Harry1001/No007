package businessLogic.infobl;

import data.InfoDataImpl;
import dataService.InfoDataService;
import typeDefinition.Job;
import vo.infovo.StaffVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/15.
 */
public class StaffInfoBL extends InfoBL {

    public StaffInfoBL(){
        this(new InfoDataImpl());
    }
    public StaffInfoBL(InfoDataService infoData) {
        super(infoData);
    }

    public ArrayList<StaffVO> getStaffList(){
        return null;
    }

    public Job verifyPassword(String id, String password){
        //todo
        return Job.COURIER;
    }
}
