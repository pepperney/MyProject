package cn.com.learn.designPattern.P11__Composite;
/**
 * 将多个对象组合在一起进行操作，常用于表示树形结构，例如二叉树
 * @author pepper
 *
 */
public class Test {

	
	/**
	 * 这里我们构造一个树形机构，一个根节点，两个二级节点，两个三级节点
	 * @param args
	 */
	public static void main(String[] args) {

		//根节点
		Component tree = new Branch();
		
		//二级节点
		Component branch1 = new Branch();
		Component branch2 = new Branch();
			
		//三级节点
		Component leaf1_1 = new Leaf();
		Component leaf1_2 = new Leaf();
		
		//组合所有元素成为一棵树
		branch1.add(leaf1_1);
		branch1.add(leaf1_2);
		tree.add(branch1);
		tree.add(branch2);
		
		tree.method();
		
		
	}
}