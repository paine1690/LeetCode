package interview;

import java.util.Arrays;
import java.util.Scanner;

public class Router2 {
	
	static int n, m;
	static int satisfaction[];
	static Edge edges[];
	static int head[], cnt;
	static int degree[];
	static int dp[][][][];

	static void init(){
	    cnt=0;
	    head=new int[2*(n+1)];
	    Arrays.fill(head, -1);
	    dp=new int[n+1][m+1][2][2];
	    degree=new int[2*(n+1)];	
	    satisfaction=new int[n+1];
	    edges=new Edge[2*(n+1)];
	    for(int i=0; i<edges.length; i++){
	    	edges[i]=new Edge();
	    }
	}

	static void addEdge(int from, int to){
	    edges[cnt].to = to;
	    edges[cnt].next = head[from];
	    head[from] = cnt++;
	}

	static void dfs(int root, int father){
	    boolean isLeaf=true;
	    int child_cnt=0;
	    for(int i=head[root]; i!=-1; i=edges[i].next){
	        if(edges[i].to==father) continue;
	        isLeaf = false;
	        child_cnt++;
	        dfs(edges[i].to, root);
	    }
	    if(child_cnt==1){
	        int child=edges[head[root]].to;
	        
	        for(int i=0; i<=m; i++){
	            dp[root][i][0][0]=Math.max(dp[child][i][0][0], dp[root][i][0][0]);
	            dp[root][i][0][0]=Math.max(dp[child][i][0][1], dp[root][i][0][0]);
	            dp[root][i][0][1]=Math.max(dp[child][i][1][1]+satisfaction[root], dp[root][i][0][1]);
	            if(i<m){
	            	dp[root][i+1][1][1]=Math.max(dp[child][i][0][0]+satisfaction[root]+satisfaction[child], 
                            dp[root][i + 1][1][1]);
					dp[root][i+1][1][1]=Math.max(dp[child][i][0][1]+satisfaction[root], dp[root][i+1][1][1]);
					dp[root][i+1][1][1]=Math.max(dp[child][i][1][1]+satisfaction[root], dp[root][i+1][1][1]);
	            }
	            
	        }
	    }
	    if(child_cnt==2){
	        int child1=edges[head[root]].to;
	        int child2=edges[edges[head[root]].next].to;
	        for(int i=0; i<=m; i++){
	            for(int j=0; j<=m; j++){
	                if(i+j>m) continue;
	                dp[root][i+j][0][0]=Math.max(dp[child1][i][0][0]+dp[child2][j][0][0],
	                                            dp[root][i + j][0][0]);
	                dp[root][i+j][0][0]=Math.max(dp[child1][i][0][0]+dp[child2][j][0][1],
	                                            dp[root][i + j][0][0]);
	                dp[root][i+j][0][0]=Math.max(dp[child1][i][0][1]+dp[child2][j][0][0],
	                                            dp[root][i + j][0][0]);
	                dp[root][i+j][0][0]=Math.max(dp[child1][i][0][1]+dp[child2][j][0][1],
	                                            dp[root][i + j][0][0]);
	                
	                dp[root][i+j][0][1]=Math.max(dp[child1][i][1][1]+dp[child2][j][0][0]+satisfaction[root], 
	                                            dp[root][i + j][0][1]);
	                dp[root][i+j][0][1]=Math.max(dp[child1][i][1][1]+dp[child2][j][0][1]+satisfaction[root], 
	                                            dp[root][i + j][0][1]);
	                dp[root][i+j][0][1]=Math.max(dp[child1][i][0][0]+dp[child2][j][1][1]+satisfaction[root], 
	                                            dp[root][i + j][0][1]);
	                dp[root][i+j][0][1]=Math.max(dp[child1][i][0][1]+dp[child2][j][1][1]+satisfaction[root], 
	                                            dp[root][i + j][0][1]);
	                dp[root][i+j][0][1]=Math.max(dp[child1][i][1][1]+dp[child2][j][1][1]+satisfaction[root], 
	                                            dp[root][i + j][0][1]);

	                if(i+j<m){
		                dp[root][i+j+1][1][1]=Math.max(dp[child1][i][0][0]+dp[child2][j][0][0]+ 
		                                                satisfaction[child1]+satisfaction[child2]+satisfaction[root],
		                                                dp[root][i+j+1][1][1]);
		                dp[root][i+j+1][1][1]=Math.max(dp[child1][i][0][1]+dp[child2][j][0][0]+
		                								satisfaction[root]+satisfaction[child2],
		                                                dp[root][i+j+1][1][1]);
		                dp[root][i+j+1][1][1]=Math.max(dp[child1][i][0][0]+dp[child2][j][0][1]+ 
		                                                satisfaction[child1]+satisfaction[root],
		                                                dp[root][i+j+1][1][1]);
		                dp[root][i+j+1][1][1]=Math.max(dp[child1][i][0][1]+dp[child2][j][0][1]+ 
		                                                satisfaction[root],
		                                                dp[root][i+j+1][1][1]); 
		                
		                dp[root][i+j+1][1][1]=Math.max(dp[child1][i][1][1]+dp[child2][j][0][0]+
		                                                satisfaction[child2]+satisfaction[root],
		                                                dp[root][i+j+1][1][1]);
		                dp[root][i+j+1][1][1]=Math.max(dp[child1][i][1][1]+dp[child2][j][0][1]+ 
		                                                satisfaction[root],
		                                                dp[root][i+j+1][1][1]);
		                dp[root][i+j+1][1][1]=Math.max(dp[child1][i][1][1]+dp[child2][j][1][1]+ 
		                                                satisfaction[root],
		                                                dp[root][i+j+1][1][1]);
	                }
	            }
	        }
	    }
	    if(isLeaf){
	        for(int i=1; i<=m; i++){
	            dp[root][i][1][1]=satisfaction[root];
	        }
	        return;
	    }
	}

	
	
	
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		n=s.nextInt();
		m=s.nextInt();
		init();
		for(int i=1; i<=n; i++){
			satisfaction[i]=s.nextInt();
		}
		
		for(int i=0; i<n-1; i++){
			int u=s.nextInt();
			int v=s.nextInt();
			addEdge(u, v);
			addEdge(v, u);
			degree[u]++; 
			degree[v]++;
		}
		s.close();
		
		
		int root=0;
		for(int i=1; i<=n; i++){
	        if(degree[i]==1||degree[i]==2){
	            dfs(root=i, -1);
	            break;
	        }
	    }
		
	    int re=-1;
	    for(int i=0; i<=m; i++){
	        for(int j=0; j<2; j++){
	            for(int k=0; k<2; k++){
	            	re=Math.max(re, dp[root][i][j][k]);
	            }
	        }
	    }
	    System.out.println(re);
		
	}
}

class Edge{
    int to, next;
};
