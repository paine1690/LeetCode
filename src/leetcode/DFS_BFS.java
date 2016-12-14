package leetcode;

import java.util.ArrayList;
import java.util.List;

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
    
    
	public static void main(String[] args) {
		int[][] nums={
				{1,2,2,3,5},
				{3,2,3,4,4},
				{2,4,5,3,1},
				{6,7,1,4,5},
				{5,1,1,2,4},
		};
		System.out.println(pacificAtlantic(nums));

	}

}