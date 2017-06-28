package cn.com.learn.dataStructure.linkList;

/**
 * 实现单链表
 * 
 * @author pepper
 *
 */
public class HeadLinkList {
	/**
	 * 构造方法 Creates a new instance of HeadLinkList. Description
	 */
	public HeadLinkList() {
		head = null;
	}

	/**
	 * 头节点
	 */
	private Node head;

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	/**
	 * 获取迭代器
	 * @Description
	 * @author niepei
	 * @return
	 */
	public Iterator getIterator() {
		return new Iterator(this);
	}

	/**
	 * 判断链表是否为空
	 * @Description
	 * @author niepei
	 * @return
	 */
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * 在表头插入一个新的链接点
	 * 为了插入一个新的链接点，只需要新创建的链接点的next字段等于原来的head值，然后改变head的值，使他指向新创建的链接点
	 * @param intData
	 * @param doubleData
	 */
	public void insertHead(Node node) {
		node.next = head; // node --> 原来的head
		head = node; // head --> node
	}

	/**
	 * 查找链表中包含指定关键字的链接点
	 * @param key
	 * @return
	 */
	public Node find(int key) {
		Node current = head;
		while (current.index != key) {
			if (current.next == null) {
				return null;
			} else {
				current = current.next;
			}
		}
		return current;
	}

	/**
	 * 删除节点
	 * 这里删除的是第一个元素
	 * @return
	 */
	public Node deleteHead() {
		Node temp = head;
		head = head.next;
		return temp;
	}

	/**
	 * 删除节点
	 * 删除包含指定关键字的链接点
	 * @param key
	 * @return
	 */
	public Node delete(int key) {
		Node current = head;
		Node previous = head;
		while (current.index != key) {
			if (current.next == null) {
				return null;
			} else {
				previous = current;
				current = current.next;
			}
		}
		if (current == head) {
			head = head.next;
		} else {
			previous.next = current.next;
		}
		return current;
	}

	/**
	 * 打印链表
	 * @Description
	 * @author niepei
	 */
	public void display() {
		System.out.print("[" + "\t");
		Node current = head;
		while (current != null) {
			current.displayNode();
			current = current.next;
		}
		System.out.print("]" + "\n" + "\n");
	}
	
	
	
	/**
	 * 测试
	 * @Description 
	 * @author niepei
	 * @param args
	 */
	public static void main(String[] args) {

		HeadLinkList linkList = new HeadLinkList();
		linkList.insertHead(new Node(1));
		linkList.insertHead(new Node(2));
		linkList.insertHead(new Node(3));
		linkList.insertHead(new Node(4));
		linkList.insertHead(new Node(5));

		linkList.display();
		
		while(!linkList.isEmpty()){
			Node link = linkList.deleteHead();
			link.displayNode();
		}
		System.out.print("\n" + "\n");
		
		linkList.display();
		
		linkList.insertHead(new Node(6));
		linkList.insertHead(new Node(7));
		linkList.insertHead(new Node(8));
		linkList.insertHead(new Node(9));
		
		linkList.display();
		
		linkList.find(6).displayNode();
		System.out.print("\n" + "\n");
		
		linkList.delete(8).displayNode();
		System.out.print("\n" + "\n");
		
		linkList.display();
		
	}

}
