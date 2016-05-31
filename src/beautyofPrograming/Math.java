package beautyofPrograming;

public class Math {

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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(count1(12));
	}

}
