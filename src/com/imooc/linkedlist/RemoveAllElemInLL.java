package com.imooc.linkedlist;

public class RemoveAllElemInLL {
//这个类里只是实现移除链表中所有元素的方法
	//首先定义该类的Node
	private class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val=x;
		}
	}
	
	public ListNode removeAllElements(ListNode head, int val) {
		//不适用虚拟头节点，需要对head特殊处理
		while(head != null && head.val==val) {
			//有可能删除之后的头节点还是待删除元素
			ListNode delNode=head;
			head=head.next;
			delNode.next=null;
		}
		//上一步删除之后链表可能为空
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
				//在if中删除完一个之后可能下一个节点还要删除，所以prev不能移动到下一个节点
				prev=prev.next;
			}
		}
		//删除结束之后把头节点给回去方便定位
		return head;
	}
	
	public static void main(String[] args) {
		

	}

}
