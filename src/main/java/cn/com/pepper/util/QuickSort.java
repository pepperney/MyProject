package cn.com.pepper.util;

public class QuickSort {

	private static int[] array = { 1, 5, 3, 7, 4, 0, 9, 6, 2, 8 };
	
//	private static int[] array = { 2, 5, 4, 1, 3 };


	public static void showArray() {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
		System.out.println();
	}

	public static int partion(int left, int right, int pivot) {
		while (left < right) {
			while (array[left] < pivot && left < right) {
				left++;
			}
			while (right > 0 && array[right] > pivot) {
				right--;
			}
			int temp = array[left];
			array[left] = array[right];
			array[right] = temp;
			showArray();
		}
		System.out.println("---------------------------------");
		return left;
	}

	public static void sort(int left, int right) {
		if (right - left <= 0) {
			return;
		} else {
			int pivot = array[right];
			int pivotIndex = partion(left, right, pivot);
			sort(left, pivotIndex - 1);
			sort(pivotIndex + 1, right);
		}
	}

	public static void main(String[] args) {
		showArray();
		sort(0, array.length - 1);
		showArray();
	}

}






