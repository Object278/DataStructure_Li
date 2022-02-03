package com.imooc.segmenttree;

public class SegmentTree<E> {
	//data���û������������ݵı��ݣ�tree������õ��߶���
	private E[] data;
	private E[] tree;
	private Merger<E> merger;
	//�����merger����Ʒ�����Comparator����Ʒ�ʽ������
	public SegmentTree(E[] arr, Merger merger) {
		this.merger=merger;
		data=(E[])new Object[arr.length];
		for(int i=0; i<arr.length; i++) {
			data[i]=arr[i];
		}
		//����������Ҫ4n�Ŀռ�
		tree=(E[])new Object[4*arr.length];
		buildSegmentTree(0, 0, data.length-1);
	}
	
	//����treeIndexλ��һ���߶�������ʾ[left......right]�Ŀռ�
	private void buildSegmentTree(int treeIndex, int left, int right) {
		if(left == right) {
			tree[treeIndex]=data[right];
			return;
		}
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		//����һ�е�д����Ϊ�˷�ֹ����������ӵ������
		int mid=left+(right-left)/2;
		//֮��Ҫ�����������ȴ�����ǰ�ڵ����������
		buildSegmentTree(leftTreeIndex, left, mid);
		buildSegmentTree(rightTreeIndex, mid+1, right);
		//�����Ǻϲ����������߶�������Ϣ����κϲ���ҵ���߼��й�
		//����һ��Merger�ں����ӿڣ������û��Զ�����κϲ�
		tree[treeIndex]=merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
	}
	
	//��������[queryL......queryR]�����ڵ�ֵ
	public E query(int queryL, int queryR) {
		if(queryL < 0 || queryL > data.length || queryR < 0 || queryR > data.length || queryL > queryR) {
			throw new IllegalArgumentException("Index is illegal");
		}
		return query(0, 0, data.length-1, queryL, queryR);
	}
	
	//��treeIndexΪ�����߶���������[queryL......queryR]�����ֵ
	//Ҳ���԰�װһ���߶�����Node��
	private E query(int treeIndex, int left, int right, int queryL, int queryR) {
		//���������������ǵ�ǰ����
		//����߼�������һ��else��Ĵ��η�������������һ���߶�����Ҷ�ӽڵ���
		if(left == queryL && right == queryR) {
			return tree[treeIndex];
		}
		int mid=left+(right-left)/2;
		//�����������ǵ�ǰ�ڵ������������tree�������λ�ã������������Ʒ�Χ
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		//�������������͵�ǰ�ڵ��������һ���ϵ��û��
		//ֱ��ȥ�����������
		if(queryL >= mid+1) {
			return query(rightTreeIndex, mid+1, right, queryL, queryR);
		}else if(queryR <= mid) {
			//��������������������һ���ϵ��û��
			return query(leftTreeIndex, left, mid, queryL, queryR);
		}else {
			//��������ҽڵ�Ȳ�ȫ�������ӽڵ�Ҳ����ȫ�����ӽڵ㣬��ô��һ����Ҫ���
			E leftResult=query(leftTreeIndex, left, mid, queryL, mid);
			E rightResult=query(rightTreeIndex, mid+1, right, mid+1, queryR);
			//����֮���ֵ��Ҫ�ں�
			return merger.merge(leftResult, rightResult);
		}
	}
	
	//��indexλ�õ�ֵ����val
	public void set(int index, E val) {
		if(index < 0 || index > data.length) {
			throw new IllegalArgumentException("Index is illegal");
		}
		data[index]=val;
		set(0, 0, data.length-1, index, val);
	}
	
	private void set(int treeIndex, int left, int right, int index, E val) {
		if(left == right) {
			//��������¾��Ѿ��ҵ���Ҫ�޸ĵ�ֵ��
			tree[treeIndex]=val;
			return;
		}
		int mid=left+(right-left);
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		if(index >= mid+1) {
			set(rightTreeIndex, mid+1, right, index, val);
		}else {
			set(leftTreeIndex, left, mid, index, val);
		}
		//�����к��������˼·
		//�޸���֮�������ںϣ���֤���׽ڵ��ֵҲ���޸���
		tree[treeIndex]=merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
		return;
	}
	
	public E get(int index) {
		if(index<0 || index>data.length) {
			throw new IllegalArgumentException("Index illegal");
		}
		return data[index];
	}
	
	public int getSize() {
		return data.length;
	}
	
	private int leftChild(int index) {
		return 2*index+1;
	}
	
	private int rightChild(int index) {
		return 2*index+2;
	}
	
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append('[');
		for(int i=0; i<tree.length; i++) {
			if(tree[i] != null) {
				res.append(tree[i]);
			}else {
				res.append("null");
			}
			if(i != tree.length) {
				res.append(", ");
			}
		}
		res.append(']');
		return res.toString();
	}
	
	public static void main(String[] args) {
		 Integer[] nums= {-2, 0, 3, -5, 2, -1};
		 SegmentTree<Integer> segTree=new SegmentTree(nums, new Merger<Integer>(){
			@Override
			public Integer merge(Integer o1, Integer o2) {
				return o1>o2 ? o1 : o2;
			}	 
		 });
		 //lambda���ʽ��������
		 //SegmentTree<Integer> segTree1=new SegmentTree(nums, (o1, o2) -> o1+o2);
		 //System.out.println(segTree);
		 System.out.println(segTree.query(0, 2));
	}

}
