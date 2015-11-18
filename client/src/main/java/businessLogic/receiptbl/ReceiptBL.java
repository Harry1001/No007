package businessLogic.receiptbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogicService.receiptblservice.CreateReceiptBLService;
import businessLogicService.receiptblservice.GetReceiptBLService;
import data.ReceiptDataImpl;
import dataService.ReceiptDataService;
import po.receiptpo.*;
import vo.receiptvo.*;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;

public class ReceiptBL implements GetReceiptBLService, CreateReceiptBLService{


	private ReceiptDataService receiptData;

	/*
	 * constructor
	 */
	public ReceiptBL(){
		this(new ReceiptDataImpl());
	}
	public ReceiptBL(ReceiptDataService receiptData){
		this.receiptData=receiptData;
	}
	

	public ArrayList<? extends ReceiptVO> getListByTime(myTime fromTime, myTime toTime, ReceiptType type){
		//如果起始时间小于终止时间，报错
		//todo

		ArrayList<ReceiptVO> receiptVOs= new ArrayList<ReceiptVO>();
		try{
			ArrayList<? extends ReceiptPO> receiptPOs= receiptData.getList(type,fromTime,toTime);

			switch (type){
				case SEND:{
					for(SendReceiptPO po: (ArrayList<SendReceiptPO>)receiptPOs){
						receiptVOs.add(new SendReceiptVO(po));
					}
					break;
				}
				case DESPATCH:{
					for(DespatchReceiptPO po: (ArrayList<DespatchReceiptPO>)receiptPOs){
						receiptVOs.add(new DespatchReceiptVO(po));
					}
					break;
				}
				case ENTRUCK:{
					for(EntruckReceiptPO po: (ArrayList<EntruckReceiptPO>)receiptPOs){
						receiptVOs.add(new EntruckReceiptVO(po));
					}
					break;
				}
				case TRANSFER: {
					for (TransferReceiptPO po : (ArrayList<TransferReceiptPO>) receiptPOs) {
						receiptVOs.add(new TransferReceiptVO(po));
					}
					break;
				}
				case STOREARRIVAL:{
					for (StoreArrivalReceiptPO po : (ArrayList<StoreArrivalReceiptPO>) receiptPOs) {
						receiptVOs.add(new StoreArrivalReceiptVO(po));
					}
					break;
				}
				case HUBARRIVAL:{
					for (HubArrivalReceiptPO po : (ArrayList<HubArrivalReceiptPO>) receiptPOs) {
						receiptVOs.add(new HubArrivalReceiptVO(po));
					}
					break;
				}
				case DEPOTIN:{
					for (DepotInReceiptPO po : (ArrayList<DepotInReceiptPO>) receiptPOs) {
						receiptVOs.add(new DepotInReceiptVO(po));
					}
					break;
				}
				case DEPOTOUT:{
					for (DepotOutReceiptPO po : (ArrayList<DepotOutReceiptPO>) receiptPOs) {
						receiptVOs.add(new DepotOutReceiptVO(po));
					}
					break;
				}
				case CHARGE:{
					for (ChargeReceiptPO po : (ArrayList<ChargeReceiptPO>) receiptPOs) {
						receiptVOs.add(new ChargeReceiptVO(po));
					}
					break;
				}
				case PAY:{
					for (PayReceiptPO po : (ArrayList<PayReceiptPO>) receiptPOs) {
						receiptVOs.add(new PayReceiptVO(po));
					}
					break;
				}
				case RECEIVE:{
					for (ReceiveReceiptPO po : (ArrayList<ReceiveReceiptPO>) receiptPOs) {
						receiptVOs.add(new ReceiveReceiptVO(po));
					}
					break;
				}
			}

		}catch (RemoteException e){
			System.out.println("get receipt list from data layer fail");
		}
		return receiptVOs;
	}

	public void createReceipt(ReceiptVO item) {
		ReceiptType type = item.getType();
		try{
			switch (type){

				case SEND:receiptData.addItem(new SendReceiptPO((SendReceiptVO)item));break;
				case DESPATCH:receiptData.addItem(new DespatchReceiptPO((DespatchReceiptVO)item));break;
				case ENTRUCK:receiptData.addItem(new EntruckReceiptPO((EntruckReceiptVO)item));break;
				case TRANSFER:receiptData.addItem(new TransferReceiptPO((TransferReceiptVO)item));break;
				case STOREARRIVAL:receiptData.addItem(new StoreArrivalReceiptPO((StoreArrivalReceiptVO)item));break;
				case HUBARRIVAL:receiptData.addItem(new HubArrivalReceiptPO((HubArrivalReceiptVO)item));break;
				case DEPOTIN:receiptData.addItem(new DepotInReceiptPO((DepotInReceiptVO)item));break;
				case DEPOTOUT:receiptData.addItem(new DepotOutReceiptPO((DepotOutReceiptVO)item));break;
				case CHARGE:receiptData.addItem(new ChargeReceiptPO((ChargeReceiptVO)item));break;
				case PAY:receiptData.addItem(new PayReceiptPO((PayReceiptVO)item));break;
				case RECEIVE:receiptData.addItem(new ReceiveReceiptPO((ReceiveReceiptVO)item));break;
			}
		}catch (RemoteException e){
			System.out.println("create receipt int data layer fail");
		}

	}

}
