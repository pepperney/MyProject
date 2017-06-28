package cn.com.learn.designPattern.P14__TemplateMethod;

public class SubTemplate extends Template{
	
	public void start() {
		
		System.out.println("第一步：start");
	}
	
	public void end() {
		System.out.println("第三步：end");
	}

	public void process() {
		System.out.println("第二步：process");
	}

	

}
