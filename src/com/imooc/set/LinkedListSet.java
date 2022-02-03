package com.imooc.set;

public class LinkedListSet<E> implements Set<E> {
	private LinkedList<E> list;
	
	public LinkedListSet() {
		list=new LinkedList<>();
	}
	
	public int getSize() {
		return list.getSize();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public boolean contains(E val) {
		return list.contains(val);
	}
	
	public void add(E val) {
		if(!list.contains(val)) {
			list.addFirst(val);
		}
	}
	
	public void remove(E val) {
		list.removeElement(val);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
