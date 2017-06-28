package cn.com.learn.dataStructure.recurse;

/**
 * 
 * 汉诺（Hanoi）塔问题：古代有一个梵塔，塔内有三个座A、B、C，A座上有64个盘子，盘子大小不等，大的在下，小的在上。
 * 有一个和尚想把这64个盘子从A座移到C座，但每次只能允许移动一个盘子，并且在移动过程中，3个座上的盘子始终保持大盘在下，
 * 小盘在上。在移动过程中可以利用B座，要求打印移动的步骤。
 * 
 * 如果有1个盘子，则不需要利用B座，直接将盘子从A移动到C。
 * 如果有2个盘子，可以先将盘子1上的盘子2移动到B；将盘子1移动到c；将盘子2移动到c。
 * 如果有3个盘子，那么根据2个盘子的结论，可以借助c将盘子1上的两个盘子从A移动到B；将盘子1从A移动到C，A变成空座，借助A座，将B上的两个盘子移动到C。
 * 如果有4个盘子，那么首先借助空座C，将盘子1上的三个盘子从A移动到B；将盘子1移动到C，A变成空座；借助空座A，将B座上的三个盘子移动到C。
 * 
 * @author pepper
 * 
 */

public class HanoiTower {

	public static void hanoi(int n, char from, char temp, char to) {
		if (n == 1) {
			System.out.println("move Disk " + 1 + " from " + from + " to " + to);
		} else {
			hanoi(n - 1, from, to, temp);
			System.out.println("move Disk " + n + " from " + from + " to " + to);
			hanoi(n - 1, temp, from, to);
		}
	}

	public static void main(String[] args) {
		hanoi(3, 'A', 'B', 'C');
	}

}
