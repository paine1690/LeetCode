package second_leetcode;

/**
 * 		二分查找问题
 * 
 * @author Paine
 *
 */
public class BinarySearch {
	
	/**
	 * 返回第一个大于某数的序号
	 */
	
	
	//278. First Bad Version   
	private static boolean isBadVersion(int version){
		return true;
	}
    public int firstBadVersion(int n) {
        int start=1, end=n;
        while(start<=end){
        	int mid=(start+end)>>>1;
        	if(isBadVersion(mid)){
        		end=mid-1;
        	}else{
        		start=mid+1;
        	}
        }
        return start;
    }
	
	/**
	 * 返回第一个小于某数的序号
	 */
    
    
    
    
    
    //35. Search Insert Position
    public static int searchInsert(int[] nums, int target) {
    	int start=0, end=nums.length-1;
    	while(start<=end){
    		int mid=(start+end)>>>1;
    		if(nums[mid]==target){
    			return mid;
    		}else if(nums[mid]>target){
    			end=mid-1;
    		}else{
    			start=mid+1;
    		}
    	}
    	return start;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={1,3,5,6};
		System.out.println(searchInsert(nums, 0));
	}

}
