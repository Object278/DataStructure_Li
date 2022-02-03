package com.imooc.queue;

import java.util.Random;

import com.imooc.linkedlist.LinkedListQueue;

public class SpeedTest {
	//测试使用q运行opCount个enqueue和dequeue个操作的时间，单位：秒
	private static double testQueue(Queue<Integer> q, int opCount) {
		long sTime=System.nanoTime();//返回纳秒
		Random random=new Random();
		for(int i=0;i<opCount;i++) {
			q.enqueue(random.nextInt(Integer.MAX_VALUE));
		}
		for(int i=0;i<opCount;i++) {
			q.dequeue();
		}
		long eTime=System.nanoTime();
		return (eTime-sTime)/1000000000.0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opCount=100000;
		ArrayQueue<Integer> arrayqueue=new ArrayQueue<>();
		double time1=testQueue(arrayqueue, opCount);
		//对于ArrayQueue来说testQueue是O（n hoch 2）复杂度		
		System.out.println("ArrayQueue  Time:"+time1+" s");
		LoopQueue<Integer> loopqueue=new LoopQueue<>();
		double time2=testQueue(loopqueue, opCount);
		//对于LoopQueue来说testQueue是O（n）复杂度，只和opCount大小有关	
		System.out.println("LoopQueue  Time:"+time2+" s");
		//如果两个算法复杂度在一个级别，运行时间还是可能有差别，因为JVM层面上会自动做一些优化
		LinkedListQueue<Integer> llq=new LinkedListQueue<>();
		double time3=testQueue(llq, opCount);
		System.out.println("LinkedListQueue  Time:"+time3+" s");
	}

}
