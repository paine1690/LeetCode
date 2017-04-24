package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
 * 数组由有序数组经过某种变换得到，不完全有序
 * 还有两种是最常用的
 * 
 * 数组范围0-n
 * while(i<=j)
 * 
 * 		条件			返回		可能的范围k
 * 1、第一个>=tar		i		0-n+1
 * 2、最后一个<=tar	j		-1-n
 * 
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
    
    //350. Intersection of Two Arrays II
    private static int binarySearch(int[] nums, int start, int tar){
    	int i=start, j=nums.length-1;
    	while(i<=j){
    		int mid=i+(j-i)/2;
    		if(nums[mid]==tar){
    			while(mid>start&&nums[mid-1]==nums[mid]){
    				mid--;
    			}
    			return mid;
    		}else if(nums[mid]>tar){
    			j=mid-1;
    		}else{
    			i=mid+1;
    		}
    	}    	
    	return -1;
    }    
    
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        if(nums1.length<nums2.length){
        	int[] temp=nums1;
        	nums1=nums2;
        	nums2=temp;
        }      
        List<Integer> list=new ArrayList<Integer>();
        int start=0;
        for(int i=0; i<nums1.length; i++){
        	int temp=binarySearch(nums2, start, nums1[i]);
        	if(temp>=0){
        		list.add(nums1[i]);
        		start=temp+1;
        	}
        }
        
        int[] re=new int[list.size()];
    	int cnt=0;
    	Iterator<Integer> i=list.iterator();
    	while(i.hasNext()){
    		re[cnt++]=(int) i.next();
    	} 	    	
    	return re;        
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
        return i;
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
    	return i;
    }    
    
    //275. H-Index II       第一个 nums[i]>=len-i
    public int hIndex(int[] citations) {
        int[] nums=citations;
        int len=nums.length;
        int i=0, j=len-1;
    	while(i<=j){
    		int mid=i+(j-i)/2;
    		if(nums[mid]>=len-mid){
    			j=mid-1;
    		}else{
    			i=mid+1;
    		}
    	}    	
    	return len-i;
    }   
    
    //436. Find Right Interval    先排序，然后找第一个>=tar的
    static class Interval {
    	int start;
    	int end;
    	Interval() { start = 0; end = 0; }
    	Interval(int s, int e) { start = s; end = e; }
    }
    
    private static void merge(int[] nums, int[] pos, int start, int mid, int end){
    	int i=start, j=mid+1, k=0;
    	int[] temp=new int[end-start+1];
    	while(i<=mid&&j<=end){
    		if(nums[pos[i]]<=nums[pos[j]]){
    			temp[k++]=pos[i++];
    		}else{
    			temp[k++]=pos[j++];
    		}
    	}
    	while(i<=mid){
    		temp[k++]=pos[i++];
    	}
    	while(j<=end){
    		temp[k++]=pos[j++];
    	}
    	for(k=0; k<temp.length; k++){
    		pos[start+k]=temp[k];
    	}
    }
    
    private static void sort(int[] nums, int[] pos, int start, int end){
    	if(start<end){
    		int mid=start+(end-start)/2;
    		sort(nums, pos, start, mid);
    		sort(nums, pos, mid+1, end);
    		merge(nums, pos, start, mid, end);
    	}
    }
    
    public static int[] findRightInterval(Interval[] intervals) {
        int len=intervals.length;
        int[] nums=new int[len];
        int[] pos=new int[len];
        int[] re=new int[len];
    	for(int i=0; i<nums.length; i++){
    		nums[i]=intervals[i].start;
    		pos[i]=i;
    	}
    	sort(nums, pos, 0, len-1);
    	System.out.println(Arrays.toString(pos));
    	int i, j;
    	for(int c=0; c<len; c++){
    		int tar=intervals[c].end;
    		
    		i=0;
    		j=len-1;
    		while(i<=j){
    			int mid=i+(j-i)/2;
    			if(nums[pos[mid]]>=tar){
    				j=mid-1;
    			}else{
    				i=mid+1;
    			}
    		}
    		re[c]=j+1>=len? -1:pos[i];    		
    	}
    	return re;
    }
    
    //287. Find the Duplicate Number
    private static int count(int[] nums, int tar){
    	int re=0;
    	for(int i=0; i<nums.length; i++){
    		if(nums[i]<=tar){
    			re++;
    		}
    	}
    	return re;
    }
    
    public static int findDuplicate(int[] nums) {
       int i=0, j=nums.length-1;
       while(i<=j){
    	   int mid=i+(j-i)/2;
    	   int cnt=count(nums, mid);
    	   if(cnt>mid){
    		   j=mid-1;
    	   }else{
    		   i=mid+1;
    	   }
       }
       return i;
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
        return (int)j;    	
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
        return (int)j;
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
	
    //209. Minimum Size Subarray Sum  最后一个<=tar
    public static int minSubArrayLen(int s, int[] nums) {
        for(int i=1; i<nums.length; i++){
        	if(nums[i]==s){
        		return 1;
        	}
        	nums[i]+=nums[i-1];
        }
        int len=Integer.MAX_VALUE;
        for(int c=0; c<nums.length; c++){
        	int num=nums[c];
        	if(num<s){
        		continue;
        	}else{        		
        		int i=0, j=c-1, tar=num-s;
        		while(i<=j){
        			int mid=i+(j-i)/2;
        			if(nums[mid]<=tar){
        				i=mid+1;
        			}else{
        				j=mid-1;
        			}
        		}		
        		int index=j;
        		len=Math.min(len, c-index);       		
        	}
        }
    	return len==Integer.MAX_VALUE? 0: len;
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
		System.out.println(Integer.MAX_VALUE);
		//System.out.println(findDuplicate(new int[]{1,2,2,3,4}));
		//		Interval[] i=new Interval[]{new Interval(1,4), new Interval(2,3), new Interval(3,4)};
//		System.out.println(Arrays.toString(findRightInterval(i)));
//		int[] nums1={84, 84, 84, 84, 84};
//		int[] nums2={84, 84};
//		System.out.println(Arrays.toString(intersect(nums1, nums2)));
	}
}
