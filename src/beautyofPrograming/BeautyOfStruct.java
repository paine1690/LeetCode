package beautyofPrograming;

import java.util.ArrayList;
import java.util.List;

/**
 * 		第三章 结构之法 -字符串及链表的探索
 * @author Paine
 *
 */
 
 
public class BeautyOfStruct {

	/*
	 * 3.1 字符串移位包含  
	 */
	private static int[] getNext(String P){
		int m=P.length();
		int[] next=new int[m+1];
		next[0]=next[1]=0;
		int j=0;
		for(int i=1; i<m; i++){
			while(j>0&&P.charAt(i)!=P.charAt(j)){
				j=next[j];
			}
			if(P.charAt(i)==P.charAt(j)){
				j++;
			}
			next[i+1]=j;
		}		
		return next;
	}
	private static boolean kmp(String T, String P){
		int[] next=getNext(P);
		int j=0;
		for(int i=0; i<T.length(); i++){
			while(j>0&&T.charAt(i)!=P.charAt(j)){
				j=next[j];
			}
			if(T.charAt(i)==P.charAt(j)){
				j++;
			}
			if(j==P.length()){
				return true;
			}
		}
		return false;
	}
	public static boolean isIn(String s1, String s2){
		s1+=s1.substring(0, s2.length()-1);
		System.out.println(s1);
		return kmp(s1, s2);
	}
	
	
	/*
	 * 3.2 电话号码与对应英语单词
	 */
	//leetcode 17. Letter Combinations of a Phone Number.
    static String[] number=new String[]{
    		"",
    		"",
    		"abc",
    		"def",
    		"ghi",
    		"jkl",
    		"mno",
    		"pqrs",
    		"tuv",
    		"wxyz",
    };
    static int[] total=new int[]{0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
    
    //循环求法
    public static List<String> letterCombinations(String digits) {
    	List<String> re=new ArrayList<String>();
        int len=digits.length();
        if(len==0){
        	return re;
        }
        int[] answer=new int[len];          
        
        while(true){
        	StringBuilder s=new StringBuilder();
        	for(int i=0; i<len; i++){
        		s.append(number[digits.charAt(i)-'0'].charAt(answer[i]));
        	}
        	re.add(s.toString());
        	int k=len-1;
        	while(k>=0){
        		if(answer[k]<total[digits.charAt(k)-'0']-1){
        			answer[k]++;
        			break;
        		}else{
        			answer[k]=0;
        			k--;
        		}
        	}      	
        	if(k<0){
        		break;
        	}
        }    
    	return re;
    }
    
    //递归求法
    public static List<String> letterCombinations2(String digits) {
    	int len=digits.length();
    	List<String> re=new ArrayList<String>();
    	if(len==0){
    		return re;
    	}    	
    	char[] chars=new char[len];
    	dfsGet(digits, 0, chars, re);
    	return re;    	
    }
    
    private static void dfsGet(String dig, int index, char[] chars, List<String> re){
    	if(index==dig.length()){
    		re.add(new String(chars));
    		return;
    	}
    	int num=dig.charAt(index)-'0';
    	for(int i=0; i<total[num]; i++){
    		chars[index]=number[num].charAt(i);
    		dfsGet(dig, index+1, chars, re);
    	}
    }	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
