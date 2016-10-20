package codingInterviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Chapter5 {

	/*
	 * 29、数组中超过一半的数字
	 */
    public static int MoreThanHalfNum_Solution(int [] array) {
        int re=0, c=0;
        
        for(int i=0; i<array.length; i++){
        	if(array[i]==re){
        		c++;
        	}else if(c==0){
        		re=array[i];
        		c++;
        	}else{
        		c--;
        	}
        }        
        int cnt=0;
        for(int i=0; i<array.length; i++){
        	if(array[i]==re){
        		cnt++;
        	}
        }
        return cnt>array.length/2? re: 0;   	
    }
	
	/*
	 * 30、最小的K个数
	 */
    private static void adjust(int[] heap, int i){
    	int max=i, left=2*i+1, right=left+1;
    	while(left<heap.length){
    		if(heap[left]>heap[i]){
    			max=left;
    		}
    		if(right<heap.length&&heap[right]>heap[max]){
    			max=right;
    		}
    		
    		if(max!=i){
    			int temp=heap[i];
    			heap[i]=heap[max];
    			heap[max]=temp;
    			i=max;
    			left=2*i+1;
    			right=left+1;
    		}else{
    			break;
    		}
    	}
    }
    
    private static void heapBuild(int[] heap){
    	for(int i=heap.length/2-1; i>=0; i--){
    		adjust(heap, i);
    	}
    }
    
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
    	ArrayList<Integer> re=new ArrayList<Integer>();
    	if(k==0||k>input.length){
    		return re;
    	}
    	int[] heap=new int[k];
    	for(int i=0; i<heap.length; i++){
    		heap[i]=input[i];
    	}
    	heapBuild(heap);
    	for(int i=heap.length; i<input.length; i++){
    		if(input[i]<heap[0]){
    			heap[0]=input[i];
    			adjust(heap, 0);    			
    		}
    	}
    	
    	for(int i=0; i<heap.length; i++){
    		re.add(heap[i]);
    	}
    	return re;
    } 
    
    /*
     * 31、连续子数组的最大和
     */
    public static int FindGreatestSumOfSubArray(int[] array) {
    	if(array.length==0){
    		return 0;
    	}
    	int re=array[0];
        for(int i=1; i<array.length; i++){
        	array[i]=Math.max(array[i], array[i]+array[i-1]);
        	re=Math.max(re, array[i]);
        }
        return re;
    }
    
    /*
     * 32、1到n整数中1出现的次数
     */
    public static int NumberOf1Between1AndN_Solution(int n) {
        int re=0;
        int factor=1;
       
        int low=0, cur=0, high=0;
        while(n/factor>0){
        	high=n/factor/10;
        	cur=(n/factor)%10;
        	low=n%factor;
        	
        	
        	if(cur==0){
        		re+=high*factor;
        	}else if(cur==1){
        		re+=high*factor+low+1;
        	}else{
        		re+=(high+1)*factor;
        	}
        	factor*=10;
        }
        return re;
    }
    
    /*
     * 33、把数组排成最小的数
     */
    public static String PrintMinNumber(int [] numbers) {
    	Integer[] nums=new Integer[numbers.length];
    	for(int i=0; i<numbers.length; i++){
    		nums[i]=numbers[i];
    	}
    	Arrays.sort(nums, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				String s1=String.valueOf(o1);
				String s2=String.valueOf(o2);
				String left=s1+s2;
				String right=s2+s1;
				return left.compareTo(right);
			}
    		
    	});
    	StringBuilder s=new StringBuilder();
    	for(int i=0; i<nums.length; i++){
    		s.append(nums[i]);
    	}
    	return s.toString();
    }
    
    /*
     * 34、丑数
     */
    public int GetUglyNumber_Solution(int index) {
    	if(index==0){
    		return 0;
    	}
    	int[] nums=new int[index];
    	nums[0]=1;
    	int c2=0, c3=0, c5=0;
    	int v2=nums[c2]*2, v3=nums[c3]*3, v5=nums[c5]*5; 
    	for(int i=1; i<nums.length; i++){
    		nums[i]=Math.min(v2, Math.min(v3, v5));
    		if(nums[i]==v2){
    			c2++;
    			v2=nums[c2]*2;
    		}
    		if(nums[i]==v3){
    			c3++;
    			v3=nums[c3]*3;
    		}
    		if(nums[i]==v5){
    			c5++;
    			v5=nums[c5]*5;
    		}
    	}
    	
        return nums[nums.length-1];
    }
    
    /*
     * 35、第一个只出现一次的字符
     */
    public int FirstNotRepeatingChar(String str) {
    	int[] pos=new int[128];
    	for(int i=0; i<str.length(); i++){
    		char c=str.charAt(i);
    		pos[c]++;
    	}
    	for(int i=0; i<str.length(); i++){
    		if(pos[str.charAt(i)]==1){
    			return i;
    		}
    	}
    	
        return -1;
    }
    
    /*
     * 36、求数组中的逆序对
     */
    static int re;
    
    private static void merge(int[] nums, int start, int mid, int end){
    	int i=start, j=mid+1, k=0;
    	int[] temp=new int[end-start+1];
    	while(i<=mid&&j<=end){
    		if(nums[i]<=nums[j]){
    			temp[k++]=nums[i++];
    		}else{
    			temp[k++]=nums[j++];
    			re+=(mid-i+1);
    		}
    	}
    	while(i<=mid){
    		temp[k++]=nums[i++];
    	}
    	while(j<=end){
    		temp[k++]=nums[j++];
    	}
    	
    	for(k=0; k<temp.length; k++){
    		nums[start+k]=temp[k];
    	}
    }
    
    private static void mergeSort(int[] nums, int start, int end){
    	if(start<end){
    		int mid=start+(end-start)/2;
    		mergeSort(nums, start, mid);
    		mergeSort(nums, mid+1, end);
    		merge(nums, start, mid, end);
    	}
    }
    
    public static int InversePairs(int [] array) {
        re=0;
        mergeSort(array, 0, array.length-1);
    	return re;
    }
    
    /*
     * 37、两个链表的第一个公共结点
     */
    private int getLength(ListNode head){
    	int re=0;
    	ListNode pNode=head;
    	while(pNode!=null){
    		pNode=pNode.next;
    		re++;
    	}
    	return re;
    }    
    
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
    	int m=getLength(pHead1);
    	int n=getLength(pHead2);
    	int diff=0;
    	ListNode lNode, sNode;
    	if(m>=n){
    		diff=m-n;
    		lNode=pHead1;
    		sNode=pHead2;
    	}else{
    		diff=n-m;
    		lNode=pHead2;
    		sNode=pHead1;
    	}
    	
    	while(diff>0){
    		lNode=lNode.next;
    		diff--;
    	}
    	
    	while(lNode!=null){
    		if(lNode==sNode){
    			return lNode;
    		}
    		lNode=lNode.next;
    		sNode=sNode.next;
    	}    	
    	return null;
    }
    
	public static void main(String[] args) {
		System.out.println(NumberOf1Between1AndN_Solution(5));
		//System.out.println(InversePairs(new int[]{}));

	}

}
