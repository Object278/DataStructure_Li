package com.imooc.queue;

public interface Queue<E> {
	void enqueue(E e);
	E dequeue();
	int getSize();
	E getFront();
	boolean isEmpty();
	
}
