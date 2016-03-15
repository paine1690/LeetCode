import java.util.Arrays;

public class BinarySearch {
	//没有具体功能，为了确保编译没有错误。
	static boolean  isBadVersion(int version){
		return true;
	}
	
	public int firstBadVersion(int n) {
    	int left=1, right=n;
        while(left<=right){
        	int mid=left+(right-left)/2;
        	if(isBadVersion(mid)){
        		right=mid-1;
        	}else{
        		left=mid+1;
        	}
        }    	
    	return right+1;
    }
    
    public static int mySqrt(int x) {
    	if(x<2){
    		return x;
    	}
        int left=1, right=x/2+1;
    	while(left<=right){
    		int mid=left+(right-left)/2;
    		int temp=x/mid;
    		if(temp==mid){
    			return mid;
    		}else if(temp<mid){
    			right=mid-1;
    		}else{
    			left=mid+1;
    		}
    	}
    	return right;
    }
    
    public static int[] searchRange(int[] nums, int target) {
        int[] re={-1,-1};
        if(nums[0]>target||nums[nums.length-1]<target){
        	return re;
        }
        int left=0, right=nums.length-1;
        while(left<=right){
        	int mid=left+(right-left)/2;
        	if(nums[mid]==target){
                int i=mid, j=mid;
                while(j<nums.length-1&&nums[j+1]==target){
                	j++;
                }
                while(i>0&&nums[i-1]==target){
                	i--;
                }
                re[0]=i;       	
                re[1]=j;	
        		break;
        	}else if(nums[mid]<target){
        		left=mid+1;
        	}else{
        		right=mid-1;
        	}
        }
        return re;
    }
    

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        int n=matrix[0].length;
    	int mid = 0, left=0, right=m-1;
    	while(left<=right){
    		mid=left+(right-left)/2;
    		if(matrix[mid][0]==target){
    			return true;
    		}else if(matrix[mid][0]>target){
    			right=mid-1;
    		}else{
    			left=mid+1;
    		}
    	}
    	if(right==-1){
    		return false;
    	}
    	int temp=right;
    	left=0; 
    	right=n-1;
    	while(left<=right){
    		mid=left+(right-left)/2;
    		if(matrix[temp][mid]==target){
    			return true;
    		}else if(matrix[temp][mid]<target){
    			left=mid+1;
    		}else{
    			right=mid-1;
    		}
    	}
    	return false;
    }
   
    public static int searchInsert(int[] nums, int target) {
        int mid, left=0, right=nums.length-1;
        while(left<=right){
        	mid=left+(right-left)/2;
        	if(nums[mid]==target){
        		return mid;
        	}else if(nums[mid]>target){
        		right=mid-1;
        	}else{
        		left=mid+1;
        	}
        }
        
        return right+1;
    }
    
    public static int findMin(int[] nums) {
        int mid, left=0, right=nums.length-1;
        while(left<right){
        	mid=left+(right-left)/2;
        	if(nums[mid]>=nums[right]){
        		left=mid+1;
        	}else{
        		right=mid;
        	}
        }
    	System.out.println(right);
    	if(right==-1){
    		return nums[0];
    	}
    	return nums[right];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={3,1,2};
		int[][] matrix={{1,3}};
		System.out.println(findMin(nums));

	}

}
