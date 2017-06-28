package cn.com.learn.dataStructure.recurse;

/**
 * 使用递归计算n的阶乘
 * @author pepper
 *
 */
public class Factorial {

	public static int getFactorial(int n){
		if( n==0 ){
			return 1;
		}else{
			return n*getFactorial(n-1);
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(getFactorial(1));
		
		System.out.println(getFactorial(1));
		
		System.out.println(getFactorial(5));
	}
}
