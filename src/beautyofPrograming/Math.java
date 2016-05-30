package beautyofPrograming;

public class Math {

	//2.1 求二进制中1的个数
	//2.2就n的阶乘中二进制最后一位1的位置
	public static int jiechentg(int n){
		int re=0;
		while(n>0){
			n>>=1;
			re+=n;
		}		
		return re;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
