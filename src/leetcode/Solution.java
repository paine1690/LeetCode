package leetcode;

import java.util.Arrays;

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
    
    
    
   
    

    

    
    
    
    
    
    

    

    
    public static boolean makesquare(int[] nums) {
    	if(nums.length<4){
    		return false;
    	}
        int sum=0;
        for(int num: nums){
        	sum+=num;
        }
    	if(sum%4!=0){
    		return false;
    	}
    	sum/=4;
    	System.out.println(sum);
    	int[] d=new int[4];
    	Arrays.sort(nums);
    	int cnt=0;
    	for(int i=nums.length-1; i>=0; i--){
    		int num=nums[i];
    		int j=0;
    		for(; j<4; j++){
    			int index=cnt++%4;
    			if(d[index]+num<=sum){
    				d[index]+=num;
    				break;
    			}
    		}
    		if(j==4){
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    
	public static void main(String[] args) {
		System.out.println(makesquare(new int[]{10,6,5,5,5,3,3,3,2,2,2,2}));
		
	}

}
