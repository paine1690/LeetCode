package leetcode;

import java.util.HashSet;
import java.util.Set;

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
    
    //223. Rectangle Area  计算两个矩形的重叠面积
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int re=(C-A)*(D-B)+(G-E)*(H-F);
              
        int a1=Math.max(A, E);
        int a2=Math.min(C, G);
        int a=0,b=0;
        if(a2>a1){
        	a=a2-a1;
        }
        int b1=Math.max(B, F);
        int b2=Math.min(D, H);
        if(b2>b1){
        	b=b2-b1;
        }
        return re-a*b;
    }
    
    //202. Happy Number
    public static boolean isHappy(int n) {
        Set<Integer> set=new HashSet<Integer>();
        while(n!=1){
        	if(set.contains(n)){
        		return false;
        	}else{
        		set.add(n);
        		int temp=0;
        		while(n>0){
        			int i=n%10;
        			temp+=i*i;
        			n/=10;
        		}
        		n=temp;
        	}
        }
    	return true;
    }
    
    //67. Add Binary
    public static String addBinary(String a, String b) {
        String s="";
        int i=a.length()-1;
        int j=b.length()-1;
        int flag=0;
        while(i>=0&&j>=0){
        	int temp=a.charAt(i--)-'0'+b.charAt(j--)-'0'+flag;
        	if(temp>1){
        		temp-=2;
        		flag=1;
        	}else{
        		flag=0;
        	}
        	s=temp+s;
        }
        while(i>=0){
        	int temp=a.charAt(i--)-'0'+flag;
        	if(temp>1){
        		temp-=2;
        		flag=1;
        	}else{
        		flag=0;
        	}
        	s=temp+s;
        }
        while(j>=0){
        	int temp=b.charAt(j--)-'0'+flag;
        	if(temp>1){
        		temp-=2;
        		flag=1;
        	}else{
        		flag=0;
        	}
        	s=temp+s;
        }
        if(flag==1){
        	s=1+s;
        }
        return s;
    }
    
    //172. Factorial Trailing Zeroes
    public static int trailingZeroes(int n) {
        int re=0;
        while(n>0){
        	re+=n/5;
        	n/=5;
        }
        return re;
    }
    
    //43. Multiply Strings
    public static String multiply(String num1, String num2) {
    	long re=0;
    	int ji=0;
    	int n1=num1.length()-1, n2=num2.length()-1;
    	while(n1>0){
    		if(num1.charAt(n1)=='0'){
    			ji++;
    			n1--;
    			continue;
    		}
    		break;
    	}
    	while(n2>0){
    		if(num2.charAt(n2)=='0'){
    			ji++;
    			n2--;
    			continue;
    		}
    		break;
    	}
    	
    	for(int i=0; i<=n1; i++){
    		int nums1=num1.charAt(i)-'0';
    		long temp=0;
    		for(int j=0; j<=n2; j++){
    			int nums2=num2.charAt(j)-'0';
    			temp=temp*10+nums1*nums2;
    		}
    		re=re*10+temp;
    	}
    	StringBuilder s=new StringBuilder();
    	for(int i=0; i<ji; i++){
    		s.append("0");
    	}
    	String result=String.valueOf(re);
    	System.out.println(result);
    	if(re!=0){
    		result+=s.toString();
    	}    	
    	return result;
    }
    
    //343. Integer Break
    public static int integerBreak(int n) {
        if(n<4){
        	return n-1;
        }
        int re=1;
        while(n>4){
        	re*=3;
        	n-=3;
        }
        return re*n;
    }
    
    
	public static void main(String[] args) {
		System.out.println(integerBreak(5));

	}

}

