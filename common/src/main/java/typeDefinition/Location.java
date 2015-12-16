package typeDefinition;

import java.io.Serializable;

public class Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String TransferNum;
	private int regionID;//区
	private int rowID;//排
	private int shelfID;//架
	private int postID;//位
	
	public Location(String transferNum, int regionID,int rowID,int shelfID,int postID){
		this.setTransferNum(transferNum);
		this.setRegionID(regionID);
		this.setRowID(rowID);
		this.setShelfID(shelfID);
		this.setPostID(postID);
	}
	
	 public int getRegionID(){
		 return regionID;
	 }
	 
	 public void setRegionID(int regionID){
		 this.regionID=regionID;
	 }
	 
	 public int getRowID(){
		 return rowID;
	 }
	 
	 public void setRowID(int rowID){
		 this.rowID=rowID;
	 }

	public int getShelfID() {
		return shelfID;
	}

	public void setShelfID(int shelfID) {
		this.shelfID = shelfID;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public String getTransferNum() {
		return TransferNum;
	}

	public void setTransferNum(String transferNum) {
		TransferNum = transferNum;
	}
	 
	 
}
