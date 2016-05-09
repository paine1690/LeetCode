package leetcode;

public class Array_leetcode {
    
	//27. Remove Element
    public static int removeElement(int[] nums, int val) {
        int re=0;
        for(int i=0; i<nums.length; i++){
        	if(nums[i]==val){
        		re++;
        		continue;
        	}
        	nums[i-re]=nums[i];
        }
        return nums.length-re;
    }
    
	public static void main(String[] args) {
		int[] nums={3,2,2,3};
		System.out.println(removeElement(nums, 3));
		
	}

}
