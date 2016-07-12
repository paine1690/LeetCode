package leetcode;




import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 
 * 分治 
 * 
 * 求第k大元素，运用快排思想
 * 两个有序数组的中值，运用求两个数组第k小元素
 * @author Paine
 *
 */
public class DivideAndConquer {
	
	//215. Kth Largest Element in an Array
	private static int partition(int[] nums, int p, int r){
		int x=nums[r];
		int i=p-1;
		int temp;
		for(int j=p; j<r; j++){
			if(nums[j]<=x){
				i++;
				temp=nums[i];
				nums[i]=nums[j];
				nums[j]=temp;				
			}
		}
		temp=nums[i+1];
		nums[i+1]=nums[r];
		nums[r]=temp;
		return i+1;
	}
	
    private static int randomPartition(int[] nums, int p, int r){
		Random rand=new Random();
		int i=rand.nextInt(r-p)+p;
		int temp=nums[i];
		nums[i]=nums[r];
		nums[r]=temp;
		return partition(nums, p, r);
	}
	
	public static int findKthLargest(int[] nums, int k) {
		int len=nums.length;
		if(len==1){
			return nums[0];
		}
		int p=0, r=len-1,re=0;
		while(p<r){
			re=randomPartition(nums, p, r);
			if(len-re==k){
				return nums[re];
			}else if(len-re<k){
				r=re-1;
			}else{
				p=re+1;
			}
		}
		return nums[r];		
	}
	
	//4. Median of Two Sorted Arrays
	private static double findKth(int[] nums1, int s1, int len1, int[] nums2, int s2, int len2, int k){
		if(len1>len2){//保证nums1为短的那个
			return findKth(nums2, s2, len2, nums1, s1, len1, k);
		}
		if(len1==0){//短数组中元素都被抛弃，返回长数组中第k个元素即nums2[s2+k-1]
			return nums2[s2+k-1];
		}
		
		if(k==1){
			return java.lang.Math.min(nums1[s1], nums2[s2]);
		}
		int p=java.lang.Math.min(k/2, len1);
		int q=k-p;
		if(nums1[s1+p-1]<nums2[s2+q-1]){
			return findKth(nums1, s1+p, len1-p, nums2, s2, len2, k-p);
		}else if(nums1[s1+p-1]>nums2[s2+q-1]){
			return findKth(nums1, s1, len1, nums2, s2+q, len2-q, k-q);
		}else{
			return nums1[s1+p-1];
		}
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int k=nums1.length+nums2.length;
		if((k&1)==1){
			return findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, k/2+1);
		}else{
			return (findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, k/2)+findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, k/2+1))/2;
		}
	}
	
	//241. Different Ways to Add Parentheses
    public static List<Integer> diffWaysToCompute(String input) {
        
    	
    	return null;
    }
    
    
    //315. Count of Smaller Numbers After Self
    private static void reverse(int[] nums, int[] smaller, int[] pos, int start, int end){
    	if(start>=end){
    		return ;
    	}
    	int mid=start+(end-start)/2;
    	reverse(nums, smaller, pos, start, mid);
    	reverse(nums, smaller, pos, mid+1, end);
    	int[] temp=new int[end-start+1];
    	int i=start, j=mid+1, k=0;
    	int count=0;
    	while(i<=mid||j<=end){
    		if(i>mid){
    			temp[k++]=pos[j++];
    		}else if(j>end){
    			smaller[pos[i]]+=count;
    			temp[k++]=pos[i++];
    		}else if(nums[pos[i]]<=nums[pos[j]]){
    			smaller[pos[i]]+=count;
    			temp[k++]=pos[i++];
    		}else{
    			count++;
    			temp[k++]=pos[j++];
    		}
    		
    	}    	
    	for(i=0; i<k; i++){
    		pos[start+i]=temp[i];
    	}    	
    }
    
    public static List<Integer> countSmaller(int[] nums) {
    	List<Integer> re=new ArrayList<Integer>();
    	if(nums==null||nums.length==0){
    		return re;
    	}
    	int[] smaller=new int[nums.length];
    	int[] pos=new int[nums.length];
    	for(int i=0; i<pos.length; i++){
    		pos[i]=i;
    	}
    	reverse(nums, smaller, pos, 0, nums.length-1);
    	for(int i=0; i<smaller.length; i++){
    		re.add(smaller[i]);
    	}
    	return re;
    }
    
	public static void main(String[] args) {
		System.out.println(countSmaller(new int[]{5,2,6,1}));
//		int[] nums1={1,2};
//		int[] nums2={1,2};
//		System.out.println(findMedianSortedArrays(nums1, nums2));
	}

}
