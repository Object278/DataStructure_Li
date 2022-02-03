package com.imooc.recurrence;

public class ArraySum {
	public static int sum(int[] arr) {
		return sum(arr, 0, arr.length-1)+sum(arr, 0);
	}
	//����arr[left����������n-1]�ĺ���������������ĵݹ����
	//�ݹ���̶��û����Σ��û����ô�����left�������
	private static int sum(int[] arr, int left) {
		//�����鱻��СΪ�յ�ʱ�򣬺�һ��Ϊ0���ݹ���ֹ
		if(left==arr.length) {
			return 0;
		}
		return arr[left]+sum(arr, left+1);
	}
	//����ÿ����һ������ĺ�
	/**
	 * ÿ�ν�����ֳ�����ݹ����
	 * @param arr ����
	 * @param left �����������ĵ�һ��Ԫ��
	 * @param right �����������������һ��Ԫ��
	 * @return ����/������֮��
	 */
	private static int sum(int[] arr, int left, int right) {
		if(left==right) {
			return arr[right];
		}
		int length=right-left+1;
		if(length%2==1) {
			//(length-1)/2�����µ�����������ҵ����Ӧ�ĸ����������ֵ
			//(length+1)/2�����µ����������������Ӧ�ĸ����������ֵ
			return sum(arr, left, (length-1)/2+left)+sum(arr, (length+1)/2+left , right);
		}else {
			//����Java��int����֮������Ľ�����Զ�ȡ�������֣�����lengthΪż�������������lengthΪ�������������
			//length/2-1�����µ�����������ҵ����Ӧ�ĸ����������ֵ
			//length/2�����µ����������������Ӧ�ĸ����������ֵ
			return sum(arr, left, length/2-1+left)+sum(arr ,length/2+left, right);
		}
	}
	public static void main(String[] args) {
		int[] sum= {1,2,3,4,5,6,7,8,9};
		//2nһ����ż��
		System.out.println(sum(sum)/2);
	}

}
