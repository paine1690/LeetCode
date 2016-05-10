package leetcode;

public class DynamicProgramming {
	
	//70. Climbing Stairs		斐波那契数列
    public static int climbStairs(int n) {
    	int a=0, b=1;
    	int re=0;
    	for(int i=1; i<=n; i++){
    		re=a+b;
    		a=b;
    		b=re;
    	}
        return re;
    }
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(climbStairs(4));
	}

}
