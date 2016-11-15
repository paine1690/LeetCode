package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 *                 总结
 *    二分查找
 * 主要用于带查找数组有序的情况，每次缩小一半的范围，复杂度可以到达log(n)
 * 对于反转一次的有序数组，同样适用，有很多类似的题，可以在判断的时时候适当的加上边界条件
 * 掌握主体思想以后，主要就是根据题意来分析进行具体的判断，修改一些判断条件
 * 
 * 分为4类，
 * 查找符合条件的，不符合则返回-1
 * 找第一个符合条件的数
 * 找最后一个符合条件的数
 * 数组由有序数组经过某种变换得到，不完全有序
 * 
 * 注意：求mid时，如果直接mid=(keft+right)/2,有可能会导致溢出
 * 		所以，要用mid=left+(right-left)/2
 */

public class BinarySearch {
	
   

    
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
    

    

    
    //349. Intersection of Two Arrays
    private static boolean search3(int[] nums, int tar){
    	int i=0, j=nums.length-1;
    	while(i<=j){
    		int mid=i+(j-i)/2;
    		if(nums[mid]==tar){
    			return true;
    		}else if(nums[mid]>tar){
    			j=mid-1;
    		}else{
    			i=mid+1;
    		}
    	}    	
    	return false;
    }
    
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set=new HashSet<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        if(nums1.length<nums2.length){
        	int[] temp=nums1;
        	nums1=nums2;
        	nums2=temp;
        }
        
        for(int i=0; i<nums1.length; i++){
        	if(i!=0&&nums1[i]==nums1[i-1]){
        		continue;
        	}        	
    		if(search3(nums2, nums1[i])){
    			set.add(nums1[i]);
    		}
        	
        }
    	int[] re=new int[set.size()];
    	int cnt=0;
    	Iterator<Integer> i=set.iterator();
    	while(i.hasNext()){
    		re[cnt++]=(int) i.next();
    	} 	
    	
    	return re;
    }
	/**
	 * 1、查找符合条件的，不符合则返回-1
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
    
    //374. Guess Number Higher or Lower
    static class GuessGame{
    	int guess(int num){
    		return 0;
    	}
    }
    
    static class Solution extends GuessGame {    	
        public int guessNumber(int n) {
            int i=1, j=n;
        	while(i<=j){
        		int mid=i+(j-i)/2;
        		int num=guess(mid);
        		if(num==0){
        			return mid;
        		}else if(num==1){
        			i=mid+1;
        		}else{
        			j=mid-1;
        		}
        	}
        	return 0;
        }
    }
	
	/**
	 * 2、找第一个符合条件的数
	 */
	//没有具体功能，为了确保编译没有错误。
	static boolean  isBadVersion(int version){
		return true;
	}
	
	//278. First Bad Version   第一个isBadVersion()
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
	
	/**
	 * 3、找最后一个符合条件的数
	 */
    //441. Arranging Coins  最后一个<=n
    private static long sum(long n){
    	return (1+n)*n/2;
    }
    
    public static int arrangeCoins(int n) {
        long i=1, j=n;
        while(i<=j){
        	long mid=i+(j-i)/2;
        	long sum=sum(mid);        	
        	if(sum<=n){
        		i=mid+1;
        	}else{
        		j=mid-1;
        	}
        }
        return (int)i-1;    	
    }
    
    //69. Sqrt(x) 最后一个 平方<=x的数
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
    
    //74. Search a 2D Matrix
    public boolean searchMatrix4(int[][] matrix, int target) {
        int i=0, j=matrix.length-1;
        while(i<=j){
        	int mid=i+(j-i)/2;
        	if(matrix[mid][0]<=target){
        		i=mid+1;
        	}else{
        		j=mid-1;
        	}
        }        
        int row=i-1;
        if(row<0){
        	return false;
        }
    	int[] nums=matrix[row];
    	i=0;
    	j=nums.length-1;
    	while(i<=j){
    		int mid=i+(j-i)/2;
    		if(nums[mid]==target){
    			return true;
    		}else if(nums[mid]>target){
    			j=mid-1;
    		}else{
    			i=mid+1;
    		}
    	}   	
    	return false;
    }
	
	/**
	 * 4、数组由有序数组经过某种变换得到，不完全有序
	 */	
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
     
    
	//33. Search in Rotated Sorted Array
    public static int search(int[] nums, int target) {
        int i=0, j=nums.length-1;
        while(i<=j){
        	int mid=i+(j-i)/2;
        	if(nums[mid]==target){
        		return mid;
        	}else if(nums[mid]>target){
        		if(nums[mid]<nums[i]){
        			j=mid-1;
        		}else{
        			if(target>=nums[i]){
        				j=mid-1;
        			}else{
        				i=mid+1;
        			}
        		}
        	}else{
        		if(nums[mid]>=nums[i]){
        			i=mid+1;
        		}else{
        			if(target>=nums[i]){
        				j=mid-1;
        			}else{
        				i=mid+1;
        			}
        		}
        	}
        }
        return -1;
    }   
    
    //81. Search in Rotated Sorted Array II
    public boolean search2(int[] nums, int target) {
        int i=0, j=nums.length-1;
        while(i<=j){
        	if(nums[i]==nums[j]){
        		if(nums[i]==target){
        			return true;
        		}
        		i++;
        		continue;
        	}
        	int mid=i+(j-i)/2;
        	if(nums[mid]==target){
        		return true;
        	}else if(nums[mid]>target){
        		if(nums[mid]<nums[i]){
        			j=mid-1;
        		}else{
        			if(target>=nums[i]){
        				j=mid-1;
        			}else{
        				i=mid+1;
        			}
        		}
        	}else{
        		if(nums[mid]>=nums[i]){
        			i=mid+1;
        		}else{
        			if(target>=nums[i]){
        				j=mid-1;
        			}else{
        				i=mid+1;
        			}
        		}
        	}
        }
        return false;
    }
    
    
    
    
	public static void main(String[] args) {
		//System.out.println(Arrays.toString(intersection(new int[]{1}, new int[]{1})));
		int[] nums={4,5,6,7,0,1,2};		
		System.out.println(search(nums,2));
	}
}
