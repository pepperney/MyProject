package cn.com.learn.dataStructure.hashTable;

import cn.com.learn.dataStructure.hashTable.SortedLink.Node;

/**
 * 链地址法实现的哈希表--在哈希表每个单元中设置链表
 * 链地址法存储的数据比数组容量大，因为数组中的每一个单元都是一个链表
 * @author pepper
 *
 */
public class HashTable2 {

	private SortedLink hashArray[];
	private int size;

	public HashTable2(int size) {
		this.size = size;
		hashArray = new SortedLink[size];
		for (int i = 0; i < size; i++) {
			hashArray[i] = new SortedLink();
		}
	}

	public void display() {
		for (int i = 0; i < size; i++) {
			System.out.print(i + ". ");
			hashArray[i].displayList();
		}
	}

	public int hashFunction(int key) {
		return key %= size;
	}

	public void insert(Node node) {
		int key = node.getKey();
		int value = this.hashFunction(key);
		hashArray[value].insert(node);
	}

	public void delete(int key) {
		int value = this.hashFunction(key);
		hashArray[value].delete(key);
	}

	public Node find(int key) {
		int value = this.hashFunction(key);
		return hashArray[value].find(key);
	}
	
	
	
	public static void main(String[] args) {
		HashTable2 hash = new HashTable2(10);

		for (int i = 0; i < 21; i++) {
			int key = (int) (Math.random() * 100);
			hash.insert(new Node(key));
		}

		hash.display();
		System.out.println("=====================================================");

		hash.insert(new Node(7));
		hash.display();
		System.out.println("=====================================================");


		System.out.println("<<<<<<<" + hash.find(7).getKey() + ">>>>>>>\n");

		hash.delete(7);
		hash.display();
		
	}
}
