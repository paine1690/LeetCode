package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static List<List<Integer>> re;    
    private static void twoSum(int[] nums, int start, int end, int target){
    	
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
    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
    	re=new ArrayList<List<Integer>>();
    	if(nums==null||nums.length<3){
    		return re;
    	}
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length-2; i++){
        	if(i>0&&nums[i]==nums[i-1]){
        		continue;
        	}
        	twoSum(nums, i+1, nums.length-1, 0-nums[i]);

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
        for(int i=0; i<nums.length-2; i++){
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
    
    //18. 4Sum
    public static List<List<Integer>> fourSum(int[] nums, int target) {
    	List<List<Integer>> re=new ArrayList<List<Integer>>();
        if(nums==null||nums.length<4){
        	return re;
        }        
        Arrays.sort(nums);
    	for(int i=0; i<nums.length-3; i++){
    		if(i>0&&nums[i]==nums[i-1]){
    			continue;
    		}
    		for(int j=nums.length-1; j>i+2; j--){
    			if(j<nums.length-1&&nums[j]==nums[j+1]){
    				continue;
    			}    			
    			int start=i+1;
    			int end=j-1;
    			
    			while(start<end){
    				int sum=nums[i]+nums[j]+nums[start]+nums[end];
    				if(sum==target){
    					re.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[start], nums[end], nums[j])));
    					while(start<end&&nums[start]==nums[start+1]){
    						start++;
    					}
    					while(start<end&&nums[end-1]==nums[end]){
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
    		}
    	}    	
    	return re;
    }
    
    public static List<List<Integer>> fourSum2(int[] nums, int target) {//没通过 0000怎么办
    	List<List<Integer>> re=new ArrayList<List<Integer>>();
		if(nums==null||nums.length<4){
			return re;
		}
		Arrays.sort(nums);
		Map<Integer, List<List<Integer>>> map=new HashMap<Integer, List<List<Integer>>>(); 
		List<Integer> temp;
		for(int i=0; i<nums.length; i++){
			if(i>0&&nums[i]==nums[i-1]){
				continue;
			}
			for(int j=i+1; j<nums.length; j++){
				if(j>i+1&&nums[j]==nums[j-1]){
					continue;
				}
				int sum=nums[i]+nums[j];
				temp=new ArrayList<Integer>(Arrays.asList(nums[i], nums[j]));	
				if(map.containsKey(target-sum)){						
					List<List<Integer>> diff=map.get(target-sum);
					for(List<Integer> list: diff){
						if(list.get(1)>nums[i]){
							continue;
						}
						List<Integer> r=new ArrayList<Integer>(list);
						r.addAll(temp);
						re.add(r);
					}
				}			
				if(map.containsKey(sum)){
					map.get(sum).add(temp);
				}else{											
					map.put(sum, new ArrayList<List<Integer>>(Arrays.asList(temp)));
				}				
			}
		}    	    	
    	return re;
    }
    
    //56. Merge Intervals
    class Interval {
    	      int start;
    	      int end;
    	      Interval() { start = 0; end = 0; }
    	      Interval(int s, int e) { start = s; end = e; }
    	 }
    
    static class myComparator implements Comparator<Interval>{

		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.start<o2.start? -1:(o1.start==o2.start? 0: 1);
		}
    	
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> re=new ArrayList<Interval>();
        if(intervals==null||intervals.size()<1){
        	return re;
        }
        Collections.sort(intervals, new myComparator());
        int temp=0;
        for(int i=1; i<intervals.size(); i++){
        	if(intervals.get(i).start<=intervals.get(temp).end){
        		intervals.get(temp).end=Math.max(intervals.get(temp).end, intervals.get(i).end);
        	}else{
        		re.add(intervals.get(temp));
        		temp=i;
        	}
        }       
        re.add(intervals.get(temp));        
    	return re;
    }
    
    //57. Insert Interval
    private int binarySearch(List<Interval> nums, int target){
    	int i=0, j=nums.size()-1;
    	while(i<=j){
    		int mid=i+(j-i)/2;
    		if(nums.get(mid).start<=target){
    			i=mid+1;
    		}else{
    			j=mid-1;
    		}
    	}
    	return j;
    }
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	List<Interval> re=new ArrayList<Interval>();
    	int index=0;
    	if(intervals.size()<1){
    		intervals.add(newInterval);
    	}else{
            index=binarySearch(intervals, newInterval.start);
            intervals.add(index+1, newInterval);
    	}
    	
        int temp=0;
        for(int i=1; i<intervals.size(); i++){
        	if(intervals.get(i).start<=intervals.get(temp).end){
        		intervals.get(temp).end=Math.max(intervals.get(temp).end, intervals.get(i).end);
        	}else{
        		re.add(intervals.get(temp));
        		temp=i;
        	}
        }       
        re.add(intervals.get(temp));        
    	return re;
    }
    
    //189. Rotate Array
    private static void rotateArray(int[] nums, int i, int j){
    	while(i<j){
    		int temp=nums[i];
    		nums[i]=nums[j];
    		nums[j]=temp;
    		i++;
    		j--;
    	}
    }
    
    public static void rotate(int[] nums, int k) {
    	int len=nums.length;
    	k%=len;
    	rotateArray(nums, 0, len-k-1);
    	rotateArray(nums, len-k, len-1);
    	rotateArray(nums, 0, len-1);
    	System.out.println(Arrays.toString(nums));
    }
    
    //48. Rotate Image
    public void rotate(int[][] matrix) {
        if(matrix==null||matrix.length==0){
        	return;
        }
        int n=matrix.length;
        int temp=0;
        for(int i=0; i<n; i++){
        	for(int j=i+1; j<n; j++){
        		temp=matrix[i][j];
        		matrix[i][j]=matrix[j][i];
        		matrix[j][i]=temp;
        	}
        }        
        for(int i=0; i<n; i++){
        	int left=0, right=n-1;
        	while(left<right){
        		temp=matrix[i][left];
        		matrix[i][left]=matrix[i][right];
        		matrix[i][right]=temp;
        		left++;
        		right--;
        	}
        }
    }
    
    //414. Third Maximum Number
    public static int thirdMax(int[] nums) {    	
        Integer[] max=new Integer[3];
        int num=0, temp=0;
      
        for(int i=0; i<nums.length; i++){
        	num=nums[i];
        	for(int j=0; j<max.length; j++){
        		if(max[j]==null||max[j]==num){
        			max[j]=num;
        			break;
        		}
        		if(num>max[j]){
        			temp=max[j];
        			max[j]=num;
        			num=temp;
        		}
        	}
        }
        System.out.println(Arrays.toString(max));
        return max[2]!=null? max[2]: max[0];
    }
    
    //448. Find All Numbers Disappeared in an Array
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> re=new ArrayList<Integer>();
        int len=nums.length;
        
        for(int i=0; i<len; i++){
        	nums[(nums[i]-1)%len]+=len;
        }
        for(int i=0; i<len; i++){
        	if(nums[i]<=len){
        		re.add(i+1);
        	}
        }
        return re;
    }
    
    //442. Find All Duplicates in an Array
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> re=new ArrayList<Integer>();
        int len=nums.length;
        for(int i=0; i<len; i++){
        	nums[(nums[i]-1)%len]+=len;
        }
        len*=2;
        for(int i=0; i<nums.length; i++){
        	if(nums[i]>len){
        		re.add(i+1);
        	}
        }
        return re;
    }
    
    //419. Battleships in a Board
    public int countBattleships(char[][] board) {
        if(board.length==0||board[0].length==0){
        	return 0;
        }
        int re=0, m=board.length, n=board[0].length;
        for(int i=0; i<m; i++){
        	for(int j=0; j<n; j++){
        		if(board[i][j]=='X'){
        			if((i>0&&board[i-1][j]=='X')||(j>0&&board[i][j-1]=='X')){
        			}else{
        				re++;
        			}
        		}
        	}
        }
        return re;
    }
    
	public static void main(String[] args) {		
		
		//rotate(new int[]{1,2}, 0);
		int[] nums={4,3,2,7,8,2,3,1};
		System.out.println(findDisappearedNumbers(nums));

	}
}
