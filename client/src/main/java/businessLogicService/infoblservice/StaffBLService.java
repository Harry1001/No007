package businessLogicService.infoblservice;

import myexceptions.InfoBLException;
import vo.infovo.StaffVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface StaffBLService {

    /**
     * 获取司机信息列表，用于期初建账
     * @return
     */
    public ArrayList<StaffVO> getStaffList() throws RemoteException;

    /**
     * 界面层要求添加一个机构Info
     *
     * @throws InfoBLException, 表示RMI连接失败
     */
    public void addStaff(StaffVO vo) throws InfoBLException, RemoteException;

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     */
    public void deleteStaff(String id) throws RemoteException;

    /**
     * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
     */
    public void modifyStaff( String id, StaffVO vo) throws InfoBLException, RemoteException;
}
