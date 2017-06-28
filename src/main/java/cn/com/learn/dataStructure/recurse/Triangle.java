package cn.com.learn.dataStructure.recurse;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * 求三角数字的第n项：a[n] = a[n-1] + n;
 * 例如：1,3,6,10,15,21,27,34
 * @author pepper
 *
 */
public class Triangle {

	public static int number;
	public static void main(String[] args) throws Exception {
		System.out.println("please input a number:");
		number = getInt();
		int answer = triangle(number);
		System.out.println("the answer is : " + answer);
	}
	
	/**
	 * 使用递归求第n项
	 * @param n
	 * @return
	 */
	public static int triangle(int n){
		if(n==1){
			return 1;
		}else{
			return triangle(n-1) + n;
		}
	}
	/**
	 * 使用循环求第n项
	 * @param n
	 * @return
	 */
	public static int triangle(Integer n){
		int sum = 0;
		while(n>0){
			sum = sum + n;
			--n;
		}
		return sum;
	}
	
	public static String getString()throws Exception{
		InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(isr);
	    String str = br.readLine();
	    return str;
	} 
	
	public static int getInt()throws Exception{
		String str = getString();
		return Integer.parseInt(str);
	}
}
