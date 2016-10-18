package leetcode;

import java.util.Arrays;

/*
 *                 总结
 *    二分查找
 * 主要用于带查找数组有序的情况，每次缩小一半的范围，复杂度可以到达log(n)
 * 对于反转一次的有序数组，同样适用，有很多类似的题，可以在判断的时时候适当的加上边界条件
 * 掌握主体思想以后，主要就是根据题意来分析进行具体的判断，修改一些判断条件
 * 
 * 注意：求mid时，如果直接mid=(keft+right)/2,有可能会导致溢出
 * 		所以，要用mid=left+(right-left)/2
 */

public class BinarySearch {
	
	//没有具体功能，为了确保编译没有错误。
	static boolean  isBadVersion(int version){
		return true;
	}
	
	//278. First Bad Version
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
    
	//69. Sqrt(x)
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
    
    //34. Search for a Range
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
    
    //74. Search a 2D Matrix
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
    
    //35. Search Insert Position
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
    
    //153. Find Minimum in Rotated Sorted Array
    public static int findMin(int[] nums) {
    	int mid, left=0, right=nums.length-1;
        while(left<right){
        	mid=left+(right-left)/2;
        	
        	if(nums[mid]>nums[right]){
        		left=mid+1;
        	}else{
        		right=mid;
        	}
        }
    	return nums[right];
    }
    
    //154. Find Minimum in Rotated Sorted Array II
    public static int findMin2(int[] nums) {
    	int mid, left=0, right=nums.length-1;
        while(left<right){
        	if(nums[left]==nums[right]){
        		left++;
        		continue;
        	}
        	mid=left+(right-left)/2;        	
        	if(nums[mid]>nums[right]){
        		left=mid+1;
        	}else{
        		right=mid;
        	}
        }
    	if(right==-1){
    		return nums[0];
    	}
    	return nums[right];
    }
    
    //240. Search a 2D Matrix II
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int i=0, j=matrix[0].length-1;
        while(i<matrix.length&&j>0){
        	int val=matrix[i][j];
        	if(val>target){
        		j--;
        	}else if(val<target){
        		i++;
        	}else{
        		return true;
        	}
        }
    	return false;
    }
    
    //275. H-Index II
    public static int hIndex(int[] citations) {
        int len=citations.length;
    	int left=0, right=len-1;
        while(left<=right){
        	int mid=left+(right-left)/2;
        	if(citations[mid]>=len-mid){
        		right=mid-1;
        	}else{
        		left=mid+1;
        	}
        }    	
    	return len-right-1;
    }
    
    //162. Find Peak Element
    public static int findPeakElement(int[] nums) {
        int left=0, right=nums.length-1;
        while(left<right){
        	int mid=left+(right-left)/2;
        	if(nums[mid]<nums[mid+1]){
        		left=mid+1;
        	}else{
        		right=mid;
        	}
        }
    	return left;
    }
    
    //33. Search in Rotated Sorted Array
    public static int search(int[] nums, int target) {
        int left=0, right=nums.length-1;
        while(left<=right){
        	int mid=left+(right-left)/2;
        	if(nums[mid]==target){
        		return mid;
        	}else if(nums[mid]>target){
        		if(nums[mid]<nums[right]){
        			right=mid-1;
        		}else{
        			if(nums[left]==target){
        				return left;
        			}else if(nums[left]<target){
        				right=mid-1;
        			}else{
        				left=mid+1;
        			}
        		}
        	}else{//nums[mid]<target
        		if(nums[mid]>nums[left]){
        			left=mid+1;
        		}else{
        			if(nums[right]==target){
            			return right;
        			}else if(nums[right]>target){
        				left=mid+1;
        			}else{
        				right=mid-1;
        			}
        		}
        	}
        }
    	return -1;
    }
    
    //81. Search in Rotated Sorted Array II
    public static boolean search2(int[] nums, int target) {
    	int left=0, right=nums.length-1;
        while(left<=right){
        	if(nums[left]==nums[right]){
        		left++;
        		continue;
        	}
        	int mid=left+(right-left)/2;
        	if(nums[mid]==target){
        		return true;
        	}else if(nums[mid]>target){
        		if(nums[mid]<=nums[right]){
        			right=mid-1;
        		}else{
        			if(nums[left]==target){
        				return true;
        			}else if(nums[left]<target){
        				right=mid-1;
        			}else{
        				left=mid+1;
        			}
        		}
        	}else{//nums[mid]<target
        		if(nums[mid]>=nums[left]){
        			left=mid+1;
        		}else{
        			if(nums[right]==target){
            			return true;
        			}else if(nums[right]>target){
        				left=mid+1;
        			}else{
        				right=mid-1;
        			}
        		}
        	}
        }
    	if(right>=0){
        	return nums[right]==target;
        }
    	return false;
    }
    
    //268. Missing Number
    public static int missingNumber(int[] nums) {
        if(nums.length==1){
        	return nums[0]==0? 1:0;
        }
        Arrays.sort(nums);
        int left=0, right=nums.length-1;
        while(left<right){
        	int mid=(left+right)>>>1;
        	if(nums[mid]>mid){
        		
        		right=mid-1;
        	}else{
        		if(nums[mid]+1==nums[mid+1]){
        			left=mid+1;
        		}else{
        			return nums[mid]+1;
        		}
        	}
        
        }
        if(left==right&&nums[right]>right){
        	return right;
        }
    	return right+1;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] nums={3,24,50,79,88,150,345};		
		//System.out.println(Arrays.toString(twoSum(nums,200)));
	}
}
