package vo;
/**
 * 此类为策略的父类
 * @author Administrator
 *
 */
public abstract class StrategyVO {
	private String type;
	
	public StrategyVO(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
}
