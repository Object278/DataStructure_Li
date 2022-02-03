package com.imooc.recurrence;;

public class RemoveAllElemInLL {
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
	//ʹ�õݹ�Ĵ���ǳ����ţ���Ȼ��Ŀ�����Ҳ������û
	public ListNode removeAllElements(ListNode head, int val) {
		//����������Ϊ��ĳһ������ɾ����head֮��������������val
		//����ֻҪ�ټ��һ��head��val�ǲ���val�ͺ���
//		ListNode res=this.removeAllElements(head.next, val);
//		if(head.val == val) {
//			//���head.val == val����ô����head�ڵ㣬����ɾ��֮���������
//			return res;
//		}else {
//			//head��val����Ҫɾ�������������������Ͼͺ���
//			head.next=res;
//			//�������һ��ͷ���൱�ڵõ�����������
//			return head;
//		}
		//����������Ŀ�������д��
		if(head==null) {
			//������ɾ��������value֮���ǿ�
			return null;
		}
		head.next=this.removeAllElements(head.next, val);
		return head.val == val ? head.next : head;
		//���������ɾ���Ľ�㻹���������������Ȼ�û�������
	}
	
	public static void main(String[] args) {
		int[] nums = { 1, 2, 6, 3, 4, 5, 6 };
		//��������ΪListNode��private�ű�������д
		RemoveAllElemInLL a=new RemoveAllElemInLL();
		ListNode head =a.new ListNode(nums);
		System.out.println(head);
		ListNode res = (new RemoveAllElemInLL()).removeAllElements(head, 6);
		System.out.println(res);
	}

}
