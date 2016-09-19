package interview;

import java.util.HashMap;
import java.util.Map;

public class Main{
	
	private static String preProcess(String str){
    	int len=str.length();
		if(len==0){
			return "^$";
		}
		StringBuilder s=new StringBuilder("^");
		for(int i=0; i<str.length(); i++){
			s.append("#").append(str.charAt(i));
		}
		return s.append("#$").toString();
    }
	
	
	private static void Manatcher(String s){
		int C=0, R=0;
		s=preProcess(s);
		int [] P=new int[s.length()];
		
		for(int i=1; i<s.length()-1; i++){
			int j=C-(i-C);
			int diff=R-i;
			if(diff>=0&&diff>P[j]){
				P[i]=P[j];
			}else{
				P[i]=Math.max(diff, 0);
				while(s.charAt(i+P[i]+1)==s.charAt(i-P[i]-1)){
					P[i]++;
				}
				C=i;
				R=i+P[i];
			}	
		}
		
		int max=0;
		for(int i=0; i<P.length; i++){
			max=Math.max(P[i], max);
		}
		System.out.println(max);
	}
  
	
	
	
	public static boolean[] printPrime(int n){
		boolean[] prime=new boolean[n];
		prime[2]=true;
		for(int i=3; i<n; i++){
			if((i&1)!=0){
				prime[i]=true;
			}
		}
		int sqrt=(int)Math.sqrt(n);
		for(int i=3; i<=sqrt; i+=2){
			if(prime[i]){
				for(int j=i*i; j<n; j+=i){
					prime[j]=false;
				}
			}
		}
		for(int i=0; i<n; i++){
			if(prime[i]){
				System.out.println(i);
			}
		}
		return prime;
	}
  
	public static void main(String[] args) {
		Map<Object, String> map=new HashMap<Object, String>();
		map.put(new Sougou(), "1");
		map.put(new Sougou(), "2");
		System.out.println(map.get(new Sougou()));
	}
  
}
class Sougou{
	public int hashCode(){
		return 1;
	}
}
