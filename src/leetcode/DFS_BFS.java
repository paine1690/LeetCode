package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DFS_BFS {
	
	
	
    //417. Pacific Atlantic Water Flow
    private static void dfsFlow(int[][] matrix, int[][] d, int i, int j, int tar, int m, int n){
    	if((d[i][j]&tar)!=0){
    		return;
    	}
    	d[i][j]|=tar;
    	if(i>0&&matrix[i-1][j]>=matrix[i][j]){
    		dfsFlow(matrix, d, i-1, j, tar, m, n);
    	}
    	if(j>0&&matrix[i][j-1]>=matrix[i][j]){
    		dfsFlow(matrix, d, i, j-1, tar, m, n);
    	}
    	if(i<m-1&&matrix[i+1][j]>=matrix[i][j]){
    		dfsFlow(matrix, d, i+1, j, tar, m, n);
    	}
    	if(j<n-1&&matrix[i][j+1]>=matrix[i][j]){
    		dfsFlow(matrix, d, i, j+1, tar, m, n);
    	}
    }
    
    
    public static List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> re=new ArrayList<int[]>();
        if(matrix.length==0||matrix[0].length==0){
        	return re;
        }
        int m=matrix.length, n=matrix[0].length;
        int[][] d=new int[m][n];
        for(int j=0; j<n; j++){
        	dfsFlow(matrix, d, 0, j, 1, m, n);
        	dfsFlow(matrix, d, m-1, j, 2, m, n);
        }
        for(int i=0; i<m; i++){
        	dfsFlow(matrix, d, i, 0, 1, m, n);
        	dfsFlow(matrix, d, i, n-1, 2, m, n);
        }
        for(int i=0; i<m; i++){
        	for(int j=0; j<n; j++){
        		if(d[i][j]==3){
        			re.add(new int[]{i, j});
        		}
        	}
        }
        return re;
    }
    
    //473. Matchsticks to Square
    private static boolean dfsSquare(int[] nums, int index, int[] d, int sum){
    	if(index==nums.length){
    		boolean flag=true;
    		for(int i=0; i<d.length; i++){
    			if(d[i]!=sum){
    				flag=false;
    				break;
    			}
    		}
    		return flag;
    	}
    	
    	int num=nums[index];
    	for(int i=0; i<d.length; i++){
    		if(d[i]+num<=sum){
    			d[i]+=num;
    			if(dfsSquare(nums, index+1, d, sum)){
    				return true;
    			}
    			d[i]-=num;
    		}
    	}
    	return false;
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
    	return dfsSquare(nums, 0, d, sum);
    }
    
    //200. Number of Islands
    private void dfsCnt(char[][] grid, int i, int j, int m, int n){
    	if(grid[i][j]=='1'){
    		grid[i][j]='*';
    		
    		if(i>0){
    			dfsCnt(grid, i-1, j, m, n);
    		}
    		if(j>0){
    			dfsCnt(grid, i, j-1, m, n);
    		}
    		if(i<m-1){
    			dfsCnt(grid, i+1, j, m, n);
    		}
    		if(j<n-1){
    			dfsCnt(grid, i, j+1, m, n);
    		}
    	}
    }
    
    public int numIslands(char[][] grid) {
    	if(grid.length==0||grid[0].length==0){
    		return 0;
    	}
    	int m=grid.length, n=grid[0].length;
        int re=0;
        for(int i=0; i<m; i++){
        	for(int j=0; j<n; j++){
        		if(grid[i][j]=='1'){
        			dfsCnt(grid, i, j, m, n);
        			re++;
        		}
        	}
        }
        return re;
    }
    
    //491. Increasing Subsequences
    private static void dfsFind(int[] nums, int index, int num, Set<List<Integer>> re, List<Integer> list){
    	if(index>=nums.length){
    		return;
    	}
    	for(int i=index; i<nums.length; i++){
    		if(nums[i]>=num){
    			List<Integer> temp=new LinkedList<Integer>(list);    						
    			temp.add(nums[i]);
    			if(re.contains(temp)){
    				continue;
    			}
    			System.out.println(temp);
    			re.add(temp);
    			dfsFind(nums, i+1, nums[i], re, temp);
    		}
    	}
    }    
    
    public static List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> re=new HashSet<List<Integer>>();
        if(nums.length>=2){
        	List<Integer> list;
            for(int i=0; i<nums.length; i++){
            	list=new LinkedList<Integer>();
            	list.add(nums[i]);
            	dfsFind(nums, i+1, nums[i], re, list);        
            }
            
        }
        return new ArrayList<List<Integer>>(re);
    }
    
    
	public static void main(String[] args) {
		System.out.print(findSubsequences(new int[]{1,2,3,1}));
//		int[][] nums={
//				{1,2,2,3,5},
//				{3,2,3,4,4},
//				{2,4,5,3,1},
//				{6,7,1,4,5},
//				{5,1,1,2,4},
//		};
//		System.out.println(makesquare(new int[]{5,5,5,5,4,4,4,4,3,3,3,3}));

	}

}
