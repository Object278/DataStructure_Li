package com.imooc.avltree;

public class BST<K extends Comparable<K>, V> {
	private class Node{
		public K key;
		public V value;
		public Node left, right;
		
		public Node(K key, V value) {
			this.key=key;
			this.value=value;
			left=null;
			right=null;
		}
	}
	
	private Node root;
	private int size;
	
	public BST() {
		root=null;
		size=0;
	}
	//������nodeΪ���ڵ�Ķ����������У�key���ڵĽڵ�
	private Node getNode(Node node,K key) {
		if(node == null) {
			return null;
		}
		if(key.compareTo(node.key)==0) {
			return node;
		}else if(key.compareTo(node.key)<0) {
			return getNode(node.left, key);
		}else {
			return getNode(node.right, key);
		}
	}
	
	public void add(K key, V value) {
		root=add(root, key, value);
	}

	private Node add(Node node, K key, V value) {
		if(node==null) {
			size++;
			return new Node(key, value);
		}
		if(key.compareTo(node.key)<0) {
			node.left=add(node.left, key, value);
		}else if(key.compareTo(node.key)>0) {
			node.right=add(node.right, key, value);
		}else {
			node.value=value;
		}
		//�ظ��Ļ�����Ϊ�û��������µ�valueΪ�û������õ�value
		
		return node;
	}

	public V remove(K key) {
		Node node=getNode(root, key);
		if(node != null) {
			root=remove(root, key);
			return node.value;
		}
		return null;
	}
	
	private Node remove(Node node, K key) {
		if(node == null) {
			return null;
		}
		if(key.compareTo(node.key)<0) {
			node.left=remove(node.left, key);
			return node;
		}else if(key.compareTo(node.key)>0) {
			node.right=remove(node.right, key);
			return node;
		}else {
			if(node.left==null) {
				Node rightNode=node.right;
				node.right=null;
				size--;
				return rightNode;
			}
			if(node.right==null) {
				Node leftNode=node.left;
				node.left=null;
				size--;
				return leftNode;
			}
			//����Ϊ��ɾ���ڵ�������������Ϊ�յ����
			//�ҵ�����ڵ�ĺ������������ڵ�
			Node successor=minimun(node.right);
			successor.right=removeMin(node.right);
			successor.left=node.left;
			node.left=node.right=null;
			return successor;
		}
	}
	
	private Node minimun(Node node) {
		if(node.left==null) {
			return node;
		}
		return minimun(node.left);
	}
	
	private Node removeMin(Node node) {
		if(node.left==null) {
			Node rightNode=node.right;
			node.right=null;
			size--;
			return rightNode;
		}
		node.left=removeMin(node.left);
		return node;
	}

	public boolean contains(K key) {
		return getNode(root, key) != null;
	}

	public V get(K key) {
		Node node=getNode(root, key);
		return node == null ? null : node.value;
	}

	public void set(K key, V newValue) {
		Node node=getNode(root, key);
		if(node== null) {
			throw new IllegalArgumentException("key does not exist");
		}
		node.value=newValue;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}
		
	public static void main(String[] args) {

	}

}
