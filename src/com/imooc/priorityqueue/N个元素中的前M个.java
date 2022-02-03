package com.imooc.priorityqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.PriorityQueue;
import java.util.Comparator;

public class N��Ԫ���е�ǰM�� {
//����������г���Ƶ�ʸ���K��Ԫ��
	
	public List<Integer> topKFrequent(int[] nums, int k){
		TreeMap<Integer, Integer> map=new TreeMap<>();
		for(int num: nums) {
			if(map.containsKey(num)) {
				map.put(num, map.get(num)+1);
			}else {
				map.put(num, 1);
			}
		}
		PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>(){
			@Override
			//���¶�������֮��ıȽϷ���������Ƶ�αȽ�
			public int compare(Integer o1, Integer o2) {
				return map.get(o1)-map.get(o2);
			}		
		}
		//Ҳ����ʹ��lambda���ʽ�����������
		//(a, b) ->map.get(a)-map.get(b);
		);
		for(int key: map.keySet()) {
			if(pq.size() < k) {
				pq.add(key);
				//��������ѵ������Ǳ�֤�Ѷ���ʼ���ǵ�ǰ������С��Ԫ�أ�����ÿ�ζ����Ա�֤�ɹ��滻
			}else if(map.get(key) > map.get(pq.peek())) {
				//�������е�int���͵����ְ������ȼ��Ž�ȥ��ʹ�����ȼ��Ƚϵ�ʱ����getȡ��Ԫ�ض�Ӧ�����ȼ�
				pq.remove();
				pq.add(key);
			}
		}
		LinkedList<Integer> res=new LinkedList<>();
		while(!pq.isEmpty()) {
			res.add(pq.remove());
		}
		return res;
	}
	
	public static void main(String[] args) {
		//int[] nums=new int{1,1,1,2,2,3};

	}

}
