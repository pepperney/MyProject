package cn.com.learn.dataStructure.hashTable;

public class SortedLink {

	public static class Node {

		private int index;

		public Node next;

		public Node(int index) {
			this.index = index;
		}

		public int getKey() {
			return index;
		}

		public void display() {
			System.out.print("{" + index + "}\t");
		}
	}

	private Node head;

	public SortedLink() {
		head = null;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public Node find(int key) {
		Node current = head;
		while (current != null && current.getKey() <= key) {
			if (current.getKey() == key) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public void insert(Node node) {
		int key = node.getKey();
		Node previous = null;
		Node current = head;
		while (current != null && key > current.getKey()) {
			previous = current;
			current = current.next;
		}
		if (previous == null) {
			head = node;
		} else {
			previous.next = node;
		}
		node.next = current;
	}

	public void delete(int key) {
		Node previous = null;
		Node current = head;
		while (current != null && key != current.getKey()) {
			previous = current;
			current = current.next;
		}
		if (previous == null) {
			head = head.next;
		} else {
			previous.next = current.next;
		}
	}

	public void displayList() {
		System.out.print("[" + "\t");
		Node current = head;
		while (current != null) {
			current.display();
			current = current.next;
		}
		System.out.print("]" + "\n");
	}
}
