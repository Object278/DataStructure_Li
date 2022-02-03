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

//sizeָ����һ��node�����м���Ԫ��
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
		// ˽�е�add�������Ѿ��Խڵ�Ϊnull�����˴���
		root = add(root, val, null);
	}

	// ����NodeΪ���Ķ������������Ԫ��
	// ���ز����½ڵ�֮������������ĸ���null���Ա���Ϊһ�����������൱�ڶ�������ײ�һ��Ҷ�Ӷ���null��
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
			// ���nodeΪ�յĻ�����ô����Ľڵ���ǲ����������������½ڵ�
		}
		if (val.compareTo(node.val) < 0) {
			node.size++;
			node.left = add(node.left, val, node);
		} else if (val.compareTo(node.val) > 0) {
			node.size++;
			node.right = add(node.right, val, node);
		} else {
			// ��������val���ڵ�ǰ��node����count++, size++.
			node.size++;
		}
		// ���ز��������������ĸ�
		return node;
	}

	// ���������������Ƿ����val
	public boolean contains(E val) {
		return contains(root, val);
	}

	// ����nodeΪ���Ķ������������Ƿ���val
	// �����������л���û�������������
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

	// ������������ǰ�����
	public void preOrder() {
		hoch = 0;
		preOrder(root);
	}

	// ǰ�������nodeΪ���Ķ�����
	private void preOrder(Node node) {
		// �ݹ���ֹ����
		if (node == null) {
			int b = (hoch - tiefe) >> 31;
			hoch = hoch - (b & (hoch - tiefe));
			System.out.println("tiefe:" + tiefe);
			return;
		}
		// �ݹ飬��С�����ģ
		// ȷ�ϵ�ǰ�ڵ���Ԫ�أ�tiefe++
		tiefe++;
		System.out.println(node.val);
		// ��ʼ�����Ƚڵ����������������
		preOrder(node.left);
		preOrder(node.right);
		// ���굱ǰ�ڵ����������֮�󣬷��ط�����һ���ڵ㣬��Ӧ��tiefe--
		// ������ڵ�tiefe==0�� ֮��ÿ����һ���1����Ϊhoch�Ķ�����hoch���ڴ�blatt������������������
		tiefe--;
		return;
	}

	// ǰ������ķǵݹ��㷨NR(nonrecurrsive)
	public void preOrderNR() {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.println(cur.val + " size: " + cur.size + " depth: " + cur.depth);
			// ��Ϊ����ֱ�ӷ��ʽڵ㣬����Ҫ�ٻص����ڵ�ݹ��ȥ������tiefe�ĸı���ʱ������Ծʽ�ģ�����̫����ȷ��hoch
			// ����ջ���ӽڵ�����ջ��ڵ�ֻ��Ϊ���ȱ����������ٱ���������
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
	}

	// �������
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

	// ��������ķǵݹ��㷨NR(nonrecurrsive)
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

	// ��������ķǵݹ��㷨NR(nonrecurrsive)
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

	// �������NR�ǵݹ�
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

	// ��������ݹ�
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

	// Ѱ�Ҷ�������������СԪ��
	// �ǵݹ�д���൱�ڱ���һ������
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

	// Ѱ�Ҷ��������������Ԫ��
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

	// �Ӷ�����������ɾ����Сֵ���ڵĽڵ�
	public E removeMin() {
		E ret = minimum();
		root = removeMin(root);
		return ret;
	}

	// ɾ����nodeΪ���Ķ����������е���Сֵ������ɾ���ڵ���µĶ���������ĸ��ڵ�
	private Node removeMin(Node node) {
		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size--;
			// ����ɾ�����������ĸ�
			return rightNode;
		}
		// ����������Ϊ���ڵ��������
		node.left = removeMin(node.left);
		node.size--;
		// ���û�еݹ鵽�׵Ļ������ڵ���������ĸ�����
		return node;
	}

	// �Ӷ�����������ɾ�����ֵ���ڵĽڵ�
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

	// ɾ�������������е�ĳ���ڵ�
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
				// �����Ҷ�ӽڵ���Ϊ���ӽڵ�Ϊnull�Ľڵ㣬�ڵ�һ��if�ﴦ����
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
					// ���ҵ�follow����ɾ��follow��removeMin(node.right)���ص���Ȼ��node.right
				Node follow = minimum(node.right);
				// removeMin������һ��size--����һ��size--���Դ���ɾ����node����Ϊfollowʵ����û�б�ɾ��
				follow.right = removeMin(node.right);
				follow.left = node.left;
				node.left = node.right = null;
				// follow��node���������е���С�ڵ�
				return follow;
			}
		}
	}

	// Ѱ��һ�����ܲ��ڶ����������е�Ԫ�ص�floor�ķ�����floor�����д��ڣ�����۸����ڣ���Ԫ�ص�Ԫ���е���С��
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
			// �����Ҫ�����ң����������һ��Ϊnull���ǵ�ǰ�Ľ���ֵ���Ǵ���
			if (node.left == null) {
				return node.val;
			}
			System.out.println(floor);
			// �ȹ�עfloor��Ŀǰ�ڵ��費��Ҫ�޸�
			if (floor.compareTo(val) <= 0) {
				floor = node.val;
			} else if ((floor.compareTo(node.val)) > 0) {
				floor = node.val;
			}
			System.out.println(floor);
			// floorҲ���п�����֮��Ľڵ㻹�ᱻ�޸�
			return findFloor(node.left, val, floor);
		} else {
			// �����Ҫ�����ң������Ҳ���һ��Ϊnull���ǵ�ǰ�Ľ���ֵ���Ǵ���
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

	// Ѱ��һ�����ܲ��ڶ����������е�Ԫ�ص�ceil�ķ�����ceil��С�ڵ���val�е����Ԫ��
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
			// �����Ҫ�����ң����������һ��Ϊnull���ǵ�ǰ�Ľ���ֵ���Ǵ���
			if (node.left == null) {
				ceilList.add(node.val);
				return ceilList;
			}
			if (val.compareTo(node.val) > 0) {
				ceilList.add(node.val);
			}
			return findCeil(node.left, val, ceilList);
		} else {
			// �����Ҫ�����ң������Ҳ���һ��Ϊnull���ǵ�ǰ�Ľ���ֵ���Ǵ���
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

	// �������������ĳ��Ԫ�ذ��մ�С�����˳�������ڼ�
	// �������벻�ڶ������е�Ԫ��
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
			// ���ҵĶ�������ظ�Ԫ�ش洢��ͬһ���ڵ㣬���Ե��ڵ�������ֻ�ᴥ��һ��
			if (node.left != null) {
				rank += node.left.size + 1;
			} else {
				rank++;
			}
		}
		return rank;
	}

	// ��������е�xλ��Ԫ��
	public E select(int position) {
		if (position > size || position < 1) {
			throw new IllegalArgumentException("select failed, position illegal");
		}
		E ret = select(root, position);
		return ret;
	}

	// ʹ�����������ĸ��position���ϼ��٣�ÿ�μ��ٺ��position��������ѯԪ���ڵ�ǰ�����е���λ
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
	 * ���ɶ������ַ����ķ���
	 * 
	 * @param root2 ��ǰ��Ҫ�����ַ��������Ľڵ�
	 * @param tiefe ��ǰ������ȵĸ��ڵ�����
	 * @param res   ���ɺ���ַ���
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
