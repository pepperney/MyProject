package cn.com.learn.clone.implentsCloneable;

public class Test1 {
	public static void main(String[] args) throws CloneNotSupportedException {
		
		Car benz = new Car("Benz");
		Person p1 = new Person("张三");
		p1.setCar(benz);
		
		System.out.println("p1  --> " + p1);
		
		
		Person p2 =  p1.clone();
		p2.setName("李四");
		System.out.println("p2  --> " + p2);
		
		System.out.println("p1  --> " + p1);
		
	}
}
