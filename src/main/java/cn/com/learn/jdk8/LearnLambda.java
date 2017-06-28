package cn.com.learn.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class LearnLambda {

	private int ivalue = 0;

	private List<Parent> parentList;

	public int getIvalue() {
		return ivalue;
	}

	public void setIvalue(int ivalue) {
		this.ivalue = ivalue;
	}

	public List<Parent> getParentList() {
		return parentList;
	}

	public void setParentList(List<Parent> parentList) {
		this.parentList = parentList;
	}

	@Override
	public String toString() {
		return "ivalue=" + ivalue + ", parentList=" + parentList;
	}

	public void method1() {

		List<String> names = new ArrayList<>();
		names.add("aa");
		names.add("dd");
		names.add("bb");
		names.add("cc");

		System.out.println(names);

		// 使用lambda表达式对list排序
		Collections.sort(names, (o1, o2) -> o1.compareTo(o2));
		// 使用匿名内部类对list排序
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		System.out.println(names);
	}

	@Test
	public void method2() {

		List<String> names = new ArrayList<>();
		List<String> lowercaseNames = new ArrayList<>();
		names.add("TaoBao");
		names.add("ZhiFuBao");
		// 使用for循环
		for (String name : names) {
			lowercaseNames.add(name.toLowerCase());
		}
		// 使用lambda表达式
		lowercaseNames = names.stream().map((String name) -> {
			return name.toLowerCase();
		}).collect(Collectors.toList());
		lowercaseNames = names.stream().map(name -> {
			return name.toLowerCase();
		}).collect(Collectors.toList());
		System.out.println(lowercaseNames);

	}

	@Test
	public void method3() {

		List<LearnLambda> list = new ArrayList<>();

		LearnLambda t1 = new LearnLambda();
		t1.setIvalue(1);

		List<Parent> subList1 = new ArrayList<>();
		subList1.add(new Parent().setName("name11"));
		subList1.add(new Parent().setName("name12"));

		t1.setParentList(subList1);
		list.add(t1);

		LearnLambda t2 = new LearnLambda();
		t2.setIvalue(2);

		List<Parent> subList2 = new ArrayList<>();
		subList2.add(new Parent().setName("name21"));
		subList2.add(new Parent().setName("name22"));

		t2.setParentList(subList2);
		list.add(t2);

		System.out.println(list);
		System.out.println("--------------------------------------------------------");

		list.stream().filter(sub -> sub.getIvalue() == 1).forEach(sub -> sub.getParentList().stream()
				.filter(str -> str.getName().equals("name11")).forEach(str -> System.out.println(str.getName())));

	}

	@Test
	public void method5() {

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		list = list.stream().filter(obj -> obj < 5).collect(Collectors.toList());
		for (int i : list) {
			System.out.println(i);
		}
	}

}

class Parent {

	private String name;

	public String getName() {
		return name;
	}

	public Parent setName(String name) {
		this.name = name;
		return this;
	}

}
