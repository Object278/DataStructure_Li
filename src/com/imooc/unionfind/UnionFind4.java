package com.imooc.unionfind;

public class UnionFind4 implements UF{
	//����rank��������ȣ����Ż���Ӧ��������ȵ͵���������ȸߵ�����Ϊ����
	//��size��һ���ܹ���׼ȷ���ж��������
	
	private int[] parent;
	private int[] rank; //rank[i]�д洢������iΪ���ļ��ϵ��������
	
	public UnionFind4(int size) {
		parent=new int[size];
		rank=new int[size];
		for(int i=0; i<size; i++) {
			parent[i]=i;
			//��ʼ״���ǣ�ÿһ��Ԫ���Լ������ĸ߶�Ϊ1
			rank[i]=1;
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
		if(rank[pRoot] < rank[qRoot]) {
			//��ȵ͵����ĸ��ڵ�ָ����ȸߵ����ĸ��ڵ�
			parent[pRoot]=qRoot;
			//�����ϲ��Ժ�����������Ȼ����ȱȽ����������ȣ���Ϊ�²���������������ȱȽ������һ����
			//��������������ӵĲ�����ô��
		}else if(rank[qRoot] < rank[pRoot]){
			parent[qRoot]=pRoot;
		}else {
			//�����ͬ��ʱ�򣬿������ָ���������Ҫ��һ
			parent[qRoot] = pRoot;
			rank[pRoot] += 1;
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
