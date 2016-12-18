package leetcode;

import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSet_leetcode {

	
	
	//220. Contains Duplicate III
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    	if(k<1 || t<0 || nums==null || nums.length<2) return false;  
        SortedSet<Long> set=new TreeSet<Long>();
    	for(int i=0; i<nums.length; i++){
    		SortedSet<Long> sub=set.subSet((long)nums[i]-t,  (long)nums[i]+t+1);
    		if(!sub.isEmpty()){
    			return true;
    		}
    		
    		if(i>=k){
    			set.remove((long)nums[i-k]);
    		}
    		set.add((long)nums[i]);
    		
    	}
    	return false;
    }
	
	
	
	public static void main(String[] args) {
		//System.out.println(containsNearbyAlmostDuplicate(new int[]{-1, -1}, 1, 0));

	}

}
