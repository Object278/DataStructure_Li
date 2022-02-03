package com.imooc.segmenttree;

public class NumArray2 {
//不使用线段树，只是使用一个预处理sum数组求给定数组里i到j的元素的和
//sum[i]存储前i个元素的和（[0...i-1]的元素的和），这样求i到j的和就是sum[j]-sum[i]，sum[0]=0，
	
	private int[] sum;
	
	public NumArray2(int[] nums) {
		sum=new int[nums.length+1];
		sum[0]=0;
		for(int i=1; i<sum.length; i++) {
			sum[i]=sum[i-1]+nums[i-1];
		}
	}
	
	public int sumRange(int i, int j) {
		//注意sum和nums的Index有1的差别
		return sum[j+1]-sum[i];
	}
	
	public static void main(String[] args) {

	}

}
