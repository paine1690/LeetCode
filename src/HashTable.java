import java.util.*;

public class HashTable {
	/*
	 * 类名HashTable是按照leetcode上面的tag起的，实际用的是HashMap
	 */
	
    public static int singleNumber(int[] nums) {
    	Set<Integer> set=new HashSet<Integer>();
    	for(int i=0; i<nums.length; i++){
    		if(set.contains(nums[i])){
    			set.remove(nums[i]);
    		}else{
    			set.add(nums[i]);
    		}
    	}
    	
    	Iterator<Integer> it=set.iterator();
    	return it.next();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={1,5,6,4,1,6,4};
		System.out.println(singleNumber(nums));
		
	}

}
