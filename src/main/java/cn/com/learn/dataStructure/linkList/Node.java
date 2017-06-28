package cn.com.learn.dataStructure.linkList;


/**
 * 当用于实现单向链表时（HeadLinkList,HeadTailLinkList）只需要用到next元素；
 * 当用于实现双向链表是，则需要使用next，previous元素。
 * @author pepper  
 *
 */
public class Node {

	public int index;
	public Node next;
	public Node previous;

	public Node(int index) {
		this.index = index;
	}
	
	public void displayNode() {
		System.out.print( index + "\t");
	}
}
