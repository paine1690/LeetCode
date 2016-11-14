package second_leetcode;

/**
 * 		二分查找问题
 * 
 * @author Paine
 *
 */
public class BinarySearch {
	/**
	 * 返回目标数
	 */
	//34. Search for a Range
    public int[] searchRange(int[] nums, int target) {
        int i=0, j=nums.length-1;
        while(i<=j){
        	int mid=i+(j-i)/2;
        	if(nums[mid]==target){
        		i=mid;
        		j=mid;
        		while(i>0&&nums[i-1]==target){
        			i--;
        		}
        		while(j<nums.length-1&&nums[j+1]==target){
        			j++;
        		}       		
        		return new int[]{i, j};   
        	}else if(nums[mid]>target){
        		j=mid-1;
        	}else{
        		i=mid+1;
        	}
        }
    	return new int[]{-1, -1};
    }
	
    //367. Valid Perfect Square
    public static boolean isPerfectSquare(int num) {
    	if(num==1){
    		return true;
    	}
        long i=1, j=num/2;
        while(i<=j){
        	long mid=i+(j-i)/2;
        	long mul=mid*mid;
        	if(mul==num){
        		return true;
        	}else if(mul>num){
        		j=mid-1;
        	}else{
        		i=mid+1;
        	}
        }
    	return false;
    }
	
	
    
    
    
    
    
    
    
    
    
    
    
	/**
	 * 返回第一个满足条件的序号
	 */	
	
	//278. First Bad Version   
	private static boolean isBadVersion(int version){
		return true;
	}
    public int firstBadVersion(int n) {
        int i=1, j=n;
        while(i<=j){
        	int mid=i+(j-i)/2;
        	if(isBadVersion(mid)){
        		j=mid-1;
        	}else{
        		i=mid+1;
        	}
        }
        return j+1;
    }	
  
    //35. Search Insert Position  第一个>=n
    public static int searchInsert(int[] nums, int target) {
    	int i=0, j=nums.length-1;
    	while(i<=j){
    		int mid=i+(j-i)/2;
    		if(nums[mid]>=target){
    			j=mid-1;
    		}else{
    			i=mid+1;
    		}
    	}
    	return j+1;
    }
	
    
    
    //300. Longest Increasing Subsequence  第一个>=n的    
    private int binarySearch(int[] nums, int end, int tar){
    	int i=0, j=end;
    	while(i<=j){
    		int mid=i+(j-i)/2;
    		if(nums[mid]>=tar){
    			j=mid-1;
    		}else{
    			i=mid+1;
    		}
    	}
    	return j+1;
    }
    
    
    public int lengthOfLIS(int[] nums) {
    	if(nums.length==0){
    		return 0;
    	}
        
        int[] dp=new int[nums.length];
        int len=0;
        dp[0]=nums[0];
        for(int i=1; i<nums.length; i++){
        	int num=nums[i];
        	if(num>dp[len]){
        		dp[++len]=num;
        	}else{
        		int index=binarySearch(dp, len, num);
        		dp[index]=num;        		
        	}
        }
        return len+1;
    }     
    
    
	/**
	 * 返回最后满足条件的序号
	 */	
    //441. Arranging Coins  最后一个<=n
    private long getSum(long n){
    	return (1+n)*n/2;
    }
    public int arrangeCoins(int n) {
    	long i=1, j=n;
        while(i<=j){
        	long mid=i+(j-i)/2;
        	long num=getSum(mid);
        	if(num<=n){
        		i=mid+1;
        	}else{
        		j=mid-1;
        	}
        }
        return (int) (i-1);
    }
    
    //69. Sqrt(x) 最后一个 平方<=x
    public static int mySqrt(int x) { 
    	if(x==1){
    		return 1;
    	}
        long i=0, j=x/2;
        while(i<=j){
        	long mid=i+(j-i)/2;
        	long mul=mid*mid;
        	if(mul<=x){
        		i=mid+1;
        	}else{
        		j=mid-1;
        	}
        }
        return (int) (i-1);
    }
    
    
    
    
    
    
    /**
     * 并不完全有序
     * @param nums
     * @return
     */
    //153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int i=0, j=nums.length-1;
        while(i<j){
        	int mid=i+(j-i)/2;
        	if(nums[mid]<nums[j]){
        		j=mid;
        	}else{
        		i=mid+1;
        	}   
        }    	
    	return nums[j];
    }
    
    //154. Find Minimum in Rotated Sorted Array II
    public int findMin2(int[] nums) {
        int i=0, j=nums.length-1;
        while(j>0&&nums[j-1]==nums[j]){
			j--;
		}
        while(i<nums.length-1&&nums[i+1]==nums[i]){
			i++;
		}
        while(i<j){
        	int mid=i+(j-i)/2;
        	if(nums[mid]<nums[j]){
        		j=mid;
        		while(j>0&&nums[j-1]==nums[j]){
        			j--;
        		}
        	}else{
        		i=mid+1;
        		while(i<nums.length-1&&nums[i+1]==nums[i]){
        			i++;
        		}
        	}
        }
    	return nums[j];
    }
    
    //162. Find Peak Element
    public int findPeakElement(int[] nums) {
        int i=0, j=nums.length-1;
        while(i<j){
        	int mid=i+(j-i)/2;
        	if(nums[mid]<nums[mid+1]){
        		i=mid+1;
        	}else{
        		j=mid;
        	}
        }
        return j;
    }    
    
    
	public static void main(String[] args) {
//		int[] nums={10,9,2,5,3,7,101,18};
		System.out.println(mySqrt(9));
	}

}
