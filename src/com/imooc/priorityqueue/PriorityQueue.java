package com.imooc.priorityqueue;

public interface PriorityQueue<V> {
	V dequeue();
	V getFront();
	int getSize();
	boolean isEmpty();
	void enqueue(Integer pri, V val);
}
