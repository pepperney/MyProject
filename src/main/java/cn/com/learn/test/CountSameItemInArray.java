package cn.com.learn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用map计算list中相同元素的个数
 * @Description 
 * @author niepei
 * @date 2017年2月8日 上午9:34:51 
 * @version V1.3.1
 */
public class CountSameItemInArray {

	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		for(int i=0;i<5;i++){
			list.add("green");
		}
		for(int i=0;i<10;i++){
			list.add("blue");
		}
		
		Map<String,Integer> map = new HashMap<>();
		Integer count = 1;
		for(String str : list){
			if(!map.containsKey(str)){
				count = 1;
				map.put(str, count);
			}else{
				map.put(str, ++count);
			}
			
		}
		
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		
		
	}
	
}
