package vo.infovo;

import java.io.Serializable;

import typeDefinition.InfoType;

public abstract class InfoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private InfoType type;
	
	
	public InfoVO(InfoType type){
		this.type=type;
	}


	public InfoType getType() {
		return type;
	}
	
}
