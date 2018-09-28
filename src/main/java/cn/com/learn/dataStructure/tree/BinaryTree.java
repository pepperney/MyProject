package cn.com.learn.dataStructure.tree;

import java.util.Stack;

/**
 * 二叉树：如果树的每个节点最多只能有两个子节点，这样的树成为二叉树 二叉搜索树：左子节点小于父节点，右子节点大于等于父节点
 * 
 * @author pepper
 * @see 参考此博客：https://www.cnblogs.com/ysocean/p/8032642.html
 * 
 */
public class BinaryTree {
	/**
	 * 树节点--内部类
	 * 
	 * @Description
	 * @author niepei
	 * @date 2017年4月19日 下午6:11:43
	 * @version V1.3.1
	 */
	private static class Node {
		public int index;
		public Node leftChild;
		public Node rightChild;

		public Node(int index) {
			this(null, null, index);
		}

		public Node(Node leftChild, Node rightChild, int index) {
			super();
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.index = index;
		}

		public void displayNode() {
			System.out.print('{');
			System.out.print("index=" + index);
			System.out.print('}');
		}
	}

	/**
	 * 根节点
	 */
	private Node root;

	public BinaryTree() {
		root = null;
	}

	/**
	 * 查找目标节点
	 * 
	 * @param target
	 * @return
	 */
	public Node find(Node target) {
		Node current = root;
		while (current.index != target.index) {
			if (target.index < current.index) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
			if (current == null) {
				return null;
			}
		}
		return current;
	}

