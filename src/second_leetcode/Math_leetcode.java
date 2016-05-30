package second_leetcode;

public class Math_leetcode {

	
	//50. Pow(x, n)
    public double myPow(double x, int n) {
        if(n<0){
        	if(n==Integer.MIN_VALUE){
        		return 1.0/myPow(x, Integer.MAX_VALUE)*x;
        	}else{
        		return 1.0/myPow(x, -n);
        	}
        }
        if(n==0){
        	return 1.0;
        }
        double re=1.0;
        while(n>0){
        	if((n&1)==1){
        		re*=x;
        	}
        	x*=x;
        	n>>=1;
        }
    	return re;	    	
    }
    
    //169. Majority Element
    public int majorityElement(int[] nums) {
        int re=0;
        int count=0;
        for(int i=0; i<nums.length; i++){
        	if(nums[i]==re){
        		count++;
        	}else if(count==0){
        		re=nums[i];
        		count=1;
        	}else{
        		count--;
        	}
        }
        return re;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
