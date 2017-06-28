package cn.com.learn.dataStructure.sortMethod;


/**
 * 冒泡排序
 * @author pepper
 *
 */
public class BubbleSort extends MethodSort{

	public BubbleSort(int[] a) {
		super(a);
	}



	@Override
	public void sortArray() {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		
	}
	
	

	public static void main(String args[]) {

		int a[] = { 4, 3, 6, 9, 7, 1, 2, 5, 8,0 };
		MethodSort sm = new BubbleSort(a);
		sm.showArray();
		sm.sortArray();
		sm.showArray();

	}

	
	
}
