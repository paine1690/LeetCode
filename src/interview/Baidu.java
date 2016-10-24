package interview;

import java.util.Arrays;

public class Baidu {

	/*
	 * 6×9的的方格中，起点的左下角，终点在右上角，从起点到终点，只能从下向上，从左向右走，问一共有多少种不同的走法。
		A.  4200
		B.   5005
		C.  1005
		D.  以上都不正确
		
		
		
		原来不是卡特兰数
	 * 
	 * 
	 */
	
	public static int Catlan(int m, int n){
		if(m<1||n<1){
			return 1;
		}
		return Catlan(m-1, n)+Catlan(m, n-1);		
	}
	
	private static void test(){
		int m=7, n=10;
		int[][] nums=new int[m][n];
		for(int i=0; i<n; i++){
			nums[0][i]=1;
		}
		
		for(int i=1; i<m; i++){
			nums[i][0]=1;
			for(int j=1; j<n; j++){
				nums[i][j]=nums[i-1][j]+nums[i][j-1];
			}
		}
		for(int i=0; i<m; i++){
			System.out.println(Arrays.toString(nums[i]));
		}
		
	}
	public static void main(String[] args) {
		test();
		System.out.println(Catlan(6, 9));

	}

}
