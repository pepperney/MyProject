package cn.com.learn.designPattern.P12__Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 被观察者--完成管理并通知观察者的职责
 * @author  pepper
 * @date 2016-7-30
 */
public abstract class AbstractSubject  {
	
	private List<Observer> list = new ArrayList<Observer>();

	/**
	 * 
	 * @Description:增加观察者 
	 * @param @param observer   
	 * @return $
	 * @throws
	 * @author pepper
	 * @date 2016-7-30
	 */
	public void addObserver(Observer observer) {

		list.add(observer);
	}
	
	/**
     * 
     * @Description: 删除观察者
     * @param @param observer   
     * @return $
     * @throws
     * @author pepper
     * @date 2016-7-30
     */
	public void removeObserver(Observer observer) {

		list.remove(observer);

	}

	/**
	 * 
	 * @Description: 通知观察者更新状态
	 * @param    
	 * @return $
	 * @throws
	 * @author pepper
	 * @date 2016-7-30
	 */
	public void notifyAllObserver() {
		
		for(Observer observer : list){
			observer.update();
		}
	}	
	
	public abstract void changeStatus();

}
