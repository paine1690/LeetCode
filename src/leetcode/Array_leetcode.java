package leetcode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Array_leetcode {

	
    
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
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] aa={1,1,1,1,2,3};
	}

}
