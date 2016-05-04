package leetcode;

import java.util.HashMap;

public class String_leetcode {
	
	//344. Reverse String   反转字符串
    public static String reverseString(String s) {
        StringBuilder re=new StringBuilder();
        for(int i=s.length()-1; i>=0; i--){
        	re.append(s.charAt(i));
        }
    	return re.toString();
    }
    
    //6. ZigZag Conversion
    public static String convert(String s, int numRows) {
    	int n=s.length();
    	if(numRows==1||numRows>=n){
    		return s;
    	}
    	else{
            int dk=numRows*2-2;
            char[] ss=s.toCharArray();
            char[] chars=new char[n];
            int k=0;
            for(int i=0; i<numRows; i++){
            	for(int j=i; j<n&&k<n; j+=dk){
            		chars[k++]=ss[j];
            		int temp=j+dk-2*i;
            		if(i>0&&i<numRows-1&&temp<n){	
            			chars[k++]=ss[temp];
            		}
            	}
            }
            String re=String.valueOf(chars);
        	return re;
    	}
    }
    
    //165. Compare Version Numbers
    public static int compareVersion(String version1, String version2) {
        String[] s1=version1.split("\\.");
        String[] s2=version2.split("\\.");
        int n1=0, n2=0;
        int i=0, j=0;
        while(i<s1.length&&j<s2.length){
        	n1=n1*10+Integer.valueOf(s1[i++]);
        	n2=n2*10+Integer.valueOf(s2[j++]);
        }
        while(i<s1.length){
        	n1=n1*10+Integer.valueOf(s1[i++]);
        	n2=n2*10+0;
        }
        while(j<s2.length){
        	n1=n1*10+0;
        	n2=n2*10+Integer.valueOf(s2[j++]);
        }        
        if(n1>n2){
        	return 1;
        }else if(n1<n2){
        	return -1;
        }else{
        	return 0;
        }
    }
    
    //13. Roman to Integer
    public static int romanToInt(String s) {
    	int re=0;
    	int o=0;
        int[] step={1000, 500, 100, 50, 10, 5, 1};
        char[] add ={'M','D','C','L','X','V','I'};
        HashMap<Character, Integer> map=new HashMap<Character, Integer>();
        for(int i=0;i<step.length;i++){
        	map.put(add[i], step[i]);
        }
        char[] chars=s.toCharArray();
        for(int i=chars.length-1; i>=0; i--){
        	int n=map.get(chars[i]);
        	System.out.println(n);
        	if(n>o){
        		re+=n;
        	}else{
        		re-=n;
        	}
        	o=n;
        }
        return re;
    }
    
    //38. Count and Say
    private static String countandsay(String str){
    	StringBuilder s=new StringBuilder();
        int count=1;
        char value=' ';
        for(int i=0; i<str.length(); i++){
        	char temp=str.charAt(i);
        	if(temp==value){
        		count++;
        	}else{
        		s.append(count).append(value);
        		value=temp;
        		count=1;
        	}
        }
        return s.append(count).append(value).substring(2, s.length());
    }
    
    public static String countAndSay(int n) {
        String re="1";
        for(int i=1; i<n; i++){
        	re=countandsay(re);
        }
        return re;
    }
    
    //58. Length of Last Word
    public static int lengthOfLastWord(String s) {
        int re=0;
        int i;
        for(i=s.length()-1; i>=0; i--){
        	if(s.charAt(i)!=' ')
        		break;
        }
        for(; i>=0; i--){
        	if(s.charAt(i)!=' '){
        		re++;
        	}else{
        		return re;
        	}
        }
        return re;
    }
    
    //14. Longest Common Prefix
    public static String longestCommonPrefix(String[] strs) {
    	StringBuilder s=new StringBuilder();
    	if(strs.length==0){
    		return s.toString();
    	}
    	int len=Integer.MAX_VALUE;
        for(int i=0; i<strs.length; i++){
        	if(strs[i].length()<len)
        		len=strs[i].length();
        }
        for(int i=0; i<len; i++){
        	char temp=strs[0].charAt(i);
        	for(int j=0; j<strs.length; j++){
        		if(strs[j].charAt(i)!=temp){
        			return s.toString();
        		}
        	}
        	s.append(temp);
        }
        return s.toString();
    }
    
    //345. Reverse Vowels of a String
    private static boolean isVowel(char c){
    	return c=='a'||c=='e'||c=='i'||c=='o'||c=='u';
    }
    public static String reverseVowels(String s) {
        StringBuilder re=new StringBuilder(s);
        int i=0, j=s.length()-1;
        while(i<j){
        	if(!isVowel(s.charAt(i))){
        		i++;
        	}else if(!isVowel(s.charAt(j))){
        		j--;
        	}else{
        		char temp=re.charAt(i);
        		re.replace(i, i+1, String.valueOf(re.charAt(j)));
        		re.replace(j, j+1, String.valueOf(temp));
        		i++;
        		j--;
        	}
        }
        return re.toString();
    }
    
    
    
    
    //125. Valid Palindrome  TwoPointers  isPalindrome2
    //28. Implement strStr()   kmp
    
	public static void main(String[] args) {
		String[] strs={"qweqwe","qwe","qwe","qwe"};
		System.out.println(reverseVowels("leetcode"));
	}

}
