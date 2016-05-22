package leetcode;

public class Brainteaser {

	//292. Nim Game
    public boolean canWinNim(int n) {
        return n%4==0;
    }
    
    //319. Bulb Switcher
    public static int bulbSwitch(int n) {
//    	LTE
//        int[] d=new int[n+1];
//        for(int i=1; i<=n; i++){
//        	for(int j=1; i*j<=n; j++){
//        		d[i*j]++;
//        	}
//        }
//        int re=0;
//        System.out.println(Arrays.toString(d));
//        for(int i=1; i<=n; i++){
//        	if((d[i]&1)==1){
//        		re++;
//        	}
//        }
//        return re;
    	return (int) Math.sqrt(n);
    }
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Math.sqrt(24));
		System.out.println(bulbSwitch(24));
	}

}
