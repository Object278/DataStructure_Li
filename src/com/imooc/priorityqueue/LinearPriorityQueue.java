package com.imooc.priorityqueue;

import java.util.ArrayList;

//����һ���ײ�Ϊ˳�����ݽṹ-��̬��������ȶ���
public class LinearPriorityQueue<V> implements PriorityQueue<V> {
	
	private class Node{
		Integer pri;
		V val;
		//���ȼ�����Ϊ1-10��10��ߣ���Java�еĶ�����ͬ
		public Node(Integer pri, V val) {
			this.pri=pri;
			this.val=val;
		}
		
		public Node(V val) {
			this.val=val;
			this.pri=5;
		}
	}
	
	private ArrayList<Node> list=new ArrayList<>();
	
	@Override
	public void enqueue(Integer pri, V val) {
		Node node=new Node(pri, val);
		if(list.size()==0) {
			list.add(node);
		}
		for(int i=0;i<list.size();i++) {
			if(node.pri<list.get(i).pri) {
				list.add(i, node);
				break;
			}
			if(i==list.size()-1) {
				list.add(node);
				break;
			}
		}
	}

	@Override
	public V dequeue() {
		Node node=list.get(list.size()-1);
		list.remove(list.size()-1);
		return node.val;
	}

	@Override
	public V getFront() {
		return list.get(list.size()-1).val;
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public static void main(String[] args) {

	}

}
