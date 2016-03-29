import java.util.Arrays;

public class BitManipulation {
	/*
	 * 136.Single Number
	 */
	
	public static int[] countBits(int num) {
        int[] re=new int[num+1];
        re[0]=0;
        int step=1;
        int i=1;
        while(i<=num){
        	for(int j=0; j<step&&i<=num; j++){
        		re[i++]=re[j]+1;
        	}
        	step=step*2;
        }
        return re;
    }
	
	public static int singleNumber(int[] nums) {
        int re=0;
		for(int num: nums){
			re^=num;
		}
		return re;
		
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={1,1,2,2,3,3,4,5,4};
		System.out.println(singleNumber(nums));
	}

}
