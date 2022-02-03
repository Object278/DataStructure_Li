package com.imooc.map;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

	private class Node {
		public K key;
		public V value;
		public Node left, right;
		public int height;// ����ڵ㵱ǰ��height

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			height = 1;// ����ӵĽڵ�һ����Ҷ�ӣ�����heightΪ1
		}
	}

	private Node root;
	private int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	// ������nodeΪ���ڵ�Ķ����������У�key���ڵĽڵ�
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
		// �ظ��Ļ�����Ϊ�û��������µ�valueΪ�û������õ�value
		// ������֮�����height������ƽ�����Ӻ�ά��ƽ����, ÿһ���ڵ��height�������ӽ���height�еĽϴ�ֵ+1
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		int balanceFactor = getBalanceFactor(node);
		// ƽ��ά��
		// ���һLL������ת����������������б����Ϊ�ڲ�ƽ��ڵ�����������ӽڵ�
		if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
			return rightRotate(node);
		}
		// �����RR������ת����������������б����Ϊ�ڲ�ƽ��ڵ���Ҳ���Ҳ���ӽڵ�
		if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
			return leftRotate(node);
		}
		// �����LR�����ӽڵ�����ת�󸸽ڵ�����ת����������������б����Ϊ�ڲ�ƽ��ڵ�������Ҳ���ӽڵ�
		if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		// �����RR�����ӽڵ�����ת�󸸽ڵ�����ת����������������б����Ϊ�ڲ�ƽ��ڵ���Ҳ���Ҳ���ӽڵ�
		if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	// ������ת֮���Ǹ����ĸ��ڵ�
	private Node rightRotate(Node node) {
		Node newFather = node.left;
		Node temp = node.left.right;
		// ������ת
		newFather.right = node;
		node.left = temp;
		// ����height��ֻ��node.left��node��height����
		// ��ת֮���ȼ����µ��ӽڵ�node��height�ټ����µĸ��ڵ�newFather��height
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		newFather.height = Math.max(getHeight(newFather.left), getHeight(newFather.right)) + 1;
		return newFather;
	}

	private Node leftRotate(Node node) {
		Node newFather = node.right;
		Node temp = node.right.left;
		// ������ת
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
		} else {// ������������߼�����
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
				// ����Ϊ��ɾ���ڵ�������������Ϊ�յ����
				// �ҵ�����ڵ�ĺ������������ڵ�
				Node successor = minimun(node.right);
				// removeMin������û�ж�ƽ���Խ���ά�������ܵ�������һ�����ƽ���ԣ����Ը���Ϊһ��д��
				successor.right = remove(node.right, successor.key);
				successor.left = node.left;
				node.left = node.right = null;
				retNode = successor;
			}
		}
		//����retNode����Ϊ�գ�����ɾ������Ҷ�ӽڵ㣩��������Ҫһ�ε����жϣ����Ϊ��ֱ��return null��
		if(retNode == null) {
			return null;//��ֹ��ָ���쳣
		}
		// retNode����Ҫ��������һ��ݹ�Ľڵ㣬���Ƿ���֮ǰ��Ҫ���һ������ڵ��ƽ����
		retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
		int balanceFactor = getBalanceFactor(retNode);
		// ƽ��ά����������������ӵ�ʱ��һ��
		// ���һLL������ת����������������б����Ϊ�ڲ�ƽ��ڵ�����������ӽڵ�
		if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
			return rightRotate(retNode);
		}
		// �����RR������ת����������������б����Ϊ�ڲ�ƽ��ڵ���Ҳ���Ҳ���ӽڵ�
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
			return leftRotate(retNode);
		}
		// �����LR�����ӽڵ�����ת�󸸽ڵ�����ת����������������б����Ϊ�ڲ�ƽ��ڵ�������Ҳ���ӽڵ�
		if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
			retNode.left = leftRotate(retNode.left);
			return rightRotate(retNode);
		}
		// �����RR�����ӽڵ�����ת�󸸽ڵ�����ת����������������б����Ϊ�ڲ�ƽ��ڵ���Ҳ���Ҳ���ӽڵ�
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

	// �жϵ�ǰ�Ķ������ǲ���һ������������
	public boolean isBST() {
		// ���ö����������������֮��Ԫ�ذ��մ�С�����˳�����
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

	// �ж϶������ǲ���ƽ�������
	public boolean isBalanced() {
		return isBalanced(root);
	}

	private boolean isBalanced(Node node) {
		if (node == null) {
			return true;
			// �յ���һ����ƽ���
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
