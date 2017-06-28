
package cn.com.learn.clone.implentsSerializable;



public class Test2 {
	public static void main(String[] args) throws Exception {
		Car benz = new Car("Benz");
		Person p1 = new Person("张三");
		p1.setCar(benz);

		System.out.println("p1  --> " + p1);

		Person p2 = CloneUtil.clone(p1);
		p2.setName("李四");
		System.out.println("p2  --> " + p2);

		System.out.println("p1  --> " + p1);
	}
}
