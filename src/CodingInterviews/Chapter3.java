package codingInterviews;

import java.util.Arrays;

public class Chapter3 {
	
	
	
	
	
	/*
	 * 11、数值的整数次方
	 */
	
	public static double pow(double x, int n){
		if(n<0){
			if(n==Integer.MIN_VALUE){
				return 1/pow(x, Integer.MAX_VALUE)*x;
			}else{
				return 1/pow(x, -n);
			}
		}
		if(n==0){
			return 1.0;
		}
		double re=1.0;
		while(n!=0){
			if((n&1)==1){
				re*=x;
			}
			x*=x;
			n>>=1;
		}
		return re;		
	}
	
	
	/*
	 * 12、 打印从1到最大的n位数
	 * 注意数字溢出  数组 回溯
	 */
	private static void printNums(int[] nums){
		int i=0;
		while(i<nums.length&&nums[i]==0){
			i++;
		}
		StringBuilder s=new StringBuilder();
		for(; i<nums.length; i++){
			s.append(nums[i]);
		}		
		System.out.println(s.toString());
	}
	
	private static void dfsNums(int[] nums, int index){
		if(index==nums.length){
			printNums(nums);
			return;
		}
		for(int i=0; i<10; i++){
			nums[index]=i;
			dfsNums(nums, index+1);
		}		
	}
	
	public static void printNums(int n){
		int[] nums=new int[n];
		dfsNums(nums, 0);
		
	}
	
	/*
	 * 13、O(1)时间删除链表结点
	 * 
	 * leetcode 237. Delete Node in a Linked List
	 */
	
	
	/*
	 * 14、 调整数组顺序使奇数位于偶数前面
	 */
	public static void adjust(int[] nums){
		int i=0, j=nums.length-1;
		while(i<=j){
			if((nums[i]&1)==1){
				i++;
				continue;
			}
			if((nums[j]&1)!=1){
				j--;
				continue;
			}
			int temp=nums[i];
			nums[i]=nums[j];
			nums[j]=temp;
			i++;
			j--;
		}
		System.out.println(Arrays.toString(nums));
	}
	
	
	
	public static void main(String[] args) {
		printNums(3);
		//System.out.println(pow(0, -5));
	}

}














