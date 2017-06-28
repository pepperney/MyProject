package cn.com.learn.dataStructure.linkList;

/**
 * 双向双端链表 注： 双向：链表的节点元素拥有next和previous索引，分别指向该节点的前一个节点和后一个节点
 * 双端：链表中有head，tail两个节点，总是指向链表的第一个节点和最后一个节点
 * 
 * @author pepper
 * 
 */
public class DoublyLinkList {
	private Node head;
	private Node tail;

	public DoublyLinkList() {
		head = null;
		tail = null;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public void insertHead(Node node) {
		if (isEmpty()) {
			tail = node;
		} else {
			head.previous = node;
		}
		node.next = head;
		head = node;
	}

	public void insertTail(Node node) {
		if (isEmpty()) {
			head = node;
		} else {
			tail.next = node;
		}
		node.previous = tail;
		tail = node;
	}

	/**
	 * 在某个拥有特定值的节点后面插入一个新的节点 在index属性等于传入的index的节点后面插入传入的node元素
	 * 
	 * @param index
	 * @param node
	 * @return
	 */
	public boolean insertAfter(int index, Node node) {
		Node current = head;
		while (current.index != index) {
			current = current.next;
			if (current == null) {
				return false;
			}
		}
		// 如果新节点插入到表尾，它的next字段必须为空，tail必须指向新创建的这个节点
		if (current == tail) {
			node.next = null;
			tail = node;
		} else {
			node.next = current.next; // node --> old next
			current.next.previous = node; // node <-- old next
		}
		node.previous = current; // old current <-- node
		current.next = node; // old current --> node
		return true;
	}

	public Node deleteHead() {
		Node temp = head;
		if (head.next.previous == null) {
			head = head.next;
		} else {
			head.next.previous = null;
		}
		head = head.next;
		return temp;
	}

	public Node deleteTail() {
		Node temp = tail;
		if (tail.previous.next == null) {
			tail = tail.previous;
		} else {
			tail.previous.next = null;
		}
		tail = tail.previous;
		return temp;
	}

	public Node delete(Node node) {
		Node current = head;
		while (current.index != node.index) {
			current = current.next;
			if (current == null) {
				return null;
			}
		}
		// 假设被删除的节点既不是head也不是tail，则：
		// 被删除节点（current）的前一个节点（current.previous）的next字段指向被删除节点的后一个节点（current.next）；
		// 被删除节点（current）的后一个节点（current.next）的previous字段指向被删除节点的前一个节点（current.previous）；
		if (current == head) {
			head = current.next;
		} else {
			current.previous.next = current.next;
		}
		if (current == tail) {
			tail = current.previous;
		} else {
			current.next.previous = current.previous;
		}
		return current;
	}

	public void displayFromHeadToTail() {
		System.out.print("[" + "\t");
		Node current = head;
		while (current != null) {
			current.displayNode();
			current = current.next;
		}
		System.out.print("]" + "\n" + "\n");
	}

	public void displayFromTailToHead() {
		System.out.print("[" + "\t");
		Node current = tail;
		while (current != null) {
			current.displayNode();
			current = current.previous;
		}
		System.out.print("]" + "\n" + "\n");
	}

	/**
	 * 测试
	 * 
	 * @Description
	 * @author niepei
	 * @param args
	 */
	public static void main(String[] args) {
		DoublyLinkList list = new DoublyLinkList();

		list.insertHead(new Node(33));
		list.insertHead(new Node(22));
		list.insertHead(new Node(11));

		list.displayFromHeadToTail();

		list.insertTail(new Node(55));
		list.insertTail(new Node(66));
		list.insertTail(new Node(77));

		list.displayFromTailToHead();

		Node node = new Node(44);
		list.insertAfter(33, node);

		list.displayFromHeadToTail();

		list.deleteHead();
		list.deleteTail();
		list.delete(node);

		list.displayFromHeadToTail();
	}
}
