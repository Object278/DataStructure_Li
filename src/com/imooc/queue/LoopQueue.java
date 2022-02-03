package com.imooc.queue;

public class LoopQueue<E> implements Queue<E> {
	//循环队列
	private E[] data;
	private int front, tail, size;
	//size可以被front于tail计算出来

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
			//循环队列中始终浪费一个空间，所以这里调用getCapacity（）方法而不是直接data。length*2
			this.resize(this.getCapacity()*2);
		}
		data[tail]=e;
		size++;
		tail=(tail+1)%data.length;
	}

	public void resize(int newCapacity) {
		E[] newData=(E[])new Object[newCapacity+1];
		//将data中的所有元素转移到newData，只不过front位元素放在newData第0位，tail位元素放在newData第size-1位，这里size是data中的元素数
		for(int i=0;i<size;i++) {
			newData[i]=data[(i+front)%data.length];
			//data中的元素整体相对于newData有一个front大小的偏移，取余是为了保证循环到数组前部的元素也能被正常加入newData
		}
		data=newData;
		front=0;
		tail=size;
		
	}
	
	@Override
	public E dequeue() {
		if(this.isEmpty()) {
			throw new IllegalArgumentException("无法执行元素出队操作，队列为空");
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
			throw new IllegalArgumentException("无法执行元素出队操作，队列为空");
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
			//一旦i等于tail，代表数组内所有元素都已经遍历了，同时i必须循环的+1
			res.append(data[i]);
			//如果当前索引的下一个不是tail，那么当前索引的元素就不是最后一个元素
			//另外一种遍历循环队列的方式在resize方法里使用了，两种方法可以互换
		    if((i+1)%data.length != tail) {
			    res.append(" , ");
		    }
		}
		res.append("] tail");
		return res.toString();
	}

}
