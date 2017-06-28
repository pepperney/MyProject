package cn.com.learn.dataStructure.sortMethod;

/**
 * 快速排序--效率最高的排序方法
 * 
 * @author pepper
 * 
 */
public class QuickSort extends MethodSort {

	public QuickSort(int[] a) {
		super(a);
	}

	public void sortArray() {
		this.quickSort3(0, a.length - 1);
	}

/*====================================  第一种实现   =============================================*/
	public void quickSort1(int left, int right) {
		if (right - left <= 0) {
			return;
		}
		else {
			int pivot = a[right];
			int pivotIndex = partArray1(left, right, pivot);
			quickSort1(left, pivotIndex - 1);
			quickSort1(pivotIndex + 1, right);
		}
	}

	/**
	 * 划分数组，以中轴划分，小于中轴的数在左边，大于中轴的数在右边
	 * 
	 * @param a
	 * @param left
	 * @param right
	 * @param pivot
	 * @return
	 */
	private int partArray1(int left, int right, int pivot) {
		// leftPointer,rightPointer分别指向数组的两头
		int leftPointer = left - 1;
		int rightPointer = right;
		while (true) {
			// 当左指针遇到比枢纽pivot小的数据项时，它继续右移，遇到比中轴大的数据项时就停下来
			while (a[++leftPointer] < pivot)
				;
			// 当右指针遇到比中轴pivot大的数据项时，它继续左移，遇到比中轴小的数据项时就停下来
			while (rightPointer > 0 && a[--rightPointer] > pivot)
				;
			// 当两个指针相遇时，退出外层循环，即已经将数组划分完成
			if (leftPointer >= rightPointer) {
				break;
			}
			// 第一个循环在发现比中轴大的数据项时停下来，第二个循环在发现比中轴小的数据项时就停下来，这时候交换这两个数据项
			else {
				int temp = a[leftPointer];
				a[leftPointer] = a[rightPointer];
				a[rightPointer] = temp;
			}
		}
		int temp = a[leftPointer];
		a[leftPointer] = a[right];
		a[right] = temp;
		return leftPointer;
	}

/* ================================== 第二种实现  ==================================================*/
	public void quickSort2(int left, int right) {
		int size = right - left + 1;
		if (size <= 3) {
			manualSort(left, right);
		}
		else {
			int pivot = medianOf3(left, right);
			int pivotIndex = partArray2(left, right, pivot);
			quickSort2(left, pivotIndex - 1);
			quickSort2(pivotIndex + 1, right);
		}
	}

	private int medianOf3(int left, int right) {
		int median = (left + right) / 2;
		if (a[left] > a[median]) {
			swap(left, median);
		}
		if (a[left] > a[right]) {
			swap(left, right);
		}
		if (a[median] > a[right]) {
			swap(median, right);
		}
		swap(median, right - 1);
		return a[right - 1];
	}

	private int partArray2(int left, int right, int pivot) {
		int leftPointer = left;
		int rightPointer = right - 1;
		while (true) {
			while (a[++leftPointer] < pivot)
				;
			while (a[--rightPointer] > pivot)
				;
			if (leftPointer >= rightPointer) {
				break;
			}
			else {
				swap(leftPointer, rightPointer);
			}
		}
		swap(leftPointer, right - 1);
		return leftPointer;
	}

	public void manualSort(int left, int right) {
		int size = right - left + 1;
		if (size <= 1) {
			return;
		}
		if (size == 2) {
			if (a[left] > a[right]) {
				swap(left, right);
			}
			return;
		}
		else {
			if (a[left] > a[right - 1]) {
				swap(left, right - 1);
			}
			if (a[left] > a[right]) {
				swap(left, right);
			}
			if (a[right - 1] > a[right]) {
				swap(right - 1, right);
			}
		}
	}

	public void swap(int arg1, int arg2) {
		int temp = a[arg1];
		a[arg1] = a[arg2];
		a[arg2] = temp;
	}
	
/* ================================== 第三种实现  ==================================================*/
	
	public void quickSort3(int left, int right) {
		int size = right - left + 1;
		if (size <= 10) {
			insertSort(left, right);
		}
		else {
			int pivot = medianOf3(left, right);
			int pivotIndex = partArray2(left, right, pivot);
			quickSort3(left, pivotIndex - 1);
			quickSort3(pivotIndex + 1, right);
		}
	}

	public void insertSort(int left, int right) {
		int i, j;
		for (i = left + 1; i <= right; i++) {
			int temp = a[i];
			j = i;
			while (j > left && a[j - 1] >= temp) {
				a[j] = a[j - 1];
				--j;
			}
			a[j] = temp;
		}
	}
	
	
/* ================================== 测     试   ==================================================*/
	
	public static void main(String[] args) {
		int a[] = { 4, 3, 6, 9, 7, 1, 2, 5, 8, 0 };
		QuickSort sm = new QuickSort(a);
		sm.showArray();
		sm.sortArray();
		sm.showArray();

	}
}
