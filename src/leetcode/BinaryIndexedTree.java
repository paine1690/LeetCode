package leetcode;

import java.util.Arrays;
import java.util.List;

public class BinaryIndexedTree {

	
	//315. Count of Smaller Numbers After Self  16ms
	private static int[] tree;
	
	public static List<Integer> countSmaller(int[] nums) {
		int[] temp=nums.clone();
		Arrays.sort(temp);
		for(int i=0; i<nums.length; i++){
			nums[i]=Arrays.binarySearch(temp,  nums[i]);
		}
		tree=new int[nums.length];
		Integer[] re=new Integer[nums.length];
		for(int i=nums.length-1; i>=0; i--){
			re[i]=sumRange(nums[i]);
			add(nums[i]+1, 1);
		}
		return Arrays.asList(re);		
	}
		
	
	private static void add(int index, int val){
		while(index<tree.length){
			tree[index]+=val;
			index+=lowBit(index);
		}
	}
	
	private static int sumRange(int index){
		int re=0;
		while(index>0){
			re+=tree[index];
			index-=lowBit(index);
		}
		return re;
	}
	
	private static int lowBit(int index){
		return index&(-index);
	}
	
	public static void main(String[] args) {
		System.out.println(countSmaller(new int[]{5,2,6,1}));

	}

}
