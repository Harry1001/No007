package po;

import java.io.Serializable;

import typeDefinition.InfoType;

/**
 *信息管理的父类，其子类对应距离信息
 */
public abstract class InfoPO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private InfoType type;
	
	public InfoPO(InfoType type) {
		this.type=type;
	}

	public InfoType getType() {
		return type;
	}

	
	
}
