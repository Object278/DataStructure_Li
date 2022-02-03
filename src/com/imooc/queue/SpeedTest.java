package com.imooc.queue;

import java.util.Random;

import com.imooc.linkedlist.LinkedListQueue;

public class SpeedTest {
	//����ʹ��q����opCount��enqueue��dequeue��������ʱ�䣬��λ����
	private static double testQueue(Queue<Integer> q, int opCount) {
		long sTime=System.nanoTime();//��������
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
		//����ArrayQueue��˵testQueue��O��n hoch 2�����Ӷ�		
		System.out.println("ArrayQueue  Time:"+time1+" s");
		LoopQueue<Integer> loopqueue=new LoopQueue<>();
		double time2=testQueue(loopqueue, opCount);
		//����LoopQueue��˵testQueue��O��n�����Ӷȣ�ֻ��opCount��С�й�	
		System.out.println("LoopQueue  Time:"+time2+" s");
		//��������㷨���Ӷ���һ����������ʱ�仹�ǿ����в����ΪJVM�����ϻ��Զ���һЩ�Ż�
		LinkedListQueue<Integer> llq=new LinkedListQueue<>();
		double time3=testQueue(llq, opCount);
		System.out.println("LinkedListQueue  Time:"+time3+" s");
	}

}
