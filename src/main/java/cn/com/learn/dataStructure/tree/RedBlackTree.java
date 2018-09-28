package cn.com.learn.dataStructure.tree;

/**
 * @see 原理参考博客：https://www.cnblogs.com/ysocean/p/8004211.html
 */
public class RedBlackTree {

	private enum Color {
		RED, BLACK
	}

	private static class Node {
		public int index;
		public Node leftChild;
		public Node rightChild;
		public Color color;
		public Node parent;

		public Node(int index) {
			this(null, null, null, null, index);
		}

		public Node(Node leftChild, Node rightChild, Node parent, Color color, int index) {
			super();
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
			this.color = color;
			this.index = index;
		}

		public void displayNode() {
			System.out.print('{');
			System.out.print("index=" + index + ",color=" + color);
			System.out.print('}');
		}

	}

	// NIL：叶子节点--值为空的节点
	private final Node NIL = new Node(null, null, null, Color.BLACK, -1);
	// 根节点
	private Node root;

	public RedBlackTree() {
		root = NIL;
	}

	public RedBlackTree(Node root) {
		this.root = root;
	}

	// 查找节点
	public Node find(Node node) {
		Node current = root;
		while (current.index != node.index) {
			if (node.index < current.index) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
			if (current == NIL) {
				return null;
			}
		}
		return current;
	}

	// 插入节点
	public void insert(Node node) {
		Node parent = NIL;
		Node current = root;
		while (current != NIL) {
			parent = current;
			if (node.index < current.index) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
		}
		node.parent = parent;
		if (parent == NIL) {
			root = node;
			root.parent = NIL;
		} else if (node.index < parent.index) {
			parent.leftChild = node;
		} else {
			parent.rightChild = node;
		}
		node.leftChild = NIL;
		node.rightChild = NIL;
		node.color = Color.RED;
		insertFixup(node);
	}

	// 插入调整
	public void insertFixup(Node node) {
		// 当前节点的父节点为红色
		while (node.parent.color == Color.RED) {
			// 如果当前节点的父节点是当前节点的祖父节点的左子节点
			if (node.parent == node.parent.parent.leftChild) {
				Node rightNuncle = node.parent.parent.rightChild;
				// 当前节点的父节点是红色，且当前节点的祖父节点的另一个子节点（叔叔节点）也是红色
				if (rightNuncle.color == Color.RED) {
					rightNuncle.color = Color.BLACK;
					node.parent.color = Color.BLACK;
					node.parent.parent.color = Color.RED;
					node = node.parent.parent;
				} else {
					if (node == node.parent.rightChild) {
						node = node.parent;
						leftRotate(node);
					}
					node.parent.color = Color.BLACK;
					node.parent.parent.color = Color.RED;
					rightRotate(node.parent.parent);
				}
			} else {
				Node leftNuncle = node.parent.parent.leftChild;
				if (leftNuncle.color == Color.RED) {
					node.parent.color = Color.BLACK;
					leftNuncle.color = Color.BLACK;
					node.parent.parent.color = Color.RED;
					node = node.parent.parent;
				} else {
					if (node == node.parent.leftChild) {
						node = node.parent;
						rightRotate(node);
					}
					node.parent.color = Color.BLACK;
					node.parent.parent.color = Color.RED;
					leftRotate(node.parent.parent);
				}
			}
		}
		root.color = Color.BLACK;
	}

	// 删除节点
	public Node delete(Node node) {

		Node parent = NIL;
		Node current = NIL;
		if (node == null || node == NIL) {
			return NIL;
		} else {
			if (node.leftChild == NIL || node.rightChild == NIL) {
				parent = node;
			} else {
				parent = getSuccessor(node);
			}

			if (parent.leftChild != NIL) {
				current = parent.leftChild;
			} else {
				current = parent.rightChild;
			}

			current.parent = parent.parent;

			if (parent.parent == NIL) {
				root = current;
			} else {
				if (parent == parent.parent.leftChild) {
					parent.parent.leftChild = current;
				} else {
					parent.parent.rightChild = current;
				}
			}

			if (parent != node) {
				node.index = parent.index;
			}

			if (parent.color == Color.BLACK) {
				deleteFixup(current);
			}
			return parent;
		}

	}

