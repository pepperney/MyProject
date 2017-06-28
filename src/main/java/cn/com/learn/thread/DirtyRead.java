package cn.com.learn.thread;

/**
 * 此类用于演示脏读现象
 * @Description 
 * @author niepei
 * @date 2017年4月25日 下午3:52:20 
 * @version V1.3.1
 */
public class DirtyRead {
	public String username = "A";
	public String password = "AA";

	public synchronized void setValue(String username, String password) {
		try {
			this.username = username;
			Thread.sleep(5000);
			this.password = password;
			System.out.println("setValue method thread name = " + Thread.currentThread().getName() + "\t,username = "+ this.username + ",password = " + this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getValue() {
		System.out.println("getValue method thread name = " + Thread.currentThread().getName() + "\t,username = "+ username + ",password = " + password);
	}
	
	
	
	/**
	 * 测试脏读现象
	 * @Description 
	 * @author niepei
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		DirtyRead var = new DirtyRead();
		Thread1 a = new Thread1(var);
		a.start();
		Thread.sleep(200);
		var.getValue();
		
	}
	
	
	/**
	 * 打印结果出现脏读，解决办法是对getValue方法加Synchronized关键字
	 * getValue method thread name = main,    username = B,password = AA
	 * setValue method thread name = Thread-0,username = B,password = BB
	 */
	
	

}

class Thread1 extends Thread {
	private DirtyRead var;

	public Thread1(DirtyRead var) {
		this.var = var;
	}

	@Override
	public void run() {
		var.setValue("B", "BB");
	}
}
