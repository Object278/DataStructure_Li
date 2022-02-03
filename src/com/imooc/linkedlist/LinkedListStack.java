package com.imooc.linkedlist;

import com.imooc.stack.Stack;

public class LinkedListStack<E> implements Stack<E> {

	private LinkedList<E> list;
	
	public LinkedListStack() {
		list=new LinkedList();
	}
	
	@Override
	public int getSize() {
		return list.getSize();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public E pop() {
		return list.removeFirst();
	}

	@Override
	public E peak() {
		return list.getFirst();
	}

	@Override
	public void push(E e) {
		list.addFirst(e);
	}

	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Stack:  top=");
		res.append(list);
		//这里应该是自动调用list的toString方法了
		return res.toString();
	}
	public static void main(String[] args) {
		LinkedListStack<Integer> lls=new LinkedListStack<>();
		for(int i=0;i<5;i++) {
			lls.push(i);
			System.out.println(lls);
		}
		lls.pop();
		System.out.println(lls);
		
	}
}
