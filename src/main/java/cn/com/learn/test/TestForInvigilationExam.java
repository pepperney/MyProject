package cn.com.learn.test;

import java.util.ArrayList;
import java.util.List;

public class TestForInvigilationExam {

	
	public static void main(String[] args) {
		int teacherNum = 27;
		int examCount = 100;
		int defaultCount = examCount / teacherNum;

		List<Integer> invigiCountArray = new ArrayList<>();

		for (int i = 0; i < teacherNum; i++) {
			invigiCountArray.add(defaultCount);
			examCount -= defaultCount;
		}
		System.out.println(examCount);
		for (int i = 0; i < teacherNum; i++) {
			if (examCount > 0) {
				invigiCountArray.set(i, invigiCountArray.get(i) + 1);
				examCount--;
			}
		}
		System.out.println(invigiCountArray);
		Integer sum = 0;
		sum = invigiCountArray.stream().mapToInt(i -> i).sum();

		System.out.println(sum);
	}
}
