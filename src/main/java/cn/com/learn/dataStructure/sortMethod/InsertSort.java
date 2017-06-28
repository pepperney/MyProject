package cn.com.learn.dataStructure.sortMethod;


/**
 * 插入排序：假设数组中存在某个数flag，它前面的数是有序的，后面的数是无序的，现在将这个数flag插入到
 * 前面有序数组的合适位置，有序数组中大于flag的元素通通右移
 * 
 * @author pepper
 * 
 */
public class InsertSort extends MethodSort {

	public InsertSort(int[] a) {
		super(a);
	}

	@Override
	public void sortArray() {
		for (int i = 1; i < a.length; i++) {
			int j = i - 1;
			int temp = a[i];
			for (; j >= 0 && temp < a[j]; j--) {
				// 将大于temp的值整体后移一个单位
				a[j + 1] = a[j];
			}
			a[j + 1] = temp;
		}
	}

	public static void main(String args[]) {

		int a[] = { 4, 3, 6, 9, 7, 1, 2, 5, 8, 0 };
		MethodSort sm = new InsertSort(a);
		sm.showArray();
		sm.sortArray();
		sm.showArray();

	}
}
