package leetcode;
import java.util.Arrays;

public class TwoPointers {
	/*
	 * 直接处理字符串
	 */
	public static boolean isPalindrome(String s) {
		if(s==null){
			return true;
		}
		s=s.replaceAll("\\W", "").toLowerCase();
		if(s.length()<2){
			return true;
		}
		for(int i=0; i<s.length()/2; i++){
			if(s.charAt(i)==s.charAt(s.length()-i-1)){
				continue;
			}
			return false;
		}
		return true;
	}
	
	
	/*
	 * 不用正则表达式
	 */
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
	 
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	if(n==0){
    		return;
    	}
        int i=m-1, j=n-1, k=m+n-1;
        while(i>=0&&j>=0){
        	if(nums1[i]>nums2[j]){
        		nums1[k--]=nums1[i--];
        	}else{
        		nums1[k--]=nums2[j--];
        	}
        }
        while(j>=0){
        	nums1[k--]=nums2[j--];
        }
    }
    
    public static int strStr(String haystack, String needle) {
    	if(needle.length()==0){
    		return 0;
    	}
    	if(haystack.length()==0){
    		return -1;
    	}
    	int i, j;
    	for(i=0; i<haystack.length()-needle.length()+1;i++){
			for(j=0; j<needle.length(); j++){
				if(haystack.charAt(i+j)!=needle.charAt(j)){
					break;
				}
			}
			if(j==needle.length()){
				return i;
			}
    	}
        return -1;
    }
    
    public static int removeDuplicates(int[] nums) {
        int len=nums.length;
        if(len<2){
        	return len;
        }
        int i=1, j=1;
        for(; j<nums.length; j++){
        	if(nums[j]==nums[j-1]){
        		while(j<nums.length&&nums[j]==nums[j-1]){
        			j++;
        			len--;
        		}
        		if(j==nums.length)
        			break;
        		nums[i++]=nums[j];
        	}else{
        		nums[i++]=nums[j];
        	}
        }
        
        return len;
    }

    
    public static int removeDuplicates2(int[] nums) {
    	int len=nums.length;
        if(len<2){
        	return len;
        }
        int i=1, j=1;
        for(; j<nums.length; j++){
        	if(nums[j]==nums[j-1]){
        		nums[i++]=nums[j++];
        		while(j<nums.length&&nums[j]==nums[j-1]){
        			j++;
        			len--;
        		}
        		if(j==nums.length)
        			break;
        		nums[i++]=nums[j];
        	}else{
        		nums[i++]=nums[j];
        	}
        }
        return len;
    }
    
    public static void sortColors(int[] nums) {
    	int len=nums.length;
    	if(len<2){
    		return;
    	}
        int i=0, j=0, k=0;
        while(k<len-j){
        	if(nums[k]==0){
        		int temp=nums[k];
        		nums[k]=nums[i];
        		nums[i]=temp;
        		i++;
        	}else if(nums[k]==2){
        		int temp=nums[k];
        		nums[k]=nums[len-j-1];
        		nums[len-j-1]=temp;
        		j++;
        		continue;
        	}
        	k++;
        }
        System.out.println(Arrays.toString(nums));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="asdf";
		String s2="df";
		int[] nums={1,1,2};
		sortColors(nums);
	}

}
