package cn.com.learn.dataStructure.recurse;

/**
 * 回文字
 * @Description 
 * @author niepei
 * @date 2017年2月8日 下午2:21:44 
 * @version V1.3.1
 */
public class Anagram {

	private static int wordSize;
	private static int count;
	private static char[] charArray = new char[100];

	public static void doAnagram(int size) {
		if (size == 1) {
			return;
		}
		for (int i = 0; i < size; i++) {
			doAnagram(size - 1);
			if (size == 2) {
				displayWord();
			}
			rotate(size);
		}
	}

	public static void rotate(int size) {
		int i;
		int position = wordSize - size;
		char temp = charArray[position];
		for (i = position + 1; i < wordSize; i++) {
			charArray[i - 1] = charArray[i];
		}
		charArray[i - 1] = temp;
	}

	public static void displayWord() {
		if (count < 99) {
			System.out.println(" ");
		}
		if (count < 9) {
			System.out.println();
		}
		System.out.print(++count + ": ");
		for (int i = 0; i < wordSize; i++) {
			System.out.print(charArray[i]);
		}
		System.out.print(" ");
		System.out.flush();
		if (count % 6 == 0) {
			System.out.print(" ");
		}
	}
	
	public static void main(String[] args) {
		String str = "ABC";
		wordSize = str.length();
		count = 0;
		System.out.println(str.length());
		for(int j=0;j<wordSize;j++){
			charArray[j] = str.charAt(j);
		}
		doAnagram(wordSize);
	}
}
