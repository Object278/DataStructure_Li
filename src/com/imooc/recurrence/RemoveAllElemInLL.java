package com.imooc.recurrence;;

public class RemoveAllElemInLL {
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
	//使用递归的代码非常优雅，当然三目运算符也功不可没
	public ListNode removeAllElements(ListNode head, int val) {
		//这里可以理解为用某一个方法删除了head之后的链表里的所有val
		//所以只要再检查一下head的val是不是val就好了
//		ListNode res=this.removeAllElements(head.next, val);
//		if(head.val == val) {
//			//如果head.val == val，那么抛弃head节点，返回删除之后的子链表
//			return res;
//		}else {
//			//head的val不需要删除，把它连在子链表上就好了
//			head.next=res;
//			//链表给出一个头就相当于得到了整个链表
//			return head;
//		}
		//下面是用三目运算符的写法
		if(head==null) {
			//空链表删除完所有value之后还是空
			return null;
		}
		head.next=this.removeAllElements(head.next, val);
		return head.val == val ? head.next : head;
		//这个函数被删除的结点还会挂在链表上吗，虽然用户读不到
	}
	
	public static void main(String[] args) {
		int[] nums = { 1, 2, 6, 3, 4, 5, 6 };
		//可能是因为ListNode是private才必须这样写
		RemoveAllElemInLL a=new RemoveAllElemInLL();
		ListNode head =a.new ListNode(nums);
		System.out.println(head);
		ListNode res = (new RemoveAllElemInLL()).removeAllElements(head, 6);
		System.out.println(res);
	}

}
