package cn.com.learn.dataStructure.sortMethod;

import cn.com.learn.dataStructure.heap.Heap;
import cn.com.learn.dataStructure.heap.Heap.Node;

public class HeapSort extends MethodSort {

	public HeapSort(int[] a) {
		super(a);
	}

	@Override
	public void sortArray() {
		
		Heap heap = new Heap(a.length);
		for (int i = 0; i < a.length; i++) {
			heap.insert(new Node(a[i]));
		}	
		for (int i= a.length - 1; i >= 0; i--) {
			Node node = heap.remove();
			a[i] = node.getIndex();
		}
	}

	public static void main(String[] args) {

		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i] = (int) (Math.random() * 99);
		}

		MethodSort sm = new HeapSort(a);
		sm.showArray();
		sm.sortArray();
		sm.showArray();
	}
}
