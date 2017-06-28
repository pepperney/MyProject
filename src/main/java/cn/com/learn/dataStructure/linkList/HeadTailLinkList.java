package cn.com.learn.dataStructure.linkList;

/**
 * 单向双端链表-------注意区分双端链表和双向链表
 * 
 * @author pepper
 *
 */
public class HeadTailLinkList {

	private Node head;
	private Node tail;

	public HeadTailLinkList() {
		head = null;
		tail = null;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public void insertHead(Node node) {
		if (isEmpty()) {
			tail = node;
		}
		node.next = head;
		head = node;
	}

	public void insertTail(Node node) {
		if (isEmpty()) {
			head = node;
		} else {
			tail.next = node; // old tail --> node
		}
		tail = node; // node <-- tail
	}

	public Node deleteHead() {
		Node temp = head;
		if (head.next == null) {
			tail = null;
		}
		head = head.next;
		return temp;
	}

	public void displayList() {
		System.out.print("[" + "\t");
		Node current = head;
		while (current != null) {
			current.displayNode();
			current = current.next;
		}
		System.out.print("]" + "\n" + "\n");
	}

	public static void main(String[] args) {
		HeadTailLinkList list = new HeadTailLinkList();
		list.insertHead(new Node(1));
		list.displayList();
		list.head.displayNode();
		list.tail.displayNode();
	}
}
