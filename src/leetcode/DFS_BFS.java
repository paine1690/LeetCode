package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    //529. Minesweeper
    private static int cnt(char[][] board, int i, int j, int m, int n){
    	if(i<0||j<0||i>=m||j>=n){
    		return 0;
    	}
    	char c=board[i][j];
    	if(c=='M'){
    		return 1;
    	}
    	return 0;
    }    
    
    private static void click(char[][] board, int i, int j, int m, int n){
    	if(i<0||j<0||i>=m||j>=n){
    		return;
    	}
    	
    	char c=board[i][j];    	
    	if(c=='E'){
    		int cnt=0;
        	for(int x=i-1; x<=i+1; x++){
        		for(int y=j-1; y<=j+1; y++){
        			cnt+=cnt(board, x, y, m, n);
        		}
        	}
        	if(cnt==0){
        		board[i][j]='B';
        		for(int x=i-1; x<=i+1; x++){
            		for(int y=j-1; y<=j+1; y++){
            			click(board, x, y, m, n);
            		}
            	}
        	}else{
        		board[i][j]=(char)('0'+cnt);
        	}
    	}    	  
    }
    
    public static char[][] updateBoard(char[][] board, int[] click) {
        if(board.length==0||board[0].length==0){
        	return board;
        }
    	int m=board.length, n=board[0].length;
    	int i=click[0], j=click[1];
    	if(board[i][j]=='M'){
    		board[i][j]='X';
    	}else{
    		click(board, i, j, m, n);
    	}    	
    	for(char[] chars: board){
    		System.out.println(Arrays.toString(chars));
    	}
    	
    	return board;
    }
    
    //542. 01 Matrix    
    public static List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
    	int m=matrix.size(), n=matrix.get(0).size();
    	Integer[][] matrix2=new Integer[m][n];
    	
    	for(int i=0; i<m; i++){
    		Integer[] temp=new Integer[]{};
    		matrix2[i]=matrix.get(i).toArray(temp);
    	}
    	for(Integer[] nums: matrix2){
    		System.out.println(Arrays.toString(nums));
    	}
    	
    	Integer[][] re=new Integer[m][n];
    	for(int i=0; i<m; i++){
    		for(int j=0; j<n; j++){
    			if(matrix2[i][j]==0){
    				re[i][j]=0;
    			}else{
    				int min=10001;
    				if(i>0){
    					min=Math.min(min, re[i-1][j]);
    				}
    				if(j>0){
    					min=Math.min(min, re[i][j-1]);
    				}
    				re[i][j]=min+1;
    			}
    		}
    	}
    	
    	for(int i=m-1; i>=0; i--){
    		for(int j=n-1; j>=0; j--){
    			if(re[i][j]!=0&&re[i][j]!=1){
    				if(i<m-1){
    					re[i][j]=Math.min(re[i][j], re[i+1][j]+1);
    				}
    				if(j<n-1){
    					re[i][j]=Math.min(re[i][j], re[i][j+1]+1);
    				}
    			}
    		}
    	}
    	
    	
    	
    	for(Integer[] nums: re){
    		System.out.println(Arrays.toString(nums));
    	}
    	List<List<Integer>> result=new ArrayList<List<Integer>>();
    	for(Integer[] nums: re){
    		result.add(new ArrayList<Integer>(Arrays.asList(nums)));
    	}
    	
        return result;
    }
    
    //547. Friend Circles
    static int re;
    
    static private void dfs(int[][] m, int i, int len){
    	for(int j=0; j<len; j++){
    		if(m[i][j]==-1){
    			return;
    		}else if(m[i][j]==1){
    			m[i][j]=-1;
    			dfs(m, j, len);
    		}else{
    			m[i][j]=-1;
    		}
    	}
    }
    
    static public int findCircleNum(int[][] M) {
    	if(M.length==0||M[0].length==0){
    		return 0;
    	}
    	int len=M.length;
        re=0;
        
        for(int i=0; i<len; i++){
        	int j=0;
        	for(j=0; j<len; j++){
        		if(M[i][j]==-1){
        			break;
        		}else if(M[i][j]==1){    
        			M[i][j]=-1;
        			if(i!=j){
        				dfs(M, j, len);
        			}        			
        		}
        	}
        	if(j==len){
        		re++;
        	}
        }        
        return re;
    }
    
	public static void main(String[] args) {
		int[][] M=new int[][]{
			{1,1,0},
			{1,1,0},
			{0,0,1},
		};
		System.out.println(findCircleNum(M));
//		List<Integer> l1=new ArrayList<Integer>(Arrays.asList(0,0,0));
//		List<Integer> l2=new ArrayList<Integer>(Arrays.asList(0,1,0));
//		List<Integer> l3=new ArrayList<Integer>(Arrays.asList(1,1,1));
//		List<Integer> l4=new ArrayList<Integer>(Arrays.asList(1,1,1));
//		List<Integer> l5=new ArrayList<Integer>(Arrays.asList(0,0,0));
//		updateMatrix(new ArrayList<List<Integer>>(Arrays.asList(l1, l2, l3, l4, l5)));
//		System.out.print(updateBoard(new char[][]{
//			"EEEEE".toCharArray(),
//			"EEMEE".toCharArray(),
//			"EEEEE".toCharArray(),
//			"EEEEE".toCharArray()}, new int[]{3, 0}));
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
