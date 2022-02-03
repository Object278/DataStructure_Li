package com.imooc.trie;

import java.util.TreeMap;

public class WordDictionary {
	
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
	
	public WordDictionary() {
		root=new Node();
	}
	
	public void addWird(String word) {
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
		//�����һ������֮����Ҫ�ж�һ�µ�ǰ����ĸ�ǲ���ĳһ�����ʵĽ�β
		//����ǵĻ�˵����������Ѿ�����ӹ��ˣ�����Ҫsize++
		if(!cur.isWord) {
			cur.isWord=true;
		}
	}
	
	public boolean search(String word) {
		return match(root, word, 0);
	}
	
	//��node�ڵ㿪ʼ����String�ַ�����indexλΪģʽ����ƥ��
	private boolean match(Node node, String word, int index) {
		//��ֹ������������word�ַ���
		if(index == word.length()) {
			return node.isWord;
		}
		char c=word.charAt(index);
		if(c != '.') {
			if(node.next.get(c) == null) {
				return false;
			}
			return match(node.next.get(c), word, index+1);
		}else {
			//�����һ���ǡ�.���Ļ�����ô������ǰ�ڵ�������ӽڵ�
			for(char nextChar: node.next.keySet()) {
				//�����һ��ƥ��ɹ����Ǿ�˵���з���wordģʽ�ĵ��ʣ�ֱ��true
				if(match(node.next.get(nextChar), word, index+1)) {
					return true;
				}
			}
			//�����껹û�о�return false
			return false;
		}
	}
	
	public static void main(String[] args) {

	}

}
