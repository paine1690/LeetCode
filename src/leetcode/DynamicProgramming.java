package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamicProgramming {
	
	static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
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
    		nums[i]=Math.max(nums[i], nums[i-1]+nums[i]);
    		re=Math.max(re, nums[i]);
    	}
    	return nums[nums.length-1];
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
    	while(i<=j){
    		int mid=(i+j)>>>1;
        	if(nums[mid]==target){
        		return mid;
        	}else if(nums[mid]<target){
        		i=mid+1;
        	}else{
        		j=mid-1;
        	}
    	}
    	return ++j;
    }
    
    public static int lengthOfLIS2(int[] nums) {
    	if(nums.length<2){
    		return nums.length;
    	}
    	int len=0;
    	int[] d=new int[nums.length+1];
    	d[0]=Integer.MIN_VALUE;
    	for(int i=0; i<nums.length; i++){
    		if(nums[i]>d[len]){
    			len++;
    			d[len]=nums[i];
    		}else{
    			int j=binarySearch(d, 1, len, nums[i]);
    			d[j]=nums[i];
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
        	int num=Integer.valueOf(s.substring(i-2,  i));
        	if(num<=26&&num>=10){
        		nums[i]+=nums[i-2];
        	}
        	if(s.charAt(i-1)!='0'){
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
    
    //221. Maximal Square
    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0){
        	return 0;
        }
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] dp=new int[m][n];
        int max=matrix[0][0]=='1'? 1:0;
    	for(int i=0; i<n; i++){
    		if(matrix[0][i]=='1'){
    			dp[0][i]=1;
    			max=1;
    		}
    	}
    	for(int i=0; i<m; i++){
    		if(matrix[i][0]=='1'){
    			dp[i][0]=1;
    			max=1;
    		}
    	}
    	for(int i=1; i<m; i++){
    		for(int j=1; j<n; j++){
    			if(matrix[i][j]=='1'){
    				dp[i][j]=Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1])+1;
    				if(dp[i][j]>max){
    					max=dp[i][j];
    				}
    			}
    		}
    	}
    	return max*max;
    }
    
    //152. Maximum Product Subarray
    public static int maxProduct(int[] nums) {
        if(nums.length<2){
        	return nums.length==0? 0:nums[0];
        }
        int max=nums[0];
        int min=nums[0];
        int re=nums[0];
    	for(int i=1; i<nums.length; i++){
    		int a=nums[i]*max;
    		int b=nums[i]*min;
    		max=Math.max(nums[i], Math.max(a, b));
    		min=Math.min(nums[i], Math.min(a, b));
    		re=Math.max(max, re);
    	}
    	return re;
    }
    
    //122. Best Time to Buy and Sell Stock II
    public int maxProfit2(int[] prices) {
        if(prices.length<2){
        	return 0;
        }
    	int max=0;
    	for(int i=1; i<prices.length; i++){
    		if(prices[i]>prices[i-1]){
    			max+=prices[i]-prices[i-1];
    		}
    	}
    	return max;
    }
    
    //279. Perfect Squares
    public int numSquares(int n) {
        int[] dp=new int[n+1];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	for(int i=1; i*i<=n; i++){
    		dp[i*i]=1;
    	}
    	for(int a=1; a<=n; a++){
    		for(int b=1; a+b*b<=n; b++){
    			dp[a+b*b]=Math.min(dp[a+b*b], dp[a]+1);
    		}
    	}
    	return dp[n];
    }
    
    //264. Ugly Number II
    public static int nthUglyNumber(int n) {
    	int[] ugly=new int[n];
    	ugly[0]=1;
    	int n1=0, n2=0, n3=0;
    	int v1=ugly[n1]*2, v2=ugly[n2]*3, v3=ugly[n3]*5;
    	for(int i=1; i<n; i++){
    		ugly[i]=Math.min(v1, Math.min(v2, v3));
    		if(ugly[i]==v1){
    			n1++;
    			v1=ugly[n1]*2;
    		}
    		if(ugly[i]==v2){
    			n2++;
    			v2=ugly[n2]*3;
    		}
    		if(ugly[i]==v3){
    			n3++;
    			v3=ugly[n3]*5;
    		}
    	}	
    	return ugly[n-1];
    }
    
    //120. Triangle
    public static int minimumTotal(List<List<Integer>> triangle) {
    	int n=triangle.size();
        int[] dp=new int[n];
        int re=triangle.get(0).get(0);
        dp[0]=re;
        
        for(int i=1; i<triangle.size(); i++){
        	int m=triangle.get(i).size();
        	dp[m-1]=dp[m-2]+triangle.get(i).get(m-1);
        	re=dp[m-1];
        	for(int j=m-2; j>0; j--){
        		dp[j]=Math.min(dp[j-1], dp[j])+triangle.get(i).get(j);
        		re=Math.min(re, dp[j]);
        	}
        	dp[0]+=triangle.get(i).get(0);
        	re=Math.min(re, dp[0]);
        	System.out.println(Arrays.toString(dp));
        }    	
    	return re;
    }
    
    //392. Is Subseque
    public static boolean isSubsequence(String s, String t) {
    	if(s.length()==0){
    		return true;
    	}
    	if(t.length()==0){
    		return false;
    	}
        char[] ss=s.toCharArray();
        char[] ts=t.toCharArray();
        int i=0, j=0;
        for(j=0; j<ts.length; j++){
        	if(ts[j]==ss[i]){
        		if(++i>=ss.length){
        			return true;
        		}
        	}
        }    	
    	return false;
    }
    
    //337. House Robber III
    private static int[] dfs(TreeNode root){
    	int[] re={0, 0};
    	if(root!=null){
    		int[] left=dfs(root.left);
        	int[] right=dfs(root.right);
        	re[0]=left[1]+right[1];//不抢root
        	re[1]=Math.max(re[0], root.val+left[0]+right[0]);//抢root，保存最大值
    	}
    	return re;    	
    }
    
    public static int rob(TreeNode root) {
        if(root==null){
        	return 0;
        }
    	return dfs(root)[1];
    }
    
    //377. Combination Sum IV
    public int combinationSum4(int[] nums, int target) {
        if(nums.length==0){
        	return 0;
        }
        Arrays.sort(nums);
        int[] dp=new int[target+1];
        dp[0]=1;
        for(int i=1; i<=target; i++){
        	for(int j=0; j<nums.length; j++){
        		int diff=i-nums[j];
        		if(diff>=0){
        			dp[i]+=dp[diff];
        		}else{
        			break;
        		}
        	}
        }
        return dp[target+1];
    }
    
    //376. Wiggle Subsequence
    public static int wiggleMaxLength(int[] nums) {
        if(nums.length==0){
        	return 0;
        }
        int[][] dp=new int[nums.length][2];
        dp[0][0]=1;
        dp[0][1]=1;
        for(int i=1; i<nums.length; i++){
        	int a=0;
        	int b=0;
        	for(int j=i-1; j>=0; j--){
        		if(nums[j]<nums[i]){
        			a=Math.max(a, dp[j][1]);
        		}else if(nums[j]>nums[i]){
        			b=Math.max(b, dp[j][0]);
        		}        		
        	}
        	dp[i][0]=a>0? a+1:1;
        	dp[i][1]=b>0? b+1:1;
        }
        return Math.max(dp[nums.length-1][0], dp[nums.length-1][1]);
    }
    
    //32. Longest Valid Parentheses
    private static boolean isV(String s, int i, int j){
    	return i>=0&&s.charAt(i)=='('&&s.charAt(j)==')';
    }
    
    public static int longestValidParentheses(String s) {
        int re=0;
    	int[] dp=new int[s.length()];
        for(int j=1; j<s.length(); j++){
        	int i=j-dp[j-1]-1;
        	if(isV(s, i, j)){
        		dp[j]=dp[j-1]+2;
        		if(i>0){
        			dp[j]+=dp[i-1];
        		}
        		re=Math.max(re,  dp[j]);
        	}        	
        }        
    	return re;
    }
    
    //10. Regular Expression Matching    
    public static boolean isMatch(String s, String p) {
    	int m=p.length(), n=s.length();
    	boolean[][] dp=new boolean[m+1][n+1];    	
    	dp[0][0]=true;
    	for(int i=2; i<=m; i++){
    		dp[i][0]=p.charAt(i-1)=='*'&&dp[i-2][0];
    	}
    	
    	for(int i=1; i<=m; i++){
    		for(int j=1; j<=n; j++){
    			if(p.charAt(i-1)=='*'){
    				dp[i][j]=dp[i-2][j]
    					||(p.charAt(i-2)=='.'||p.charAt(i-2)==s.charAt(j-1))&&(dp[i][j-1]);
    					
    			}else{
    				dp[i][j]=(p.charAt(i-1)=='.'||p.charAt(i-1)==s.charAt(j-1))&&dp[i-1][j-1];
    			}
    		}
    	}
    	
    	for(int i=0; i<dp.length; i++){
    		System.out.println(Arrays.toString(dp[i]));
    	}
    	return dp[m][n];
    }
    
    //44. Wildcard Matching
    public static boolean isMatch2(String s, String p) {
    	int m=p.length(), n=s.length();
    	boolean[][] dp=new boolean[m+1][n+1];
    	dp[0][0]=true;
    	for(int i=1; i<=m; i++){
    		dp[i][0]=dp[i-1][0]&&p.charAt(i-1)=='*';
    		for(int j=1; j<=n; j++){
    			if(p.charAt(i-1)=='*'){
    				dp[i][j]=dp[i-1][j]||dp[i][j-1]||dp[i-1][j-1];
    			}else{
    				dp[i][j]=(p.charAt(i-1)=='?'||p.charAt(i-1)==s.charAt(j-1))&&dp[i-1][j-1];
    			}
    		}
    	}    	
    	return dp[m][n];
    }
    
    //44. Wildcard Matching
    public static boolean isMatch3(String s, String p) {
    	boolean [][]dp=new boolean[2][s.length()+1];
    	dp[0][0]=true;
    	int cur=0, last=0;
    	for(int i=1; i<=p.length(); i++){
    		cur=i&1;
    		last=1-cur;
    		dp[cur][0]=dp[last][0]&&p.charAt(i-1)=='*';
    		for(int j=1; j<=s.length(); j++){
    			if(p.charAt(i-1)=='*'){
    				dp[cur][j]=dp[cur][j-1]||dp[last][j]||dp[last][j-1];
    			}else{
    				dp[cur][j]=(p.charAt(i-1)=='?'||p.charAt(i-1)==s.charAt(j-1))&&dp[last][j-1];
    			}
    		}
    	}
    	return dp[cur][s.length()];
    }
    
    //416. Partition Equal Subset Sum
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int num: nums){
        	sum+=num;
        }
        if((sum&1)==1){
        	return false;
        }
        
        int n=nums.length;
        sum/=2;
        // dp[i][j] 表示 如果我们取前i个数字，且背包容量为j的情况下，最多能放入多少东西
        int[][] dp=new int[n][sum+1];
        for(int i=nums[0]; i<=sum; i++){
        	dp[0][i]=nums[0];
        }
        for(int i=1; i<n; i++){
        	for(int j=nums[i]; j<=sum; j++){
        		dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-nums[i]]+nums[i]);
        	}
        }
        
        return dp[n-1][sum]==sum;
    }
    
    public static boolean canPartition2(int[] nums) {
        int sum=0;
        for(int num: nums){
        	sum+=num;
        }
        if((sum&1)==1){
        	return false;
        }
    	sum/=2;
    	boolean[] dp=new boolean[sum+1];//数组中和是否可以为dp[i]
    	dp[0]=true;
    	for(int i=0; i<nums.length; i++){
    		for(int s=sum; s>=nums[i]; s--){
    			dp[s]|=dp[s-nums[i]];
    		}
    	}
    	return dp[sum];
    }
    
    //123. Best Time to Buy and Sell Stock III
    public int maxProfit3(int[] prices) {
        if(prices.length<2){
        	return 0;
        }
        int len=prices.length;
        int[] pre=new int[len];
        int[] post=new int[len];
    	
    	int min=prices[0];
    	for(int i=1; i<len; i++){
    		min=Math.min(min, prices[i]);
    		pre[i]=Math.max(pre[i-1], prices[i]-min);
    	}
    	int max=prices[len-1];
    	for(int i=len-2; i>=0; i--){
    		max=Math.max(max, prices[i]);
    		post[i]=Math.max(post[i+1], max-prices[i]);
    	}
    	
    	int re=0;
    	for(int i=0; i<len; i++){
    		re=Math.max(re, pre[i]+post[i]);
    	}
    	
    	return re;
    }
    
    //188. Best Time to Buy and Sell Stock IV
    public int maxProfit(int k, int[] prices) {
        if(prices.length<2){
        	return 0;
        }
        int len=prices.length;
        if(k>=len){
        	return maxProfit2(prices);
        }
        
        int[][] local=new int[len][k+1];
        int[][] global=new int[len][k+1];
    	
        for(int i=1; i<len; i++){
        	int diff=prices[i]-prices[i-1];
        	for(int j=1; j<=k; j++){
        		local[i][j]=Math.max(local[i-1][j]+diff, global[i-1][j-1]);
        		global[i][j]=Math.max(local[i][j], global[i-1][j]);
        	}
        }
    	
    	return global[len-1][k];
    }
    
    //368. Largest Divisible Subset
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> re=new ArrayList<Integer>();
        Arrays.sort(nums);
        int len=nums.length;
        int[][] dp=new int[len][len+1];
        for(int i=0; i<len; i++){
        	for(int j=i+1; j<len; j++){
        		dp[i][j]=nums[j]%nums[i]==0? 1: 0;
        	}
        }
        int reMax=0, pos=0;
        
        for(int i=len-1; i>=0; i--){
        	int max=0, maxPos=i;
        	
        	for(int j=len-1; j>i; j--){
        		if(dp[i][j]==1&&dp[j][len]>max){
        			max=dp[j][len];
        			maxPos=j;
        		}
        	}
        	dp[i][i]=maxPos;
        	dp[i][len]=max+1;
        	if(dp[i][len]>reMax){
        		reMax=dp[i][len];
        		pos=i;        		
        	}
        }
        for(int[] a: dp){
        	System.out.println(Arrays.toString(a));
        }
        System.out.println(reMax+" "+pos);
        for(int i=0; i<reMax; i++){
        	re.add(nums[pos]);
        	pos=dp[pos][pos];
        }
        
        return re;
    }
    
    //467. Unique Substrings in Wraparound String
    public static int findSubstringInWraproundString(String p) {
        int[] a=new int[26];
        int cnt=0;
    	for(int i=0; i<p.length(); i++){    		
    		if(i>0&&(p.charAt(i-1)==p.charAt(i)-1||p.charAt(i-1)==p.charAt(i)+25)){
    			cnt++;
    		}else{
    			cnt=1;
    		}
    		a[p.charAt(i)-'a']=Math.max(a[p.charAt(i)-'a'], cnt);    		
    	}
    	int re=0;
    	for(int num: a){
    		re+=num;
    	}
    	return re;
    }
    
    //375. Guess Number Higher or Lower II
    private int cost(int[][] acount, int start, int end){
    	if(start>=end){
    		return 0;
    	}
    	if(acount[start][end]!=0){
    		return acount[start][end];
    	}
    	int re=Integer.MAX_VALUE;
    	for(int i=start; i<=end; i++){
    		int cost=i+Math.max(cost(acount, start, i-1), cost(acount, i+1, end));
    		re=Math.min(re, cost);
    	}
    	acount[start][end]=re;
    	return re;
    }
    
    public int getMoneyAmount(int n) {
    	int[][] acount=new int[n+1][n+1];
    	return cost(acount, 1, n);
    }
    
    //464. Can I Win
    private static int format(boolean[] state){
    	int re=0;
    	for(int i=0; i<state.length; i++){
    		re<<=1;
    		if(state[i]){
    			re|=1;
    		}
    	}
    	return re;
    }
    
    private static boolean isOK(boolean[] state, Map<Integer, Boolean> map, int tar){
    	int key=format(state);
    	if(map.containsKey(key)){
    		return map.get(key);
    	}
    	for(int i=0; i<state.length; i++){
    		if(!state[i]){
    			state[i]=true;
    			if(i+1>=tar||!isOK(state, map, tar-i-1)){
    				map.put(key, true);
    				state[i]=false;
    				return true;
    			}
    			state[i]=false;
    		}
    	}
    	map.put(key, false);
    	return false;
    }
    
    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    	if((1+maxChoosableInteger)*maxChoosableInteger<desiredTotal*2){
    		return false;
    	}
        boolean[] state=new boolean[maxChoosableInteger];
    	Map<Integer, Boolean> map=new HashMap<Integer, Boolean>();       
    	return isOK(state, map, desiredTotal);
    }
    
    //474. Ones and Zeroes      二维数组[m][n]
    private int[] count(String s){
    	int[] re=new int[2];
    	for(int i=0; i<s.length(); i++){
    		re[s.charAt(i)-'0']++;
    	}
    	return re;
    }
    
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp=new int[m+1][n+1];
        for(String s: strs){
        	int[] cnt=count(s);
        	
        	for(int i=m; i>=cnt[0]; i--){
        		for(int j=n; j>=cnt[1]; j--){
        			dp[i][j]=Math.max(1+dp[i-cnt[0]][j-cnt[1]], dp[i][j]);
        		}
        	}
        }
        return dp[m][n];
    }
    
    //32. Palindrome Partitioning II    
    public static int minCut(String s) {
        if(s.length()==0){
        	return 0;
        }
        int len=s.length();
        
        boolean[][]dp=new boolean[len][len];
        for(int i=len-1; i>=0; i--){
        	dp[i][i]=true;
        	for(int j=i+1; j<len; j++){
        		if(s.charAt(i)==s.charAt(j)){
        			dp[i][j]=(i>j-2)||((i<=j-2)&&dp[i+1][j-1]);
        		}
        	}
        }        
        int[] re=new int[len+1];
        re[len]=-1;
        for(int i=len-1; i>=0; i--){
        	re[i]=re[i+1]+1;
        	dp[i][i]=true;
        	for(int j=i+1; j<len; j++){
        		if(s.charAt(i)==s.charAt(j)){
        			if((i>j-2)||((i<=j-2)&&dp[i+1][j-1])){
        				dp[i][j]=true;
        				re[i]=Math.min(re[i], re[j+1]+1);
        			}        			
        		}        		
        	} 
        }
        System.out.println(Arrays.toString(re));
        return re[0];
    }
    
    //313. Super Ugly Number
    public static int nthSuperUglyNumber(int n, int[] primes) {
    	int[] re=new int[n];
    	int[] nums=primes.clone();
    	int[] cnt=new int[nums.length];
    	re[0]=1;

    	for(int i=1; i<n; i++){
    		int min=Integer.MAX_VALUE;
    		
    		for(int j=0; j<nums.length; j++){
    			min=Math.min(min, nums[j]);
    		}
    		re[i]=min;
    		for(int j=0; j<nums.length; j++){
    			if(nums[j]==min){
    				nums[j]=primes[j]*re[++cnt[j]];
    			}
    		}
    	}
    	System.out.println(Arrays.toString(re));
    	System.out.println("fcsaf ");
    	return re[n-1];
    }
    
    //139. Word Break
    public static boolean wordBreak(String s, Set<String> wordDict) {
    	if(s.length()==0){
    		return false;
    	}
    	Set<String> set=new HashSet<String>(wordDict);
    	boolean[] dp=new boolean[s.length()+1];
    	dp[0]=true;
    	for(int i=1; i<dp.length; i++){
    		for(int j=0; j<i; j++){
    			if(dp[j]&&set.contains(s.substring(j, i))){
    				dp[i]=true;
    				break;
    			}
    		}
    	}
    	System.out.println(Arrays.toString(dp));
    	return dp[s.length()];
    }
    
    //486. Predict the Winner
    private static int getMax(int[][] dp, int[] nums, int start, int end){
    	if(start==end){
    		return nums[start];
    	}
    	if(dp[start][end]!=0){
    		return dp[start][end];
    	}
    	
    	int re=Math.max(nums[start]-getMax(dp, nums, start+1, end), nums[end]-getMax(dp, nums, start, end-1));
    	dp[start][end]=re;
    	return re;
    }    
    
    public static boolean PredictTheWinner(int[] nums) {
    	int[][] dp=new int[nums.length][nums.length];
    	return getMax(dp, nums, 0, nums.length-1)>=0;
    }
   
    //516. Longest Palindromic Subsequence
    public int longestPalindromeSubseq(String s) {
        int[][] dp=new int[s.length()][s.length()];
    	
        for(int i=0; i<s.length(); i++){
        	dp[i][i]=1;
        	for(int j=i-1; j>=0; j--){
        		if(s.charAt(i)==s.charAt(j)){
        			dp[i][j]=dp[i-1][j+1]+2;
        		}else{
        			dp[i][j]=Math.max(dp[i-1][j], dp[i][j+1]);
        		}
        	}
        }    	
    	return dp[s.length()-1][0];
    }
    
    //322. Coin Change
    public static int coinChange2(int[] coins, int amount) {
        int[] dp=new int[amount+1];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	dp[0]=0;
        for(int i=1; i<=amount; i++){        	
        	for(int j=0; j<coins.length; j++){
        		int diff=i-coins[j];
        		if(diff>=0&&dp[diff]!=Integer.MAX_VALUE){
        			dp[i]=Math.min(dp[i], dp[diff]+1);
        		}
        	}
        }
    	return dp[amount]==Integer.MAX_VALUE? -1: dp[amount];
    }
        
	public static void main(String[] args) {
		System.out.println(PredictTheWinner(new int[]{10,17,11,16,17,9,14,17,18,13,11,4,17,18,15,3,13,10,6,10}));
	}

}

