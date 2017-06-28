package cn.com.learn.dataStructure.recurse;

//二分查找法 
public class DichotomySearch {

	/**
	 * 循环的查找方法
	 * @param item
	 * @param a
	 * @return
	 */
	public int find(int item, int a[]) {

		int currentIndex;
		int leftBound = 0;
		int rightBound = a.length - 1;

		while (true) {
			System.out.println("find " + item + " in [" + leftBound + "," + rightBound + "]");
			currentIndex = (leftBound + rightBound) / 2;
			System.out.println("当前的中间值为：" + currentIndex);
			if (a[currentIndex] == item) {
				return currentIndex;
			} else if (leftBound > rightBound) {
				return a.length;
			} else {
				if (a[currentIndex] > item) {
					rightBound = currentIndex;
				} else {
					leftBound = currentIndex;
				}
			}
		}
	}

	/**
	 * 递归的查找方法
	 * @param item
	 * @param a
	 * @param leftBound
	 * @param rightBound
	 * @return
	 */
	public int find(int item, int a[], int leftBound, int rightBound) {

		System.out.println("find " + item + " in [" + leftBound + "," + rightBound + "]");
		int currentIndex = (leftBound + rightBound) / 2;
		System.out.println("当前的中间值为：" + currentIndex);
		if (a[currentIndex] == item) {
			return currentIndex;
		} else if (leftBound > rightBound) {
			return a.length;
		} else {
			if (a[currentIndex] > item) {
				return find(item, a, leftBound, currentIndex);
			} else {
				return find(item, a, currentIndex, rightBound);
			}
		}
	}

	public static void main(String[] args) {

		DichotomySearch ds = new DichotomySearch();
		int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int searchKey = 6;
		int index;

		index = ds.find(searchKey, a);
		System.out.println("要查找的元素的下标是：" + index);
		System.out.println("a[" + index + "] = 要查找的元素 ： " + searchKey);

		System.out.println("--------------------------------");

		index = ds.find(searchKey, a, 0, a.length - 1);
		System.out.println("要查找的元素的下标是：" + index);
		System.out.println("a[" + index + "] = 要查找的元素 ： " + searchKey);
	}
}
