package com.imooc.linkedlist;

public class RemoveAllElemInLL {
//�������ֻ��ʵ���Ƴ�����������Ԫ�صķ���
	//���ȶ�������Node
	private class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val=x;
		}
	}
	
	public ListNode removeAllElements(ListNode head, int val) {
		//����������ͷ�ڵ㣬��Ҫ��head���⴦��
		while(head != null && head.val==val) {
			//�п���ɾ��֮���ͷ�ڵ㻹�Ǵ�ɾ��Ԫ��
			ListNode delNode=head;
			head=head.next;
			delNode.next=null;
		}
		//��һ��ɾ��֮���������Ϊ��
		if(head==null) {
			return null;
		}
		ListNode prev=head;
		while(prev.next!=null) {
			if(prev.next.val==val) {
				ListNode delNode=prev.next;
				prev.next=delNode.next;
				delNode.next=null;
			}else {
				//��if��ɾ����һ��֮�������һ���ڵ㻹Ҫɾ��������prev�����ƶ�����һ���ڵ�
				prev=prev.next;
			}
		}
		//ɾ������֮���ͷ�ڵ����ȥ���㶨λ
		return head;
	}
	
	public static void main(String[] args) {
		

	}

}
