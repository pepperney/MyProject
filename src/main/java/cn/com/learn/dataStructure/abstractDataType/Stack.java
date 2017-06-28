package cn.com.learn.dataStructure.abstractDataType;

import cn.com.learn.dataStructure.linkList.HeadLinkList;
import cn.com.learn.dataStructure.linkList.Node;

public class Stack {

	private HeadLinkList list;
	
	public Stack(){
		list = new HeadLinkList();
	}
	
	public void push(Node item){
		list.insertHead(item);
	}
	
	public Node pop(){
		return list.deleteHead();
	}
	
	public void display() {
		list.display();
	}
	
	public boolean isEmpty() {
		return (list.isEmpty());
	}
}
