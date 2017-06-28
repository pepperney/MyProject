package cn.com.learn.readThinkInJava;

public class ValidateStaticInitial {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Person person = new Person(3);
		Person.f(3);
		/**
		 * 打印结果如下
		 * Person(1) 
		 * Person(2) 
		 * Person(3) 
		 * f(3)
		 * 
		 */
	}
}

class Person {

	static Person p1 = new Person(1);

	public Person(int i) {
		System.out.println("Person(" + i + ")");
	}

	static {
		p2 = new Person(2);
	}

	static Person p2;

	static void f(int i) {
		System.out.println("f(" + i + ")");
	}

}
