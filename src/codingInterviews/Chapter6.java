package codingInterviews;

public class Chapter6 {

	
	
	
	/*
	 * 47、不用加减乘除做加法
	 */
	public static int add(int num1, int num2){		
		while(num2!=0){
			int sum=num1^num2;
			int carry=(num1&num2)<<1;
			num1=sum;
			num2=carry;
		}	
		return num1;
	}
	
	
	public static void main(String[] args) {
		System.out.println(add(-1, -2));

	}

}
