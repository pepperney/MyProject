package cn.com.learn.dataStructure.abstractDataType;

import cn.com.learn.dataStructure.linkList.Node;

public class StackTest {
	
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push(new Node(1));
		stack.push(new Node(2));
		stack.push(new Node(3));
		stack.push(new Node(4));
		// 可以看到先入后出的结果
		while (!stack.isEmpty()) {
			System.out.println(stack.pop().index);
		}
	}
}
