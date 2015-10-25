package po;

import typeDefinition.Location;

public class CommodityPO {

	private int depotInNum;//入库数量
	private int depotOutMoney;//金额
	private Location storeloc;//存储位置
	private int depotChange;//库存变化数量
	
	public CommodityPO(int depotInNum,int depotOutMoney,
			Location storeloc,int depotChange){
		this.depotInNum=depotInNum;
		this.depotOutMoney=depotOutMoney;
		this.storeloc=storeloc;
		this.depotChange=depotChange;
	}

	public int getDepotInNum() {
		return depotInNum;
	}

	public void setDepotInNum(int depotInNum) {
		this.depotInNum = depotInNum;
	}

	public int getDepotOutMoney() {
		return depotOutMoney;
	}

	public void setDepotOutMoney(int depotOutMoney) {
		this.depotOutMoney = depotOutMoney;
	}

	public Location getStoreloc() {
		return storeloc;
	}

	public void setStoreloc(Location storeloc) {
		this.storeloc = storeloc;
	}

	public int getDepotChange() {
		return depotChange;
	}

	public void setDepotChange(int depotChange) {
		this.depotChange = depotChange;
	}
}
