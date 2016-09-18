package interview;

public class Baidu {

	/*
	 * 6×9的的方格中，起点的左下角，终点在右上角，从起点到终点，只能从下向上，从左向右走，问一共有多少种不同的走法。
		A.  4200
		B.   5005
		C.  1005
		D.  以上都不正确
	 * 
	 * 
	 */
	
	public static int Catlan(int m, int n){
		if(m<1||n<1){
			return 1;
		}
		return Catlan(m-1, n)+Catlan(m, n-1);		
	}
	
	
	public static void main(String[] args) {
		System.out.println(Catlan(6, 9));

	}

}
