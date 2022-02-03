package com.imooc.hashcode;

import java.util.TreeMap;

public class HashTable<K, V> {
	//��һ��ʵ�ֵĹ�ϣ���Key���Ǳ���ɱȣ���Ϊ�ڲ�ʹ����TreeMap
	//��������������֮�価��������2����ϵ�������ǱȽ��ʺ����������һ�������ӽ�int���͵Ĵ洢����
	private final int[] capacity= {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 2516843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
	private static final int upperTol=10;
	private static final int lowerTol=2;
	private int capacityIndex=7;//��ʾ��ϣ��Ĵ�С��capacity�����е�Indexλ������
	private TreeMap<K, V>[] hashtable;
	private int M;// ��ϣ��ĳ��ȣ�Ҳ��ȡģ�����ж�Mȡģ��M
	private int size;

	public HashTable() {
		this.M = capacity[capacityIndex];
		size = 0;
		// ������������������е�ÿһ��Ԫ�صģ���������������鱾��ģ����Բ���������ʵ������ʱ��ӷ��ͣ������������е�ÿһ��Ԫ��ʵ������ʱ�����
		hashtable = new TreeMap[M];
		// java����������»���Ҫ�������е�ÿһ��λ��ʵ����
		for (int i = 0; i < M; i++) {
			hashtable[i] = new TreeMap<>();
		}
	}

	// ���������͵�key�������Ӧ������
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public int getSize() {
		return size;
	}

	public void add(K key, V val) {
		// �����ϣֵ�����ܿ����Ƚϴ�
		TreeMap<K, V> map = hashtable[hash(key)];
		if (map.containsKey(key)) {
			// �����Ӧ���������Ѿ�����Key�����Ϊ�޸�value��ֵ
			map.put(key, val);
		} else {
			map.put(key, val);
			size++;
			if(size >= upperTol * M && capacityIndex+1 < capacity.length) {
				//����û��ʹ��N/M >= upperTol��Ϊ�˷�ֹint���float����
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
		//�������޸�M��ֵ��������hash�������ȡģ�����������У���Ҫע�������ʱ�����ʹ��oldM
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
