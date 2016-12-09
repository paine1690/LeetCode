package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Greedy {

	
	
	//330. Patching Array
    public int minPatches(int[] nums, int n) {
        long miss=1;
        int re=0, i=0;
        
        while(miss<=n){
        	if(i<nums.length&&nums[i]<=miss){
        		miss+=nums[i++];
        	}else{
        		miss+=miss;
        		re++;
        	}
        } 	    	
    	return re;
    }
	
  //376. Wiggle Subsequence
    public static int wiggleMaxLength(int[] nums) {
    	if(nums.length<=1){
    		return nums.length;
    	}
    	int re=nums.length, flag=0;
    	for(int i=1; i<nums.length; i++){
    		if(nums[i]==nums[i-1]){
    			re--;
    		}else if(nums[i]>nums[i-1]){
    			if(flag==1){
    				re--;
    			}
    			flag=1;
    		}else{
    			if(flag==-1){
    				re--;
    			}
    			flag=-1;
    		}
    	}
    	return re;
    }
    
    //455. Assign Cookies
    public int findContentChildren(int[] g, int[] s) {
    	int re=0;
    	Arrays.sort(g);
    	Arrays.sort(s);
    	int i=0, j=0;
    	while(i<g.length&&j<s.length){
    		if(s[j]>=g[i]){
    			re++;
    			i++;
    			j++;
    		}else{
    			j++;
    		}
    	}
    	return re;    	
    }
    
    //435. Non-overlapping Intervals
    static class Interval {
    	int start;
    	int end;
    	Interval() { start = 0; end = 0; }
    	Interval(int s, int e) { start = s; end = e; }
    }
    
    public int eraseOverlapIntervals(Interval[] intervals) {
        if(intervals.length<2){
        	return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>(){

			@Override
			public int compare(Interval i1, Interval i2) {
				if(i1.start!=i2.start){
					return i1.start>i2.start? 1: -1;
				}else{
					return i1.end==i2.end? 0: i1.end>i2.end? 1: -1;
				}
			}
        	
        });
    	int re=0, max=intervals[0].end;
    	for(int i=1; i<intervals.length; i++){
    		if(intervals[i].start==intervals[i-1].start){
    			re++;
    		}else{
    			if(intervals[i].start<max){
    				re++;
    				max=Math.min(max, intervals[i].end);
    			}else{
    				max=intervals[i].end;
    			}
    			
    		}
    	}    	
    	return re;
    }
    
    //452. Minimum Number of Arrows to Burst Balloons
    public static int findMinArrowShots(int[][] points) {
    	if(points.length<2){
    		return points.length;
    	}
    	int re=1;
    	Arrays.sort(points, new Comparator<int[]>(){
			@Override
			public int compare(int[] i1, int[] i2) {
				if(i1[0]==i2[0]){
					return i1[1]==i2[1]? 0: i1[1]>i2[1]? 1: -1;
				}else{
					return i1[0]>i2[0]? 1: -1;
				}
			}
    	});
    	
    	int pre=points[0][1];
    	for(int i=1; i<points.length; i++){
    		if(points[i][0]==points[i-1][0]){
    			continue;
    		}    		
    		if(points[i][0]<=pre){
    			pre=Math.min(pre, points[i][1]);
    		}else{
    			re++;
    			pre=points[i][1];
    		}
    	}
    	return re;
    }
    
    
	public static void main(String[] args) {
		System.out.println(findMinArrowShots(new int[][]{
			{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}
		}));

	}

}
