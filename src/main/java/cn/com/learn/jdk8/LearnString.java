package cn.com.learn.jdk8;

public class LearnString {
	public static void main(String[] args) {
		/**
		 * JVM中存在着一个字符串池，其中保存着很多String对象; 并且可以被共享使用，因此它提高了效率。
		 * 由于String类是final的，它的值一经创建就不可改变。
		 * 字符串池由String类维护，我们可以调用intern()方法来访问字符串池。
		 */
		String s1 = "abc";// 在字符串池创建了一个对象
		String s2 = "abc";// 字符串池已经存在对象"abc"(共享),所以创建0个对象，累计创建一个对象
		System.out.println("s1 == s2 : " + (s1 == s2));// true 指向同一个对象
		System.out.println("s1.equals(s2) : " + (s1.equals(s2)));// true 值相等
		
		// ------------------------------------------------------over

		String s3 = new String("abc");// 如果原来的字符串池中没有"abc"这个对象则创建两个对象，一个存放在字符串池中，一个存放在堆中；
		String s4 = new String("abc");// 由于上一步创建了两个对象，原来的字符串池中存在"abc"对象，此时只创建了一个对象，存放在堆中，对象的引用s4存放在栈中
		System.out.println("s3 == s4 : " + (s3 == s4));// false s3和s4在栈中的地址不同，指向堆中的不同对象
		System.out.println("s3.equals(s4) : " + (s3.equals(s4)));// true s3和s4的值相同
		System.out.println("s1 == s3 : " + (s1 == s3));// false 存放的地区不同，一个栈区，一个堆区
		System.out.println("s1.equals(s3) : " + (s1.equals(s3)));// true 值相同
		
		// ------------------------------------------------------over
		
		/**
		 * 由于常量的值在编译的时候就被确定(优化)了。 在这里，"ab"和"cd"都是常量，因此变量str1的值在编译时就可以确定。
		 * 这行代码编译后的效果等同于： String str1 = "abcd";
		 */
		String str1 = "ab" + "cd"; // 1个对象
		String str11 = "abcd";
		System.out.println("str1 == str11 : " + (str1 == str11));// true
		
		// ------------------------------------------------------over
		
		/**
		 * 局部变量str2,str3存储的是存储两个字符串对象(intern字符串对象)的地址。
		 * 
		 * 第三行代码原理：str2+str3会首先在堆中创建一个StringBuilder类的对象，同时用str2指向的字符串对象完成初始化，
		 * 然后调用append方法完成对str3所指向的字符串的合并，接着调用StringBuilder的toString()方法在堆中创建一个
		 * 新的String对象，最后将刚生成的 String对象的堆地址存放在局部变量str4中。
		 * 
		 * 而str5存储的是字符串池中"abcd"所对应的字符串对象的地址。str4与str5地址当然不一样了。
		 * 
		 * 内存中实际上有五个字符串对象：字符串池中的三个字符串对象(str2,str3,str5)、堆中的String对象(s4)和StringBuilder对象。
		 * 
		 */
		String str2 = "ab"; // 1个对象str2
		String str3 = "cd"; // 1个对象str3
		String str4 = str2 + str3;
		String str5 = "abcd";
		System.out.println("str4 == str5 : " + (str4 == str5));// false
		
		// ------------------------------------------------------over
		
		/**
		 * JAVA编译器对string + 基本类型/常量 是当成常量表达式直接求值来优化的。
		 * 运行期的两个string相加，会产生新的对象的，存储在堆(heap)中
		 */
		String str6 = "b";
		String str7 = "a" + str6;// str6为变量，在运行期才会被解析。
		String str67 = "ab";
		System.out.println("str7 = str67 : " + (str7 == str67));// false
		final String str8 = "b";
		String str9 = "a" + str8;// str8为常量变量，编译期会被优化
		String str89 = "ab";
		System.out.println("str9 == str89 : " + (str9 == str89));//true
		
		// ------------------------------------------------------over
	}
}
