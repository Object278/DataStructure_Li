package com.imooc.priorityqueue;

import com.imooc.heap.MaxHeap;

public class MaxHeapPriorityQueue<V extends Comparable<V>> implements PriorityQueue<V> {
	
	private MaxHeap<V> maxHeap;

	@Override
	public V dequeue() {
		return maxHeap.extractMax();
	}

	@Override
	public V getFront() {
		return maxHeap.findMax();
	}

	@Override
	public int getSize() {
		return maxHeap.size();
	}

	@Override
	public boolean isEmpty() {
		return maxHeap.isEmpty();
	}

	@Override
	public void enqueue(Integer pri, V val) {
		maxHeap.add(val);
	}

	public static void main(String[] args) {

	}

}
