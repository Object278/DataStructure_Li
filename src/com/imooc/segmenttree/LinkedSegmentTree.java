package com.imooc.segmenttree;

public class LinkedSegmentTree<E> {
	private class Node<E>{
		E val;
		int left, right;
		Node<E> leftNode, rightNode;
		
		public Node(E val, int left, int right) {
			this.val=val;
			this.left=left;
			this.right=right;
			this.leftNode=null;
			this.rightNode=null;
		}
	}
	
	private Node<E> root;
	private E[] data;
	private Merger<E> merger;
	
	public LinkedSegmentTree(E[] nums, Merger merger) {
		data=(E[])new Object[nums.length];
		for(int i=0; i<nums.length; i++) {
			data[i]=nums[i];
		}
		root=new Node<E>(null, 0, data.length-1);
		root.val=buildLinkedSegmentTree(root);
	}
	//如果Node使用了泛型，那么所有写Node的地方都得写Node<E>
	private E buildLinkedSegmentTree(Node<E> node) {
		if(node.left == node.right) {
			return data[node.right];
		}
		int mid = node.left+(node.right-node.left)/2;
		node.leftNode = new Node<E>(buildLinkedSegmentTree(node.leftNode), node.left, mid);
		node.rightNode = new Node<E>(buildLinkedSegmentTree(node.rightNode), mid+1, node.right);
		return merger.merge(node.leftNode.val, node.rightNode.val);
	}
	
	public E query(int queryL, int queryR) {
		if(queryL < 0 || queryL > data.length || queryR < 0 || queryR > data.length || queryL > queryR) {
			throw new IllegalArgumentException("Index is illegal");
		}
		return query(root, queryL, queryR);
	}
	
	private E query(Node<E> node, int queryL, int queryR) {
		if(node.left == queryL && node.right == queryR) {
			return node.val;
		}
		int mid=node.left+(node.right-node.left)/2;
		if(queryL >= mid+1) {
			return query(node.rightNode, queryL, queryR);
		}else if(queryR <= mid) {
			return query(node.leftNode, queryL, queryR);
		}else {
			return merger.merge(query(node.leftNode, queryL, mid), query(node.rightNode, mid+1, queryR));
		}
	}
	
	//将单一位置的元素更新成某个元素
	public void set(int index, E val) {
		if(index < 0 || index > data.length) {
			throw new IllegalArgumentException("Index is illegal");
		}
		data[index]=val;
		set(root, index, val);
	}
	
	private void set(Node<E> node, int index, E val) {
		if(node.left == node.right) {
			node.val=val;
			return;
		}
		int mid=node.left+(node.right-node.left);
		if(index >= mid+1) {
			set(node.rightNode, index, val);
		}else {
			set(node.leftNode, index, val);
		}
		node.val=merger.merge(node.leftNode.val, node.rightNode.val);
		return;
	}
	
	public E get(int index) {
		if(index<0 || index>data.length) {
			throw new IllegalArgumentException("Index illegal");
		}
		return data[index];
	}
	
	public int getSize() {
		return data.length;
	}
	
}
