package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    //118. Pascal's Triangle
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        if(numRows==0){
        	return re;
        }
        List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		re.add(list);
        for(int i=2; i<=numRows; i++){
    		list=new ArrayList<Integer>(i);
    		list.add(1);
    		for(int j=1; j<i-1; j++){
    			list.add(re.get(i-2).get(j-1)+re.get(i-2).get(j));
    		}
    		list.add(1);
    		re.add(list);
    	}
    	return re;
    }
    
    //119. Pascal's Triangle II
    private static List<Integer> getNextRow(List<Integer> list, int n){
    	List<Integer> re= new ArrayList<Integer>(n+1);
    	re.add(1);
    	for(int i=1; i<n; i++){
    		re.add(list.get(i-1)+list.get(i));
    	}
    	re.add(1);
    	return re;
    }
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> re=new ArrayList<Integer>();
        re.add(1);
        if(rowIndex==0){
        	return re;
        }
        re.add(1);
        for(int i=2; i<=rowIndex; i++){
        	re=getNextRow(re, i);
        }
        
        return re;
    }
    
    //119. Pascal's Triangle II
    public static List<Integer> getRow2(int rowIndex) {
    	int[] nums=new int[rowIndex+1];
    	List<Integer> re=new ArrayList<Integer>(rowIndex+1);
    	for(int i=0; i<nums.length; i++){
    		for(int j=i; j>=0; j--){
    			if(j==i){
    				nums[j]=1;
    			}else if(j==0){
    				nums[j]=1;
    			}else{
    				nums[j]=nums[j]+nums[j-1];
    			}
    		}
    	}
    	for(int i=0; i<nums.length; i++){
    		re.add(nums[i]);
    	}
    	return re;
    }
    
    //31. Next Permutation
    private static void swap(int[] nums, int i, int j){
    	int temp=nums[i];
    	nums[i]=nums[j];
    	nums[j]=temp;
    }
    
    private static void reverse(int[] nums, int i, int j){
    	while(i<j){
    		swap(nums, i++, j--);
    	}
    }
    
    public static void nextPermutation(int[] nums) {
    	if(nums.length<2){
    		return;
    	}
    	int i, k;
    	for(i=nums.length-2; i>=0; i--){
    		if(nums[i]<nums[i+1]){
    			break;
    		}
    	}
    	if(i<0){
    		reverse(nums, 0, nums.length-1);
    		return;
    	}
    	for(k=nums.length-1; k>i;k--){
    		if(nums[k]>nums[i]){
    			break;
    		}
    	}
    	
    	swap(nums, i, k);
    	reverse(nums, i+1, nums.length-1);
    }
      
    
    //28. Implement strStr()   kmp
    
	public static void main(String[] args) {
		//String[] strs={"qweqwe","qwe","qwe","qwe"};
		int[] nums={1,1};
		nextPermutation(nums);
		System.out.println(Arrays.toString(nums));
		
		
	}

}
