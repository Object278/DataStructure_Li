package com.imooc.set;

public class LinkedList<E> {
	//���Node���������˽���ڲ��࣬�������޷�����
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
	//��������������ͷ�ڵ�
	private Node dummyhead;
	private int size;
	
	public LinkedList() {
		//���ﲻ��д����ʦ��dummyhead=null����һ��
		dummyhead=new Node(null,null);
		size=0;
	}
	//�������������ת��������Ĺ��췽��
	public LinkedList(E[] arr) {
        if(arr==null || arr.length==0) {
        	throw new IllegalArgumentException("����Ϊ�գ��޷���������");
        }
        dummyhead.next=new Node(arr[0]);
        Node cur=dummyhead.next;
        //Java�������������Ԫ�ؼ���������ֵҲ������������
        //head��ʵ��������ĵ�һ��Ԫ�أ��洢����ָ���Ӧ�Ķ���/��ֵ������
        //head.next��ָ����һ������/��ֵ�����û��߳�Ϊ��һ������Ԫ�ص�value��������
        for(int i=1;i<arr.length;i++) {
        	cur.next=new Node(arr[i]);
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
	
	//�������index��0-based������index+1��֮�����Ԫ��
	//��������������������ã�������ϰ����Ϊ������һ������index�������
	public void add(int index, E value) {
		if(index<0 || index>size) {
			throw new IllegalArgumentException("addʧ�ܣ�index�Ƿ�");
		}
		//��prev��ǰ�ƶ�index��Ѱ�Ҳ���λ�õ�ǰһ��Ԫ��
		Node prev=dummyhead;
		//dummyhead�ǵ�һ���ڵ�ǰ��Ľڵ㣬������index��
		for(int i=0;i<index;i++) {
			prev=prev.next;
		}
		//	Node node=new Node(value);
		//node.next=prev.next;
		//	prev.next=node;
		//���ŵİ����仰д��һ�仰
		prev.next=new Node(value, prev.next);
		size++;
	}
	
	//������ͷ����µ�Ԫ��
	public void addFirst(E value) {
		this.add(0, value);
	}
		
	public void addLast(E e) {
		add(size, e);
	}
	
	//��ȡ����ĵ�index��0-based����Ԫ��
	//��������������������ã�������ϰ����Ϊ������һ������index�������
	public E get(int index) {
		//ע�⣬��Ϊ����ĵ�size��Ԫ��������βnull
		//���������IllegalArgument����size
		if(index<0 || index>=size) {
			throw new IllegalArgumentException("getʧ�ܣ�index�Ƿ�");
		}
		//����Ҫ����ÿһ��λ�õ�Ԫ�أ�������indexλ�õ�Ԫ��
		Node cur=dummyhead.next;
		for(int i=0;i<index;i++) {
			cur=cur.next;
		}
		return cur.value;
		//�������ʹ��index���в���
	}
	
	public E getFirst() {
		return get(0);
	}
	
	public E getLast() {
		return get(size-1);
	}
	
	//��������ĵ�index��0-based����Ԫ��
	//��������������������ã�������ϰ����Ϊ������һ������index�������
	public void set(int index, E value) {
		//ע�⣬��Ϊ����ĵ�size��Ԫ��������βnull
		//���������IllegalArgument����size
		if(index<0 || index>=size) {
			throw new IllegalArgumentException("setʧ�ܣ�index�Ƿ�");
		}
		Node cur=dummyhead.next;
		for(int i=0;i<index;i++) {
			cur=cur.next;
		}
		cur.value=value;
	}
	
	//�����������Ƿ���Ԫ��value
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
	
	//ɾ������ĵ�index��0-based����Ԫ��
	//��������������������ã�������ϰ����Ϊ������һ������index�������
	public E remove(int index) {
		//����ɾ������βnullӦ��û�����壬����sizeҲ�ǷǷ�Argument
		if(index<0 || index>size) {
			throw new IllegalArgumentException("removeʧ�ܣ�index�Ƿ�");
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
		//cur�Ǳ��������е�ǰ��Node�����ã�����ȻҪ��head��ʼ����
		Node cur=dummyhead.next;
		res.append("head: ");
		//��curָ��null������û����һ��Ԫ�أ�������ֹ
		while(cur!=null) {
			//Ҳ������forѭ��������forѭ�����ӽ���
			//for(Node cur=dummyhead.next; cur != null; cur=cur.next)
			//ÿ��Node�ڲ�������ָ��value��next������ָ��
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
