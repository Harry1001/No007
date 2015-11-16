package businessLogicService.infoblservice;

import java.util.ArrayList;

import typeDefinition.InfoType;
import typeDefinition.Job;
import vo.infovo.InfoVO;

public interface InfoBLService {
	
	/*
	 * 根据创建controller时的type类型返回对应的InfoVO列表
	 */
	public ArrayList<? extends InfoVO> getInfoList();
	
	/*
	 * 界面层要求添加一个Info
	 * 添加成功则返回true并让数据层更新相关持久化数据并通知更新系统日志，添加失败则返回false
	 */
	public boolean addInfo(InfoVO infoItem);
	
	/*
	 * 界面层调用此方法请求在数据层中删除对应Info
	 */
	public void deleteInfo(String id);
	
	/*
	 * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
	 */
	public boolean modifyInfo( String id, InfoVO infoItem);

}
