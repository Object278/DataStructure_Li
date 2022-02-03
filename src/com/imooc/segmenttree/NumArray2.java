package com.imooc.segmenttree;

public class NumArray2 {
//��ʹ���߶�����ֻ��ʹ��һ��Ԥ����sum���������������i��j��Ԫ�صĺ�
//sum[i]�洢ǰi��Ԫ�صĺͣ�[0...i-1]��Ԫ�صĺͣ���������i��j�ĺ;���sum[j]-sum[i]��sum[0]=0��
	
	private int[] sum;
	
	public NumArray2(int[] nums) {
		sum=new int[nums.length+1];
		sum[0]=0;
		for(int i=1; i<sum.length; i++) {
			sum[i]=sum[i-1]+nums[i-1];
		}
	}
	
	public int sumRange(int i, int j) {
		//ע��sum��nums��Index��1�Ĳ��
		return sum[j+1]-sum[i];
	}
	
	public static void main(String[] args) {

	}

}
