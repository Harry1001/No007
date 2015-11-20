package businessLogicService.infoblservice;

import vo.infovo.AgencyVO;

import java.util.ArrayList;

/**
 * Created by Harry on 2015/11/18.
 */
public interface GetAgencyInfoBLService {

    /**
     * 获取机构信息列表，用于期初建账
     * @return
     */
    public ArrayList<AgencyVO> getAgencyList();

    /**
     * 界面层要求添加一个机构Info
     * 添加成功则返回true并让数据层更新相关持久化数据并通知更新系统日志，添加失败则返回false
     */
    public boolean addAgency(AgencyVO agencyItem);

    /**
     * 界面层调用此方法请求在数据层中删除对应Info
     */
    public void deleteAgency(String id);

    /**
     * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
     */
    public boolean modifyAgency( String id, AgencyVO agencyItem);
}
