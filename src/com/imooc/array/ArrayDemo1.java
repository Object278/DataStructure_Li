package com.imooc.array;

public class ArrayDemo1<E> {
	private E[] data;
	private int size;

	// 构造函数，传入数组的容量
	public ArrayDemo1(int capacity) {
		data =(E[])new Object[capacity];
		size = 0;
	}

	// 无参构造方法
	public ArrayDemo1() {
		this(10);
		// 这里用this调用了本类的含参数构造方法
		// 如同super()是调用父类的无参数构造方法一样
	}

	// 用静态数组构造一个动态数组
	public ArrayDemo1(int[] arr) {
		data =(E[])new Object[arr.length];
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void addLast(E e) {
		// data[size++]也可以，因为++在size的后面
		//data[size] = e;
		//size++;
		this.add(size, e);
	}

	public void addFirst(E e) {
		add(0, e);
	}

	// 在数组的index位置插入新元素e
	public void add(int index, E e) {
		// 如果index大于size，则数组内部元素不是紧密排列，这是不被希望的
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Require index >=0 or <=size");
		}
		if (size == data.length) {
			//这里使用一个指数函数来进行扩容比线性函数有很大优势
			//Java的Colleations包中的ArrayList类的扩容参数是1.5，每次扩大1.5倍。
			resize(2*data.length);
		}
		// 每一个位置都往后挪一个位置，最后一步把index位的元素挪出来
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = e;
		size++;
	}
	//这里是私有方法，只能由数组内部逻辑进行
	private void resize(int newCapacity) {
		E[] newData=(E[])new Object[newCapacity];
		for(int i=0;i<size;i++) {
			newData[i]=data[i];
		}
		data=newData;
		//size不需要维护，capacity都是用data.length属性替代，也不需要维护
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(String.format("Array size=%d, capacity=%d", size, data.length));
		res.append("[");
		for (int i = 0; i < size; i++) {
			res.append(data[i]);
			if ((i + 1) != size) {
				res.append(" , ");
			}
		}
		res.append("]");
		return res.toString();
	}

	// 获取index位置的元素，用户没有办法访问没有写入的元素
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Get ist failed, Illegal Index Value");
		}
		return data[index];
	}
	
	public E getLast() {
		return get(size-1);
	}
	
	public E getFirst() {
		return get(0);
	}

	public void set(int index, E e) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Set ist failed, Illegal Index Value");
		}
		data[index] = e;
	}

	// 查找数组中是否有特定元素
	public boolean contains(E e) {
		for (int i = 0; i < size; i++) {
			//两个类对象之间的值比较用equals方法
			//==是两个类对象之间的引用比较
			if (data[i].equals(e)) {
				return true;
			}
		}
		return false;
	}

	// 查找数组中的特定元素并且返回index值，如果没有找到返回-1
	public int find(E e) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	// 找到数组中所有的元素e的index，如果数组中没有这个元素返回一个长度为0的数组
	public ArrayDemo1<Integer> findAll(E e) {
		ArrayDemo1<Integer> ret=new ArrayDemo1<Integer>();
		for(int i=0;i<size;i++) {
			if(data[i].equals(e)) {
				ret.addLast(i);
			}
		}
		return ret;
	}

	// 删除某一index的元素并且将后续元素进行位移，返回被删除的元素
	public E remove(int index) {
		E m = null;
		// get方法中判断index的合法性，保证空数组不会执行删除操作
		m = this.get(index);
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		//这里size还指向一个E对象的引用，所以这个引用不会被回收
		//这个对象被称为loitering objects，它还在程序里闲逛，不能被回收，但也没有用了。这一现象不等于Memory Leak内存泄漏
		data[size]=null;
		//写上这一句话就把这个引用的空间释放了
		if(size==data.length/4 && data.length/2!=0) {
			//当length为1的时候一缩容就变成0了，但是长度为0的数组没有意义
			this.resize(data.length/2);
		}
		return m;
	}

	public E removeFirst() {
		return this.remove(0);
	}

	public E removeLast() {
		// 直接size--之后用户就访问不到最后一个值了，不一定要用0覆盖最后一个值
		return this.remove(size - 1);
	}

	// 从数组中删除元素e，返回是否删除
	// 由于当前数组可以存放重复元素，所以删除第一个元素e之后不能保证不再存在这个e了
	public boolean removeElement(E e) {
		int index = this.find(e);
		if (index != -1) {
			remove(index);
			return true;
		}
		return false;
	}

	public boolean removeAllElement(E e) {
		ArrayDemo1<Integer> arr=this.findAll(e);
		if(arr.size==0) {
			return false;
		}else {
			//这里一定要反过来删除，不然删除了前面的元素后面的元素的index就变化了
		    for(int i=arr.size;i>0;i--) {
			    this.remove(arr.get(i-1));
			    //remove方法里已经有size--了
		    }
		    return true;
		}
	}

	public E[] getData() {
		return data;
	}

	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		ArrayDemo1<Integer> arr=new ArrayDemo1<Integer>(10);
		for(int i=0;i<10;i++) {
			arr.addLast(i);
		}
		arr.addLast(1);
		System.out.println(arr);
		ArrayDemo1<Integer> all1=arr.findAll(1);
		System.out.println(all1);
		//removeAll也可以正常运行啦
		System.out.println(arr.removeAllElement(1));
		arr.addLast(100);
		System.out.println(arr);
		for(int i=0;i<6;i++) {
			arr.removeLast();
			System.out.println(arr);
		}
		
		
	}

}
