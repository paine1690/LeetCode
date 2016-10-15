package interview;

import java.util.Arrays;

public class Prepare {

	
	
	private static int partition(int[] nums, int start, int end){
		int i=start, j=end;
		int x=nums[i];
		while(i<j){
			while(i<j&&bigger(nums[j], x)){
				j--;
			}
			if(i<j){
				nums[i++]=nums[j];
			}
			while(i<j&&bigger(x, nums[i])){
				i++;
			}
			if(i<j){
				nums[j--]=nums[i];
			}
		}
		nums[i]=x;
		System.out.println(Arrays.toString(nums));
		return i;
	}
	
	private static boolean bigger(int a, int b){
		String s1=String.valueOf(a);
		String s2=String.valueOf(b);
		
		if(s1.length()>s2.length()){
			return !bigger(b, a);
		}
		
		int i=0, j=0;
		while(i<s1.length()){
			if(s1.charAt(i)<s2.charAt(j)){
				return false;
			}
			else if(s1.charAt(i)>s2.charAt(j)){
				return true;
			}
			i++;
			j++;
		}
		return true;
	}
	
	private static void quik(int[] nums, int start, int end){
		
		if(start<end){
			int mid=partition(nums, start, end);
			quik(nums, start, mid-1);
			quik(nums, mid+1, end);
		}
	}
	
	
	public static void main(String[] args) {
		int[] nums={9,99,909,9901,8, 0};
		quik(nums, 0, nums.length-1);
		System.out.println(Arrays.toString(nums));
		
	}

}
