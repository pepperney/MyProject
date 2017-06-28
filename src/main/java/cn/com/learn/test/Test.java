package cn.com.learn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) throws Exception {
		Map<String, String> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("3");
		int i = 0;
		for (String str : list) {
			boolean exist = false;
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (str.equals(entry.getKey())) {
					exist = true;
					break;
				}
			}
			if (exist == false) {
				map.put(str, "title" + ++i);
			}

		}

		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

	}

}
