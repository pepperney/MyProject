package cn.com.learn.dataStructure.stackAndQueue;


/**
 * 用数组实现优先级队列：这是一个升序优先级队列，数据项的值越小优先级越高；
 * 最小数据项总是在数组的最高端（下标最大），最大的数据项总是在下标值为0
 * 的位置上。存储情况如下：
 *   4   ---->   10 
 *   3   ---->   20 
 *   2   ---->   30 
 *   1   ---->   40 
 *   0   ---->   50
 * 
 * @author pepper
 *
 */
public class PriorityQueue {

	private int maxSize;
	private int count;
	private long[] priorityQueueArray;
	
	
	public PriorityQueue(int maxSize){
		this.maxSize = maxSize;
		priorityQueueArray = new long[maxSize];
		count = 0;
	}
	/**
	 * 插入：按升序插入到合适位置
	 * @param item
	 */
	public void insert(long item) {
		int i;
		if (count == 0) {
			priorityQueueArray[count++] = item;
		} else {
			for (i = count - 1; i >= 0; i--) {
				if (item > priorityQueueArray[i]) {
					priorityQueueArray[i + 1] = priorityQueueArray[i];
				} else {
					break;
				}
			}
			priorityQueueArray[i + 1] = item;
			count++;
		}
	}
	/**
	 * 删除队头的元素--优先级最高(下标最大，数据项最小)的元素
	 * @return
	 */
	public long remove(){
		return priorityQueueArray[--count];
	}
	/**
	 * 查看队头的元素--优先级最高(下标最大，数据项最小)的元素
	 * @return
	 */
	public long peek(){
		return priorityQueueArray[count-1];
	}
	
	public boolean isEmpty(){
		return (count == 0);
	}
	
	public boolean isFull(){
		return (count == maxSize);
	}
	
	
	
	public static void main(String[] args) {
		PriorityQueue priorityQueue = new PriorityQueue(5);
		priorityQueue.insert(30);
		priorityQueue.insert(10);
		priorityQueue.insert(40);
		priorityQueue.insert(50);
		priorityQueue.insert(20);
		
		while(!priorityQueue.isEmpty()){
			System.out.println(priorityQueue.remove());	
		}
	}
	
}
