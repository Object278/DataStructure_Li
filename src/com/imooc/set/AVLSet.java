package com.imooc.set;

import com.imooc.avltree.AVLTree;

public class AVLSet<E extends Comparable<E>> implements Set<E> {
	
	private AVLTree<E, Object> avl;
	
	public AVLSet() {
		avl=new AVLTree<>();
	}
	
	@Override
	public void add(E val) {
		avl.add(val, null);
	}

	@Override
	public void remove(E val) {
		avl.remove(val);
	}

	@Override
	public boolean contains(E val) {
		return avl.contains(val);
	}

	@Override
	public int getSize() {
		return avl.getSize();
	}

	@Override
	public boolean isEmpty() {
		return avl.isEmpty();
	}
	
	public static void main(String[] args) {

	}

}
