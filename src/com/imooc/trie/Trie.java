package com.imooc.trie;

import java.util.TreeMap;

public class Trie {
	//��ͬ���Ե���С��Ԫ��һ����Character���͵ģ�������Ƴɷ��ͣ�������Щ������ϵ���漰��������
	private class Node{
		public boolean isWord;
		public TreeMap<Character, Node> next;
		public Node(boolean isWord) {
			this.isWord=isWord;
			next=new TreeMap<>();
		}
		
		public Node() {
			this(false);
		}
	}
	
	private Node root;
	private int size;
	
	public Trie() {
		root=new Node();
		size=0;
	}
	
	//���ص�������
	public int getSize() {
		return size;
	}
	
	//��Trie�����һ���µĵ��ʣ�֮�����дһ�µݹ�д��
	//���һ�����ʣ�֮��ѵ��ʲ����ĸ�ټ���Trie
	public void add(String word) {
		Node cur=root;
		for(int i=0; i<word.length(); i++) {
			char c=word.charAt(i);
			//�����ǰ�ڵ��nextָ����û��ָ��c����ָ�룬��Ҫ�´���
			//֮��Ϊ��ѭ���ܹ���������Ҫ��cur�ƶ�һ��
			if(cur.next.get(c) != null) {//cur.next.get(c) == null ?
				cur.next.put(c, new Node());
			}
			cur=cur.next.get(c);
		}
		//�����һ������֮����Ҫ�ж�һ�µ�ǰ����ĸ�ǲ���ĳһ�����ʵĽ�β
		//����ǵĻ�˵����������Ѿ�����ӹ��ˣ�����Ҫsize++
		if(!cur.isWord) {
			cur.isWord=true;
			size++;
		}
	}
	
	public boolean contains(String word) {
		Node cur=root;
		for(int i=0; i<word.length(); i++) {
			char c=word.charAt(i);
			if(cur.next.get(c) == null) {
				return false;
			}
			cur=cur.next.get(c);
		}
		//���Ҫ��һ�����һ����ĸ�ǲ���һ�����ʵĽ�β
		return cur.isWord;
	}
	
	public boolean isPrefix(String prefix) {
		Node cur=root;
		for(int i=0; i<prefix.length(); i++) {
			char c=prefix.charAt(i);
			if(cur.next.get(c) == null) {
				return false;
			}
			cur=cur.next.get(c);
		}
		//����ܹ����������ѭ������ô˵��һ���������ǰ׺��
		return true;
	}
	
	public static void main(String[] args) {

	}

}
