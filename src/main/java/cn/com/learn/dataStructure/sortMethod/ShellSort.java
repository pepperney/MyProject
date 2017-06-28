package cn.com.learn.dataStructure.sortMethod;


/**
 * 希尔排序：
 * 通过加大插入排序元素之间的间隔，并在这些有间隔的元素中进行插入排序，从而是数据项能大跨度的移动，
 * 当这些数据项经过一趟排序后，希尔排序减小数据项之间的间隔再进行排序。
 * 数据项之间的间隔成为增量，习惯上用字母h表示，Knuth序列递归公式为：h=3*h+1
 * 
 * @author pepper
 *
 */
public class ShellSort extends MethodSort {

	public ShellSort(int[] a) {
		super(a);
	}

	@Override
	public void sortArray() {
		
		int h = 1; //设置最小增量为1
		//生成增量序列（1,4,13,40,121,364,1093...）
		while (h <= a.length / 3) {
			h = h * 3 + 1;
		}
		
		//逐渐减小增量，知道增量h=1
		while (h > 0) {
			//对增量为h的序列进行插入排序
			for (int i = h; i < a.length; i++) {
				int temp = a[i];
				int j = i;			
				for(;j > h - 1 && a[j - h] >= temp;j -= h){
					a[j] = a[j - h];
				}				
				a[j] = temp;
			}
			//减小增量h
			h = (h - 1) / 3;
		}
	}
	
	public static void main(String[] args) {
		int a[] = { 4, 3, 6, 9, 7, 1, 2, 5, 8,0 };
		MethodSort sm = new ShellSort(a);
		sm.showArray();
		sm.sortArray();
		sm.showArray();
	}
}
