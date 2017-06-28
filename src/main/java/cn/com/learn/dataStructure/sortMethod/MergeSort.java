package cn.com.learn.dataStructure.sortMethod;

/**
 * 归并排序
 * 
 * @author pepper
 * 
 */
public class MergeSort extends MethodSort {

	public MergeSort(int[] a) {
		super(a);
	}

	@Override
	public void sortArray() {
		this.mergeSort(0, a.length - 1);
	}

	/**
	 * 递归调用自身排序
	 * @param left
	 * @param right
	 */
	public void mergeSort(int left, int right) {
		// 如果只有一个元素，不需要排序
		if (left == right) {
			return;
		}
		else {
			int middle = (left + right) / 2;// 查找到中间元素
			mergeSort(left, middle);// 对左区间排序
			mergeSort(middle + 1, right);// 对右区间排序
			merge(a, left, middle, right);// 将两个区间的有序数组归并到一起
		}
	}

	/**
	 * 将排好序的两个有序数组归并到一个数组
	 * @param a
	 * @param left
	 * @param middle
	 * @param right
	 */
	public void merge(int[] a, int left, int middle, int right) {

		int[] tempArray = new int[a.length];
		int index = left;// index是tempArray的索引
		int temp1 = left;// temp1记录左区间起始位置
		int temp2 = middle + 1;// temp2记录右区间起始位置

		while (left <= middle && temp2 <= right) {
			// 从两个数组中取出最小的放入中间数组
			if (a[left] <= a[temp2]) {
				tempArray[index++] = a[left++];
			}
			else {
				tempArray[index++] = a[temp2++];
			}
		}
		// 剩余部分依次放入中间数组
		while (temp2 <= right) {
			tempArray[index++] = a[temp2++];
		}
		while (left <= middle) {
			tempArray[index++] = a[left++];
		}
		// 将中间数组中的内容复制回原数组
		while (temp1 <= right) {
			a[temp1] = tempArray[temp1++];
		}
	}

	public static void main(String[] args) {

		int a[] = { 4, 3, 6, 9, 7, 1, 2, 5, 8, 0 };
		MethodSort sm = new MergeSort(a);
		sm.showArray();
		sm.sortArray();
		sm.showArray();
	}
}
