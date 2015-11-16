package vo.commodityvo;

import java.util.ArrayList;

import typeDefinition.Location;

public class CheckResultVO {

	private int depotoutnum;
	private int depotinnum;
	private ArrayList<Location> locations;

	public int getDepotoutnum() {
		return depotoutnum;
	}

	public void setDepotoutnum(int depotoutnum) {
		this.depotoutnum = depotoutnum;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public int getDepotinnum() {
		return depotinnum;
	}

	public void setDepotinnum(int depotinnum) {
		this.depotinnum = depotinnum;
	}
	
}
