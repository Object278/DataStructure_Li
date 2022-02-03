package com.imooc.unionfind;

public class UnionFind2 implements UF {

	private int[] parent;
	
	public UnionFind2(int size) {
		parent=new int[size];
		for(int i=0; i<size; i++) {
			parent[i]=i;
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
		parent[pRoot]=parent[qRoot];
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
