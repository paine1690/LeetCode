package leetcode;

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
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
