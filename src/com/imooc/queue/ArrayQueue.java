package com.imooc.queue;

import com.imooc.array.ArrayDemo1;

public class ArrayQueue<E> implements Queue<E> {
//使用动态数组做底层实现队列

	private ArrayDemo1<E> array;

	public ArrayQueue(int capacity) {
		array = new ArrayDemo1(capacity);
	}

	public ArrayQueue() {
		array = new ArrayDemo1();
	}

	public int getCapacity() {
		return array.getData().length;
	}

	@Override
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		// 从队列尾放入
		array.addLast(e);
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		// 从队列头取出
		return array.removeFirst();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return array.getSize();
	}

	@Override
	public E getFront() {
		// TODO Auto-generated method stub
		return array.getFirst();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return array.isEmpty();
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Queue:  ");
		res.append("front [");
		for (int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if (i != array.getSize() - 1) {
				res.append(" , ");
			}
		}
		res.append("] tail");
		return res.toString();
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> queue = new ArrayQueue();
		for (int i = 0; i < 20; i++) {
			queue.enqueue(i);
			System.out.println(queue);
			if (i % 3 == 2) {
				queue.dequeue();
				System.out.println(queue);
			}
		}

	}
}
