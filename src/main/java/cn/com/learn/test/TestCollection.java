package cn.com.learn.test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestCollection {

	static int a = 3;

	public static void testTransferPosition() {
		System.out.println(a >> 1);
	}

	public static void main(String[] args) {
		learnHashMap();
	}

	/**
	 * ArrayList
	 * @Description
	 * @author niepei
	 */
	public static void learnArrayList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		System.out.println(list.get(0));
		System.out.println(list.remove(0));
		System.out.println(list.get(0));
	}

	/**
	 * LinkedList
	 * @Description
	 * @author niepei
	 */
	public static void learnLinkedList() {
		List<Integer> list = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		System.out.println(list.get(0));
		System.out.println(list.remove(0));
		System.out.println(list.get(0));
	}

	/**
	 * Queue
	 * 用ArrayDeque实现队列功能
	 * @Description
	 * @author niepei
	 */
	public static void learnArryDequeUseForQueue() {
		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < 3; i++) {
			queue.addLast(i);
		}
		while (queue.isEmpty() == false) {
			System.out.println(queue.removeFirst());
		}
	}

	/**
	 * Stack
	 * 用ArrayDeque实现栈的功能
	 * @Description
	 * @author niepei
	 */
	public static void learnArryDequeUseForStack() {
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < 3; i++) {
			stack.addLast(i);
		}
		while (stack.isEmpty() == false) {
			System.out.println(stack.removeLast());
		}
	}

	/**
	 * map
	 * @Description 
	 * @author niepei
	 */
	public static void learnHashMap() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 1);
		map.put(2, 1);
		map.put(3, 3);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

}
