package com.imooc.array;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayDemo1 arr=new ArrayDemo1(20);
		for(int i=0;i<10;i++) {
			arr.addLast(i);
		}
		arr.add(1, 100);
		System.out.println(arr);
		arr.addFirst(-1);
		System.out.println(arr);
	}

}
