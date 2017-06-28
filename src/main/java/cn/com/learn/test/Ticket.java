package cn.com.learn.test;

public class Ticket implements Runnable {

	public Integer tickets;

	public Ticket(int tickets) {
		this.tickets = tickets;
	}

	@Override
	public void run() {
		synchronized (tickets) {
			while (tickets > 0) {
				while (tickets > 20) {
					System.out.println(Thread.currentThread().getName() + ":" + tickets-- );
				}
				
				if (Thread.currentThread().getName().equals("B")) {
					System.out.println(Thread.currentThread().getName() + ":" + tickets--);
				}
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		Ticket ticket = new Ticket(100);
		new Thread(ticket, "A").start();
		new Thread(ticket, "B").start();
	}

}
