package leetcode;
import java.util.HashMap;




public class Solution {	
	
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
    
   
    
    
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
	}

}
