package cn.com.learn.dataStructure.stackAndQueue;


/**
 * 使用数组实现循环队列
 * @author pepper
 *
 */
public class Queue {

	private int maxSize;
	private int headIndex;
	private int tailIndex;
	private int count;
	private long[] queueArray;

	public Queue(int maxSize) {
		this.maxSize = maxSize;
		queueArray = new long[maxSize];
		headIndex = 0;//队头元素的索引
		tailIndex = -1;//队尾元素的索引
		count = 0;//队列中的元素个数
	}

	/**
	 * 插入元素到队尾
	 * @param item
	 */
	public void insert(long item) {
		if (tailIndex == maxSize - 1) {
			tailIndex = -1;
		}
		queueArray[++tailIndex] = item;
		count++;
	}
    /**
     * 删除队头的元素
     * @return
     */
	public long remove() {
		long temp = queueArray[headIndex++];
		if (headIndex == maxSize) {
			headIndex = 0;
		}
		count--;
		return temp;
	}
	
	/**
	 * 查看队头元素
	 * @return
	 */
	public long peek(){
		return queueArray[headIndex];
	}
	
	public boolean isEmpty(){
		return (count == 0);
	}
	
	public boolean isFull(){
		return (count == maxSize);
	}
	
	public int size(){
		return count;
	}
	
	
	public static void main(String[] args) {
		Queue queue = new Queue(5);
		queue.insert(10);
		queue.insert(20);
		queue.insert(30);
		queue.insert(40);
		
		queue.remove();
		queue.remove();
		queue.remove();
		
		queue.insert(50);
		queue.insert(60);
		queue.insert(70);
		queue.insert(80);
		
		while(!queue.isEmpty()){
			System.out.println(queue.remove());
		}
	}
}
