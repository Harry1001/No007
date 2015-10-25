package businessLogic;

import java.util.Observable;
import java.util.Observer;

public abstract class BLImpl extends Observable implements Observer{

	/*
	 * 监听者向被监听者注册
	 */
	public void register(BLImpl listener){
		this.addObserver(listener);
	}
}
