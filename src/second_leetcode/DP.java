package second_leetcode;

public class DP {

	//121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        if(prices.length==0){
        	return 0;
        }
    	int re=0;
    	int min=prices[0];
    	
    	for(int i=1; i<prices.length; i++){
    		int diff=prices[i]-min;
    		if(diff>0){
    			re=Math.max(re, diff);
    		}
    		min=Math.min(min, prices[i]);
    	}    	
    	return re;
    }
    
    //122. Best Time to Buy and Sell Stock II
    public int maxProfit2(int[] prices) {
        if(prices.length==0){
        	return 0;
        }
    	int re=0;
        int last=prices[0];
        for(int i=1; i<prices.length; i++){
        	if(prices[i]>last){
        		re+=prices[i]-last;        		
        	}
        	last=prices[i];
        }
        return re;
    }
    

	//198. House Robber    
    public int rob(int[] nums) {
    	if(nums.length<2){
        	return nums.length==0? 0:nums[0];
        }
    	nums[1]=Math.max(nums[1], nums[1]);
    	for(int i=2; i<nums.length; i++){
    		nums[i]=Math.max(nums[i]+nums[i-2], nums[i-1]);
    	}
    	return nums[nums.length-1];
    }
    
    //213. House Robber II
    public int rob2(int[] nums) {
        if(nums.length<3){
        	if(nums.length==2){
            	return Math.max(nums[0], nums[1]);
            }
        	return nums.length==0? 0:nums[0];
        }
        int[] nums2=nums.clone();
    	nums[1]=Math.max(nums[0], nums[1]);
    	for(int i=2; i<nums.length-1; i++){
    		nums[i]=Math.max(nums[i]+nums[i-2], nums[i-1]);
    	}
    	
    	nums2[2]=Math.max(nums2[1], nums2[2]);
    	for(int i=3; i<nums2.length; i++){
    		nums2[i]=Math.max(nums2[i]+nums2[i-2], nums2[i-1]);
    	}
    	return Math.max(nums[nums.length-2], nums2[nums2.length-1]);
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
