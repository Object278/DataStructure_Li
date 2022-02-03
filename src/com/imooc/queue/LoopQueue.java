package com.imooc.queue;

public class LoopQueue<E> implements Queue<E> {
	//ѭ������
	private E[] data;
	private int front, tail, size;
	//size���Ա�front��tail�������

	public static void main(String[] args) {
		LoopQueue<Integer> queue=new LoopQueue<>();
		for(int i=0;i<20;i++) {
			queue.enqueue(i);
			System.out.println(queue);
			if(i%3==2) {
				queue.dequeue();
				System.out.println(queue);
			}
		}

	}

	public LoopQueue(int capacity) {
		data=((E[])new Object[capacity+1]);
		front=0;
		tail=0;
		size=0;
	}
	
	public LoopQueue() {
		data=((E[])new Object[11]);
		front=0;
		tail=0;
		size=0;
	}
	
	public int getCapacity() {
		return data.length-1;
	}
	
	@Override
	public void enqueue(E e) {
		if((tail+1)%data.length==front) {
			//ѭ��������ʼ���˷�һ���ռ䣬�����������getCapacity��������������ֱ��data��length*2
			this.resize(this.getCapacity()*2);
		}
		data[tail]=e;
		size++;
		tail=(tail+1)%data.length;
	}

	public void resize(int newCapacity) {
		E[] newData=(E[])new Object[newCapacity+1];
		//��data�е�����Ԫ��ת�Ƶ�newData��ֻ����frontλԪ�ط���newData��0λ��tailλԪ�ط���newData��size-1λ������size��data�е�Ԫ����
		for(int i=0;i<size;i++) {
			newData[i]=data[(i+front)%data.length];
			//data�е�Ԫ�����������newData��һ��front��С��ƫ�ƣ�ȡ����Ϊ�˱�֤ѭ��������ǰ����Ԫ��Ҳ�ܱ���������newData
		}
		data=newData;
		front=0;
		tail=size;
		
	}
	
	@Override
	public E dequeue() {
		if(this.isEmpty()) {
			throw new IllegalArgumentException("�޷�ִ��Ԫ�س��Ӳ���������Ϊ��");
		}
		E ret=data[front];
		data[front]=null;
		size--;
		front=(front+1)%data.length;
		if(size==this.getCapacity()/4 && this.getCapacity()/2 != 0) {
			this.resize(this.getCapacity()/2);
		}
		return ret;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public E getFront() {
		if(this.isEmpty()) {
			throw new IllegalArgumentException("�޷�ִ��Ԫ�س��Ӳ���������Ϊ��");
		}
		return data[front];
	}

	@Override
	public boolean isEmpty() {
		return front==tail;
	}

	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Queue:  size:  "+size+"  Capacity:"+this.getCapacity()+"  front:  "+front+"  tail:  "+tail+"  length:  "+data.length+"  (tail-front)%data.length:  "+(tail-front)%data.length);
		res.append("  front [");
		for(int i=front;i!=tail;i=(i+1)%data.length) {
			//һ��i����tail����������������Ԫ�ض��Ѿ������ˣ�ͬʱi����ѭ����+1
			res.append(data[i]);
			//�����ǰ��������һ������tail����ô��ǰ������Ԫ�ؾͲ������һ��Ԫ��
			//����һ�ֱ���ѭ�����еķ�ʽ��resize������ʹ���ˣ����ַ������Ի���
		    if((i+1)%data.length != tail) {
			    res.append(" , ");
		    }
		}
		res.append("] tail");
		return res.toString();
	}

}
