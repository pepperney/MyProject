package cn.com.learn.designPattern.P10__Command;

public class CommandImpl implements Command {

	private Receiver receiver;

	public CommandImpl(Receiver receiver) {
		
		this.receiver = receiver;
	}

	@Override
	public void informReceiver() {

		System.out.println("服务员：我马上通知厨师为您做，请稍等");
		receiver.executeCommand();

	}

}