	/**
	 * 插入节点到合适位置
	 * 
	 * @param node
	 */
	public void insert(Node node) {
		if (root == null) {
			root = node;
		} else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				// 如果要插入的节点小于当前节点，则继续与当前节点的左子节点比较
				if (node.index < current.index) {
					current = current.leftChild;
					// 如果走到了该分支的尽头，那么插入到左子节点
					if (current == null) {
						parent.leftChild = node;
						return;
					}
				}
				// 如果要插入的节点大于当前节点，则继续与当前节点的右子节点比较
				else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = node;
						return;
					}
				}
			}
		}
	}

	/**
	 * 删除目标节点
	 * 
	 * @param target
	 * @return
	 */
	public boolean delete(Node target) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		// 查找要删除的节点
		while (current.index != target.index) {
			parent = current;
			if (target.index < current.index) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) {
				return false;
			}
		}

		// 如果要删除的节点没有子节点，直接删除
		if (current.leftChild == null && current.rightChild == null) {
			// 如果要删除的是根节点，则清空整个树
			if (current == root) {
				root = null;
			}
			// 如果要删除的节点是左子节点，则将其父节点的左子节点删除（置为null）
			else if (isLeftChild) {
				parent.leftChild = null;
			}
			// 如果要删除的节点是右子节点，则将其父节点的右子节点删除（置为null）
			else {
				parent.rightChild = null;
			}
		}

		// 如果要删除的节点没有左子节点，则用它的右子节点代替
		else if (current.leftChild == null) {
			if (current == root) {
				root = current.rightChild;
			} else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			} else {
				parent.rightChild = current.rightChild;
			}
		}

		// 如果要删除的节点没有右子节点，则则用它的左子节点代替
		else if (current.rightChild == null) {
			if (current == root) {
				root = current.leftChild;
			} else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			} else {
				parent.rightChild = current.leftChild;
			}
		}

		// 如果要删除的节点左子节点和右子节点都存在，则用它的中序后继来代替该节点
		else {
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.leftChild = successor;
			} else {
				parent.rightChild = successor;
			}
			/**
			 * 找到后继之后，后继节点可能与target有两种位置关系： 1.如果后继节点是目标节点（被删节点）的右子节点,则需要：
			 * <1>.把target从它的父节点的rightChild删除，把这个字段指向它的后继
			 * <2>.把targe的左子节点移出来，插入到后继的leftChild
			 * 2.如果后继节点是目标节点（被删节点）的右子节点的左子节点,则需要：
			 * <1>.把后继节点父节点的leftChild字段置为后继的右子节点
			 * <2>.把后继节点的rightChild字段置为目标节点的右子节点
			 * <3>.把target从它的父节点的rightChild删除，把这个字段指向它的后继
			 * <4>.把targe的左子节点移出来，插入到后继的leftChild
			 */
			// 第二种情况的前两步由getSuccessor()方法完成，第一种情况的两步和第二种情况的后两步由delete方法完成
			successor.leftChild = current.leftChild;
		}
		return true;
	}

	/**
	 * 获得目标节点的后继节点 后继节点：对于每个节点来说，比该节点次大的节点（大于该节点的所有节点中的最小值）就是它的中序后继（简称后继） 算法：
	 * 首先找到目标节点的右子节点，它一定比目标节点大，然后转到目标节点的右子节点的左子节点（如果有的话），然后到
	 * 这个节点的左子节点，以此类推，顺着左子节点一直往下找，这个路径的最后一个左子节点就是目标节点的后继 分析：
	 * 这里实际上是要找比目标节点大的节点集合中的最小的一个一个节点，当找到目标节点的右子节点时，以这个右子节点为
	 * 根的子树的所有节点都比目标节点大，现在要找到这棵树中最小的节点，只需沿着所有左子节点找下去即可
	 * 
	 * @param target
	 * @return
	 */
	private Node getSuccessor(Node target) {
		Node successorParent = target;
		Node successor = target;
		Node current = target.rightChild;
		// 查找后继节点
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		// 如果后继节点是目标节点的右子节点的左子节点
		if (successor != target.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = target.rightChild;
		}
		return successor;
	}

	/**
	 * 遍历树
	 * 
	 * @param traverseType
	 */
	public void traverse(int traverseType) {
		switch (traverseType) {
		case 1:
			System.out.println("\n preOrder traversal: ");
			preOrder(root);
			break;
		case 2:
			System.out.println("\n inOrder traversal: ");
			inOrder(root);
			break;
		case 3:
			System.out.println("\n postOrder traversal: ");
			postOrder(root);
			break;
		}
	}

	/**
	 * 前序遍历树
	 * 
	 * @param localRoot
	 */
	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.print(localRoot.index + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	/**
	 * 中序遍历树--二叉搜索树常用的遍历方式 中序遍历二叉搜索树会使所有节点按升序被访问到 遍历可用于任何二叉树
	 * 
	 * @param localRoot
	 */
	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.index + " ");
			inOrder(localRoot.rightChild);
		}
	}

	/**
	 * 后序遍历树
	 * 
	 * @param localRoot
	 */
	private void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.index + " ");

		}
	}

	/**
	 * 打印树形结构
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void displayTree() {
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("................................................................................");
		while (isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int i = 0; i < nBlanks; i++) {
				System.out.print(" ");
			}

			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.index);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if (temp.leftChild != null || temp.rightChild != null) {
						isRowEmpty = false;
					}
				} else {
					System.out.print("00");
					localStack.push(null);
					localStack.push(null);
				}
				for (int i = 0; i < nBlanks * 2 - 2; i++) {
					System.out.print(" ");
				}
			}
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false) {
				globalStack.push(localStack.pop());
			}
		}
		System.out.println("................................................................................");
	}

	/**
	 * 二叉搜索输测试
	 * 
	 * @Description
	 * @author niepei
	 * @param args
	 */
	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();

		tree.insert(new Node(50));
		tree.insert(new Node(25));
		tree.insert(new Node(75));
		tree.insert(new Node(12));
		tree.insert(new Node(37));
		tree.insert(new Node(43));
		tree.insert(new Node(30));
		tree.insert(new Node(33));
		tree.insert(new Node(87));
		tree.insert(new Node(93));
		tree.insert(new Node(97));

		tree.displayTree();

		Node target = new Node(77);

		tree.insert(target);
		tree.displayTree();

		tree.find(target).displayNode();

		System.out.println("\n" + tree.delete(target));

		tree.traverse(1);
		tree.traverse(2);
		tree.traverse(3);

	}
}
