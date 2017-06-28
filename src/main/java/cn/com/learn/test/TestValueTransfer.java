package cn.com.learn.test;

/**
 * 验证java的值传递
 * @Description 
 * @author niepei
 * @date 2017年2月8日 上午9:31:09 
 * @version V1.3.1
 */
public class TestValueTransfer {

	public static void test1(boolean test) {
		test = !test;
		System.out.println("2:" + test);
	}
	
	
	public static void test2(StringBuffer sb){
		sb.append(",world");
		System.out.println(sb);
	}
	
	
	public static void test3(String str){
		str = "world";
		System.out.println(str);
	}
	
	public static void test4(char[] chs){
		chs[0]='C';
		System.out.println(chs);
	}

	public static void main(String[] args) {
		System.out.println("--------------------> 基本类型");
		boolean test = true;
		System.out.println("1:" + test);//true
		test1(test);//false
		System.out.println("3:" + test);//true
		System.out.println("--------------------> 基本类型"+"\n");
		
		System.out.println("--------------------> 对象类型1");
		StringBuffer sb = new StringBuffer("hello");
		System.out.println(sb);
		test2(sb);
		System.out.println(sb);
		System.out.println("--------------------> 对象类型1"+"\n");
		
		System.out.println("--------------------> 对象类型2");
		String str = "hello";
		System.out.println(str);
		test3(str);
		System.out.println(str);
		System.out.println("--------------------> 对象类型2"+"\n");
		
		
		System.out.println("--------------------> 数组类型2");
		char[] chs = {'H','E','L','L','O'};//test参数源
		System.out.println(chs);
		test4(chs);
		System.out.println(chs);
		System.out.println("--------------------> 对数组类型2"+"\n");
	}
}
