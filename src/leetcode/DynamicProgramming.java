package leetcode;

import java.util.Arrays;

public class DynamicProgramming {
	
	//70. Climbing Stairs		斐波那契数列
    public static int climbStairs(int n) {
    	int a=0, b=1;
    	int re=0;
    	for(int i=1; i<=n; i++){
    		re=a+b;
    		a=b;
    		b=re;
    	}
        return re;
    }
	
    //121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
    	if(prices.length<2){
    		return 0;
    	}
        int re=0;
        int low=prices[0];
        for(int i=1; i<prices.length; i++){
        	if(prices[i]<low){
        		low=prices[i];
        	}else{
        		re=Math.max(re, prices[i]-low);
        	}
        }
        return re;
    }
	
    //198. House Robber   dp[i]=max(dp[i-1], dp[i-2]+num[i])
    public int rob(int[] nums) {
        if(nums.length<2){
        	return nums.length==0? 0:nums[0];
        }
    	nums[1]=Math.max(nums[0], nums[1]);
    	for(int i=2; i<nums.length; i++){
    		nums[i]=Math.max(nums[i-1], nums[i-2]+nums[i]);
    	}
    	return nums[nums.length-1];
    }
    
    //303. Range Sum Query - Immutable
    class NumArray {
    	private int[] re;
        public NumArray(int[] nums) {
        	if(nums.length>0){
        		re=new int[nums.length];
                re[0]=nums[0];
                for(int i=1; i<nums.length; i++){
                	re[i]=nums[i]+re[i-1];
                }
        	}
        }
        public int sumRange(int i, int j) {
           return i==0? re[j]:(re[j]-re[i-1]);
        }
    }
    
    //304. Range Sum Query 2D - Immutable
    class NumMatrix {
    	private int[][] matrix;
        public NumMatrix(int[][] matrix) {
            this.matrix=matrix;
            if(matrix.length==0||matrix[0].length==0){
            	return;
            }
            int m=matrix.length;
            int n=matrix[0].length;
            
            for(int i=1; i<n; i++){
            	matrix[0][i]+=matrix[0][i-1];
            }
            
            for(int i=1; i<m; i++){
            	matrix[i][0]+=matrix[i-1][0];
            	for(int j=1; j<n; j++){
            		matrix[i][j]+=(matrix[i-1][j]+matrix[i][j-1]-matrix[i-1][j-1]);
            	}
            }            
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
        	if(row1==0&&col1==0){
        		return matrix[row2][col2];
        	}else if(row1==0){
        		return matrix[row2][col2]-matrix[row2][col1-1];
        	}else if(col1==0){
        		return matrix[row2][col2]-matrix[row1-1][col2];
        	}else{
        		return matrix[row2][col2]-matrix[row1-1][col2]-matrix[row2][col1-1]+matrix[row1-1][col1-1];
        	}
        }
    }
    
    //64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
    	if(grid.length==0||grid[0].length==0){
        	return 0;
        }
        int m=grid.length;
        int n=grid[0].length;
        for(int i=1; i<m; i++){
        	grid[i][0]+=grid[i-1][0];
        }
        for(int i=1; i<n; i++){
        	grid[0][i]+=grid[0][i-1];
        }
        for(int i=1; i<m&&i<n; i++){
        	grid[i][i]+=Math.min(grid[i-1][i], grid[i][i-1]);
        	for(int j=i+1; j<m; j++){
        		grid[j][i]+=Math.min(grid[j-1][i], grid[j][i-1]);
        	}
        	for(int j=i+1; j<n; j++){
        		grid[i][j]+=Math.min(grid[i-1][j], grid[i][j-1]);
        	}
        }
        return grid[m-1][n-1];
    }
    
    //62. Unique Paths
    public int uniquePaths(int m, int n) {
        if(m==0||n==0){
        	return 0;
        }
    	int[][] nums=new int[m][n];
    	nums[0][0]=1;
    	for(int i=1; i<m; i++){
    		nums[i][0]=1;
    	}
    	for(int i=1; i<n; i++){
    		nums[0][i]=1;
    	}
    	for(int i=1; i<m; i++){
    		for(int j=1; j<n; j++){
    			nums[i][j]=nums[i-1][j]+nums[i][j-1];
    		}
    	}
    	return nums[m-1][n-1];
    }
    
    //63. Unique Paths II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length==0||obstacleGrid[0].length==0||obstacleGrid[0][0]==1){
        	return 0;
        }
    	int m=obstacleGrid.length;
    	int n=obstacleGrid[0].length;
    	obstacleGrid[0][0]=1;
    	int i=0;
    	for(i=1; i<m; i++){
    		if(obstacleGrid[i][0]==1){
    			break;
    		}else{
    			obstacleGrid[i][0]=1;
    		}
    	}
    	for(; i<m; i++){
    		obstacleGrid[i][0]=0;
    	}
    	for(i=1; i<n; i++){
    		if(obstacleGrid[0][i]==1){
    			break;
    		}else{
    			obstacleGrid[0][i]=1;
    		}
    	}
    	for(; i<n; i++){
    		obstacleGrid[0][i]=0;
    	}
    	
    	for(i=1; i<m; i++){
    		for(int j=1; j<n; j++){
    			if(obstacleGrid[i][j]==1){
    				obstacleGrid[i][j]=0;
    			}else{
    				obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
    			}
    		}
    	}
    	return obstacleGrid[m-1][n-1];
    }
    
    //53. Maximum Subarray
    public static int maxSubArray(int[] nums) {
    	if(nums.length==0){
    		return 0;
    	}
    	int re=nums[0];
        for(int i=1; i<nums.length; i++){
        	if(nums[i-1]>0){
        		nums[i]+=nums[i-1];
        	}
        	if(nums[i]>re){
        		re=nums[i];
        	}
        }
    	return re;
    }
    
    //300. Longest Increasing Subsequence
    public static int lengthOfLIS(int[] nums) {
    	if(nums.length<2){
    		return nums.length;
    	}
    	int[] count=new int[nums.length];
    	for(int i=0; i<count.length; i++){
    		count[i]=1;
    	}
        int re=1;
        for(int i=1; i<nums.length; i++){
        	int max=0;
        	for(int j=0; j<i; j++){
        		if(nums[j]<nums[i]&&count[j]>max){
        			max=count[j];
        		}
        	}
        	count[i]=max+1;
        }
        for(int i=0; i<count.length; i++){
        	if(count[i]>re){
        		re=count[i];
        	}
        }
        return re;
    }
    
    //300. Longest Increasing Subsequence
    private static int binarySearch(int[] nums, int i, int j, int target){
    	while(i<j){
    		int mid=(i+j)>>>1;
        	if(target>nums[mid]&&target<=nums[mid+1]){
        		return mid;
        	}else if(nums[mid]<target){
        		i=mid+1;
        	}else{
        		j=mid-1;
        	}
    	}
    	return 0;
    }
    
    public static int lengthOfLIS2(int[] nums) {
    	if(nums.length<2){
    		return nums.length;
    	}
    	int len=1;
    	int[] d=new int[nums.length+1];
    	d[1]=nums[0];
    	for(int i=1; i<nums.length; i++){
    		if(nums[i]>d[len]){
    			d[++len]=nums[i];
    		}else{
    			int j=binarySearch(d, 0, len, nums[i]);
    			d[++j]=nums[i];
    		}
    		System.out.println(Arrays.toString(d));
    	}
    	return len;
    }
    
    //91. Decode Ways
    public static int numDecodings(String s) {
        if(s.length()<1||s.charAt(0)-'0'==0){
        	return 0;
        }
        int[] nums=new int[s.length()+1];
        nums[0]=1;
        nums[1]=1;
        for(int i=2; i<nums.length; i++){
        	if(Integer.valueOf(s.substring(i-2,  i))<=26&&Integer.valueOf(s.substring(i-2,  i))>=10){
        		nums[i]+=nums[i-2];
        	}
        	if(Integer.valueOf(s.substring(i-1, i))!=0){
        		nums[i]+=nums[i-1];
        	}
        }
        return nums[nums.length-1];
    }
    
    //213. House Robber II
    public static int rob2(int[] nums) {
        if(nums.length<3){
        	if(nums.length==2){
            	return Math.max(nums[0], nums[1]);
            }
        	return nums.length==0? 0:nums[0];
        }
        
        int max=0;
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0], nums[1]);
        
    	for(int i=2; i<nums.length-1; i++){
    		dp[i]=Math.max(dp[i-1], nums[i]+dp[i-2]);
    	}
    	max=dp[nums.length-2];
    	dp[1]=nums[1];
    	dp[2]=Math.max(nums[1], nums[2]);
    	for(int i=3; i<nums.length; i++){
    		dp[i]=Math.max(dp[i-1], nums[i]+dp[i-2]);
    	}
    	System.out.println(dp[nums.length-1]);
    	return Math.max(max, dp[nums.length-1]);
    }
    
    //96. Unique Binary Search Trees
    public int numTrees(int n) {
    	if(n<3){
    		return n;
    	}
        int[] dp=new int[n+1];
    	dp[0]=1;
    	dp[1]=1;
    	dp[2]=2;
    	for(int i=3; i<=n; i++){
    		for(int j=0; j<i; j++){
    			dp[i]+=dp[j]*dp[i-j-1];
    		}
    	}
    	return dp[n];
    }
	public static void main(String[] args) {
		int nums[]={1,2,1,1};
		//String s="1";
		System.out.println(rob2(nums));
	}

}

