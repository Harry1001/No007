package businessLogicService.infoblservice;

import java.util.ArrayList;

import typeDefinition.InfoType;
import typeDefinition.Job;
import vo.infovo.InfoVO;

public interface InfoBLService {
	
	/*
	 * 返回type类型的InfoVO列表
	 */
	public ArrayList<InfoVO> getInfoList(InfoType type);
	
	/*
	 * 界面层要求添加一个Info
	 * 添加成功则返回true并让数据层更新相关持久化数据并通知更新系统日志，添加失败则返回false
	 */
	public boolean addInfo(InfoVO infoItem);
	
	/*
	 * 界面层调用此方法请求在数据层中删除对应Info
	 */
	public void deleteInfo(InfoVO infoItem);
	
	/*
	 * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
	 */
	public boolean modifyInfo(InfoType type, String id, InfoVO infoItem);
	
	/*
	 * 验证用户名和密码是否正确，正确与不正确都返回对应job
	 */
	public Job verifyPassword(String id, String password);
}
