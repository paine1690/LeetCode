package leetcode;
import java.util.HashMap;
import java.util.HashSet;

public class Array_leetcode {

	
    
    
    
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set=new HashSet<Integer>();
    	for(int i=0; i<nums.length; i++){
    		if(set.contains(nums[i])){
    			return true;
    		}else{
    			set.add(nums[i]);
    		}
    	}
    	
    	return false;
    }
    
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	int len=nums.length;
    	if(len<2){
    		return false;
    	}
    	HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
    	for(int i=0; i<len; i++){
    		if(!map.containsKey(nums[i])){
    			map.put(nums[i], i);
    		}else{
    			if((i-map.get(nums[i])<=k)){
    				return true;
    			}
    			map.put(nums[i], i);
    		}
    	}	
    	return false; 
    }
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] aa={1,1,1,1,2,3};
	}

}