	// 删除节点后的调整
	private void deleteFixup(Node node) {
		if (node == NIL) {
			return;
		}

		while (node != root && node.color == Color.BLACK) {

			if (node == node.parent.leftChild) {
				Node rightBrother = node.parent.rightChild;
				if (rightBrother.color == Color.RED) {
					// node节点为左孩子，node节点的兄弟为RED
					rightBrother.color = Color.BLACK;
					node.parent.color = Color.RED;
					leftRotate(node.parent);
					rightBrother = node.parent.rightChild;
				}
				if (rightBrother.leftChild.color == Color.BLACK && rightBrother.rightChild.color == Color.BLACK) {
					rightBrother.color = Color.RED;
					node = node.parent;
					continue;
				} else if (rightBrother.rightChild.color == Color.BLACK) {
					rightBrother.leftChild.color = Color.BLACK;
					rightBrother.color = Color.RED;
					rightRotate(rightBrother);
					rightBrother = node.parent.leftChild;
				}
				rightBrother.color = node.parent.color;
				node.parent.color = Color.BLACK;
				rightBrother.rightChild.color = Color.BLACK;
				leftRotate(node.parent);
				node = root;
			} else {
				Node leftBrother = node.parent.leftChild;
				if (leftBrother.color == Color.RED) {
					leftBrother.color = Color.BLACK;
					node.parent.color = Color.RED;
					rightRotate(node.parent);
					leftBrother = node.parent.leftChild;
				}
				if (leftBrother.leftChild.color == Color.BLACK && leftBrother.rightChild.color == Color.BLACK) {
					leftBrother.color = Color.RED;
					node = node.parent;
					continue;
				} else if (leftBrother.leftChild.color == Color.BLACK) {
					leftBrother.rightChild.color = Color.BLACK;
					leftBrother.color = Color.RED;
					leftRotate(leftBrother);
					leftBrother = node.parent.rightChild;
				}
				leftBrother.color = node.parent.color;
				node.parent.color = Color.BLACK;
				leftBrother.leftChild.color = Color.BLACK;
				rightRotate(node.parent);
				node = root;
			}
		}
		node.color = Color.BLACK;
	}

	// 查找节点node的后继节点
	public Node getSuccessor(Node target) {

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

	// 左旋函数
	private void leftRotate(Node node) {

		Node rightNode = node.rightChild;

		node.rightChild = rightNode.leftChild;
		if (rightNode.leftChild != NIL) {
			rightNode.leftChild.parent = node;
		}
		rightNode.parent = node.parent;

		if (node.parent == NIL) {
			rightNode = root;
		} else if (node == node.parent.leftChild) {
			node.parent.leftChild = rightNode;
		} else {
			node.parent.rightChild = rightNode;
		}

		rightNode.leftChild = node;
		node.parent = rightNode;

	}

	// 右旋函数
	private void rightRotate(Node node) {

		Node leftNode = node.leftChild;
		node.leftChild = leftNode.rightChild;

		if (leftNode.rightChild != null) {
			leftNode.rightChild.parent = node;
		}

		leftNode.parent = node.parent;

		if (node.parent == NIL) {
			root = leftNode;
		} else if (node == node.parent.leftChild) {
			node.parent.leftChild = leftNode;
		} else {
			node.parent.rightChild = leftNode;
		}

		leftNode.rightChild = node;
		node.parent = leftNode;
	}

	// 中序遍历红黑树
	public void printTree() {
		inOrderTraverse(root);
	}

	private void inOrderTraverse(Node localRoot) {
		if (localRoot != NIL) {
			inOrderTraverse(localRoot.leftChild);
			System.out.println(" 节点：" + localRoot.index + "的颜色为：" + localRoot.color);
			inOrderTraverse(localRoot.rightChild);
		}
	}

	public Node getNIL() {
		return NIL;
	}

	/**
	 * 测试红黑树
	 * 
	 * @Description
	 * @author niepei
	 * @param args
	 */
	public static void main(String[] args) {
		RedBlackTree rbtree = new RedBlackTree();
		int[] a = { 13, 8, 11, 17, 15, 6, 1, 22, 25, 27 };
		for (int i = 0; i < a.length; i++) {
			Node node = new Node(a[i]);
			rbtree.insert(node);
		}

		Node node = new Node(20);

		rbtree.insert(node);

		rbtree.find(node).displayNode();

		rbtree.delete(node);

	}
}
