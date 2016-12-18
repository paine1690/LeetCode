package leetcode;

/*
 *                 总结
 *    位运算
 * 常用的位运算，  ^ | ~ ^ >> <<
 * 
 * 与运算 ^
 * 		1、判断int a奇偶  
 * 			a&1==1 奇数
 * 		2、判断int a是否为2的幂  
 * 			return a&(a-1)==0&&a>0
 * 
 * 移位运算 <<
 * 		3、取int a的第i位     
 * 			(a>>i)&1
 * 		4、求两数平均值            
 * 			int mid=(left+right)>>>1//不会溢出
 * 		5、将int a的第i位清零/置一         
 * 			a=a&~(a<<i)   
 * 			a=a|(a<<i)
 * 异或 ^
 * 		6、int a两次异或同一个数，还是a
 * 			a^x^x==a
 * 		7、交换两个数的值，不用额外的空间
 * 		void swap(int a, int b){
 * 			a^=b;
 * 			b^=a;
 * 			a^=b;
 * 		}
 * 		8、求绝对值
 * 		int abs(int x){
 * 			int y=x>>31;
 * 			return (x^y)-y;
 * 		}
 * 		
 * 补码:计算机中数值都是以补码的存储的.补码=反码+1
 * 		1、-x=~+1=~(x-1)
 * 		2、x的二进制中右边第一个不为0的位
 * 			x&(-x)
 * 		3、x的二进制中右边第一个不为0的位变为0
 * 			x&(x-1)
 *  
 */

public class BitManipulation {
	
	//338. Counting Bits
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
	
	//136. Single Number
	public static int singleNumber(int[] nums) {
        int re=0;
		for(int num: nums){
			re^=num;
		}
		return re;
		
    }
	
	//137. Single Number II
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
    
    //260. Single Number III
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
    
    //169. Majority Element
    public static int majorityElement(int[] nums) {
    	int re=0;
    	int count=0;
    	for(int i=0; i<nums.length; i++){
    		if(count==0){
    			re=nums[i];
    			count=1;
    		}else{
    			if(nums[i]==re){
    				count++;
    			}else{
    				count--;
    			}
    		}
    	}
    	return re;
    }
    
    //268. Missing Number
    public static int missingNumber(int[] nums) {
    	int sum=0;
    	for(int num: nums){
    		sum+=num;
    	}
    	int re=((1+nums.length)*nums.length>>>1)-sum;
        return re;
    }
    
    //268. Missing Number
    public static int missingNumber2(int[] nums) {
    	int re=0;
    	for(int i=0; i<nums.length; i++){
    		re^=(nums[i]^i);
    	}
        return re^nums.length;
    }
    
    //191. Number of 1 Bits
	public static int hammingWeight(int n) {
		int re = 0;
		for (int i = 0; i < 32; i++) {
			if (((n >> i) & 1) == 1) {
				re++;
			}
		}
		return re;
	}
	
	//191. Number of 1 Bits
	public static int hammingWeight2(int n) {
		int re = 0;
		while(n!=0){
			re++;
			n&=(n-1);
		}
		return re;
	}
	
	//231. Power of Two
    public static boolean isPowerOfTwo(int n) {
    	return (n&(n-1))==0&&n!=0;
    }
    
    //190. Reverse Bits
    public static int reverseBits(int n) {
    	int re=0;
        for(int i=0; i<32; i++){
        	re=(re<<1)|((n>>i)&1);
        }
    	return re;
    }
    
    //190. Reverse Bits
    public static int reverseBits2(int n) {
    	long re=0L;
        long max=2147483648L;
        long x=(long)n;
        long bit=x&(-x);
        while(bit!=0){
        	re|=(max/bit);
        	x=x&(x-1);
        	bit=x&(-x);
        }
    	return (int)re;
    }
    
    //201. Bitwise AND of Numbers Range
    public static int rangeBitwiseAnd(int m, int n) {
        int bit=0;
        while(m!=n){
        	m>>=1;
        	n>>=1;
        	bit++;
        }
    	return m<<bit;
    }
    
    //201. Bitwise AND of Numbers Range
    public static int rangeBitwiseAnd2(int m, int n) {
        while(n>m){
        	n=n&(n-1);
        }
    	return n;
    }

    //342. Power of Four
    public static boolean isPowerOfFour(int num) {
    	return num>0&&((num&(num-1))==0)&&((num&0x55555555)==num);
    }
    
    //137. Single Number II
    public int singleNumber4(int[] nums) {
    	int one=0;
    	int two=0;
    	for(int i=0; i<nums.length; i++){
    		two|=nums[i]&one;//先计算出现两次的
    		one^=nums[i];//再更新出现一次的
    		int three=one&two;//出现三次
    		one&=~three;//出现三次就清零
    		two&=~three;//出现三次就清零
    	}
    	return one;
    }
    
    //397. Integer Replacement
    public int integerReplacement(int n) {
        int re=0;    	
        while(n!=1){
        	if((n&1)==0){
        		n>>>=1;
        	}else if(n==3||((n>>>1)&1)==0){
        		n--;
        	}else{
        		n++;
        	}
        	re++;
        }
    	return re;
    }
    
    //389. Find the Difference
    public char findTheDifference(String s, String t) {
        char re=0;
        for(char c: s.toCharArray()){
        	re^=c;
        }
        for(char c: t.toCharArray()){
        	re^=c;
        }        
        return re;
    }
    
    //393. UTF-8 Validation
    private static int nBytes(int num){
    	int judge=(1<<7);
    	for(int i=0; i<=4; i++){
    		//System.out.println(judge);
    		if((num&judge)==0){
    			System.out.println(i);
    			return i;
    		}
    		judge>>=1;
    	}
    	return -1;    	
    }
    
    
    public static boolean validUtf8(int[] data) {
        int i=0;
        while(i<data.length){
        	int n=nBytes(data[i]);
        	if(n<0){
        		return false;
        	}
        	if(n==0){
        		i++;
        		continue;
        	}else if(n==1){
        		return false;
        	}else if(n<=4){
        		for(int j=1; j<n; j++){
        			if(++i>=data.length||nBytes(data[i])!=1){
        				return false;
        			}
        		}
        		i++;
        	}else{
        		return false;
        	}
        }
        return true;
    }
    
    //458. Poor Pigs
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int re=0;
    	int base=minutesToTest/minutesToDie+1;
    	int fac=1;
    	
    	while(fac<buckets){
    		fac*=base;
    		re++;
    	}
    	return re;
    }
    
    //461. Hamming Distance
    public static int hammingDistance(int x, int y) {
        int re=0;
        int num=x^y;
        while(num!=0){
        	num&=(num-1);
        	re++;
        }
        return re;
    }
    
    //477. Total Hamming Distance
    public static int totalHammingDistance(int[] nums) {
        int re=0;
        int[] bits=new int[32];
        for(int i=0; i<bits.length; i++){
        	for(int j=0; j<nums.length; j++){
        		if((nums[j]&1)==1){
        			bits[i]++;
        		}
        		nums[j]>>=1;
        	}
        }
        int len=nums.length;
        for(int i=0; i<bits.length; i++){
        	re+=(bits[i])*(len-bits[i]);
        }
        return re;
    }
    
	public static void main(String[] args) {
		System.out.println(validUtf8(new int[]{240,162,138,147}));
		
	}

}
