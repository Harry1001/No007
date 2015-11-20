package businessLogicService.infoblservice;

import myexceptions.AgencyBLException;
import vo.infovo.AgencyVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface GetAgencyInfoBLService {

    /**
     * 获取机构信息列表，用于期初建账或界面层显示
     * @param
     * @return 机构信息列表
     * @throws AgencyBLException, 表示RMI连接失败
     */
    public ArrayList<AgencyVO> getAgencyList() throws AgencyBLException;

    /**
     * 界面层要求添加一个机构Info
     * 添加成功则返回true并让数据层更新相关持久化数据并通知更新系统日志，添加失败则返回false
     * @param
     * @return 机构信息列表
     * @throws AgencyBLException, 表示RMI连接失败
     */
    public void addAgency(AgencyVO agencyItem) throws AgencyBLException;

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     */
    public void deleteAgency(String id) throws AgencyBLException;

    /**
     * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
     */
    public void modifyAgency( String id, AgencyVO agencyItem) throws AgencyBLException;
}
