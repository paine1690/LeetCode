package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array_leetcode {
    
	//27. Remove Element
    public static int removeElement(int[] nums, int val) {
        int re=0;
        for(int i=0; i<nums.length; i++){
        	if(nums[i]==val){
        		re++;
        		continue;
        	}
        	nums[i-re]=nums[i];
        }
        return nums.length-re;
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
      
    //238. Product of Array Except Self
    public static int[] productExceptSelf(int[] nums) {
    	if(nums.length<2){
    		return nums;
    	}
        int[] re=new int[nums.length];
        int left[] =new int[nums.length];
        int right[] =new int[nums.length];
        int temp=1;
        for(int i=0; i<nums.length; i++){
        	temp*=nums[i];
        	left[i]=temp;
        }
        temp=1;
        for(int i=nums.length-1; i>=0; i--){
        	temp*=nums[i];
        	right[i]=temp;
        }
        re[0]=right[1];
        for(int i=1; i<nums.length-1; i++){
        	re[i]=left[i-1]*right[i+1];
        }
        re[nums.length-1]=left[nums.length-2];
        return re;
    }
    
    //229. Majority Element II
    public static List<Integer> majorityElement(int[] nums) {
    	List<Integer> re=new ArrayList<Integer>();
    	int[] num=new int[2];
    	int count0=0;
    	int count1=0;
    	
    	for(int i=0; i<nums.length; i++){
    		if(num[0]==nums[i]){
    			count0++;
    		}else if(num[1]==nums[i]){
    			count1++;
    		}else if(count0==0){
    			count0=1;
    			num[0]=nums[i];
    		}else if(count1==0){
    			count1=1;
    			num[1]=nums[i];
    		}else{
    			count0--;
    			count1--;
    		}
    	}
    	count0=0;
    	count1=0;
    	for(int i=0; i<nums.length; i++){
    		if(nums[i]==num[0]){
    			count0++;
    		}else if(nums[i]==num[1]){
    			count1++;
    		}
    	}
    	if(count0>nums.length/3){
    		re.add(num[0]);
    	}
    	if(count1>nums.length/3){
    		re.add(num[1]);
    	}
    	return re;
    }
    
    //15. 3Sum
    private static List<List<Integer>> re=new ArrayList<List<Integer>>();    
    private static List<Integer> twoSum(int[] nums, int start, int end, int target){
    	
    	while(start<end){
    		int sum=nums[start]+nums[end];
    		if(sum==target){
    			re.add(new ArrayList<Integer>(Arrays.asList(0-target, nums[start], nums[end])));
    			while(start<end&&nums[start]==nums[start+1]){
    				start++;
    			}
    			while(start<end&&nums[end]==nums[end-1]){
    				end--;
    			}
    			start++;
    			end--;
    		}else if(sum<target){
    			start++;
    		}else{
    			end--;
    		}
    	}
    	return null;
    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
    	if(nums==null||nums.length<3){
    		return re;
    	}
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length; i++){
        	if(i>0&&nums[i]==nums[i-1]){
        		continue;
        	}
        	List<Integer> two=twoSum(nums, i+1, nums.length-1, 0-nums[i]);
        	if(two!=null){
        		re.add(two);
        	}
        }
        return re;
    }
    
    //16. 3Sum Closest
    public int threeSumClosest(int[] nums, int target) {
        if(nums==null||nums.length<3){
        	return 0;
        }
        Arrays.sort(nums);
        int closest=Integer.MAX_VALUE;
        int re=0;
        for(int i=0; i<nums.length; i++){
        	if(i>0&&nums[i]==nums[i-1]){
        		continue;
        	}
        	
        	int start=i+1;
        	int end=nums.length-1;
        	while(start<end){
        		int sum=nums[i]+nums[start]+nums[end];
        		if(sum==target){
        			return sum;
        		}else if(sum<target){
        			if(target-sum<closest){
        				closest=target-sum;
        				re=sum;
        			}
        			start++;
        		}else{
        			if(sum-target<closest){
        				closest=sum-target;
        				re=sum;
        			}
        			end--;
        		}
        	}
        }
        return re;
    }
    
    
    
	public static void main(String[] args) {
		int[] nums={};
		System.out.println(threeSum(nums));

	}

}
