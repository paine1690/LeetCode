package leetcode;

import java.util.Arrays;

public class Heap {

	
    
    //215. Kth Largest Element in an Array
	private static void heapAdjust(int[] nums, int i){
		int left=2*i+1;
		int right=left+1;
		int min=i;
		while(left<nums.length){
			if(nums[left]<nums[min]){
				min=left;
			}
			if(right<nums.length&&nums[right]<nums[min]){
				min=right;
			}
			if(min!=i){
				int temp=nums[min];
				nums[min]=nums[i];
				nums[i]=temp;
				i=min;
				left=2*i+1;
				right=left+1;
			}else{
				break;
			}			
		}		
	}
	
	private static void heapBuild(int[] nums){
		for(int i=nums.length/2-1; i>=0; i--){
			heapAdjust(nums, i);
		}	
	}	
	
	public static int findKthLargest(int[] nums, int k) {
		int[] heap=new int[k];
		for(int i=0; i<k; i++){
			heap[i]=nums[i];
		}
		heapBuild(heap);
		System.out.println(Arrays.toString(heap));
		for(int i=k; i<nums.length; i++){
			if(nums[i]>heap[0]){
				heap[0]=nums[i];
				heapAdjust(heap, 0);
			}
		}
		return heap[0];
	}
    
    //378. Kth Smallest Element in a Sorted Matrix
    public int kthSmallest(int[][] matrix, int k) {
        int[] heap=new int[k];
        int n=matrix.length, len=n*n;
        
        int c=0;
        int i, j;
        while(c<k){
        	i=c/n;
        	j=c%n;
        	heap[c]=matrix[i][j];
        	c++;
        }
        heapBuild(heap);
        while(c<len){
        	int num=matrix[c/n][c%n];
        	if(num<heap[0]){
        		heap[0]=num;
        		heapAdjust(heap, 0);
        	}     
        	c++;
        }
        return heap[0];
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={3,2,1,5,6,4};
		System.out.println(findKthLargest(nums, 2));

	}

}
