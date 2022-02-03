package com.imooc.linkedlist;

public class RemoveAllElemInLL1 {
	// 这个类里只是实现移除链表中所有元素的方法，用了虚拟头节点
	// 首先定义该类的Node
	private class ListNode {
		int val;
		ListNode next;

		public ListNode(int x) {
			val = x;
		}

		// 这个构造函数是为了方便测试，把以数组构造链表的函数放在ListNode里可以方便在任何位置用数组扩充链表
		public ListNode(int[] arr) {
			if (arr == null || arr.length == 0) {
				throw new IllegalArgumentException("数组为空，无法构建链表");
			}
			// this代表调用这个构造函数的对象，是这一段链表的头节点
			this.val = arr[0];
			ListNode cur = this;
			// Java里好像数组的这个元素即代表它的值也代表它的引用
			// head其实就是链表的第一个元素，存储的是指向对应的对象/数值的引用
			// head.next是指向“下一个对象/数值的引用或者称为下一个链表元素的value”的引用
			for (int i = 1; i < arr.length; i++) {
				cur.next = new ListNode(arr[i]);
				cur = cur.next;
			}
		}

		// 以当前节点为头结点的链表的信息
		@Override
		public String toString() {
			StringBuilder ret = new StringBuilder();
			ListNode cur = this;
			ret.append("head:  ");
			while (cur != null) {
				ret.append(cur.val + "-->");
				cur = cur.next;
			}
			ret.append("null");
			return ret.toString();
		}

}

	public ListNode removeAllElements(ListNode head, int val) {
		// 不适用虚拟头节点的话，需要对head特殊处理
		ListNode dummyhead = new ListNode(-1);
		dummyhead.next = head;
		ListNode prev = dummyhead;
		// 虚拟头节点可以简化逻辑
		while (prev.next != null) {
			if (prev.next.val == val) {
				ListNode delNode = prev.next;
				prev.next = delNode.next;
				delNode.next = null;
			} else {
				// 在if中删除完一个之后可能下一个节点还要删除，所以prev不能移动到下一个节点
				prev = prev.next;
			}
		}
		// 删除结束之后把删除后的头节点给回去方便定位，不一定是原来的head
		return dummyhead.next;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 6, 3, 4, 5, 6 };
		//可能是因为ListNode是private才必须这样写
		RemoveAllElemInLL1 a=new RemoveAllElemInLL1();
		ListNode head =a.new ListNode(nums);
		System.out.println(head);
		ListNode res = (new RemoveAllElemInLL1()).removeAllElements(head, 6);
		System.out.println(res);
	}

}
