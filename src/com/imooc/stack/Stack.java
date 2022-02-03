package com.imooc.stack;

public interface Stack<E> {
	int getSize();
	boolean isEmpty();
	E pop();
	E peak();
	void push(E e);
}
