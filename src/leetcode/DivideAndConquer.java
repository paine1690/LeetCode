package leetcode;




import java.util.ArrayList;
import java.util.Arrays;
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
    
    //315. Count of Smaller Numbers After Self   11ms
    private static void reverse(int[] nums, Integer[] smaller, int[] pos, int start, int end){
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
    			int aaa=pos[i];
    			smaller[aaa]+=count;
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
    	Integer[] smaller=new Integer[nums.length];
    	Arrays.fill(smaller, 0);
    	int[] pos=new int[nums.length];
    	for(int i=0; i<pos.length; i++){
    		pos[i]=i;
    	}
    	reverse(nums, smaller, pos, 0, nums.length-1);
    	re=new ArrayList<Integer>(Arrays.asList(smaller));
    	return re;
    }
    
    //215. Kth Largest Element in an Array
    private static int partition2(int[] nums, int start, int end){
    	int x=nums[start];
    	int i=start, j=end;
    	while(i<j){
    		while(i<j&&nums[j]>=x){
    			j--;
    		}
    		if(i<j){
    			nums[i++]=nums[j];
    		}
    		while(i<j&&nums[i]<x){
    			i++;
    		}
    		if(i<j){
    			nums[j--]=nums[i];
    		}
    	}
    	nums[i]=x;
    	return i;
    }
    
    private static void random(int[] nums, int start, int end){
    	Random random=new Random();
    	int r=start+random.nextInt(end-start+1);
    	int temp=nums[start];
    	nums[start]=nums[r];
    	nums[r]=temp;
    }
    
    private static int div(int[] nums, int start, int end, int k){
    	random(nums, start, end);
    	int mid=partition2(nums, start, end);
    	//System.out.println(start+" "+end+" "+mid+" "+k);
    	int th=end-mid+1;
    	if(th==k){
    		return nums[mid];
    	}else if(th>k){
    		return div(nums, mid+1, end, k);
    	}else{
    		return div(nums, start, mid-1, k-th);
    	}
    }
    
    public static int findKthLargest2(int[] nums, int k) {
        return div(nums, 0, nums.length-1, k);
    }
    
    //241. Different Ways to Add Parentheses    
    public static List<Integer> diffWaysToCompute(String input) {
    	List<Integer> re=new ArrayList<Integer>();    	
    	for(int i=1; i<input.length(); i++){
    		char c=input.charAt(i);
    		if(c=='+'||c=='-'||c=='*'){
    			List<Integer> l1=diffWaysToCompute(input.substring(0, i));
        		List<Integer> l2=diffWaysToCompute(input.substring(i+1, input.length()));
        		for(Integer a: l1){
        			for(Integer b: l2){
        				if(c=='+'){
        					re.add(a+b);
        				}else if(c=='-'){
        					re.add(a-b);
        				}else{
        					re.add(a*b);
        				}
        			}
        		}        		
    		}    		
    	}
    	if(re.isEmpty()){
    		re.add(Integer.valueOf(input));
    	}
    	return re;
    }
    
    //4. Median of Two Sorted Arrays
    private static double kThofTwoArray(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int k){
    	System.out.println(s1+" "+e1+" "+s2+" "+e2+" "+k);
    	int l1=e1-s1+1;
    	int l2=e2-s2+1;
    	if(l1>l2){
    		return kThofTwoArray(nums2, s2, e2, nums1, s1, e1, k);
    	}
    	if(l1==0){
    		return nums2[s2+k-1];
    	}
    	if(k==1){
    		return Math.min(nums1[s1], nums2[s2]);
    	}
    	
    	int m=Math.min(k/2, l1);
    	int n=k-m;
    	int num1=nums1[s1+m-1], num2=nums2[s2+n-1];
    	if(num1<num2){
    		return kThofTwoArray(nums1, s1+m, e1, nums2, s2, e2, k-m);
    	}else if(num1>num2){
    		return kThofTwoArray(nums1, s1, e1, nums2, s2+n, e2, k-n);
    	}else{
    		return nums1[s1+m-1];
    	}
    	
    }
    
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len=nums1.length+nums2.length;
        if((len&1)==1){
        	return kThofTwoArray(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, len/2+1);
        }else{
        	return (kThofTwoArray(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, len/2)
        			+kThofTwoArray(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, len/2+1))/2.0;
        }
    }
    
	public static void main(String[] args) {
//		System.out.println(diffWaysToCompute("2*3-4*5"));
//		System.out.println(findKthLargest2(new int[]{2,1}, 1));
//		System.out.println(countSmaller(new int[]{5,2,6,1}));	
		int[] nums1={2,4,6,7,8,9,10};
		int[] nums2={1,3,5};
		System.out.println(findMedianSortedArrays2(nums1, nums2));
	}

}
