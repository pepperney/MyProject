package cn.com.learn.dataStructure.hashTable;

/**
 * 开放地址法实现的哈希表--通过在哈希表中再寻找一个空位解决冲突问题
 * 哈希表在半满或接近2/3满的时候性能最好，当哈希表接近填满的时候，性能显著下降
 * 线性探测会出现聚集问题，为了解决聚集和冲突，可以使用二次探测（会产生更细的聚集问题）或者再哈希化
 * @author pepper
 *
 */
public class HashTable1 {
	
	public static class Item {

		private int data;

		public Item(int data) {
			this.data = data;
		}

		public int getKey() {
			return data;
		}
	}

	private Item[] hashArray;

	private int size;

	private Item NullItem;

	public HashTable1(int size) {
		this.size = size;
		this.hashArray = new Item[size];
		NullItem = new Item(-1);
	}

	public void display() {
		System.out.print("HashTable:\t");
		for (int i = 0; i < size; i++) {
			if (hashArray[i] != null) {
				System.out.print(hashArray[i].getKey() + "\t");
			}
			else {
				System.out.print("*\t");
			}
		}
		System.out.println("");
	}

	/**
	 * 哈希函数--线性探测法时使用
	 * @param key
	 * @return
	 */
	public int hashFunction1(int key) {
		return key % size;
	}
    /**
     * 哈希函数--再哈希法时使用
     * 
     * 必须具备如下特点：
     * （1）与哈希函数1不同，
     * （2）不能输出0（否则将没有步长，每次探测都是原步长，算法陷入死循环）数组长度必须使用素数
     * 经验表明下面的哈希函数很好：stepSize = constant - (key%constant)
     * 其中的constant是质数，且小于数组容量
     * @param key
     * @return
     */
	public int hashFunction2(int key){
		return 5- key%5;
	}
	public Item find(int key) {
		int value = this.hashFunction1(key);
		while (hashArray[value] != null) {
			if (hashArray[value].getKey() == key) {
				return hashArray[value];
			}
			++value;
			value %= size;
		}
		return null;
	}
	/**
	 * 再哈希法使用,注意这里的non参数程序不会使用，仅仅为了重载find方法
	 * @param key
	 * @return
	 */
	public Item find(int key,Item non) {
		int value = this.hashFunction1(key);
		int stepSize = this.hashFunction2(key);
		while (hashArray[value] != null) {
			if (hashArray[value].getKey() == key) {
				return hashArray[value];
			}
			value += stepSize;
			value %= size;
		}
		return null;
	}

	public void insert(Item item) {
		int key = item.getKey();
		int value = this.hashFunction1(key);
		while (hashArray[value] != null && hashArray[value].getKey() != -1) {
			++value;
			value %= size;
		}
		hashArray[value] = item;
	}
	
	/**
	 * 再哈希法使用
	 * @param key
	 * @param item
	 */
	public void insert(int key,Item item) {
		
		int value = this.hashFunction1(key);
		int stepSize = this.hashFunction2(key);
		while (hashArray[value] != null && hashArray[value].getKey() != -1) {
			value += stepSize;
			value %= size;
		}
		hashArray[value] = item;
	}

	public Item delete(int key) {
		int value = this.hashFunction1(key);
		while (hashArray[value] != null) {
			if (hashArray[value].getKey() == key) {
				Item temp = hashArray[value];
				hashArray[value] = NullItem;
				return temp;
			}
			++value;
			value %= size;
		}
		return null;
	}
	
	/**
	 * 再哈希法使用，注意这里的non参数程序不会使用，仅仅为了重载delete方法
	 * @param key
	 * @param non
	 */
	public  Item delete(int key,Item non){
		int value = this.hashFunction1(key);
		int stepSize = this.hashFunction2(key);
		while (hashArray[value] != null) {
			if (hashArray[value].getKey() == key) {
				Item temp = hashArray[value];
				hashArray[value] = NullItem;
				return temp;
			}
			value += stepSize;
			value %= size;
		}
		return null;
	}
	
	
	
	
public static void main(String[] args) {
		
		HashTable1 hash = new HashTable1(11);

//		for(int i=0;i<7;i++){
//			int key = (int)( Math.random()*11);
//			hash.insert(new Item(key));		
//		}
//		
//		hash.display();
//		
//		hash.insert(new Item(7));
//		hash.display();
//		
//		System.out.println(hash.find(7).getKey());
//		
//		hash.delete(7);
//		hash.display();
		
		for(int i=0;i<7;i++){
			int key = (int)( Math.random()*2*11);
			hash.insert(key,new Item(key));		
		}
		
		hash.display();
		
		hash.insert(7,new Item(7));
		hash.display();
		
		System.out.println(hash.find(7,null).getKey());
		
		hash.delete(7,null);
		hash.display();
	}
}
