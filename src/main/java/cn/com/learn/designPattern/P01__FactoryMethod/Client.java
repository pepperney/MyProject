package cn.com.learn.designPattern.P01__FactoryMethod;

public class Client {

	public static void main(String[] args) {
		AbstractHumanFactory factory = new HumanFactory();
		Human whiteHuman = factory.createHuman(WhiteHuman.class);
		whiteHuman.showSkinColor();
		Human yellowHuman = factory.createHuman(YellowHuman.class);
		yellowHuman.showSkinColor();
		Human blackHuman = factory.createHuman(BlackHuman.class);
		blackHuman.showSkinColor();
	}
}
