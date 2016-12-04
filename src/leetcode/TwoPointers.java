package leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwoPointers {
	
	//125. Valid Palindrome 直接处理字符串
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
	
	//88. Merge Sorted Array
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
    
    //28. Implement strStr()
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
    
    //26. Remove Duplicates from Sorted Array
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

    //80. Remove Duplicates from Sorted Array II
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
    
    //75. Sort Colors
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
    }
    
    //11. Container With Most Water
    public static int maxArea(int[] height) {
        int re=0;
        int n=height.length;
        int l=0;
        int r=n-1;
        int temp=0;
        while(l!=r){
        	if(height[l]<height[r]){
        		temp=height[l]*(r-l);
        		l++;
        	}
        	else{
        		temp=height[r]*(r-l);
        		r--;
        	}
        	if(temp>re){
    			re=temp;
    		}
        	System.out.println(String.valueOf(1));
        }
        
        return re;
    }
    
    //283. Move Zeroes
    public void moveZeroes(int[] nums) {
        if(nums.length<2){
        	System.out.println(Arrays.toString(nums));
        	return;
        }
        int i=0;
        while(i<nums.length){
        	if(nums[i]==0){
        		int count=1;
        		for(int j=i+1; j<nums.length; j++){
        			if(nums[j]==0){
        				count++;
        			}else{
        				nums[j-count]=nums[j];
        			}
        		}
        		for(int k=nums.length-count; k<nums.length; k++){
        			nums[k]=0;
        		}
        		return;
        	}
        	i++;
        }
    }
    
    //27. Remove Element
    public static int removeElement(int[] nums, int val) {
    	int i=0, j=0;
    	int re=nums.length;
    	for(j=0; j<nums.length; j++){
    		if(nums[j]==val){
    			while(j<nums.length&&nums[j]==val){
    				j++;
    				re--;
    			}
    		}
    		if(j>=nums.length)
    			break;
    		nums[i++]=nums[j];
    	}
    	System.out.println(Arrays.toString(nums));
    	return re;
    }
    
    //30. Substring with Concatenation of All Words
    public static List<Integer> findSubstring(String s, String[] words) {
        int step=words[0].length();
        int times=words.length;
    	int len=step*times;
    	List<Integer> re=new ArrayList<Integer>();
    	HashMap<String, Integer> map=new HashMap<String, Integer>();
    	for(int i=0; i<words.length; i++){
    		if(map.containsKey(words[i])){
    			map.put(words[i], map.get(words[i])+1);
    		}
    		else{
    			map.put(words[i],1);
    		}
    	}
    	
        int i=0;
        while((i+len)<=s.length()){
        	map.putAll(map);
        	int count=0;
        	for(int j=0; j<times; j++){
        		String nstr="";
        		int temp=i+j*step;
        		nstr=s.substring(temp, temp+step);
        		
        		if(!map.containsKey(nstr)){
        			break;
        		}
        		else{
        			count++;
        			if(map.get(nstr)>1){
        				map.put(nstr, map.get(nstr)-1);
        			}
        			else{
        				map.remove(nstr);
        			}
        		}
        	}
        	if(count==times){
        		re.add(i);
        	}
        	if(count>0){
        		map.clear();
        		for(int l=0; l<words.length; l++){
            		if(map.containsKey(words[l])){
            			map.put(words[l], map.get(words[l])+1);
            		}
            		else{
            			map.put(words[l],1);
            		}
            	}
        	}
        	i++;
        }
        
        return re;
    }
    
    //209. Minimum Size Subarray Sum
    public static int minSubArrayLen(int s, int[] nums) {
    	int i=0, j=0;
    	int sum=0;
    	int re=Integer.MAX_VALUE;
    	while(i<nums.length&&j<nums.length){
    		while(sum<s&&j<nums.length){
    			sum+=nums[j++];
    		}
    		while(i<=j&&sum>=s){
    			re=Math.min(re, j-i);
    			sum-=nums[i++];
    		}
    	}
    	return re==Integer.MAX_VALUE? 0:re;
    }
    
    //167. Two Sum II - Input array is sorted
    public static int[] twoSum(int[] numbers, int target) {
        int i=0, j=numbers.length-1;
    	while(i<j){
    		int sum=numbers[i]+numbers[j];
    		if(sum==target){
    			break;
    		}
    		if(sum<target){
    			i++;
    		}else{
    			j--;
    		}
    	}
    	return new int[]{i+1, j+1};
    	
    }
    
    //287. Find the Duplicate Number
    public static int findDuplicate(int[] nums) {
    	int i=0, j=0;
    	while(true){
    		i=nums[i];
    		j=nums[nums[j]];
    		if(i==j){
    			break;
    		}
    	}
    	j=0;
    	while(true){
    		i=nums[i];
    		j=nums[j];
    		if(i==j){
    			return i;
    		}
    	}
    }
    
    //373. Find K Pairs with Smallest Sums
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> re=new ArrayList<int[]>();
        if(nums1.length==0||nums2.length==0||k==0){
        	return re;
        }
        int len1=nums1.length, len2=nums2.length;
        int cnt=Math.min(k, len1*len2);
        int[] index=new int[len1];
        while(cnt-->0){
        	int tempMin=Integer.MAX_VALUE, m=0;
        	for(int i=0; i<index.length; i++){
        		if(index[i]<len2&&nums1[i]+nums2[index[i]]<tempMin){
        			tempMin=nums1[i]+nums2[index[i]];
        			m=i;
        		}
        	}
        	re.add(new int[]{nums1[m], nums2[index[m]++]});
        }
        return re;
    }

	public static void main(String[] args) {
		int[] nums={1,2,3,4,5};
		System.out.println(minSubArrayLen(11, nums));
	}

}
