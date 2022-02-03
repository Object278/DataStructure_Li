package com.imooc.segmenttree;

public class SegmentTree<E> {
	//data是用户传过来的数据的备份，tree是整理好的线段树
	private E[] data;
	private E[] tree;
	private Merger<E> merger;
	//这里的merger的设计方法与Comparator的设计方式很相似
	public SegmentTree(E[] arr, Merger merger) {
		this.merger=merger;
		data=(E[])new Object[arr.length];
		for(int i=0; i<arr.length; i++) {
			data[i]=arr[i];
		}
		//最差情况下需要4n的空间
		tree=(E[])new Object[4*arr.length];
		buildSegmentTree(0, 0, data.length-1);
	}
	
	//创建treeIndex位的一个线段树，表示[left......right]的空间
	private void buildSegmentTree(int treeIndex, int left, int right) {
		if(left == right) {
			tree[treeIndex]=data[right];
			return;
		}
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		//下面一行的写法是为了防止两个整型相加导致溢出
		int mid=left+(right-left)/2;
		//之后要做的事情是先创建当前节点的左右子树
		buildSegmentTree(leftTreeIndex, left, mid);
		buildSegmentTree(rightTreeIndex, mid+1, right);
		//下面是合并左右两个线段树的信息，如何合并与业务逻辑有关
		//创建一个Merger融合器接口，来让用户自定义如何合并
		tree[treeIndex]=merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
	}
	
	//返回区间[queryL......queryR]区间内的值
	public E query(int queryL, int queryR) {
		if(queryL < 0 || queryL > data.length || queryR < 0 || queryR > data.length || queryL > queryR) {
			throw new IllegalArgumentException("Index is illegal");
		}
		return query(0, 0, data.length-1, queryL, queryR);
	}
	
	//在treeIndex为根的线段树中搜索[queryL......queryR]区间的值
	//也可以包装一个线段树的Node类
	private E query(int treeIndex, int left, int right, int queryL, int queryR) {
		//如果带查找区间就是当前区间
		//这个逻辑结合最后一个else里的传参方法可以锁定在一个线段树的叶子节点上
		if(left == queryL && right == queryR) {
			return tree[treeIndex];
		}
		int mid=left+(right-left)/2;
		//下面两个量是当前节点的左右子树在tree数组里的位置，不是左右限制范围
		int leftTreeIndex=leftChild(treeIndex);
		int rightTreeIndex=rightChild(treeIndex);
		//如果待查找区间和当前节点的左子树一点关系都没有
		//直接去右子树里查找
		if(queryL >= mid+1) {
			return query(rightTreeIndex, mid+1, right, queryL, queryR);
		}else if(queryR <= mid) {
			//如果待查找区间和右子树一点关系都没有
			return query(leftTreeIndex, left, mid, queryL, queryR);
		}else {
			//如果待查找节点既不全部在左子节点也不完全在右子节点，那么它一定需要拆分
			E leftResult=query(leftTreeIndex, left, mid, queryL, mid);
			E rightResult=query(rightTreeIndex, mid+1, right, mid+1, queryR);
			//划分之后的值需要融合
			return merger.merge(leftResult, rightResult);
		}
	}
	
	//将index位置的值换成val
	public void set(int index, E val) {
		if(index < 0 || index > data.length) {
			throw new IllegalArgumentException("Index is illegal");
		}
		data[index]=val;
		set(0, 0, data.length-1, index, val);
	}
	
	private void set(int treeIndex, int left, int right, int index, E val) {
		if(left == right) {
			//这种情况下就已经找到需要修改的值了
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
		//这里有后序遍历的思路
		//修改完之后重新融合，保证父亲节点的值也被修改了
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
		 //lambda表达式好像不能用
		 //SegmentTree<Integer> segTree1=new SegmentTree(nums, (o1, o2) -> o1+o2);
		 //System.out.println(segTree);
		 System.out.println(segTree.query(0, 2));
	}

}
