package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTracking {
	
	//46. Permutations
	private static void swap(int[] nums, int a, int b){
		int temp=nums[a];
		nums[a]=nums[b];
		nums[b]=temp;
	}
	
	private static void perm(int[] nums, int start, List re) {
		if(start==nums.length){
			List<Integer> temp=new ArrayList<Integer>();
			for(int i=0; i<nums.length; i++){
				temp.add(nums[i]);
			}
			re.add(temp);
		}else{
			for(int i=start; i<nums.length; i++){
				swap(nums, start, i);
				perm(nums, start+1, re);
				swap(nums, start, i);
			}
		}
	}
	
	public static List<List<Integer>> permute(int[] nums) {    
		List<List<Integer>> re=new ArrayList<List<Integer>>();
		perm(nums, 0, re);
		return re;
    }
	public static void main(String[] args) {
		int[] nums={1,2,3};
		System.out.println(permute(nums));

	}

}
