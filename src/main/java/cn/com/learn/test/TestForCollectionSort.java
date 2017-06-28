package cn.com.learn.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TestForCollectionSort {

	private static Random ran = new Random();

	public static List<User> function() {
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUserid(i);
			user.setUsername("user" + i);
			user.setResult1((float) ran.nextInt(100));
			user.setResult2((float) ran.nextInt(100));
			userList.add(user);
		}

		for (User user : userList) {
			System.out.print(user.getResult1() + "\t");
		}
		System.out.println();
		for (User user : userList) {
			System.out.print(user.getResult2() + "\t");
		}
		System.out.println();
		return userList;
	}

	public static void main(String[] args) {
		List<User> userList = function();
		Collections.sort(userList, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return new Float(o1.getResult1() - o2.getResult1()).intValue();
			}
		});

		for (User user : userList) {
			System.out.print(user.getResult1() + "(" + user.getResult2() + ")\t");
		}
		System.out.println();

		Collections.sort(userList, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return new Float(o1.getResult2() - o2.getResult2()).intValue();
			}
		});

		for (User user : userList) {
			System.out.print("(" + user.getResult1() + ")" + user.getResult1() + "\t");
		}
	}

}

class User {
	private Integer userid;
	private String username;
	private Float result1;
	private Float result2;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Float getResult1() {
		return result1;
	}

	public void setResult1(Float result1) {
		this.result1 = result1;
	}

	public Float getResult2() {
		return result2;
	}

	public void setResult2(Float result2) {
		this.result2 = result2;
	}
}
