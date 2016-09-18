package interview;

import java.util.Scanner;

public class sougou2 {


	
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
		return prime;
	}
  
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int len=s.nextInt();
		int[] nums=new int[len];
		for(int i=0; i<len; i++){
			nums[i]=s.nextInt();
		}
		s.close();
		boolean[] prime=printPrime(nums[len-1]+1);
		int[] lala=new int[nums[len-1]+1];
		int count=0;
		for(int i=0; i<prime.length; i++){
			if(prime[i]){
				count++;
			}
			lala[i]=count;
		}
		int re=0;
		
		for(int i=0; i<nums.length; i++){
			for(int j=i+1; j<nums.length; j++){
				re+=lala[nums[j]]-lala[nums[i]];
			}
			
		}
		System.out.println(re);
	}

}
