package beautyofPrograming;

/**
 * 		第四章 数学之趣 -数学游戏的乐趣
 * @author Paine
 *
 */
public class Chapter4 {
	/*
	 * 4.3 买票找零
	 */
	public static int Catalan(int nn){
		if(nn==0){
			return 1;
		}
		int re=0;
		for(int i=0; i<nn; i+=2){
			re+=Catalan(i)*Catalan(nn-i-2);
		}
		return re;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(Catalan(8));

	}

}
