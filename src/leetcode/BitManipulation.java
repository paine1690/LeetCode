package leetcode;
import java.util.Arrays;

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
    
    public static int missingNumber(int[] nums) {
    	int sum=0;
    	for(int num: nums){
    		sum+=num;
    	}
    	int re=((1+nums.length)*nums.length>>>1)-sum;
        return re;
    }
    
    public static int missingNumber2(int[] nums) {
    	int re=0;
    	for(int i=0; i<nums.length; i++){
    		re^=(nums[i]^i);
    	}
        return re^nums.length;
    }
    
	public static int hammingWeight(int n) {
		int re = 0;
		for (int i = 0; i < 32; i++) {
			if (((n >> i) & 1) == 1) {
				re++;
			}
		}
		return re;
	}
	
	public static int hammingWeight2(int n) {
		int re = 0;
		while(n!=0){
			re++;
			n&=(n-1);
		}
		return re;
	}
	
    public static boolean isPowerOfTwo(int n) {
    	return (n&(n-1))==0&&n!=0;
    }
    
    public static int reverseBits(int n) {
    	int re=0;
        for(int i=0; i<32; i++){
        	re=(re<<1)|((n>>i)&1);
        }
    	return re;
    }
    
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
    
	public static void main(String[] args) {
		int[] nums={1,2,3,4,5,6,7};
		
		System.out.println(reverseBits2(43261596));
		
	}

}
