package leetcode;


public class Math_leetcode {

	//263. Ugly Number
    public boolean isUgly(int num) {
    	if(num<1){
        	return false;
        }
        while(num%2==0){
    		num/=2;
    	}
    	while(num%3==0){
    		num/=3;
    	}
    	while(num%5==0){
    		num/=5;
    	}
    	if(num==1){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    //204. Count Primes		素数筛法
    public int countPrimes(int n) {
        if(n<2){
            return 0;
        }
        int re=0;
        int half=n>>1;
		int sqrt=(int)Math.sqrt(n);
		boolean[] prime=new boolean[half];
		for(int i=0; i<half; i++){
			prime[i]=true;
		}
		for(int i=0; i<=sqrt; i++){
			if(prime[i]){
				for(int k=i+i+3, j=k*i+k+i; j<half; j+=k){
					//k=2*i+3,即i对应的素数，起点是k的平方，即2*j=k*k,然后依次递增k
					prime[j]=false;
				}
			}
		}
		
		for(int i=0; i<n; i++){
			if(prime[i]){
				re++;
			}
		}
		return re;
    }
    
    //326. Power of Three
    public static boolean isPowerOfThree(int n) {
    	if(n==0){
    		return false;
    	}
        double temp=Math.log(n)/Math.log(3);
        return Math.abs(temp-Math.rint(temp))<0.0000000001; 
    }
    
    //258. Add Digits
    public int addDigits(int num) {
        return (num-1)%9+1;
    }
    
    //66. Plus One
    public int[] plusOne(int[] digits) {
    	int flag=0;
    	if(++digits[digits.length-1]>9){
    		digits[digits.length-1]%=10;
    		flag=1;
    	}
        for(int i=digits.length-2; i>=0; i--){
        	if(flag==1){
        		if(++digits[i]>9){
        			digits[i]%=10;
        			flag=1;
        		}else{
        			flag=0;
        		}
        	}else{
        		break;
        	}
        }
    	if(flag==1){
    		int[] re=new int[digits.length+1];
    		re[0]=1;
    		for(int i=0; i<digits.length; i++){
    			re[i+1]=digits[i];
    		}
    		return re;
    	}
    	return digits;
    }
    
    //168. Excel Sheet Column Title
    public static String convertToTitle(int n) {
        String s="";
        while(n>0){
        	int a=(n-1)%26;
        	s=(char)('A'+a)+s;
        	n=(n-1)/26;
        }        
        return s;
    }
    
    //171. Excel Sheet Column Number
    public static int titleToNumber(String s) {
        int re=0;
        for(int i=0; i<s.length(); i++){
        	re=re*26+s.charAt(i)-'A'+1;
        }
        return re;
    }
	public static void main(String[] args) {
		System.out.println(titleToNumber("AA"));

	}

}

