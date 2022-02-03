package com.imooc.hashcode;

import java.util.TreeMap;

public class HashTable<K, V> {
	//这一章实现的哈希表的Key还是必须可比，因为内部使用了TreeMap
	//素数表，各个素数之间尽量保持了2倍关系，并且是比较适合素数，最后一个素数接近int类型的存储极限
	private final int[] capacity= {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 2516843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
	private static final int upperTol=10;
	private static final int lowerTol=2;
	private int capacityIndex=7;//表示哈希表的大小是capacity数组中的Index位的数字
	private TreeMap<K, V>[] hashtable;
	private int M;// 哈希表的长度，也是取模运算中对M取模的M
	private int size;

	public HashTable() {
		this.M = capacity[capacityIndex];
		size = 0;
		// 泛型是修饰这个数组中的每一个元素的，不是修饰这个数组本身的，所以不能再数组实例化的时候加泛型，但是在数组中的每一个元素实例化的时候可以
		hashtable = new TreeMap[M];
		// java中这种情况下还需要对数组中的每一个位置实例化
		for (int i = 0; i < M; i++) {
			hashtable[i] = new TreeMap<>();
		}
	}

	// 将任意类型的key变成它对应的索引
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public int getSize() {
		return size;
	}

	public void add(K key, V val) {
		// 计算哈希值的性能开销比较大
		TreeMap<K, V> map = hashtable[hash(key)];
		if (map.containsKey(key)) {
			// 如果对应的树里面已经含有Key，理解为修改value的值
			map.put(key, val);
		} else {
			map.put(key, val);
			size++;
			if(size >= upperTol * M && capacityIndex+1 < capacity.length) {
				//这里没有使用N/M >= upperTol是为了防止int变成float出错
				capacityIndex++;
				resize(capacity[capacityIndex]);
			}
		}
	}

	public V remove(K key) {
		TreeMap<K, V> map = hashtable[hash(key)];
		V ret = null;
		if (map.containsKey(key)) {
			ret = map.remove(key);
			size--;
			if(size < lowerTol * M && capacityIndex-1 >= 0) {
				capacityIndex--;
				resize(capacity[capacityIndex]);
			}
		}
		return ret;
	}
	
	public void set(K key, V val) {
		TreeMap<K, V> map = hashtable[hash(key)];
		if(!map.containsKey(key)) {
			throw new IllegalArgumentException("Key does not exist.");
		}
		map.put(key, val);
	}
	
	public boolean contains(K key) {
		return hashtable[hash(key)].containsKey(key);
	}
	
	public V get(K key) {
		return hashtable[hash(key)].get(key);
	}
	
	private void resize(int newM) {
		TreeMap<K, V>[] newHashTable=new TreeMap[newM];
		for(int i=0; i<newM; i++) {
			newHashTable[i]=new TreeMap<>();
		}
		//必须先修改M的值，才能让hash函数里的取模运算正常运行，还要注意便利的时候必须使用oldM
		int oldM=M;
		this.M=newM;
		for(int i=0; i<oldM; i++) {
			TreeMap<K, V> map=hashtable[i];
			for(K key: map.keySet()) {
				newHashTable[hash(key)].put(key, map.get(key));
			}
		}
		this.hashtable=newHashTable;
	}

	public static void main(String[] args) {

	}

}
