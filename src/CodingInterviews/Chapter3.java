package CodingInterviews;

public class Chapter3 {
	
	/*
	 * 12
	 * 打印从1到最大的n位数
	 * 注意数字溢出
	 */
	
	private static void print(int[] nums){
		//System.out.println(Arrays.toString(nums));
		int i=0;
		while(i<nums.length&&nums[i]==0){
			i++;
		}
		for(; i<nums.length; i++){
			System.out.print(nums[i]);
		}
		System.out.println("");
	}
	private static void printNum(int[] nums, int n, int index){
		if(n==0){
			print(nums);
			return;
		}
		for(int i=0; i<10; i++){
			nums[index]=i;
			printNum(nums, n-1, index+1);
		}	
	}
	
	public static void printN(int n){
		int[] nums=new int[n];
		for(int i=0; i<10; i++){
			nums[0]=i;
			printNum(nums, n-1, 1);
		}
	}
	
	
	public static void main(String[] args) {
		printN(4);
	}

}
