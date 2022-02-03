package com.imooc.set;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
	
	private BST<E> bst;
	
	@Override
	public void add(E val) {
		//set���е�BST�ǲ������ظ�Ԫ�صģ���add����������ظ�Ԫ�ؽ����κβ���
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
		//���Ͽ��Խ��ж����鼮�Ĵʻ�������ͳ��
		
	}
}
