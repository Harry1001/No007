package stub;

import java.util.ArrayList;

import businessLogic.BLImpl;
import businessLogicService.InfoBLService;
import typeDefinition.InfoType;
import typeDefinition.Job;
import typeDefinition.myTime;
import vo.AgencyVO;
import vo.InfoVO;
import vo.StaffVO;

public class InfoBL_Stub implements InfoBLService{

	/*
	 * 返回type类型的InfoVO列表
	 */
	public ArrayList<InfoVO> getInfoList(InfoType type) {
		// TODO Auto-generated method stub
		
		ArrayList<InfoVO> info=new ArrayList<InfoVO>();
		InfoVO vo1=new StaffVO("141250001","张三","男",new myTime(1990,10,10),Job.STROESALSMAN,3000,0);
		InfoVO vo2=new StaffVO("141250002","李四","男",new myTime(1990,2,22),Job.STROESALSMAN,3000,0);
		InfoVO vo3=new AgencyVO("栖霞","营业厅","025000000","南京市",200,8000);
		InfoVO vo4=new AgencyVO("朝阳","中转中心","022000000","北京市",300,10000);

		if(type==InfoType.STAFF){
			info.add(vo1);
			info.add(vo2);
		}
		else if(type==InfoType.AGENCY){
			info.add(vo3);
			info.add(vo4);
		}
		return info;
	}

	/*
	 * 界面层要求添加一个Info
	 * 添加成功则返回true并让数据层更新相关持久化数据并通知更新系统日志，添加失败则返回false
	 */
	public boolean addInfo(InfoVO infoItem) {
		// TODO Auto-generated method stub
/*
		InfoType type=infoItem.getType();
		if(type==InfoType.STAFF){
			if(infoItem.getStaffID().length()==9){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return true;
		}
*/
		return true;
	}

	/*
	 * 界面层调用此方法请求在数据层中删除对应Info
	 */
	public void deleteInfo(InfoVO infoItem) {
		// TODO Auto-generated method stub
/*
 * if(infoItem.exist) delete;
 * else System.out.println("not found!");
 */
		System.out.println("已删除！");
	}

	/*
	 * 将type类型的编号为id的信息修改为传入的vo中的信息，如果修改失败则返回false，成功返回true
	 */
	public boolean modifyInfo(InfoType type, String id, InfoVO infoItem) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * 验证用户名和密码是否正确，正确与不正确都返回对应job
	 */
	public Job verifyPassword(String id, String password) {
		// TODO Auto-generated method stub
		return Job.COURIER;
	}

	/*
	 * 监听者调用此方法向被监听者（即this）注册
	 */
	public void register(BLImpl listener) {
		// TODO Auto-generated method stub
		
	}

	
}
