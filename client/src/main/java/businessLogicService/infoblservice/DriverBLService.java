package businessLogicService.infoblservice;


import myexceptions.InfoBLException;
import vo.infovo.DriverVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface DriverBLService {

    /**
     * 获取司机信息列表，用于期初建账
     * @return
     */
    public ArrayList<DriverVO> getDriverList() throws InfoBLException;

    /**
     * 界面层要求添加一个机构Info
     *
     * @throws InfoBLException, 表示RMI连接失败
     */
    public void addDriver(DriverVO vo) throws InfoBLException;

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     */
    public void deleteDriver(String id) throws InfoBLException;

    /**
     * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
     */
    public void modifyDriver( String id, DriverVO vo) throws InfoBLException;
}
