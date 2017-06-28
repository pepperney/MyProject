package cn.com.learn.dataStructure.sortMethod;

/**
 * 简单排序的速度：插入排序(InsertSort) > 选择排序(SelectSort) > 冒泡排序(BubbleSort)
 * 
 * @author pepper
 * 
 */
public abstract class MethodSort {

	protected int[] a;

	public MethodSort(int[] a) {
		this.a = a;
	}

	/**
	 * 排序方法--每种算法有不同的实现
	 */
	public void sortArray() {
	};

	/**
	 * 打印数组
	 */
	public void showArray() {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + "\t");
		}
		System.out.println("");

	}

}
