package beautyofPrograming;

public class BeautyOfMath {

	//2.1 求二进制中1的个数 
	//leetcode 191. Number of 1 Bits
	
	//2.2就n的阶乘中二进制最后一位1的位置
	public static int jiechentg(int n){
		int re=0;
		while(n>0){
			n>>=1;
			re+=n;
		}		
		return re;
	}
	
	//2.3频率最高的元素 
	//leetcode 169. Majority Element
	//leetcode 229. Majority Element II
	
	//2.4
	//1到n中1出现的次数
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
	
	//2.5寻找最大的k个数

	//2.7 寻找最大公约数
	public static int gcd1(int x, int y){//辗转相除
		int temp=0;
		while(y!=0){
			temp=x%y;
			x=y;
			y=temp;
		}
		return x;
	}
	
	public static int gcd2(int x, int y){//相减，避免取模运算
		if(x<y){
			return gcd2(y, x);
		}
		if(y==0){
			return x;
		}
		return gcd2(x-y, y);
	}
	
	private static boolean isEven(int x){//判断是否为偶数
		return (x&1)==0;
	}
	public static int gcd3(int x, int y){//前面两者结合，O(log(max(x, y)))
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
	
	//2.10 寻找数组中最大数和最小数
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
	
	//2.10  分治
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
	
	public static void main(String[] args) {
		int[] nums={5,6,8,3,7,9};
		System.out.println(gcd3(42, 30));
	}

	
	
}
