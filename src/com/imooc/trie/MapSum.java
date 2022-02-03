package com.imooc.trie;

import java.util.TreeMap;

public class MapSum {
	
	private class Node{
		//我们可以使用value来表示当前的字母是不是一个单词的结尾，如果不是结尾就是0
		//即使单词的value为0，也不影响，因为这个题不关注某个节点是不是单词的结尾
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
			//如果当前节点的next指针中没有指向“c”的指针，需要新创建
			//之后为了循环能够继续，需要把cur移动一下
			if(cur.next.get(c) != null) {
				cur.next.put(c, new Node());
			}
			cur=cur.next.get(c);
		}
		//最后一个字母的value是整个单词的value
		cur.value=value;
	}
	
	public int sum(String prefix) {
		//先查找到prefix的最后一个字母的位置
		//之后找所有包含prefix的单词
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
	//遍历node的所有子树，求出value之和
	private int sum(Node node) {
		//递归终止条件是node为叶子节点，但是这里可以不写
		//因为如果node的next里面没有一个结点的话，下面的循环也不会被执行，直接返回node.value
		int res=node.value;
		for(char c: node.next.keySet()) {
			res += sum(node.next.get(c));
		}
		return res;
	}
	
	public static void main(String[] args) {

	}

}
