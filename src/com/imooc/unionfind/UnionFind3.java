package com.imooc.unionfind;

public class UnionFind3 implements UF {
	//����size���Ż�
	private int[] parent;
	private int[] sz; //sz[i]�д洢������iΪ���ļ����е�Ԫ�ظ���
	
	public UnionFind3(int size) {
		parent=new int[size];
		sz=new int[size];
		for(int i=0; i<size; i++) {
			parent[i]=i;
			//��ʼ״���ǣ�ÿһ��Ԫ���Լ��ļ�����ֻ���Լ�һ��Ԫ��
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
		//�������Ԫ�ز���һ�����ϵĻ��������ǵĸ��ڵ�ϲ�
		if(sz[pRoot] < sz[qRoot]) {
			//��Ԫ�رȽ��ٵ����ĸ��ڵ�ָ��Ԫ�������Ƚ϶����ĸ��ڵ�
			parent[pRoot]=qRoot;
			//��qRootΪ�������еĽڵ������ӵ�����Ϊ��pRootΪ�������еĽڵ���
			sz[qRoot] += sz[pRoot];
			//��������������ӵĲ�����ô��
		}else {
			//���ڵ��ڵ����
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
