package com.imooc.priorityqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.PriorityQueue;
import java.util.Comparator;

public class N个元素中的前M个 {
//求给定数组中出现频率高于K的元素
	
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
			//重新定义整型之间的比较方法，按照频次比较
			public int compare(Integer o1, Integer o2) {
				return map.get(o1)-map.get(o2);
			}		
		}
		//也可以使用lambda表达式来替代匿名类
		//(a, b) ->map.get(a)-map.get(b);
		);
		for(int key: map.keySet()) {
			if(pq.size() < k) {
				pq.add(key);
				//这里的最大堆的作用是保证堆顶部始终是当前堆里最小的元素，所以每次都可以保证成功替换
			}else if(map.get(key) > map.get(pq.peek())) {
				//把数组中的int类型的数字按照优先级放进去，使用优先级比较的时候用get取出元素对应的优先级
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
