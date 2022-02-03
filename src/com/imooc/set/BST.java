package com.imooc.set;

import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BST<E extends Comparable<E>> {

	private class Node {
		public E val;
		public Node left;
		public Node right;
		public int size, depth;;

//size指的是一个node下面有几个元素
		public Node(E val) {
			this.val = val;
			left = null;
			right = null;
			size = 1;
		}
	}

	private Node root;
	private int size;
	public static int hoch;
	private int tiefe = 0;

	public BST() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(E val) {
		// 私有的add方法中已经对节点为null进行了处理
		root = add(root, val, null);
	}

	// 向以Node为根的二分搜索树添加元素
	// 返回插入新节点之后二分搜索树的根，null可以被视为一个二叉树（相当于二叉树最底部一层叶子都是null）
	private Node add(Node node, E val, Node fatherNode) {
		if (node == null) {
			size++;
			if (fatherNode == null) {
				Node addNode = new Node(val);
				addNode.depth = 0;
				return addNode;
			} else {
				Node addNode = new Node(val);
				addNode.depth = fatherNode.depth + 1;
				return addNode;
			}
			// 如果node为空的话，那么插入的节点就是插入后二分搜索树的新节点
		}
		if (val.compareTo(node.val) < 0) {
			node.size++;
			node.left = add(node.left, val, node);
		} else if (val.compareTo(node.val) > 0) {
			node.size++;
			node.right = add(node.right, val, node);
		} else {
			// 如果插入的val等于当前的node，就count++, size++.
			node.size++;
		}
		// 返回插入后二分搜索树的根
		return node;
	}

	// 看二分搜索树中是否包含val
	public boolean contains(E val) {
		return contains(root, val);
	}

	// 看以node为根的二分搜索树中是否有val
	// 二分搜索树中基本没有索引这个概念
	private boolean contains(Node node, E val) {
		if (node == null) {
			return false;
		}
		if (val.compareTo(node.val) == 0) {
			return true;
		} else if (val.compareTo(node.val) < 0) {
			return contains(node.left, val);
		} else {
			return contains(node.right, val);
		}
	}

	// 二分搜索树的前序遍历
	public void preOrder() {
		hoch = 0;
		preOrder(root);
	}

	// 前序遍历以node为根的二叉树
	private void preOrder(Node node) {
		// 递归终止条件
		if (node == null) {
			int b = (hoch - tiefe) >> 31;
			hoch = hoch - (b & (hoch - tiefe));
			System.out.println("tiefe:" + tiefe);
			return;
		}
		// 递归，缩小问题规模
		// 确认当前节点有元素，tiefe++
		tiefe++;
		System.out.println(node.val);
		// 开始看当先节点的左子树和右子树
		preOrder(node.left);
		preOrder(node.right);
		// 看完当前节点的左右子树之后，返回访问上一个节点，对应的tiefe--
		// 定义根节点tiefe==0， 之后每向下一层加1，因为hoch的定义是hoch等于从blatt看起的最深子树的深度
		tiefe--;
		return;
	}

	// 前序遍历的非递归算法NR(nonrecurrsive)
	public void preOrderNR() {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.println(cur.val + " size: " + cur.size + " depth: " + cur.depth);
			// 因为这里直接访问节点，不需要再回到父节点递归回去，所以tiefe的改变有时候是跳跃式的，好像不太容易确定hoch
			// 先入栈右子节点再入栈左节点只是为了先遍历左子树再遍历右子树
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
	}

	// 中序遍历
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		System.out.println(node.val);
		inOrder(node.right);
	}

	// 中序遍历的非递归算法NR(nonrecurrsive)
	public void inOrderNR() {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			if (cur != null) {
				if (cur.right != null) {
					stack.push(cur.right);
				}
				stack.push(cur);
				stack.push(null);
				if (cur.left != null) {
					stack.push(cur.left);
				}
			} else {
				cur = stack.pop();
				System.out.println(cur.val);
			}
		}
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.val);
	}

	// 后序遍历的非递归算法NR(nonrecurrsive)
	public void postOrderNR() {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			if (cur != null) {
				stack.push(cur);
				stack.push(null);
				if (cur.right != null) {
					stack.push(cur.right);
				}
				if (cur.left != null) {
					stack.push(cur.left);
				}
			} else {
				cur = stack.pop();
				System.out.println(cur.val);
			}
		}
	}

	// 层序遍历NR非递归
	public void levelOrderNR() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node cur = queue.remove();
			System.out.println(cur.val);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}

	// 层序遍历递归
	public void levelOrder() {
		ArrayList<Node> arrlist = new ArrayList<>();
		arrlist.add(root);
		levelOrder(arrlist);
	}

	private void levelOrder(ArrayList<Node> arrlist) {
		if (arrlist.isEmpty()) {
			return;
		}
		ArrayList<Node> arrlist1 = new ArrayList<>();
		for (Node cur : arrlist) {
			System.out.println(cur.val);
			if (cur.left != null) {
				arrlist1.add(cur.left);
			}
			if (cur.right != null) {
				arrlist1.add(cur.right);
			}
		}
		levelOrder(arrlist1);
	}

	// 寻找二分搜索树的最小元素
	// 非递归写法相当于遍历一个链表
	public E minimum() {
		if (size == 0) {
			throw new IllegalArgumentException("BST is empty");
		}
		return minimum(root).val;
	}

	private Node minimum(Node node) {
		if (node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

	// 寻找二分搜索树的最大元素
	public E maximum() {
		if (size == 0) {
			throw new IllegalArgumentException("BST is empty");
		}
		return maximum(root).val;
	}

	private Node maximum(Node node) {
		if (node.right == null) {
			return node;
		}
		return maximum(node.right);
	}

	// 从二分搜索书中删除最小值所在的节点
	public E removeMin() {
		E ret = minimum();
		root = removeMin(root);
		return ret;
	}

	// 删除以node为根的二分搜索树中的最小值，返回删除节点后新的二分搜索书的根节点
	private Node removeMin(Node node) {
		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size--;
			// 返回删除后新子树的根
			return rightNode;
		}
		// 将新子树变为父节点的左子树
		node.left = removeMin(node.left);
		node.size--;
		// 如果没有递归到底的话，父节点的左子树的根不变
		return node;
	}

	// 从二分搜索书中删除最大值所在的节点
	public E removeMax() {
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}

	private Node removeMax(Node node) {
		if (node.right == null) {
			Node leftNode = node.left;
			node.left = null;
			size--;
			return leftNode;
		}
		node.right = removeMax(node.right);
		node.size--;
		return node;
	}

	// 删除二分搜索树中的某个节点
	public void remove(E val) {
		root = remove(root, val);
	}

	private Node remove(Node node, E val) {
		if (node == null) {
			return null;
		}
		if (val.compareTo(node.val) < 0) {
			node.left = remove(node.left, val);
			node.size--;
			return node;
		} else if (val.compareTo(node.val) > 0) {
			node.right = remove(node.right, val);
			node.size--;
			return node;
		} else {// val.compareTo(node.val)==0
				// 这里把叶子节点视为左子节点为null的节点，在第一个if里处理了
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				return rightNode;
			} else if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			} else {// node.left != null && node.right != null
					// 先找到follow，再删除follow。removeMin(node.right)返回的仍然是node.right
				Node follow = minimum(node.right);
				// removeMin中做了一次size--，这一次size--可以代表删除的node，因为follow实际上没有被删除
				follow.right = removeMin(node.right);
				follow.left = node.left;
				node.left = node.right = null;
				// follow是node的右子树中的最小节点
				return follow;
			}
		}
	}

	// 寻找一个可能不在二分搜索树中的元素的floor的方法，floor是所有大于（这里价个等于）该元素的元素中的最小的
	public E findFloor(E val) {
		E floor = root.val;
		floor = findFloor(root, val, floor);
		if (floor.compareTo(val) < 0) {
			throw new IllegalArgumentException("findFloor failed, there is no floor in the tree for the argument");
		}
		return floor;
	}

	private E findFloor(Node node, E val, E floor) {
		if ((val.compareTo(node.val)) < 0) {
			// 如果需要想左找，但是左侧下一个为null，那当前的结点的值就是答案了
			if (node.left == null) {
				return node.val;
			}
			System.out.println(floor);
			// 先关注floor在目前节点需不需要修改
			if (floor.compareTo(val) <= 0) {
				floor = node.val;
			} else if ((floor.compareTo(node.val)) > 0) {
				floor = node.val;
			}
			System.out.println(floor);
			// floor也有有可能在之后的节点还会被修改
			return findFloor(node.left, val, floor);
		} else {
			// 如果需要想右找，但是右侧下一个为null，那当前的结点的值就是答案了
			if (node.right == null) {
				return node.val;
			}
			System.out.println(floor);
			if (floor.compareTo(val) <= 0) {
				floor = node.val;
			} else if ((floor.compareTo(node.val)) > 0 && (val.compareTo(node.val)) < 0) {
				floor = node.val;
			}
			System.out.println(floor);
			return findFloor(node.right, val, floor);
		}
	}

	// 寻找一个可能不在二分搜索树中的元素的ceil的方法，ceil是小于等于val中的最大元素
	public E findCeil(E val) {
		E ceil = null;
		ArrayList<E> ceilList = new ArrayList<>();
		ceilList = findCeil(root, val, ceilList);
		Collections.sort(ceilList);
		ceil = ceilList.get(ceilList.size() - 1);
		System.out.println(ceil);
		if (ceil.compareTo(val) > 0) {
			throw new IllegalArgumentException("findCeil failed, there is no ceil in the tree for the argument");
		}
		return ceil;
	}

	private ArrayList<E> findCeil(Node node, E val, ArrayList<E> ceilList) {
		if ((val.compareTo(node.val)) <= 0) {
			// 如果需要想左找，但是左侧下一个为null，那当前的结点的值就是答案了
			if (node.left == null) {
				ceilList.add(node.val);
				return ceilList;
			}
			if (val.compareTo(node.val) > 0) {
				ceilList.add(node.val);
			}
			return findCeil(node.left, val, ceilList);
		} else {
			// 如果需要想右找，但是右侧下一个为null，那当前的结点的值就是答案了
			if (node.right == null) {
				ceilList.add(node.val);
				return ceilList;
			}
			if (val.compareTo(node.val) > 0) {
				ceilList.add(node.val);
			}
			return findCeil(node.right, val, ceilList);
		}
	}

	// 求二分搜索树中某个元素按照从小到大的顺序排名第几
	// 不能输入不在二叉树中的元素
	public int rank(E val) {
		int rank = 0;
		rank = rank(root, val, rank);
		return rank;
	}

	private int rank(Node node, E val, int rank) {
		if (node == null) {
			return rank;
		}
		if (val.compareTo(node.val) < 0) {
			rank = rank(node.left, val, rank);
		} else if (val.compareTo(node.val) > 0) {
			if (node.left != null) {
				rank += node.left.size + 1;
			} else {
				rank++;
			}
			rank = rank(node.right, val, rank);
		} else {
			// 在我的二叉树里，重复元素存储于同一个节点，所以等于的情况最多只会触发一次
			if (node.left != null) {
				rank += node.left.size + 1;
			} else {
				rank++;
			}
		}
		return rank;
	}

	// 求二叉树中第x位的元素
	public E select(int position) {
		if (position > size || position < 1) {
			throw new IllegalArgumentException("select failed, position illegal");
		}
		E ret = select(root, position);
		return ret;
	}

	// 使用了相对排序的概念，position不断减少，每次减少后的position都代表被查询元素在当前子树中的排位
	private E select(Node node, int position) {
		if (position == 0) {
			return node.val;
		}
		if (node.left != null) {
			if (position < node.left.size + 1) {
				return select(node.left, position);
			} else if (position > node.left.size + 1) {
				position -= node.left.size + 1;
				return select(node.right, position);
			} else {
				position -= node.left.size + 1;
				return select(node, position);
			}
		} else {
			position--;
			if (position == 0) {
				return select(node, position);
			} else {
				return select(node.right, position);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root, 0, res);
		return res.toString();
	}

	/**
	 * 生成二叉树字符串的方法
	 * 
	 * @param root2 当前需要生成字符串的树的节点
	 * @param tiefe 当前树的深度的根节点的深度
	 * @param res   生成后的字符串
	 */
	private void generateBSTString(BST<E>.Node node, int tief, StringBuilder res) {
		if (node == null) {
			res.append(generateDepthString(tief) + "null\n");
			return;
		}
		res.append(generateDepthString(tief) + node.val + "\n");
		generateBSTString(node.left, tief + 1, res);
		generateBSTString(node.right, tief + 1, res);
	}

	private String generateDepthString(int tief) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < tief; i++) {
			res.append("--");
		}
		return res.toString();
	}

	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		bst.add(5);
		bst.add(3);
		bst.add(2);
		bst.add(4);
		bst.add(8);
		bst.add(7);
		bst.add(9);
		bst.add(6);
		bst.add(10);
		bst.preOrderNR();
		System.out.println(bst.rank(2));
		System.out.println(bst.select(5));
//		System.out.println(bst.findCeil(10));
//		bst.postOrder();
//		bst.postOrderNR();
//		bst.levelOrderNR();
//		bst.levelOrder();
		// System.out.println(bst);
	}

}
