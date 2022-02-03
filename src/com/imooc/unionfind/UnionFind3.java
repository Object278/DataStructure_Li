package com.imooc.unionfind;

public class UnionFind3 implements UF {
	//基于size的优化
	private int[] parent;
	private int[] sz; //sz[i]中存储的是以i为根的集合中的元素个数
	
	public UnionFind3(int size) {
		parent=new int[size];
		sz=new int[size];
		for(int i=0; i<size; i++) {
			parent[i]=i;
			//初始状况是，每一个元素自己的集合里只有自己一个元素
			sz[i]=1;
		}
	}
	
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	@Override
	public void unionElements(int p, int q) {
		int pRoot=find(p);
		int qRoot=find(q);
		if(pRoot == qRoot) {
			return;
		}
		//如果两个元素不是一个集合的话，把它们的根节点合并
		if(sz[pRoot] < sz[qRoot]) {
			//让元素比较少的树的根节点指向元素数量比较多的鼠的根节点
			parent[pRoot]=qRoot;
			//以qRoot为根的树中的节点数增加的数量为以pRoot为根的树中的节点数
			sz[qRoot] += sz[pRoot];
			//这样树的深度增加的不会那么快
		}else {
			//大于等于的情况
			parent[qRoot]=pRoot;
			sz[pRoot]+=sz[qRoot];
		}
		
	}
	
	private int find(int index) {
		if(index < 0 || index > parent.length) {
			throw new IllegalArgumentException("index is illegal");
		}
		while(index != parent[index]) {
			index=parent[index];
		}
		return index;
	}

	@Override
	public int getSize() {
		return parent.length;
	}
	public static void main(String[] args) {
		

	}

}
