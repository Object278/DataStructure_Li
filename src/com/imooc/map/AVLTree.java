package com.imooc.map;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

	private class Node {
		public K key;
		public V value;
		public Node left, right;
		public int height;// 这个节点当前的height

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			height = 1;// 新添加的节点一定在叶子，所以height为1
		}
	}

	private Node root;
	private int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	// 返回以node为根节点的二分搜索树中，key所在的节点
	private Node getNode(Node node, K key) {
		if (node == null) {
			return null;
		}
		if (key.compareTo(node.key) == 0) {
			return node;
		} else if (key.compareTo(node.key) < 0) {
			return getNode(node.left, key);
		} else {
			return getNode(node.right, key);
		}
	}

	public void add(K key, V value) {
		root = add(root, key, value);
	}

	private Node add(Node node, K key, V value) {
		if (node == null) {
			size++;
			return new Node(key, value);
		}

		if (key.compareTo(node.key) < 0) {
			node.left = add(node.left, key, value);
		} else if (key.compareTo(node.key) > 0) {
			node.right = add(node.right, key, value);
		} else {
			node.value = value;
		}
		// 重复的话，认为用户传来的新的value为用户想设置的value
		// 添加完成之后更新height、计算平衡因子和维护平衡性, 每一个节点的height就是其子结点的height中的较大值+1
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		int balanceFactor = getBalanceFactor(node);
		// 平衡维护
		// 情况一LL（右旋转）：整棵树向左倾斜，因为在不平衡节点的左侧的左侧添加节点
		if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
			return rightRotate(node);
		}
		// 情况二RR（左旋转）：整棵树向右倾斜，因为在不平衡节点的右侧的右侧添加节点
		if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
			return leftRotate(node);
		}
		// 情况三LR（左子节点左旋转后父节点右旋转）：整棵树向左倾斜，因为在不平衡节点的左侧的右侧添加节点
		if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		// 情况四RR（右子节点右旋转后父节点左旋转）：整棵树向右倾斜，因为在不平衡节点的右侧的右侧添加节点
		if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	// 返回旋转之后那个树的根节点
	private Node rightRotate(Node node) {
		Node newFather = node.left;
		Node temp = node.left.right;
		// 向右旋转
		newFather.right = node;
		node.left = temp;
		// 更新height，只有node.left和node的height变了
		// 旋转之后先计算新的子节点node的height再计算新的父节点newFather的height
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		newFather.height = Math.max(getHeight(newFather.left), getHeight(newFather.right)) + 1;
		return newFather;
	}

	private Node leftRotate(Node node) {
		Node newFather = node.right;
		Node temp = node.right.left;
		// 向左旋转
		newFather.left = node;
		node.right = temp;

		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		newFather.height = Math.max(getHeight(newFather.left), getHeight(newFather.right)) + 1;

		return newFather;
	}

	public V remove(K key) {
		Node node = getNode(root, key);
		if (node != null) {
			root = remove(root, key);
			return node.value;
		}
		return null;
	}

	private Node remove(Node node, K key) {
		if (node == null) {
			return null;
		}
		Node retNode;
		if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			retNode = node;
		} else if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			retNode = node;
		} else {// 以下三种情况逻辑互斥
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				retNode = rightNode;
			} else if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				retNode = leftNode;
			} else {
				// 以下为待删除节点左右子树都不为空的情况
				// 找到这个节点的后继来顶替这个节点
				Node successor = minimun(node.right);
				// removeMin函数中没有对平衡性进行维护，可能导致在这一点打破平衡性，所以更改为一下写法
				successor.right = remove(node.right, successor.key);
				successor.left = node.left;
				node.left = node.right = null;
				retNode = successor;
			}
		}
		//由于retNode可能为空（比如删除的是叶子节点），所以需要一次单独判断，如果为空直接return null；
		if(retNode == null) {
			return null;//防止空指针异常
		}
		// retNode是需要被返回上一层递归的节点，但是返回之前需要检查一下这个节点的平衡性
		retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
		int balanceFactor = getBalanceFactor(retNode);
		// 平衡维护，具体的情况和添加的时候一样
		// 情况一LL（右旋转）：整棵树向左倾斜，因为在不平衡节点的左侧的左侧添加节点
		if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
			return rightRotate(retNode);
		}
		// 情况二RR（左旋转）：整棵树向右倾斜，因为在不平衡节点的右侧的右侧添加节点
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
			return leftRotate(retNode);
		}
		// 情况三LR（左子节点左旋转后父节点右旋转）：整棵树向左倾斜，因为在不平衡节点的左侧的右侧添加节点
		if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
			retNode.left = leftRotate(retNode.left);
			return rightRotate(retNode);
		}
		// 情况四RR（右子节点右旋转后父节点左旋转）：整棵树向右倾斜，因为在不平衡节点的右侧的右侧添加节点
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
			retNode.right = rightRotate(retNode.right);
			return leftRotate(retNode);
		}
		return retNode;
	}

	private Node minimun(Node node) {
		if (node.left == null) {
			return node;
		}
		return minimun(node.left);
	}

	private Node removeMin(Node node) {
		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size--;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	public boolean contains(K key) {
		return getNode(root, key) != null;
	}

	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		if (node == null) {
			throw new IllegalArgumentException("key does not exist");
		}
		node.value = newValue;
	}

	private int getHeight(Node node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	private int getBalanceFactor(Node node) {
		if (node == null) {
			return 0;
		}
		return getHeight(node.left) - getHeight(node.right);
	}

	// 判断当前的二叉树是不是一个二分搜索树
	public boolean isBST() {
		// 利用二分搜索树中序遍历之后元素按照从小到大的顺序输出
		ArrayList<K> keys = new ArrayList<>();
		inOrder(root, keys);
		for (int i = 1; i < keys.size(); i++) {
			if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
				return false;
			}
		}
		return true;
	}

	private void inOrder(Node node, ArrayList<K> keys) {
		if (node == null) {
			return;
		}
		inOrder(node.left, keys);
		keys.add(node.key);
		inOrder(node.right, keys);
	}

	// 判断二叉树是不是平衡二叉树
	public boolean isBalanced() {
		return isBalanced(root);
	}

	private boolean isBalanced(Node node) {
		if (node == null) {
			return true;
			// 空的树一定是平衡的
		}
		int balanceFactor = getBalanceFactor(node);
		if (Math.abs(balanceFactor) > 1) {
			return false;
		}
		return isBalanced(node.left) && isBalanced(node.right);
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public static void main(String[] args) {

	}

}
