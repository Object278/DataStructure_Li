package com.imooc.linkedlist;

public class RemoveAllElemInLL1 {
	// �������ֻ��ʵ���Ƴ�����������Ԫ�صķ�������������ͷ�ڵ�
	// ���ȶ�������Node
	private class ListNode {
		int val;
		ListNode next;

		public ListNode(int x) {
			val = x;
		}

		// ������캯����Ϊ�˷�����ԣ��������鹹������ĺ�������ListNode����Է������κ�λ����������������
		public ListNode(int[] arr) {
			if (arr == null || arr.length == 0) {
				throw new IllegalArgumentException("����Ϊ�գ��޷���������");
			}
			// this�������������캯���Ķ�������һ�������ͷ�ڵ�
			this.val = arr[0];
			ListNode cur = this;
			// Java�������������Ԫ�ؼ���������ֵҲ������������
			// head��ʵ��������ĵ�һ��Ԫ�أ��洢����ָ���Ӧ�Ķ���/��ֵ������
			// head.next��ָ����һ������/��ֵ�����û��߳�Ϊ��һ������Ԫ�ص�value��������
			for (int i = 1; i < arr.length; i++) {
				cur.next = new ListNode(arr[i]);
				cur = cur.next;
			}
		}

		// �Ե�ǰ�ڵ�Ϊͷ�����������Ϣ
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
		// ����������ͷ�ڵ�Ļ�����Ҫ��head���⴦��
		ListNode dummyhead = new ListNode(-1);
		dummyhead.next = head;
		ListNode prev = dummyhead;
		// ����ͷ�ڵ���Լ��߼�
		while (prev.next != null) {
			if (prev.next.val == val) {
				ListNode delNode = prev.next;
				prev.next = delNode.next;
				delNode.next = null;
			} else {
				// ��if��ɾ����һ��֮�������һ���ڵ㻹Ҫɾ��������prev�����ƶ�����һ���ڵ�
				prev = prev.next;
			}
		}
		// ɾ������֮���ɾ�����ͷ�ڵ����ȥ���㶨λ����һ����ԭ����head
		return dummyhead.next;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 6, 3, 4, 5, 6 };
		//��������ΪListNode��private�ű�������д
		RemoveAllElemInLL1 a=new RemoveAllElemInLL1();
		ListNode head =a.new ListNode(nums);
		System.out.println(head);
		ListNode res = (new RemoveAllElemInLL1()).removeAllElements(head, 6);
		System.out.println(res);
	}

}
