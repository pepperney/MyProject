package cn.com.learn.dataStructure.sortMethod;

import cn.com.learn.dataStructure.linkList.Node;
import cn.com.learn.dataStructure.linkList.SortLinkList;

/**
 * 表插入排序：假设有一个无序数组，将其中的数据逐个插入到有序链表，那么数据就会有序， 把他们从有序链表移除放入到数组，那么数组就变得有序。
 * 排序效率高于插入排序
 * 
 * @author pepper
 * 
 */
public class ListInsertSort extends MethodSort {

	public ListInsertSort(int[] a) {
		super(a);
	}

	@Override
	public void sortArray() {
		SortLinkList list = new SortLinkList();
		for (int i = 0; i < a.length; i++) {
			list.insertHead(new Node(a[i]));
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = list.removeHead().index;
		}

	}

	public static void main(String[] args) {

		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i] = (int) (Math.random() * 99);
		}

		MethodSort sm = new ListInsertSort(a);
		sm.showArray();
		sm.sortArray();
		sm.showArray();
	}

}
