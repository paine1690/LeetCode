package interview;

import java.util.Arrays;

public class Tencent {

	/*
	 * 腾讯在线笔试 
	 * 
	 * 蛇形矩阵
	 */
	public static void shexingjuzhen(int n){
		int count=n*n;
		int[][] nums=new int[n][n];
		int flag=1;
		int i=0, j=0, num=1;
		
		while(num<=count){
			if(flag==1){
				if(j<n&&nums[i][j]==0){
					nums[i][j]=num++;
					j++;
				}else{
					j--;
					i++;
					flag=2;
				}
			}else if(flag==2){
				if(i<n&&nums[i][j]==0){
					nums[i][j]=num++;
					i++;
				}else{
					i--;
					j--;
					flag=3;
				}
			}else if(flag==3){
				if(j>=0&&nums[i][j]==0){
					nums[i][j]=num++;
					j--;
				}else{
					j++;
					i--;
					flag=4;
				}
			}else{
				if(i>=0&&nums[i][j]==0){
					nums[i][j]=num++;
					i--;
				}else{
					i++;
					j++;
					flag=1;
				}
			}
		}
		for(i=0; i<nums.length; i++){
			System.out.println(Arrays.toString(nums[i]));
		}
	}
	public static void main(String[] args) {
		shexingjuzhen(4);

	}

}
