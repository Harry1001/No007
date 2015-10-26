package po;
/**
 * 策略类的父类
 * @author Administrator
 *
 */
public abstract class StrategyPO {
	private String type;
	public StrategyPO(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
}
