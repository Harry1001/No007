package driver;

import java.util.ArrayList;

import businessLogicService.infoblservice.InfoBLService;
import typeDefinition.InfoType;
import typeDefinition.Job;
import typeDefinition.myTime;
import vo.InfoVO;
import vo.StaffVO;

public class InfoBL_Driver {

	public void driver(InfoBLService infoBLService){
		
		ArrayList<InfoVO> info1=infoBLService.getInfoList(InfoType.STAFF);
		ArrayList<InfoVO> info2=infoBLService.getInfoList(InfoType.AGENCY);
		System.out.println(info1);
		System.out.println(info2);

/*		System.out.println("工号:"+info1.getStaffID());
		System.out.println("姓名:"+info1.getName());
		System.out.println("性别:"+info1.getGender());
		System.out.println("出生年月:"+info1.getBirthday());
		System.out.println("所属部门:"+info1.getPosition());
		System.out.println("基本工资:"+info1.getBasicSalary());
		System.out.println("出勤次数:"+info1.getWorkFrequency());
*/
		InfoVO infoItem1=new StaffVO("141250003","王五","男",new myTime(1990,3,21),Job.STROESALSMAN,3000,0);
		boolean isAdd=infoBLService.addInfo(infoItem1);
		if(isAdd){
			System.out.println("添加Info成功!");
		}
		else{
			System.out.println("Error!");
		}
		
		InfoVO infoItem2=new StaffVO("141250001","张三","男",new myTime(1990,10,10),Job.STROESALSMAN,3000,0);
		infoBLService.deleteInfo(infoItem2);
		
		String id="141250001";
		boolean isModify=infoBLService.modifyInfo(InfoType.AGENCY, id, infoItem2);
		if(isModify){
			System.out.println("已修改");//跳转到信息显示界面
		}
		else{
			System.out.println("修改失败！");
		}
		
		String password="abc000000";
		Job job=infoBLService.verifyPassword(id, password);
		System.out.println(job);
		
	}
}
