package leetcode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;




public class Solution {
	public static int[] twoSum(int[] nums, int target){
		int[] re = new int[2];
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<nums.length;i++){
			map.put(nums[i], i+1);
		}
		
		for(int i=0; i<nums.length; i++){
			if(map.containsKey(target-nums[i])){
				if(map.get(target-nums[i]) != i+1){
					re[0] = i+1;
					re[1] = map.get(target-nums[i]);
					break;
				}
			}
		}
		return re;
	}
	
	
	
	public static int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		String ss=String.valueOf(chars);
        int x = 0;
        int head = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0; i<chars.length; i++){
        	if(!map.containsKey(chars[i])){
        		map.put(chars[i], i);
        		if(map.size()>x){
        			x = map.size();
        		}
        	}
        	else{
        		if(map.size()>x){
        			x = map.size();
        		}
        		
        		int temp = map.get(chars[i]);
        		
        		for(int j=head; j<=temp; j++){
        			map.remove(chars[j]);
        		}
        		
        		head=temp+1;
        		map.put(chars[i],i);
        	}
        }
        return x;
    }
	
	
	
	
	public static String longestPalindrome(String s) {
        char[] chars=s.toCharArray();
        int length = chars.length;
        int re_l=0;
        int re_r=0;
        int re_length=1;
        for(int i=0; i<chars.length-1; i++){
        	int first=0;
        	int last=0;
        	
        	if(chars[i]==chars[i+1]){
        		first=i;
        		last=i+1;
        	}
        	
        	for(int l=first-1,r=last+1; l>=0&&r<=length-1; l--,r++){
    			if(chars[l]==chars[r]){
    				first=l;
    				last=r;
    			}
    			else{
    				break;
    			}
    		}
    		if((last-first+1)>re_length){
    			re_l=first;
    			re_r=last;
    			re_length=last-first+1;
    		}
    		
    		if(i+2<length){
        		if(chars[i]==chars[i+2]){
        			first=i;
        			last=i+2;
        		}
        	}
    		
    		for(int l=first-1,r=last+1; l>=0&&r<=length-1; l--,r++){
    			if(chars[l]==chars[r]){
    				first=l;
    				last=r;
    			}
    			else{
    				break;
    			}
    		}
    		if((last-first+1)>re_length){
    			re_l=first;
    			re_r=last;
    			re_length=last-first+1;
    		}
        }
        System.out.println(re_l);
        System.out.println(re_r);
        char[] re=new char[re_length];
        for(int i=0; i<re_length; i++){
        	re[i]=chars[i+re_l];
        }
        
        String re_s=String.valueOf(re);
        return re_s;
    }
	

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

    public static int reverse(int x) {
        long re=0;
        while(x!=0){
        	re=re*10+x%10;
        	if(re>Integer.MAX_VALUE||re<Integer.MIN_VALUE){
        		re=0;
        		break;
        	}
        	x=x/10;
        }
        return (int)re;
       
    	
    }
	
    public static boolean isPalindrome(int x) {
    	if(x<10){
            if(x>=0){
                return true;
            }
            else{
                return false;
            }
        }
        long wei =1;
    	int temp=x;
    	while(x!=0){
    		x=x/10;
    		wei*=10;
    	}
    	wei=wei/10;
    	int high=(int)wei;
    	while(high>=10){
    		if(temp/high != temp%10){
    			return false;
    		}
    		temp=(temp%high)/10;
    		high/=100;
    	}
    	return true;
           	    	  
    }
	
    
    public static String intToRoman(int num) {
        String re="";
        int[] step={1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] add ={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int temp=0;
        
        for(int i=0;i<step.length;i++){
        	temp=num/step[i];
        	for(int j=0;j<temp;j++){
        		re+=add[i];
        	}
        	num=num%step[i];
        }
       
    	return re;
    }
    
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
        	}
        	else{
        		re-=n;
        	}
        	o=n;
        }
        
        
        return re;
    }
    
    public static boolean wordPattern(String pattern, String str) {
        char[] chars=pattern.toCharArray();
        String[] strs=str.split(" ");
        if(chars.length!=strs.length){
            return false;
        }
        HashMap<Character, String> map=new HashMap<Character, String>();
        Set set=new HashSet();
    	for(int i=0;i<chars.length;i++){
    		if(map.containsKey(chars[i])){
    			if(!strs[i].equals(map.get(chars[i]))){
    				return false;
    			}
    		}
    		else{
    			if(set.contains(strs[i])){
    				return false;
    			}
    			map.put(chars[i], strs[i]);
    			set.add(strs[i]);
    		}
    	}
    	
    	return true;
    }
    public static boolean isValidSudoku(char[][] board) {
        Set<Character> set=new HashSet<Character>();
    	char s='s';
    	set.clear();
       
    	
    	return true;
    }
    
    public static boolean isAnagram(String s, String t) {
    	if(s.length()!=t.length()){
    		return false;
    	}
    	HashMap<Character, Integer> map=new HashMap<Character, Integer>();
    	char[] c1=s.toCharArray();
    	char[] c2=t.toCharArray();
    	
    	for(int i=0; i<c1.length; i++){
    		if(map.containsKey(c1[i])){
    			map.put(c1[i], map.get(c1[i])+1);
    		}
    		else{
    			map.put(c1[i], 1);
    		}
    	}
    	for(int i=0; i<c2.length; i++){
    		if(!map.containsKey(c2[i])){
    			return false;
    		}
    		else{
    			if(map.get(c2[i])==1){
    				map.remove(c2[i]);
    			}
    			else{
    				map.put(c2[i], map.get(c2[i])-1);
    			}
    		}
    	}
    	return true;
    }
    
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String p="abba";
		String s="barfoothefoobarman";
		String[] ss=new String[]{"foo","bar"};
		int a=19;
		int[] aa={1,1,1,1,2,3};
		int[] bb={4,5,6,7};
		
		
		
		
		
		
	}

}
