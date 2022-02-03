package com.imooc.trie;

import java.util.TreeMap;

public class MapSum {
	
	private class Node{
		//���ǿ���ʹ��value����ʾ��ǰ����ĸ�ǲ���һ�����ʵĽ�β��������ǽ�β����0
		//��ʹ���ʵ�valueΪ0��Ҳ��Ӱ�죬��Ϊ����ⲻ��עĳ���ڵ��ǲ��ǵ��ʵĽ�β
		public int value;
		public TreeMap<Character, Node> next;
		
		public Node(int value) {
			this.value=value;
			next=new TreeMap<>();
		}
		
		public Node() {
			this(0);
		}
	}
	
	private Node root;
	
	public MapSum() {
		root=new Node();
	}
	
	public void insert(String word, int value) {
		Node cur=root;
		for(int i=0; i<word.length(); i++) {
			char c=word.charAt(i);
			//�����ǰ�ڵ��nextָ����û��ָ��c����ָ�룬��Ҫ�´���
			//֮��Ϊ��ѭ���ܹ���������Ҫ��cur�ƶ�һ��
			if(cur.next.get(c) != null) {
				cur.next.put(c, new Node());
			}
			cur=cur.next.get(c);
		}
		//���һ����ĸ��value���������ʵ�value
		cur.value=value;
	}
	
	public int sum(String prefix) {
		//�Ȳ��ҵ�prefix�����һ����ĸ��λ��
		//֮�������а���prefix�ĵ���
		Node cur=root;
		for(int i=0; i<prefix.length(); i++) {
			char c=prefix.charAt(i);
			if(cur.next.get(c) == null) {
				return 0;
			}
			cur=cur.next.get(c);
		}
		return sum(cur);
	}
	//����node���������������value֮��
	private int sum(Node node) {
		//�ݹ���ֹ������nodeΪҶ�ӽڵ㣬����������Բ�д
		//��Ϊ���node��next����û��һ�����Ļ��������ѭ��Ҳ���ᱻִ�У�ֱ�ӷ���node.value
		int res=node.value;
		for(char c: node.next.keySet()) {
			res += sum(node.next.get(c));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}

}
