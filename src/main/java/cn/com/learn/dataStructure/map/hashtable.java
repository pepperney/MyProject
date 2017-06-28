package cn.com.learn.dataStructure.map;

public class hashtable {

	Item[] hashArray;
	int arraySize;// 定义数组长度

	public hashtable(int size) {// 构造器，初始化
		arraySize = size;
		hashArray = new Item[arraySize];
	}

	// 哈希函数
	public int hash(int key) {
		return key % arraySize;
	}

	// 插入，这里假设是数组未满，即不能插入大于arraySize的数据数
	public void insert(Item item) {
		int key = item.getKey();
		int hashCode = hash(key);
		// 若已存在同样的数据，则向下进一位，直到找到空的位置
		// 为了简单，也可要求不准有重复数据
		while (hashArray[hashCode] != null) {
			++hashCode;
			hashCode %= arraySize;
		}
		hashArray[hashCode] = item;
	}

	// 删除
	public Item delete(int key) {
		int hashCode = hash(key);
		while (hashArray[hashCode] != null) {
			if (hashArray[hashCode].getKey() == key) {
				Item temp = hashArray[hashCode];
				hashArray[hashCode] = null;
				return temp;
			}
			++hashCode;
			hashCode %= arraySize;
		}
		return null;
	}

	// 查找
	public Item find(int key) {
		int hashCode = hash(key);
		while (hashArray[hashCode] != null) {
			if (hashArray[hashCode].getKey() == key)
				return hashArray[hashCode];
			++hashCode;
			hashCode %= arraySize;
		}
		return null;
	}
	
	
}

class Item {

	private int key;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	
	
}
