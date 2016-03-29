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
	
    public static int singleNumber2(int[] nums) {
    	if(nums.length==1){
    		return nums[0];
    	}
        int re=0;
        
        for(int i=0; i<32; i++){
        	int temp=0;
        	for(int num: nums){
        		temp+=(num>>i)&1;
        	}
        	temp=temp%3;
        	re|=(temp<<i);
        }
    	return re;
    }
    
    public static int[] singleNumber3(int[] nums) {
        int[] re=new int[2];
        int bit=0;
        for(int num: nums){
        	bit^=num;
        }
        bit=bit&(-bit);
        for(int num:nums){
        	if((bit&num)==0){
        		re[0]^=num;
        	}else{
        		re[1]^=num;
        	}
        }
        return re;    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={1,1,2,2,3,3,4,4,5,6};
		System.out.println(Arrays.toString(singleNumber3(nums)));
	}

}
