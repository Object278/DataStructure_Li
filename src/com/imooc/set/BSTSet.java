package com.imooc.set;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
	
	private BST<E> bst;
	
	@Override
	public void add(E val) {
		//set包中的BST是不允许重复元素的，其add方法不会对重复元素进行任何操作
		bst.add(val);
	}

	@Override
	public void remove(E val) {
		bst.remove(val);
	}

	@Override
	public boolean contains(E val) {
		return bst.contains(val);
	}

	@Override
	public int getSize() {
		return bst.size();
	}

	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}
	
	public static void main(String[] args) {
		//集合可以进行对于书籍的词汇量进行统计
		
	}
}
