import java.util.Arrays;

public class BitManipulation {
	/*
	 * 136.Single Number
	 */
	
	public static int[] countBits(int num) {
        int[] re=new int[num+1];
        re[0]=0;
        if(num==0){
        	return re;
        }
        re[1]=1;
        int step=2;
        int i=2;
        while(i<=num){
        	for(int j=0; j<step&&i<=num; j++){
        		re[i++]=re[j]+1;
        	}
        	step=step*2;
        }
        return re;
    }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(countBits(10)));
	}

}
