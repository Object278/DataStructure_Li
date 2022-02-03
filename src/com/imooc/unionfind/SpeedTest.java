package com.imooc.unionfind;

import java.util.Random;

public class SpeedTest {
	
	private static double testUF(UF uf, int m) {
		int size=uf.getSize();
		Random random=new Random();
		long startTime=System.nanoTime();
		//�Ƚ���m�κϲ�
		for(int i=0; i<m; i++) {
			int a=random.nextInt(size);
			int b=random.nextInt(size);
			uf.unionElements(a, b);
		}
		//�ٽ���m�β�ѯ
		for(int i=0; i<m; i++) {
			int a=random.nextInt(size);
			int b=random.nextInt(size);
			uf.isConnected(a, b);
		}
	    long endTime=System.nanoTime();
	    return (endTime-startTime)/1000000000.0;
	}

	public static void main(String[] args) {
		int size=10000000;
		UnionFind1 uf1=new UnionFind1(size);
		UnionFind2 uf2=new UnionFind2(size);
		UnionFind3 uf3=new UnionFind3(size);
		UnionFind4 uf4=new UnionFind4(size);
		UnionFind5 uf5=new UnionFind5(size);
		UnionFind6 uf6=new UnionFind6(size);
		//��ִ�в��������ӽ���������UnionFind��size�Ļ�����ôuf2��Ƚ���
		//��Ϊuf1���������б������������JVM���Ż��ܺã�����uf2��Ҫ�ڲ�ͬ��������ת
		//���Ҳ�������ߺ�����Ԫ�ر�ͬ����uf2��һ���������uf2��������Ȼ�Ƚϴ�
		//����Ŀǰ��uf2�ǲ��жϱ��ϲ������ڵ��������״�ģ����ܵ�����������Ͽ졣Ӧ���ýڵ��ٵ����ĸ��ڵ�ָ��ڵ������ĸ��ڵ�
		int m=10000000;
		//System.out.println("UnionFind1 starts");
		//System.out.println("UnionFind1 ends Time:"+testUF(uf1, m)+" s");
		//System.out.println("UnionFind2 starts");
		//System.out.println("UnionFind2 ends Time:"+testUF(uf2, m)+" s");
		//UnionFind3����ȷǳ�ǳ�������ٶȷǳ���
		System.out.println("UnionFind3 starts");
		System.out.println("UnionFind3 ends Time:"+testUF(uf3, m)+" s");
		System.out.println("UnionFind4 starts");
		System.out.println("UnionFind4 ends Time:"+testUF(uf4, m)+" s");
		System.out.println("UnionFind5 starts");
		System.out.println("UnionFind5 ends Time:"+testUF(uf5, m)+" s");
		System.out.println("UnionFind6 starts");
		System.out.println("UnionFind6 ends Time:"+testUF(uf6, m)+" s");
	}

}
