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
			//如果当前节点的next指针中没有指向“c”的指针，需要新创建
			//之后为了循环能够继续，需要把cur移动一下
			if(cur.next.get(c) != null) {
				cur.next.put(c, new Node());
			}
			cur=cur.next.get(c);
		}
		//添加完一个单词之后需要判断一下当前的字母是不是某一个单词的结尾
		//如果是的话说明这个单词已经被添加过了，不需要size++
		if(!cur.isWord) {
			cur.isWord=true;
		}
	}
	
	public boolean search(String word) {
		return match(root, word, 0);
	}
	
	//从node节点开始，以String字符串从index位为模式进行匹配
	private boolean match(Node node, String word, int index) {
		//终止条件，遍历完word字符串
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
			//如果下一个是‘.‘的话，那么遍历当前节点的所有子节点
			for(char nextChar: node.next.keySet()) {
				//如果有一个匹配成功，那就说明有符合word模式的单词，直接true
				if(match(node.next.get(nextChar), word, index+1)) {
					return true;
				}
			}
			//遍历完还没有就return false
			return false;
		}
	}
	
	public static void main(String[] args) {

	}

}
