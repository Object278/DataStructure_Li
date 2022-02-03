package com.imooc.stack;

import com.imooc.array.ArrayDemo1;

public class ArrayStack<E> implements Stack<E> {
//���ڶ�̬�����ջ
	ArrayDemo1<E> array;
	
	public ArrayStack(int capacity) {
		array=new ArrayDemo1<E>(capacity);
	}
	
	public ArrayStack() {
		array=new ArrayDemo1<E>();
		//���ö�̬������޲ι��췽��
	}
	
	//���ڽӿ������ʵ���޹أ�getCaoacity�������ֻ���ö�̬����ʵ��Stack��ʱ����漰��
	//���Խӿ���û���������
	public int getCapacity() {
		return array.getData().length;
	}
	
	@Override
	public int getSize() {
		return array.getSize();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	@Override
	public void push(E e) {
		array.addLast(e);
	}

	@Override
	public E pop() {
		return array.removeLast();
	}

	@Override
	public E peak() {
		return array.getLast();
	}

	public  String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Stack:  ");
		res.append("[");
		for(int i=0;i<array.getSize();i++) {
			res.append(array.get(i));
			if(i!=array.getSize()) {
				res.append(" , ");
			}
			res.append("] top");
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		ArrayStack<Integer> stack=new ArrayStack();
		for(int i=0;i<5;i++) {
			stack.push(i+1);
			System.out.println(stack);
		}
		stack.pop();
		System.out.println(stack);
	}
}
