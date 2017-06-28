package cn.com.learn.designPattern.P08__Proxy;

/**
 * 
 * ClassName: IGamePlayer 
 * @Description: TODO
 * @author pepper
 * @date 2016-7-25
 */
public interface IGamePlayer {


    /**
     * 	
     * @Description: 打怪
     * @param    
     * @return void  
     * @throws
     * @author pepper
     * @date 2016-7-25
     */
	public void killMonster();

	/**
	 * 
	 * @Description: 升级
	 * @param    
	 * @return void  
	 * @throws
	 * @author pepper
	 * @date 2016-7-25
	 */
	public void upgrade();

}
