package cn.com.learn.readThinkInJava;

public class StaticInitailization {

	public static void main(String[] args) {
		System.out.println("creating new CupBoard() in main");
		new CupBoard();
		System.out.println("creating new CupBoard() in main");
		new CupBoard();
		table.f2(1);
		cupBoard.f3(1);
		
		/**
		 * Bowl(1) 
		 * Bowl(2) 
		 * Table() 
		 * f1(1) 
		 * Bowl(4) 
		 * Bowl(5) 
		 * Bowl(3) 
		 * CupBoard()
		 * f1(2) 
		 * creating new CupBoard() in main 
		 * Bowl(3) 
		 * CupBoard() 
		 * f1(2)
		 * creating new CupBoard() in main 
		 * Bowl(3) 
		 * CupBoard() 
		 * f1(2) 
		 * f2(1) 
		 * f3(1)
		 * 
		 */
	}
	
	static Table table = new Table();
	static CupBoard cupBoard = new CupBoard();
	
}

class Bowl {
	public Bowl(int i) {
		System.out.println("Bowl(" + i + ")");
	}

	void f1(int i) {
		System.out.println("f1(" + i + ")");
	}
}

class Table {
	static Bowl bowl1 = new Bowl(1);

	Table() {
		System.out.println("Table()");
		bowl2.f1(1);
	}

	void f2(int i) {
		System.out.println("f2(" + i + ")");
	}

	static Bowl bowl2 = new Bowl(2);

}

class CupBoard {
	Bowl bowl3 = new Bowl(3);
	static Bowl bowl4 = new Bowl(4);

	CupBoard() {
		System.out.println("CupBoard()");
		bowl4.f1(2);
	}

	void f3(int i) {
		System.out.println("f3(" + i + ")");
	}
	
	static Bowl bowl5 = new Bowl(5);
}
