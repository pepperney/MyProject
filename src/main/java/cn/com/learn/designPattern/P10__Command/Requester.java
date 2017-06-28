package cn.com.learn.designPattern.P10__Command;

/**
 * 
 * @Description: 发出命令的对象
 * @author  pepper
 * @date 2016-7-30
 */
public class Requester {

	private Command command;

	public Requester(Command command) {

		this.command = command;
	}

	public void generateCommand() {

		System.out.println("顾     客：服务员你好，我要一份糕点！");
		command.informReceiver();
	}
}
