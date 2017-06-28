package cn.com.learn.designPattern.P10__Command;

public class Client {


	public static void main(String[] args) {

		Receiver receiver = new Receiver();

		Command command = new CommandImpl(receiver);
		
		Requester requester = new Requester(command);
		
		requester.generateCommand();
	}

}
