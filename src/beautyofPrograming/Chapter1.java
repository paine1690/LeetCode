package beautyofPrograming;

/**
 * 		第一章 游戏之乐 -游戏中碰到的题目
 * @author Paine
 *
 */
public class Chapter1 {

	/*
	 * 1.1 CPU曲线听你指挥
	 */

	/*
	 * 1.2 中国象棋将帅问题
	 */
	//9进制，两位，两重循环
	public static void chess(){
		char i=81;
		while(i-->0){
			if((i/9%3)==(i%9%3)){
				continue;
			}
			System.out.println("a"+(i%9+1)+"b"+(i/9+1));
		}
	}
	
	//n重循环
	public static void n(){
		int counter=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<4; j++){
				for(int k=0; k<3; k++){
					System.out.println("counter="+counter+"i="+i+", j="+j+", k="+k);
				    counter++;
				}
			}
		}
	}
	
	public static void n2(){
		int var=5*4*3;
		int counter=0;
		int i=0;
		while(i++<var){
			System.out.println("counter="+counter+"i="+(i%3)+", j="+(i/3)+", k="+(i/(3*4)));
		    counter++;
		}
		
	}
	
	public static void main(String[] args){
		n();
	}
}
