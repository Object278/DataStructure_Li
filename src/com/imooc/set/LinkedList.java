package com.imooc.set;

public class LinkedList<E> {
	//结点Node是链表类的私有内部类，其他类无法访问
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
	//给链表设置虚拟头节点
	private Node dummyhead;
	private int size;
	
	public LinkedList() {
		//这里不能写成老师的dummyhead=null，问一下
		dummyhead=new Node(null,null);
		size=0;
	}
	//传入数组把数组转换成链表的构造方法
	public LinkedList(E[] arr) {
        if(arr==null || arr.length==0) {
        	throw new IllegalArgumentException("数组为空，无法构建链表");
        }
        dummyhead.next=new Node(arr[0]);
        Node cur=dummyhead.next;
        //Java里好像数组的这个元素即代表它的值也代表它的引用
        //head其实就是链表的第一个元素，存储的是指向对应的对象/数值的引用
        //head.next是指向“下一个对象/数值的引用或者称为下一个链表元素的value”的引用
        for(int i=1;i<arr.length;i++) {
        	cur.next=new Node(arr[i]);
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
	
	//在链表的index（0-based）处与index+1处之间添加元素
	//在链表中这个方法不常用，仅作练习，因为链表中一般抛弃index这个概念
	public void add(int index, E value) {
		if(index<0 || index>size) {
			throw new IllegalArgumentException("add失败，index非法");
		}
		//将prev向前移动index次寻找插入位置的前一个元素
		Node prev=dummyhead;
		//dummyhead是第一个节点前面的节点，所以是index次
		for(int i=0;i<index;i++) {
			prev=prev.next;
		}
		//	Node node=new Node(value);
		//node.next=prev.next;
		//	prev.next=node;
		//优雅的把三句话写成一句话
		prev.next=new Node(value, prev.next);
		size++;
	}
	
	//在链表头添加新的元素
	public void addFirst(E value) {
		this.add(0, value);
	}
		
	public void addLast(E e) {
		add(size, e);
	}
	
	//获取链表的第index（0-based）个元素
	//在链表中这个方法不常用，仅作练习，因为链表中一般抛弃index这个概念
	public E get(int index) {
		//注意，因为链表的第size个元素是链表尾null
		//所以这里的IllegalArgument包含size
		if(index<0 || index>=size) {
			throw new IllegalArgumentException("get失败，index非法");
		}
		//这里要遍历每一个位置的元素，看的是index位置的元素
		Node cur=dummyhead.next;
		for(int i=0;i<index;i++) {
			cur=cur.next;
		}
		return cur.value;
		//链表很少使用index进行操作
	}
	
	public E getFirst() {
		return get(0);
	}
	
	public E getLast() {
		return get(size-1);
	}
	
	//更新链表的第index（0-based）个元素
	//在链表中这个方法不常用，仅作练习，因为链表中一般抛弃index这个概念
	public void set(int index, E value) {
		//注意，因为链表的第size个元素是链表尾null
		//所以这里的IllegalArgument包含size
		if(index<0 || index>=size) {
			throw new IllegalArgumentException("set失败，index非法");
		}
		Node cur=dummyhead.next;
		for(int i=0;i<index;i++) {
			cur=cur.next;
		}
		cur.value=value;
	}
	
	//查找链表中是否有元素value
	public boolean contains(E value) {
		Node cur=dummyhead.next;
		while(cur!=null) {
			if(cur.value.equals(value)) {
				return true;
			}
			cur=cur.next;
		}
		return false;
	}
	
	//删除链表的第index（0-based）个元素
	//在链表中这个方法不常用，仅作练习，因为链表中一般抛弃index这个概念
	public E remove(int index) {
		//这里删除链表尾null应该没有意义，所以size也是非法Argument
		if(index<0 || index>size) {
			throw new IllegalArgumentException("remove失败，index非法");
		}
		Node prev= dummyhead;
		for(int i=0;i<index;i++) {
			prev=prev.next;
		}
		Node retNode=prev.next;
		prev.next=retNode.next;
		retNode.next=null;
		size--;
		return retNode.value;
	}
	
	public E removeFirst(){
		return remove(0);
	}
	
	public E removeLast() {
		return remove(size-1);
	}
	
	public void removeElement(E val) {
		Node cur=dummyhead;
		while(cur.next!=null) {
			if(cur.next.value.equals(val)) {
				cur.next=cur.next.next;
				cur.next.next=null;
				break;
			}
			cur=cur.next;
		}
	}

	
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		//cur是遍历过程中当前的Node的引用，它当然要从head开始遍历
		Node cur=dummyhead.next;
		res.append("head: ");
		//当cur指向null，代表没有下一个元素，遍历终止
		while(cur!=null) {
			//也可以用for循环，并且for循环更加紧凑
			//for(Node cur=dummyhead.next; cur != null; cur=cur.next)
			//每个Node内部还包含指向value和next的两个指针
			res.append(cur.value+"-->");
			cur=cur.next;
		}
		res.append("null");
		return res.toString();
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> ll=new LinkedList<>();
		for(int i=0;i<5;i++) {
			ll.addFirst(i);
			System.out.println(ll);
		}
		ll.add(2,666);
		System.out.println(ll);
		ll.remove(2);
		System.out.println(ll);
		ll.removeFirst();
		System.out.println(ll);
		ll.removeLast();
		System.out.println(ll);
	}

}
