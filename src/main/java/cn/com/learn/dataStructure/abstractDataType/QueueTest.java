package cn.com.learn.dataStructure.abstractDataType;

import cn.com.learn.dataStructure.linkList.Node;

public class QueueTest {

	public static void main(String[] args) {
		
		Queue queue = new Queue();
		
		queue.insert(new Node(1));
		queue.insert(new Node(2));
		queue.insert(new Node(3));
		queue.insert(new Node(4));
		
		queue.display();
		
		queue.remove();
		
		queue.display();
	}
}
