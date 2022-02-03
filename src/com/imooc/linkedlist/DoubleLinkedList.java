package com.imooc.linkedlist;

public class DoubleLinkedList<E> {
	//ʵ��˫��������ĳЩ������Ӧ���˵ݹ飬������ͷβָ��
	private class Node{
		public E value;
		public Node next, prev;
		
		public Node(E value, Node next, Node prev) {
			this.value=value;
			this.next=next;
			this.prev=prev;
		}
		
		public Node() {
			this(null, null, null);
		}
		
		@Override
		public String toString(){
			//�������Է��֣���valueΪnullʱ��toString�����������
			return value.toString();
			//Ҫ���Ӧ��������дtoString����
		}
	}
	//��ʵtailָ�����һ��Ԫ�ص�ǰһ��Ԫ�ؾͿ��԰Ѵ�����β���ӵĸ��ӶȽ�ΪO��1����Ҳ�Ͳ���˫��������
	//������Ǹ���ϰ
	private Node dummyhead,head,tail;
	private String strHead,strTail;
	private int size;
	
	public DoubleLinkedList() {
		dummyhead=new Node();
		head=null;
		tail=null;
		size=0;
	}
	
	public DoubleLinkedList(E[] arr) {
        if(arr==null || arr.length==0) {
        	throw new IllegalArgumentException("����Ϊ�գ��޷���������");
        }
        //˫��ҽ�
        dummyhead.next=new Node(arr[0], null, dummyhead);
        Node cur=dummyhead.next;
        //Java�������������Ԫ�ؼ���������ֵҲ������������
        //head��ʵ��������ĵ�һ��Ԫ�أ��洢����ָ���Ӧ�Ķ���/��ֵ������
        //head.next��ָ����һ������/��ֵ�����û��߳�Ϊ��һ������Ԫ�ص�value��������
        for(int i=1;i<arr.length;i++) {
        	cur.next=new Node(arr[i], null, cur);
        	cur=cur.next;
        	//������һ��ݹ��˼·
        }
	    size=arr.length;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	//��������ﲻ�漰������ͷβ���Ԫ�أ���Ϊ��head��tailָ�룬���������ֱ����ӣ��Ƚ�����ӣ�ֻ����ͷβ���������
	public void add(int index, E value) {
		if(index<0 || index>size) {
			throw new IllegalArgumentException("add failed��index illegal");
		}
		//��һ��int���͵�����>>31λ��������Ϊ0��������Ϊ-1
//		int b=(index-size>>1)>>31;
		//���head��tail��int���;Ϳ��Բ����ˣ����Ա���if��������
//		int res=b^((head & b)^(tail | b));
		if(index>(size/2)) {
			Node next=tail;
			for(int i=0;i<(size-index-1);i++) {
				next=next.prev;
			}
			//˳�򲻿��Է�����
			Node node=new Node(value, next, next.prev);
			next.prev.next=node;
			next.prev=node;
		}else {
			Node prev=head;
			for(int i=0;i<(index-1);i++) {
				prev=prev.next;
			}
			Node node=new Node(value, prev.next, prev);
			prev.next.prev=node;
			prev.next=node;
		}
	}
	
	public void addFirst(E value) {
		
	}
	
	public void addLast(E value) {
		
	}
	
	public void get(int index) {
		
	}
	
	public void getFirst() {
		
	}
	
	public void getLast() {
		
	}
	
	public void set(int index, E value) {
		
	}
	
	public boolean contains(E value) {
		return false;
	}
	
	public E remove(E value) {
		E e=null;
		return e;
	}
	public static void main(String[] args) {

	}

}
