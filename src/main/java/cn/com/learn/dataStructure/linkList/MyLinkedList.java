package cn.com.learn.dataStructure.linkList;

public class MyLinkedList<T> {

	private static class Node<T> {
		T item;
		Node<T> next;
		Node<T> prev;

		Node(Node<T> prev, T element, Node<T> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	private Node<T> first;
	private Node<T> last;
	private int size = 0;

	public MyLinkedList() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public int size() {
		return size;
	}

	/**
	 * 添加元素到链表末尾
	 * 
	 * @Description
	 * @author niepei
	 * @param element
	 */
	public void add(T element) {
		final Node<T> oldLast = last;
		final Node<T> node = new Node<T>(oldLast, element, null);
		last = node;
		if (oldLast == null) {
			first = node;
		} else {
			oldLast.next = node;
		}
		size++;
	}

	public T remove(T element) {
		Node<T> current = first;
		while (!element.equals(current.item)) {
			current = current.next;
			if (current == null) {
				return null;
			}
		}
		if (current.item.equals(first.item)) {
			first = current.next;
		} else {
			current.prev.next = current.next;
		}
		if (current == last) {
			last = current.prev;
		} else {
			current.next.prev = current.prev;
		}
		size--;
		return current.item;
	}

	public void display(MyLinkedList<Integer> list) {
		Node<Integer> current = list.first;
		while (current != null) {
			System.out.print(current.item + "\t");
			current = current.next;
		}
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println(list.size());
		list.display(list);

		list.remove(4);
		System.out.println("\n" + list.size());
		list.display(list);
	}

}
