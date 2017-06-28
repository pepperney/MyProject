package cn.com.learn.dataStructure.linkList;
public class Iterator {

	private Node current;	
	private Node previous;
	private HeadLinkList list;

	public Iterator(HeadLinkList list) {
		this.list = list;
	}

	public void reset() {
		current = list.getHead();
		previous = null;
	}

	public boolean hasNext() {
		return (current.next != null);
	}

	public void nextNode() {
		previous = current;
		current = current.next;
	}

	public Node getCurrent() {
		return current;
	}

	public void insertAfter(Node node) {
		if (list.isEmpty()) {
			list.setHead(node);
			current = node;
		} else {
			node.next = current.next;
			current.next = node;
			this.nextNode();
		}
	}

	public void insertBefore(Node node) {
		if (previous == null) {
			node.next = list.getHead();
			list.setHead(node);
			reset();
		} else {
			node.next = previous.next;
			previous.next = node;
			current = node;
		}
	}

	public Node deleteCurrent() {
		Node temp = current;
		if (previous == null) {
			list.setHead(current.next);
			reset();
		} else {
			previous.next = current.next;
			if (!hasNext()) {
				reset();
			} else {
				current = current.next;
			}
		}
		return temp;
	}
	
	
	
	public static void main(String[] args) {

		HeadLinkList list = new HeadLinkList();
		Iterator it = list.getIterator();

		it.insertAfter(new Node(1));
		it.insertAfter(new Node(2));
		it.insertAfter(new Node(6));
		it.insertBefore(new Node(5));

		list.display();

		it.reset();
		System.out.println(it.getCurrent().index + "\n");

		it.nextNode();
		System.out.println(it.getCurrent().index + "\n");

		it.insertAfter(new Node(3));
		list.display();

		it.nextNode();
		it.insertBefore(new Node(4));
		list.display();

	}
}
