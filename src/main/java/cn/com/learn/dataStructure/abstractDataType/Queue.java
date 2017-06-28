package cn.com.learn.dataStructure.abstractDataType;

import cn.com.learn.dataStructure.linkList.HeadTailLinkList;
import cn.com.learn.dataStructure.linkList.Node;

public class Queue {

	
	private HeadTailLinkList list;
	
	public Queue(){
		list = new HeadTailLinkList();
	}
		
	public void insert(Node item){
		list.insertTail(item);
	}
	
	public Node remove(){
		return list.deleteHead();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public void display(){
		list.displayList();
	}
}
