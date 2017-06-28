package cn.com.learn.designPattern.P09__Facade;


/**
 * 
 * @Description: 
 * 外观模式是为了解决类与类之家的依赖关系的，降低了类类之间的耦合度，
 * 本例中，用户需要启动电脑完成相应的操作，为了避免高层用户与cpu,memery,
 * disk直接交互耦合，我们建立一个computer类作为外观，用户只能通过computer
 * 完成相关的操作，禁止直接绕过外观（computer）而访问其子系统（cpu,memery,
 * disk）的行为
 * @author  pepper
 * @date 2016-7-30
 */
public class Computer {
	
	private Cpu cpu;
	private Memory memory;
	private Disk disk;
	
	
	public Computer(){
		
		cpu = new Cpu();
		memory = new Memory();
		disk = new Disk();
	}
	public void startUp(){
		System.out.println("begin open the computer  ");
		cpu.startUp();
		memory.startUp();
		disk.startUp();
		System.out.println("finish open the computer  ");
	}
	
	public void shutdown(){
		System.out.println("begin close the computer");
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
		System.out.println("finish close the computer");
	}
}
