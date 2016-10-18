package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prepare {

	
	
	private static void swap1(char[] chars, int i, int j){
		char temp=chars[j];
		for(int c=j; c>i; c--){
			chars[c]=chars[c-1];
		}
		chars[i]=temp;	
	}
	
	private static void swap2(char[] chars, int i, int j){
		char temp=chars[i];
		for(int c=i; c<j; c++){
			chars[c]=chars[c+1];
		}
		chars[j]=temp;
	}
	
	private static void per(List<String> re, char[] chars, int start){
		if(start>=chars.length){
			re.add(new String(chars));
		}
		
		for(int i=start; i<chars.length; i++){
			if(i!=start&&chars[i]==chars[start]){
				continue;
			}
			swap1(chars, start, i);
			per(re, chars, start+1);
			swap2(chars, start, i);
		}
	}
	
	public static List<String> pernu(String s){
		List<String> re=new ArrayList<String>();
		if(s.length()==0){
			return re;
		}
		char[] chars=s.toCharArray();		
		Arrays.sort(chars);
		per(re, chars, 0);	
		//Collections.sort(re);
		return re;
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(pernu("aab"));
		
	}

}
