package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class String_leetcode {
	
	//344. Reverse String   反转字符串
    public static String reverseString(String s) {
        StringBuilder re=new StringBuilder();
        for(int i=s.length()-1; i>=0; i--){
        	re.append(s.charAt(i));
        }
    	return re.toString();
    }
    
    //3. Longest Substring Without Repeating Characters 最长不重复子串
  	public static int lengthOfLongestSubstring2(String s) {
  		int[] pos=new int[256];
  		for(int i=0; i<pos.length; i++){
  			pos[i]=Integer.MAX_VALUE;
  		}
  		int re=0;
  		int count=0;
  		int start=0;
  		for(int i=0; i<s.length(); i++){
  			char chari=s.charAt(i);
  			int position=chari-' ';
  			if(i>pos[position]){//出现过
  				for(int j=start; j<pos[position]; j++){
  					pos[s.charAt(j)-' ']=Integer.MAX_VALUE;
  					count--;
  				}
  				start=pos[position]+1;
  				pos[position]=i;
  			}else{//没出现过
  				pos[position]=i;
  				count++;
  				if(count>re)
  					re=count;
  			}
  		}
  		return re;
  	}
  	
  	//125. Valid Palindrome 		判断回文字符串
  	public static boolean isPalindrome2(String s) {
  		if(s==null){
  			return true;
  		}
  		int i=0, j=s.length()-1;
  		char[] chars=s.toCharArray();
  		
  		while(i<j){
  			if(!Character.isLetterOrDigit(chars[i])){
  				i++;
  			} else if(!Character.isLetterOrDigit(chars[j])){
  				j--;
  			} else if(Character.toLowerCase(chars[i])!=Character.toLowerCase(chars[j])){
  				System.out.println(i);
  				System.out.println(j);
  				return false;
  			}else{
  				i++;
  				j--;
  			}
  		}
  		return true;
  	}
  	
  	//5. Longest Palindromic Substring	最长回文字串  Manacher算法
    private static String preProcess(String str){
    	int len=str.length();
		if(len==0){
			return "^$";
		}
		StringBuilder s=new StringBuilder("^");
		for(int i=0; i<str.length(); i++){
			s.append("#").append(str.charAt(i));
		}
		s.append("#$");
		return s.toString();
    }
    
    public static String longestPalindrome(String s) {
    	String T=preProcess(s);
    	int len=T.length();
    	int[] P=new int[len];
    	int C=0, R=0;
    	
    	for(int i=1; i<len-1; i++){
    		int j=C-(i-C);
    		int diff=R-i;
    		
    		if(diff>=0&&diff>P[j]){
    			P[i]=P[j];
    		}else{
    			P[i]=diff>=0? diff:0;
    			while(T.charAt(i+P[i]+1)==T.charAt(i-P[i]-1)){
    				P[i]++;
    			}
    			C=i;
    			R=i+P[i];
    		}
    	}
    	
    	int max=0;
		C=0;
		for(int i=1; i<len-1; i++){
			if(P[i]>max){
				max=P[i];
				C=i;
			}
		}
		int start=(C-max)/2;
		return s.substring(start, start+max);
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
    	return c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U';
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
    
    //71. Simplify Path
    public static String simplifyPath(String path) {
    	StringBuilder s=new StringBuilder();
    	Stack<String> stack =new Stack<String>();
        for(int i=0; i<path.length(); i++){
        	char c=path.charAt(i);
        	if(c=='/'){
        		if(s.length()>0&&!s.toString().equals(".")){
        			if(s.toString().equals("..")){
        				if(!stack.isEmpty())
        					stack.pop();
        					s.delete(0, s.length());
        			}else {
        				stack.push(s.toString());
        				s.delete(0, s.length());
        			}
        		}else 
        			s.delete(0, s.length());
        	}else{
        		s.append(c);
        	}
        }
        
        if(s.length()>0&&!s.toString().equals(".")){
			if(s.toString().equals("..")){
				if(!stack.isEmpty())
					stack.pop();
			}else {
				stack.push(s.toString());
				s.delete(0, s.length());
			}
		}else {
			s.delete(0, s.length());
		}
        
        if(stack.isEmpty()){
        	return "/";
        }
        String re="";
        while(!stack.isEmpty()){
        	re="/"+stack.pop()+re;
        }
    	return re;
    }
  	
    //151. Reverse Words in a String
    public static String reverseWords(String s) {
        StringBuilder re=new StringBuilder();
        if(s.length()==0){
        	return re.toString();
        }
        StringBuilder temp=new StringBuilder();
        int i=0;
        for(; i<s.length(); i++){
        	if(s.charAt(i)!=' '){
        		break;
        	}
        }
        for(; i<s.length(); i++){
        	if(s.charAt(i)==' '){
        		if(temp.length()>0){
        			re.insert(0, " "+temp);
            		temp.delete(0, temp.length());
        		}
        	}else{
        		temp.append(s.charAt(i));
        	}
        }
        re.insert(0, temp);
        if(re.length()>0&&re.charAt(0)==' '){
        	re.delete(0, 1);
        }
        return re.toString();
    }
    
    //22. Generate Parentheses
    private static void generate(List<String> re, int left, int right, StringBuilder s){
    	if(left==0&&right==0){
    		re.add(s.toString());
    		return;
    	}
    	if(left>0){
    		generate(re, left-1, right, new StringBuilder(s.toString()).append("("));
    	}
    	if(right>0&&left<right){
    		generate(re, left, right-1, new StringBuilder(s.toString()).append(")"));
    	}
    }
    public static List<String> generateParenthesis(int n) {
        List<String> re=new ArrayList<String>();
        generate(re, n, n, new StringBuilder(""));
        System.out.println(re.size());
    	return re;
    }
    
    
    
    //28. Implement strStr()   kmp
    private static int[] getNext(String p){
    	int[] next=new int[p.length()+1];
    	int j=0;
    	for(int i=1; i<p.length(); i++){
    		while(j>0&&p.charAt(i)!=p.charAt(j)){
    			j=next[j];
    		}
    		if(p.charAt(i)==p.charAt(j)){
    			j++;    			
    		}
    		next[i+1]=j;
    	}
    	
    	return next;
    }
    public static int strStr(String haystack, String needle) {
    	if(haystack.length()<1){
    		return 0;
    	}
    	if(needle.length()<1){
    		return -1;
    	}
    	int[] next=getNext(needle);
    	int j=0;
    	for(int i=0; i<haystack.length(); i++){
    		while(j>0&&needle.charAt(j)!=haystack.charAt(i)){
    			j=next[j];
    		}
    		if(needle.charAt(j)==haystack.charAt(i)){
    			j++;
    		}
    		if(j==needle.length()){
    			return i-j+1;
    		}
    	}    	
        return -1;
    }
    
    //43. Multiply Strings
    public static String multiply(String num1, String num2) {
        if(num1.length()==0||num2.length()==0){
        	return null;
        }
    	if(num1.equals("0")||num2.equals("0")){
    		return "0";
    	}
    	int[] re=new int[num1.length()+num2.length()+1];
    	String s1=new StringBuilder(num1).reverse().toString();
    	String s2=new StringBuilder(num2).reverse().toString();
    	
    	for(int i=0; i<s1.length(); i++){
    		int j,add=0;;
    		for(j=0; j<s2.length(); j++){
    			int mul=(s1.charAt(i)-'0')*(s2.charAt(j)-'0')+re[i+j]+add;
    			add=mul/10;
    			re[i+j]=mul%10;
    		}
    		re[i+j]=add;
    	}
    	
    	int i=re.length-1;
    	while(re[i]==0){
    		i--;
    	}
    	
    	StringBuilder s=new StringBuilder();
    	while(i>=0){
    		s.append(re[i]);
    		i--;
    	}
    	
    	return s.toString();
    }
    
    //29. Divide Two Integers
    public static int divide(int dividend, int divisor) {
        if(divisor==0){
        	return Integer.MAX_VALUE;
        }
        long a=Math.abs((long)dividend);
        long b=Math.abs((long)divisor);
        
        int sign=1;
        if(dividend<0){
        	sign=-sign;
        }
        if(divisor<0){
        	sign=-sign;
        }
        long re=0;
        while(a>=b){
        	long c=b;
        	int cnt=1;
        	while(a>=c){
        		re+=cnt;
        		a-=c;
        		cnt<<=1;
        		c<<=1;
        	}
        }
        
        if(re>Integer.MAX_VALUE&&sign==1){
        	return Integer.MAX_VALUE;
        }
        
        return sign==1? (int)re:-(int)re;
    }
    
    
	public static void main(String[] args) {
		
		System.out.println(divide(-2147483648, 1));
		//String[] strs={"qweqwe","qwe","qwe","qwe"};
		//int[] nums={1,1};
		//System.out.println(generateParenthesis(4));
		
		
	}

}
