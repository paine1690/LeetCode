package leetcode;

import java.util.ArrayList;
import java.util.List;

public class BackTracking {
	
	//46. Permutations
	private static void swap(int[] nums, int a, int b){
		int temp=nums[a];
		nums[a]=nums[b];
		nums[b]=temp;
	}
	private static void perm(int[] nums, int start, List<List<Integer>> re) {
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
	
	//47. Permutations II
	private static boolean isSwap(int[] nums, int i){
		for(int k=i+1; k<nums.length; k++){
			if(nums[i]==nums[k]){
				return false;
			}
		}
		return true;
	}
	private static void permUnique(int[] nums, int start, List<List<Integer>> re){
		List<Integer> temp=new ArrayList<Integer>();
		if(start==nums.length){
			for(int i=0; i<nums.length; i++){
				temp.add(nums[i]);
			}
			re.add(temp);
		}else{
			for(int i=start; i<nums.length; i++){
				if(isSwap(nums, i)){
					swap(nums, start, i);
					permUnique(nums, start+1, re);
					swap(nums, start, i);
				}
			}
		}
	}
	public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        permUnique(nums, 0, re);
		return re;
    }
	
	
	//131. Palindrome Partitioning
	private static boolean isPalindrome(String s, int i, int j){
		while(i<j){
			if(s.charAt(i)!=s.charAt(j)){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	static List<List<String>> re;
	
	private static void parti(String s, int start, int end, List<String> list){
		if(start>=end){
			System.out.println("re:"+list);
			re.add(new ArrayList<String>(list));
			return;
		}
		
		for(int i=start; i<end; i++){
			if(isPalindrome(s, start, i)){
				list.add(s.substring(start, i+1));
				System.out.println("add "+start+" "+i+" "+list);
				parti(s, i+1, end, list);
				list.remove(list.size()-1);
				System.out.println(list);
			}
		}
	}
	
    public static List<List<String>> partition(String s) {
        re=new ArrayList<List<String>>();
    	List<String> list=new ArrayList<String>();
    	parti(s, 0, s.length(), list);  
    	return re;
    }
	

	public static void main(String[] args) {
		String s="aaba";
		System.out.println(partition(s));
//		int[] nums={1,1,2};
//		System.out.println(permuteUnique(nums));

	}

}
