package cn.com.learn.dataStructure.linkList;

/**
 * 单向单端有序列表
 * @author pepper
 *
 */
public class SortLinkList {

	private Node head;

	public SortLinkList() {
		head = null;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public void insertHead(Node node) {
		Node previous = null;
		Node current = head;
		while (current != null && node.index > current.index) {
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

	public Node removeHead() {
		Node temp = head;
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
		System.out.print( "]" + "\n" + "\n");
	}
	
	/**
	 * 测试
	 * @Description 
	 * @author niepei
	 * @param args
	 */
	public static void main(String[] args) {
		
		SortLinkList list = new SortLinkList();
		
		list.insertHead(new Node(20));
		list.insertHead(new Node(40));
		list.insertHead(new Node(30));
		list.insertHead(new Node(50));
		
		list.displayList();
		
		list.removeHead();
		
		list.displayList();
	}
}
