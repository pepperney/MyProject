package cn.com.learn.designPattern.P08__Proxy;

public class GamePlayer implements IGamePlayer{
	
	private String name;
	
	public GamePlayer(String name){
		this.name = name;
	}

	@Override
	public void killMonster() {
		System.out.println(this.name + " is killing monster");
		
	}

	@Override
	public void upgrade() {
		System.out.println(this.name + " has upgraded");
		
	}

	
}
