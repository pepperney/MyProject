package cn.com.learn.dataStructure.map;


/**
 * 使用对象数组实现一个简单的map
 * @Description 
 * @author niepei
 * @date 2016年11月18日 下午2:51:13 
 * @version V1.3.1
 * @param <K>
 * @param <V>
 */
public class SimpleMap<K, V> {

	private Object[][] pairs;

	private int index;

	/**
	 * 构造方法：
	 * 创建一个指定长度的二维数组，行数为length（即map中可以放置的元素个数），
	 * 每行的长度为2（行的第一个元素是map的key,第二个元素是map的value）
	 * @param length
	 */
	public SimpleMap(int length) {
		pairs = new Object[length][2];
	}

	/**
	 * 向map中添加元素
	 * @Description
	 * @author niepei
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void put(K key, V value) throws Exception {
		if (index >= pairs.length) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		pairs[index++] = new Object[] { key, value };
	}

	/**
	 * 从map中获取元素
	 * @Description
	 * @author niepei
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public V get(K key) {
		for (int i = 0; i < index; i++) {
			if (key.equals(pairs[i][0])) {
				return (V) pairs[i][1];
			}
		}
		return null;
	}

	

	
	
	public static void main(String[] args) throws Exception {
		SimpleMap<String,String> map = new SimpleMap<String,String>(6);
		map.put(" number1 ", " RED ");
		map.put(" number2 ", " BLUE ");
		map.put(" number3 ", " WHITE ");
		map.put(" number4 ", " GREEN ");
		map.put(" number5 ", " BROWN ");
		map.put(" number6 ", " PURPLE ");
		for(int i =0;i<map.index;i++){
			System.out.println(map.pairs[i][0]+":"+map.pairs[i][1]);
		}
		
		
		
		
		
	}
}
