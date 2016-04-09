package leetcode;

public class Math {

	//263. Ugly Number
    public boolean isUgly(int num) {
    	if(num<1){
        	return false;
        }
        while(num%2==0){
    		num/=2;
    	}
    	while(num%3==0){
    		num/=3;
    	}
    	while(num%5==0){
    		num/=5;
    	}
    	if(num==1){
    		return true;
    	}else{
    		return false;
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
