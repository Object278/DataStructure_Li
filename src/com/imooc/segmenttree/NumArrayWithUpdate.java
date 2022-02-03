package com.imooc.segmenttree;

public class NumArrayWithUpdate {
	
	private SegmentTree<Integer> segTree;
	
	public NumArrayWithUpdate(int[] nums) {
		//int类型的数组不能直接转换成Integer类型的，需要遍历
		if(nums.length > 0) {
			Integer[] data=new Integer[nums.length];
			for(int i=0; i<data.length; i++) {
				data[i]=nums[i];
			}
			segTree=new SegmentTree(data, new Merger<Integer>() {
				@Override
				public Integer merge(Integer o1, Integer o2) {
					return o1+o2;
				}
			});
		}
	}
	
	public void update(int index, int val) {
		if(segTree == null) {
			throw new IllegalArgumentException("SegmentTree is empty");
		}
		segTree.set(index, val);
	}
	
	public int sumRange(int i, int j) {
		if(segTree == null) {
			throw new IllegalArgumentException("SegmentTree is empty");
		}
		return segTree.query(i, j);
	}
	
	public static void main(String[] args) {

	}

}
