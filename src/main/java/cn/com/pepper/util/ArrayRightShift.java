package cn.com.pepper.util;

public class ArrayRightShift {


	

	/**
	 * 数组循环右移n位  
	 * n=1:12345->51234
	 * n=1:12345->45123 
	 * @Description
	 * @author niepei
	 * @param a
	 * @param n
	 * @return
	 */
	public static int[] arrayShift(int[] a, int n) {
		if (n <= 0 || n >= a.length) {
			return a;
		}
		int[] b = new int[a.length];
		for (int i = 0; i < a.length - n; i++) {
			b[i + n] = a[i];
		}
		for (int i = 0; i < n; i++) {
			b[i] = a[a.length - n + i];
		}
		return b;
	}
	
	
	public static String[] arrayShift(String[] a, int n) {
		if (n <= 0 || n >= a.length) {
			return a;
		}
		String[] b = new String[a.length];
		for (int i = 0; i < a.length - n; i++) {
			b[i + n] = a[i];
		}
		for (int i = 0; i < n; i++) {
			b[i] = a[a.length - n + i];
		}
		return b;
	}
	
	
	
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6, 7 };
//		String[] a = { "a", "b", "c", "d", "e", "f", "g" };
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		int n = 4;
		System.out.println("\n 右 移 " + n + " 位");
		a = arrayShift(a, n);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

}
