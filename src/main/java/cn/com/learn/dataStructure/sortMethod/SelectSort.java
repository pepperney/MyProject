package cn.com.learn.dataStructure.sortMethod;


/**
 * 选择排序：
 * 第一次扫描数组，找出数组中最小的元素，与数组的第0个元素交换位置，现在a[0]是最小的元素，
 * 下一趟扫描除了a[0]的整个数组，找出数组中最小的元素，与数组的第1个元素交换位置，现在a[1]是最小的元素
 * @author pepper
 *
 */
public class SelectSort extends MethodSort {

	public SelectSort(int[] a) {
		super(a);
	}

	@Override
	public void sortArray() {

		for (int i = 0; i < a.length; i++) {
			int min = i;
			int temp = a[i];
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < temp) {
					temp = a[j];
					min = j;
				}
			}
			//交换最小值a[j]和剩余未排序数组的第一个元素a[i]
			a[min] = a[i];
			a[i] = temp;
		}
	}
	
	public static void main(String args[]) {

		int a[] = { 4, 3, 6, 9, 7, 1, 2, 5, 8, 0 };
		MethodSort sm = new SelectSort(a);
		sm.showArray();
		sm.sortArray();
		sm.showArray();

	}

}
