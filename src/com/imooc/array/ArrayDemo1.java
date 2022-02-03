package com.imooc.array;

public class ArrayDemo1<E> {
	private E[] data;
	private int size;

	// ���캯�����������������
	public ArrayDemo1(int capacity) {
		data =(E[])new Object[capacity];
		size = 0;
	}

	// �޲ι��췽��
	public ArrayDemo1() {
		this(10);
		// ������this�����˱���ĺ��������췽��
		// ��ͬsuper()�ǵ��ø�����޲������췽��һ��
	}

	// �þ�̬���鹹��һ����̬����
	public ArrayDemo1(int[] arr) {
		data =(E[])new Object[arr.length];
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void addLast(E e) {
		// data[size++]Ҳ���ԣ���Ϊ++��size�ĺ���
		//data[size] = e;
		//size++;
		this.add(size, e);
	}

	public void addFirst(E e) {
		add(0, e);
	}

	// �������indexλ�ò�����Ԫ��e
	public void add(int index, E e) {
		// ���index����size���������ڲ�Ԫ�ز��ǽ������У����ǲ���ϣ����
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Require index >=0 or <=size");
		}
		if (size == data.length) {
			//����ʹ��һ��ָ���������������ݱ����Ժ����кܴ�����
			//Java��Colleations���е�ArrayList������ݲ�����1.5��ÿ������1.5����
			resize(2*data.length);
		}
		// ÿһ��λ�ö�����Ųһ��λ�ã����һ����indexλ��Ԫ��Ų����
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = e;
		size++;
	}
	//������˽�з�����ֻ���������ڲ��߼�����
	private void resize(int newCapacity) {
		E[] newData=(E[])new Object[newCapacity];
		for(int i=0;i<size;i++) {
			newData[i]=data[i];
		}
		data=newData;
		//size����Ҫά����capacity������data.length���������Ҳ����Ҫά��
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

	// ��ȡindexλ�õ�Ԫ�أ��û�û�а취����û��д���Ԫ��
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

	// �����������Ƿ����ض�Ԫ��
	public boolean contains(E e) {
		for (int i = 0; i < size; i++) {
			//���������֮���ֵ�Ƚ���equals����
			//==�����������֮������ñȽ�
			if (data[i].equals(e)) {
				return true;
			}
		}
		return false;
	}

	// ���������е��ض�Ԫ�ز��ҷ���indexֵ�����û���ҵ�����-1
	public int find(E e) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	// �ҵ����������е�Ԫ��e��index�����������û�����Ԫ�ط���һ������Ϊ0������
	public ArrayDemo1<Integer> findAll(E e) {
		ArrayDemo1<Integer> ret=new ArrayDemo1<Integer>();
		for(int i=0;i<size;i++) {
			if(data[i].equals(e)) {
				ret.addLast(i);
			}
		}
		return ret;
	}

	// ɾ��ĳһindex��Ԫ�ز��ҽ�����Ԫ�ؽ���λ�ƣ����ر�ɾ����Ԫ��
	public E remove(int index) {
		E m = null;
		// get�������ж�index�ĺϷ��ԣ���֤�����鲻��ִ��ɾ������
		m = this.get(index);
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		//����size��ָ��һ��E��������ã�����������ò��ᱻ����
		//������󱻳�Ϊloitering objects�������ڳ������й䣬���ܱ����գ���Ҳû�����ˡ���һ���󲻵���Memory Leak�ڴ�й©
		data[size]=null;
		//д����һ�仰�Ͱ�������õĿռ��ͷ���
		if(size==data.length/4 && data.length/2!=0) {
			//��lengthΪ1��ʱ��һ���ݾͱ��0�ˣ����ǳ���Ϊ0������û������
			this.resize(data.length/2);
		}
		return m;
	}

	public E removeFirst() {
		return this.remove(0);
	}

	public E removeLast() {
		// ֱ��size--֮���û��ͷ��ʲ������һ��ֵ�ˣ���һ��Ҫ��0�������һ��ֵ
		return this.remove(size - 1);
	}

	// ��������ɾ��Ԫ��e�������Ƿ�ɾ��
	// ���ڵ�ǰ������Դ���ظ�Ԫ�أ�����ɾ����һ��Ԫ��e֮���ܱ�֤���ٴ������e��
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
			//����һ��Ҫ������ɾ������Ȼɾ����ǰ���Ԫ�غ����Ԫ�ص�index�ͱ仯��
		    for(int i=arr.size;i>0;i--) {
			    this.remove(arr.get(i-1));
			    //remove�������Ѿ���size--��
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
		//removeAllҲ��������������
		System.out.println(arr.removeAllElement(1));
		arr.addLast(100);
		System.out.println(arr);
		for(int i=0;i<6;i++) {
			arr.removeLast();
			System.out.println(arr);
		}
		
		
	}

}
