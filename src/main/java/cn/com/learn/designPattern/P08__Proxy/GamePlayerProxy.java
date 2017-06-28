package cn.com.learn.designPattern.P08__Proxy;

/**
 * @ClassName: GamePlayerProxy 
 * @Description: 代理模式就是多一个代理类出来，替原对象进行一些操作，
 *               比入玩游戏的 你找代练替你玩， 他使用的还是你的账号，
 *               依旧是你的账号在打怪升级
 * @author pepper
 * @date 2016-7-25
 */
public class GamePlayerProxy implements IGamePlayer {

	private IGamePlayer player;

	public GamePlayerProxy() {

	}

	public GamePlayerProxy(IGamePlayer player) {
		this.player = player;
	}

	@Override
	public void killMonster() {
		this.player.killMonster();

	}

	@Override
	public void upgrade() {
		this.player.upgrade();

	}

	/**
	 * 
	 * @Description: 代理类（游戏代练）除了完成被代理类（玩家）的方法（打怪，升级），还可以拥有自己的方法（收取代理费用）
	 * @param    
	 * @return void  
	 * @throws
	 * @author pepper
	 * @date 2016-7-25
	 */
	public void getPay() {
		System.out.println("一共收取费用 150￥");
	}
}
