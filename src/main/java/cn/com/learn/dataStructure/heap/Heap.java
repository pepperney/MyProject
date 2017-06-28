package cn.com.learn.dataStructure.heap;

public class Heap {
	
	public static class Node {
		private int index;

		public Node(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}

	
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	public Heap(int size) {
		this.maxSize = size;
		heapArray = new Node[maxSize];
		this.currentSize = 0;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean insert(Node node) {
		if (currentSize == maxSize) {
			return false;
		}
		heapArray[currentSize] = node;
		trickleUp(currentSize++);
		return true;
	}
	
	public Node remove() {
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}

	public void trickleUp(int index) {
		int parentIndex = (index - 1) / 2;
		Node bottom = heapArray[index];
		while (index > 0 && heapArray[parentIndex].getIndex() < bottom.getIndex()) {
			heapArray[index] = heapArray[parentIndex];
			index = parentIndex;
			parentIndex = (parentIndex - 1) / 2;
		}
		heapArray[index] = bottom;
	}

	public void trickleDown(int index) {
		int largeChild;
		Node top = heapArray[index];
		while (index < currentSize / 2) {
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			if (rightChild < currentSize && heapArray[leftChild].getIndex() < heapArray[rightChild].getIndex()) {
				largeChild = rightChild;
			}
			else {
				largeChild = leftChild;
			}
			if(top.getIndex()>=heapArray[largeChild].getIndex()){
				break;
			}
			heapArray[index] = heapArray[largeChild];
			index = largeChild;
		}
		heapArray[index] = top;
	}

	public boolean change(int oldIndex,int newIndex){
		if(oldIndex<=0 || oldIndex>=currentSize){
			return false;
		}
		int oldValue = heapArray[oldIndex].getIndex();
		heapArray[oldValue].setIndex(newIndex);
		if(oldIndex<newIndex){
			trickleUp(oldIndex);
		}else{
			trickleDown(oldIndex);
		}
		return true;
	}
	
	public void display() {
		System.out.print("heapArray:[\t");
		for (int i = 0; i < currentSize; i++) {
			if (heapArray[i] != null) {
				System.out.print(heapArray[i].getIndex() + "\t");
			}
			else {
				System.out.println("*\t");
			}
		}
		System.out.print("]\n");

		int nBlanks = 32;
		int itemPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "-----------------------------------------------------------------------------------------";
		System.out.println(dots + dots);

		while (currentSize > 0) {
			if (column == 0) {
				for (int k = 0; k < nBlanks; k++) {
					System.out.print(" ");
				}
			}
			System.out.print(heapArray[j].getIndex());
			if (++j == currentSize) {
				break;
			}

			if (++column == itemPerRow) {
				nBlanks /= 2;
				itemPerRow *= 2;
				column = 0;
				System.out.println();
			}
			else {
				for (int k = 0; k < nBlanks * 2 - 2; k++) {
					System.out.print(" ");
				}
			}
		}
		System.out.println("\n" + dots + dots);
	}
	
	
	public static void main(String[] args) {

		Heap heap = new Heap(31);
	
		heap.insert(new Node(70));
		heap.insert(new Node(40));
		heap.insert(new Node(50));
		heap.insert(new Node(20));
		heap.insert(new Node(60));
		heap.insert(new Node(100));
		heap.insert(new Node(80));
		heap.insert(new Node(30));
		heap.insert(new Node(10));
		heap.insert(new Node(90));
		
		heap.display();
		
		Node node = new Node(77);
		heap.insert(node);
		heap.display();
		
		heap.remove();
		heap.display();

	}
	
}
