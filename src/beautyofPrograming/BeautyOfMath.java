package beautyofPrograming;

import java.util.Arrays;

public class BeautyOfMath {

	/*
	 * 求二进制中1的个数 
	 */
	//leetcode 191. Number of 1 Bits
	
	/*
	 * 2.2就n的阶乘中二进制最后一位1的位置
	 */
	public static int jiechentg(int n){
		int re=0;
		while(n>0){
			n>>=1;
			re+=n;
		}		
		return re;
	}
	
	/*
	 * 2.3频率最高的元素 
	 */
	//leetcode 169. Majority Element
	//leetcode 229. Majority Element II
	
	/*
	 * 2.41到n中1出现的次数
	 */
	public static int count1(int n){
		int re=0;
		int factor=1;
		int temp;		
		int high=0;
		int low=0;
		
		while(n/factor>0){
			temp=(n/factor)%10;
			low=n-(n/factor)*factor;
			high=n/(factor*10);
			
			if(temp==0){
				re+=high*factor;
			}else if(temp==1){
				re+=(high*factor+low+1);
			}else{
				re+=(high+1)*factor;
			}
			factor*=10;
		}
		return re;
	}
	
	/*
	 * 2.5寻找最大的k个数
	 */

	/*
	 * 2.7 寻找最大公约数
	 */
	//辗转相除
	public static int gcd1(int x, int y){
		int temp=0;
		while(y!=0){
			temp=x%y;
			x=y;
			y=temp;
		}
		return x;
	}
	
	//相减，避免取模运算
	public static int gcd2(int x, int y){
		if(x<y){
			return gcd2(y, x);
		}
		if(y==0){
			return x;
		}
		return gcd2(x-y, y);
	}
	
	//前面两者结合，O(log(max(x, y)))
	private static boolean isEven(int x){//判断是否为偶数
		return (x&1)==0;
	}
	public static int gcd3(int x, int y){
		if(x<y){
			return gcd3(y, x);
		}
		if(y==0){
			return x;
		}
		
		if(isEven(x)){
			if(isEven(y)){
				return gcd3(x>>1, y>>1)<<1;
			}else{
				return gcd3(x>>1, y);
			}
		}else{
			if(isEven(y)){
				return gcd3(x, y>>1);
			}else{
				return gcd3(x-y, y);
			}
		}
	}
	
	/*
	 * 2.9 斐波那契数列
	 */
	public static int fib(int n){
		if(n==0){
			return 0;
		}
		if(n==1){
			return 1;
		}
		return fib(n-1)+fib(n-2);
	}
	
	//时间空间都是O(n)
	public static int fib2(int n){
		if(n==0){
			return 0;
		}
		if(n==1){
			return 1;
		}
		int[] fib=new int[n+1];
		fib[0]=0; fib[1]=1;
		for(int i=2; i<=n; i++){
			fib[i]=fib[i-1]+fib[i-2];
		}
		return fib[n];
	}
	
	//通过矩阵的幂次，降到O(logn)矩阵的幂次与数的幂次求法相同
	
	/*
	 * 2.10 寻找数组中最大数和最小数
	 */
	public static int[] findMaxMin(int[] nums){
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		int low,high;
		for(int i=0; i<nums.length; i+=2){
			if(i+1<nums.length){
				low=Math.min(nums[i], nums[i+1]);
				high=Math.max(nums[i], nums[i+1]);
			}else{
				low=nums[i];
				high=nums[i];
			}
			max=Math.max(max, high);
			min=Math.min(min, low);
		}		
		return new int[]{min, max};
	}
	
	//分治
	public static int[] findMinMax(int[] nums, int begin, int end){
		if(end-begin<=1){
			return new int[]{Math.min(nums[begin], nums[end]), Math.max(nums[begin], nums[end])};
		}
		int mid=(begin+end)>>>1;
		int[] head=findMinMax(nums, begin, mid);
		int[] tail=findMinMax(nums, mid+1, end);
		
		int min=Math.min(head[0], tail[0]);
		int max=Math.max(head[1], tail[1]);
		
		return new int[]{min, max};
	}
	
	/*
	 * 2.12快速寻找满足条件的两个数
	 */
	//leetcode 1
	
	/*
	 * 2.13 子数组最大乘积
	 */
	
	//额外空间存储
	public static int maxPro(int[] nums){
		int len=nums.length;
		int[] f=new int[len];
		int[] b=new int[len];
		int temp=1;
		for(int i=0; i<len; i++){
			temp*=nums[i];
			f[i]=temp;
		}
		temp=1;
		for(int i=len-1; i>=0; i--){
			temp*=nums[i];
			b[i]=temp;
		}
		int re=b[1];
		for(int i=1; i<len-1; i++){
			re=Math.max(re, f[i-1]*b[i+1]);
		}
		re=Math.max(re, f[len-2]);
		return re;		
	}
	
	//
	private static int proExc(int[] nums, int n){
		int i;
		int re=1;
		for(i=0; i<nums.length; i++){
			if(nums[i]==n){
				break;
			}
			re*=nums[i];
		}
		for(int j=i+1; j<nums.length; j++){
			re*=nums[j];
		}		
		return re;
	}
	public static int maxPro2(int[] nums){
		int zeroCount=0;
		int pCount=0;
		int nCount=0;
		int pMin=Integer.MAX_VALUE;
		int nMin=Integer.MIN_VALUE;
		int nMax=0;
		for(int i=0; i<nums.length; i++){
			if(nums[i]==0){
				zeroCount++;
			}else if(nums[i]>0){
				pCount++;
				pMin=Math.min(nums[i], pMin);
			}else{
				nCount++;
				nMin=Math.max(nums[i], nMin);
				nMax=Math.min(nums[i], nMax);
			}
		}
		
		if(zeroCount>1){
			return 0;
		}else{
			boolean p=isEven(nCount);
			if(zeroCount==1){
				if(p){
					return proExc(nums, 0);
				}else{
					return 0;
				}
			}else{
				if(p){
					if(pCount>0){
						return proExc(nums, pMin);
					}else{
						return proExc(nums, nMax);
					}
				}else{
					return proExc(nums, nMin);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		int[] nums={5,6,8,3,7,9};
		int[] nums2={3,2,8,-5,-1,0};
		System.out.println(Arrays.toString(nums));
		System.out.println(maxPro2(nums2));
	}

	
	
}
