package cn.com.learn.dataStructure.stackAndQueue;

/**
 * 使用数组实现栈
 * @author pepper
 *
 */
public class Stack {

	private int maxSize;
	private int index;//栈顶元素的下标
	private long[] stackArray;

	public Stack(int maxSize) {
		this.maxSize = maxSize;
		stackArray = new long[maxSize];
		index = -1;
	}
    /**
     * 入栈：先将顶部元素索引加1，再填充数据
     * @param item
     */
	public void push(long item) {
		stackArray[++index] = item;
	}
    /**
     * 出栈：先移除数据，再将顶部元素索引减1
     * @return
     */
	public long pop() {
		return stackArray[index--];
	}
    /**
     * 查看顶部元素
     * @return
     */
	public long peek() {
		return stackArray[index];
	}

	public boolean isEmpty() {
		return (index == -1);
	}

	public boolean isFull() {
		return (index == maxSize - 1);
	}
	
	
	public static void main(String[] args) {
		Stack stack = new Stack(10);
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		//可以看到先入后出的结果
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}
}
