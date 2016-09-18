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
			re.add(new ArrayList<String>(list));
			return;
		}
		
		for(int i=start; i<end; i++){
			if(isPalindrome(s, start, i)){
				list.add(s.substring(start, i+1));
				parti(s, i+1, end, list);
				list.remove(list.size()-1);
			}
		}
	}
	
    public static List<List<String>> partition(String s) {
        re=new ArrayList<List<String>>();
    	List<String> list=new ArrayList<String>();
    	parti(s, 0, s.length(), list);  
    	return re;
    }
	
    //17. Letter Combinations of a Phone Number
    static String[] number=new String[]{
    		"",
    		"",
    		"abc",
    		"def",
    		"ghi",
    		"jkl",
    		"mno",
    		"pqrs",
    		"tuv",
    		"wxyz",
    };
    static int[] total=new int[]{0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
    
    public List<String> letterCombinations(String digits) {
        List<String> re=new ArrayList<String>();
        if(digits.length()<1){
        	return re;
        }
        int[] answer=new int[digits.length()];
        
        while(true){
        	StringBuilder s=new StringBuilder();
        	for(int i=0; i<digits.length(); i++){
        		s.append(number[digits.charAt(i)-'0'].charAt(answer[i]));
        	}
        	re.add(s.toString());
        	int k=digits.length()-1;
        	while(k>=0){
        		if(answer[k]<total[digits.charAt(k)-'0']-1){
        			answer[k]++;
        			break;
        		}else{
        			answer[k]=0;
        			k--;
        		}
        	}
        	if(k<0){
        		break;
        	}
        }
        return re;
    }
    
    //17. Letter Combinations of a Phone Number
    public static void dfsGet(List<String> re, String s, char[] chars, int start){
    	if(start>=s.length()){
    		re.add(new String(chars));
    		return;
    	}
    	System.out.println(start+""+s.length());
    	for(int i=0; i<total[s.charAt(start)-'0']; i++){
    		chars[start]=number[s.charAt(start)-'0'].charAt(i);
    		dfsGet(re, s, chars, start+1);
    	}    	
    	
    }
    
    public static List<String> letterCombinations2(String digits) {
    	List<String> re=new ArrayList<String>();
    	if(digits.length()<1){
    		return re;
    	}
    	char[] chars=new char[digits.length()];
    	dfsGet(re, digits, chars, 0);    	
    	
    	return re;    	
    }
    
	public static void main(String[] args) {
		String s="2";
		System.out.println(letterCombinations2(s));
//		int[] nums={1,1,2};
//		System.out.println(permuteUnique(nums));

	}

}
