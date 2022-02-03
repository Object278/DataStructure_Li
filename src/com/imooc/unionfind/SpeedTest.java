package com.imooc.unionfind;

import java.util.Random;

public class SpeedTest {
	
	private static double testUF(UF uf, int m) {
		int size=uf.getSize();
		Random random=new Random();
		long startTime=System.nanoTime();
		//先进行m次合并
		for(int i=0; i<m; i++) {
			int a=random.nextInt(size);
			int b=random.nextInt(size);
			uf.unionElements(a, b);
		}
		//再进行m次查询
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
		//当执行操作数量接近甚至超过UnionFind的size的话，那么uf2会比较慢
		//因为uf1是在数组中遍历，这个过程JVM的优化很好；但是uf2需要在不同索引中跳转
		//并且操作数提高后更多的元素被同意在uf2的一个树里，导致uf2的树的深度会比较大
		//并且目前的uf2是不判断被合并两个节点的树的形状的，可能导致深度提升较快。应该让节点少的树的根节点指向节点多的树的根节点
		int m=10000000;
		//System.out.println("UnionFind1 starts");
		//System.out.println("UnionFind1 ends Time:"+testUF(uf1, m)+" s");
		//System.out.println("UnionFind2 starts");
		//System.out.println("UnionFind2 ends Time:"+testUF(uf2, m)+" s");
		//UnionFind3的深度非常浅，所以速度非常快
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
