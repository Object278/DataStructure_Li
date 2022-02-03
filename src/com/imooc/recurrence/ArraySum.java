package com.imooc.recurrence;

public class ArraySum {
	public static int sum(int[] arr) {
		return sum(arr, 0, arr.length-1)+sum(arr, 0);
	}
	//计算arr[left。。。。。n-1]的和这个函数是真正的递归过程
	//递归过程对用户屏蔽，用户不用传进来left这个参数
	private static int sum(int[] arr, int left) {
		//当数组被缩小为空的时候，和一定为0，递归终止
		if(left==arr.length) {
			return 0;
		}
		return arr[left]+sum(arr, left+1);
	}
	//或者每次求一半数组的和
	/**
	 * 每次将数组分成两半递归求和
	 * @param arr 数组
	 * @param left 数组或子数组的第一个元素
	 * @param right 数组或者子数组的最后一个元素
	 * @return 数组/子数组之和
	 */
	private static int sum(int[] arr, int left, int right) {
		if(left==right) {
			return arr[right];
		}
		int length=right-left+1;
		if(length%2==1) {
			//(length-1)/2描述新的左子数组的右点减对应的父数组的左点的值
			//(length+1)/2描述新的右子数组的左点减对应的父数组的左点的值
			return sum(arr, left, (length-1)/2+left)+sum(arr, (length+1)/2+left , right);
		}else {
			//由于Java的int类型之间相除的结果会自动取整数部分，所以length为偶数的情况可以与length为奇数的情况整合
			//length/2-1描述新的左子数组的右点减对应的父数组的左点的值
			//length/2描述新的右子数组的左点减对应的父数组的左点的值
			return sum(arr, left, length/2-1+left)+sum(arr ,length/2+left, right);
		}
	}
	public static void main(String[] args) {
		int[] sum= {1,2,3,4,5,6,7,8,9};
		//2n一定是偶数
		System.out.println(sum(sum)/2);
	}

}
