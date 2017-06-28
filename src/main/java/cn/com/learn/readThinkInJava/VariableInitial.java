package cn.com.learn.readThinkInJava;

public class VariableInitial {
	Window w1 = new Window(1);

	public VariableInitial() {
		System.out.println("VariableInitial()");
		w3 = new Window(3_3);
	}

	Window w2 = new Window(2);

	public void method() {
		System.out.println("method()");
	}

	Window w3 = new Window(3);

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		VariableInitial house = new VariableInitial();
		
		/**
		 * 打印结果如下
		 * window1 
		 * window2 
		 * window3 
		 * VariableInitial() 
		 * window33
		 */

	}
}

class Window {
	Window(int order) {
		System.out.println("window" + order);
	}
}