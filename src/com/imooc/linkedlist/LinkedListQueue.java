package com.imooc.linkedlist;

import com.imooc.queue.ArrayQueue;
import com.imooc.queue.Queue;

public class LinkedListQueue<E> implements Queue<E> {
//这里的链表有头尾指针，无虚拟头节点
	private class Node{
		public E value;
		public Node next;
		//这里将属性设置为public就可以在LinkedList里随便访问了
		public Node(E value, Node next) {
			this.value=value;
			this.next=next;
		}
		
		public Node(E value) {
			this(value, null);
		}
		
		public Node() {
			this(null, null);
		}
		
		@Override
		public String toString(){
			//经过测试发现，当value为null时，toString不能正常输出
			return value.toString();
			//要求对应的类里重写toString（）
		}
	}
		private Node head, tail;
		private int size;
			
		public LinkedListQueue() {
			head=null;
			tail=null;
			size=0;
		}

	@Override
	public void enqueue(E value) {
		//从链表尾入队，即tail位
		if(tail==null) {
			//如果tail为null，则head也是null，代表队列为空
			tail=new Node(value);
			head=tail;
		}else {
			tail.next=new Node(value);
			tail=tail.next;
		}
		size++;
	}

	@Override
	public E dequeue() {
		//从head位置出队，如果队列为空就不能出队
		if(this.isEmpty()) {
			throw new IllegalArgumentException("dequeue failed, the queue is empty");
		}
		Node retNode=head;
		head=head.next;
		//注意队列中只有一个元素的特殊情况
		//不能让tail还指向出队的元素
		if(head==null) {
			tail=null;
		}
		size--;
		return retNode.value;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public E getFront() {
		if(this.isEmpty()) {
			throw new IllegalArgumentException("getFront failed, the queue is empty");
		}
		return head.value;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Queue:  front");
		Node cur=head;
		while(cur!=null) {
			res.append(cur.value+"-->");
			cur=cur.next;
		}
		res.append("null  tail");
		return res.toString();
	}
	
	public static void main(String[] args) {
		LinkedListQueue<Integer> queue=new LinkedListQueue();
		for(int i=0;i<20;i++) {
			queue.enqueue(i);
			System.out.println(queue);
			if(i%3==2) {
				queue.dequeue();
				System.out.println(queue);
			}
		}
	}
}
