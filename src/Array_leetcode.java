import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Array_leetcode {

	
    
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
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	int m=nums1.length;
    	int n=nums2.length;
        int mid=(m+n)/2;
        int[] temp=new int[m+n];
        double re=0;
        if(m==0){
        	for(int i=0;i<mid+1;i++){
        		temp[i]=nums2[i];
        	}
        }
        else if(n==0){
        	for(int i=0;i<mid+1;i++){
        		temp[i]=nums1[i];
        	}
        }
        
        else{
            int i=0;
            int j=0;
            int k=0;
            while(i<m&&j<n&&k<mid+1){
                if(nums1[i]<nums2[j]){
                    temp[k++]=nums1[i++];
                }
                else{
                    temp[k++]=nums2[j++];
                }
            }
            while(i<m&&k<mid+1){
                temp[k++]=nums1[i++];
            }
            while(j<n&&k<mid+1){
                temp[k++]=nums2[j++];
            }
            
        }
        if((m+n)%2==1){
        	re=temp[mid];
        }
        else{
        	re=(temp[mid]+temp[mid-1])/2.0;
        }
        
        return re;    
    }
    
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set=new HashSet<Integer>();
    	for(int i=0; i<nums.length; i++){
    		if(set.contains(nums[i])){
    			return true;
    		}else{
    			set.add(nums[i]);
    		}
    	}
    	
    	return false;
    }
    
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	int len=nums.length;
    	if(len<2){
    		return false;
    	}
    	HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
    	for(int i=0; i<len; i++){
    		if(!map.containsKey(nums[i])){
    			map.put(nums[i], i);
    		}else{
    			if((i-map.get(nums[i])<=k)){
    				return true;
    			}
    			map.put(nums[i], i);
    		}
    	}	
    	return false; 
    }
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
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] aa={1,1,1,1,2,3};
	}

}
