package com.imooc.set;

public interface Set<E> {
	void add(E val);
	void remove(E val);
	boolean contains(E val);
	int getSize();
	boolean isEmpty();
}
