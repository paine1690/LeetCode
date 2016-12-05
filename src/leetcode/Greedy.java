package leetcode;

import java.util.Arrays;

public class Greedy {

	
	
	//330. Patching Array
    public int minPatches(int[] nums, int n) {
        long miss=1;
        int re=0, i=0;
        
        while(miss<=n){
        	if(i<nums.length&&nums[i]<=miss){
        		miss+=nums[i++];
        	}else{
        		miss+=miss;
        		re++;
        	}
        } 	    	
    	return re;
    }
	
  //376. Wiggle Subsequence
    public static int wiggleMaxLength(int[] nums) {
    	if(nums.length<=1){
    		return nums.length;
    	}
    	int re=nums.length, flag=0;
    	for(int i=1; i<nums.length; i++){
    		if(nums[i]==nums[i-1]){
    			re--;
    		}else if(nums[i]>nums[i-1]){
    			if(flag==1){
    				re--;
    			}
    			flag=1;
    		}else{
    			if(flag==-1){
    				re--;
    			}
    			flag=-1;
    		}
    	}
    	return re;
    }
    
    //455. Assign Cookies
    public int findContentChildren(int[] g, int[] s) {
    	int re=0;
    	Arrays.sort(g);
    	Arrays.sort(s);
    	int i=0, j=0;
    	while(i<g.length&&j<s.length){
    		if(s[j]>=g[i]){
    			re++;
    			i++;
    			j++;
    		}else{
    			j++;
    		}
    	}
    	return re;    	
    }
    
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
