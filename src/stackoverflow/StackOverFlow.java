package stackoverflow;

import java.io.IOException;
import java.io.InputStream;

public class StackOverFlow {

	
	/*
	 * 判断一个String是否包含字母表中所有元素
	 */
	
	public static boolean isAll(String s){
		int i=0;
		for(char c: s.toCharArray()){
			int x=Character.toUpperCase(c);
			if(x>='A'&&x<='Z'){
				i|=1<<(x-'A');
			}
		}
		return (i|(1<<('Z'-'A'+1)-1))==i;
		
	}
	

	
	public static void main(String[] args) {
		
		String s = "Pack my box with five dozen liquor jugs.";
		System.out.println(isAll(s));

	}
	
}








