package com.imooc.linkedlist;

import com.imooc.queue.ArrayQueue;
import com.imooc.queue.Queue;

public class LinkedListQueue<E> implements Queue<E> {
//�����������ͷβָ�룬������ͷ�ڵ�
	private class Node{
		public E value;
		public Node next;
		//���ｫ��������Ϊpublic�Ϳ�����LinkedList����������
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
			//�������Է��֣���valueΪnullʱ��toString�����������
			return value.toString();
			//Ҫ���Ӧ��������дtoString����
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
		//������β��ӣ���tailλ
		if(tail==null) {
			//���tailΪnull����headҲ��null���������Ϊ��
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
		//��headλ�ó��ӣ��������Ϊ�վͲ��ܳ���
		if(this.isEmpty()) {
			throw new IllegalArgumentException("dequeue failed, the queue is empty");
		}
		Node retNode=head;
		head=head.next;
		//ע�������ֻ��һ��Ԫ�ص��������
		//������tail��ָ����ӵ�Ԫ��
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
