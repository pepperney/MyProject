package cn.com.learn.designPattern.P05__Prototype;

public class Mail implements Cloneable{

	
	private String receiver;//接受者
	private String sender;//发送者
	private String title;//主题
	private String context;//正文
	private String appellation;//称谓
	
	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public Mail(AdvertisemeTemplate advTem){
		this.title = advTem.getTitle();
		this.context = advTem.getContext();
	}
	
	
	/*================================================================*/
	@Override
	protected Mail clone() throws CloneNotSupportedException {
		Mail mail = (Mail)super.clone();
		return mail;
	}
	/*================================================================*/
	
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
}
