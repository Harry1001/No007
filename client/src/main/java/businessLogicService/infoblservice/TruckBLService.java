package businessLogicService.infoblservice;

import myexceptions.InfoBLException;
import vo.infovo.TruckVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface TruckBLService {

    /**
     * 获取车辆信息列表，用于期初建账
     * @return
     */
    public ArrayList<TruckVO> getTruckList() throws InfoBLException;

    public void addTruck(TruckVO vo) throws InfoBLException;

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     */
    public void deleteTruck(String id) throws InfoBLException;

    /**
     * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
     */
    public void modifyTruck( String id, TruckVO vo) throws InfoBLException;
}
