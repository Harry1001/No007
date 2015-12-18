package po.strategypo;

import java.io.Serializable;

/**
 * 策略类的父类
 * @author Administrator
 *
 */
public abstract class StrategyPO implements Serializable {

	private static final long serialVersionUID = 1L;//非常重要！！！我tm找了半天这个bug！！！只有序列化才能在网络上传输！！

	private String type;
	public StrategyPO(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
}
