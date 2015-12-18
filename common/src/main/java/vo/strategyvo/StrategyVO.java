package vo.strategyvo;

import java.io.Serializable;

/**
 * 此类为策略的父类
 * @author Administrator
 *
 */
public abstract class StrategyVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String type;
	
	public StrategyVO(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
}
