package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

import businessLogicService.receiptblservice.ReceiptBLService;
import dataService.ReceiptDataService;
import po.ReceiptPO;
import po.SendReceiptPO;
import vo.ReceiptVO;
import vo.SendReceiptVO;
import typeDefinition.ReceiptState;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

public class ReceiptBL implements ReceiptBLService{

	private ReceiptDataService receiptData;
	private ArrayList<ReceiptPO> sendReceiptPOList;
	private ArrayList<? extends ReceiptPO> ReceiptPOList;
	//待补全！！！！！！
	
	/*
	 * constructor
	 */
	public ReceiptBL(ReceiptDataService receiptData){
		this.receiptData=receiptData;
	}
	
	/*
	 * 生成ReceiptBLImpl对象时就需要从数据层载入Receipt数据，载入失败则说明网络有问题，此时展示层应该给出提示
	 */
	public boolean initializ(){
		try {
			//此处要载入所有ReceiptPO的列表
			sendReceiptPOList=receiptData.getList(ReceiptType.SEND,ReceiptState.RAW,new myTime(2015,10,26) );
			//待补全！！！！！！！！！
			
			return true;
		} catch (RemoteException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ReceiptVO> getListByState(ReceiptState state) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ReceiptVO> getListByTime(myTime fromTime, myTime toTime, ReceiptType type) {
		// TODO Auto-generated method stub
		return null;
	}

	public void approve(ArrayList<ReceiptVO> itemList) {
		// TODO Auto-generated method stub
		
	}

	public void disapprove(ArrayList<ReceiptVO> itemList) {
		// TODO Auto-generated method stub
		
	}

	public void createReceipt(ReceiptVO item) {
		// TODO Auto-generated method stub
		if(item.getType()==ReceiptType.SEND){
			SendReceiptVO vo=(SendReceiptVO)item;
			ReceiptPO po=new SendReceiptPO(vo.getSenderName(),vo.getSenderLoc(),vo.getSenderUnit(),
					vo.getSenderPhone(),vo.getReceiverName(),vo.getReceiverLoc(),vo.getReceiverUnit(),
					vo.getReceiverPhone(),vo.getNumber(),vo.getWeight(),vo.getVolume(),vo.getName(),
					vo.getExpressType(),vo.getPack(),vo.getExpressNumber(),vo.getMoney(),vo.getPredictTime());
		}		
	}

}
