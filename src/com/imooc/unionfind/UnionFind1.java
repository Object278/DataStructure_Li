package com.imooc.unionfind;

public class UnionFind1 implements UF {
	
	private int[] id;
	
	public UnionFind1(int size) {
		id=new int[size];
		 for(int i=0; i<size; i++) {
			 id[i]=i;
			 //初始的时候每一个元素所属集合的编号都不一样
		 }
	}
	
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	private int find(int index) {
		if(index < 0 || index > id.length) {
			throw new IllegalArgumentException("index is illegal");
		}
		return id[index];
	}

	@Override
	public void unionElements(int p, int q) {
		int pID=find(p);
		int qID=find(q);
		if(pID == qID) {
			return;
		}
		for(int i=0; i<id.length; i++) {
			if(id[i] == pID) {
				id[i] = qID;
			}
		}
	}

	@Override
	public int getSize() {
		return id.length;
	}

	public static void main(String[] args) {
		

	}

}
