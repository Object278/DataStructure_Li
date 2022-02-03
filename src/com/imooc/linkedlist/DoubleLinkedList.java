package com.imooc.linkedlist;

public class DoubleLinkedList<E> {
	//实现双向链表，在某些方法上应用了递归，链表有头尾指针
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
			//经过测试发现，当value为null时，toString不能正常输出
			return value.toString();
			//要求对应的类里重写toString（）
		}
	}
	//其实tail指向最后一个元素的前一个元素就可以把从链表尾出队的复杂度降为O（1），也就不用双向链表了
	//这里就是个练习
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
        	throw new IllegalArgumentException("数组为空，无法构建链表");
        }
        //双向挂接
        dummyhead.next=new Node(arr[0], null, dummyhead);
        Node cur=dummyhead.next;
        //Java里好像数组的这个元素即代表它的值也代表它的引用
        //head其实就是链表的第一个元素，存储的是指向对应的对象/数值的引用
        //head.next是指向“下一个对象/数值的引用或者称为下一个链表元素的value”的引用
        for(int i=1;i<arr.length;i++) {
        	cur.next=new Node(arr[i], null, cur);
        	cur=cur.next;
        	//这里有一点递归的思路
        }
	    size=arr.length;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	//这个方法里不涉及从链表头尾添加元素，因为有head和tail指针，在下面可以直接添加（比较像入队，只不过头尾都可以入队
	public void add(int index, E value) {
		if(index<0 || index>size) {
			throw new IllegalArgumentException("add failed，index illegal");
		}
		//将一个int类型的数组>>31位后，正数变为0，负数变为-1
//		int b=(index-size>>1)>>31;
		//如果head和tail是int类型就可以操作了，可以避免if拖慢程序
//		int res=b^((head & b)^(tail | b));
		if(index>(size/2)) {
			Node next=tail;
			for(int i=0;i<(size-index-1);i++) {
				next=next.prev;
			}
			//顺序不可以反过来
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
