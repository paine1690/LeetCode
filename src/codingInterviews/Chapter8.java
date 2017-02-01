package codingInterviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Chapter8 { 
    
    /*
     * 51、数组中重复的数字
     */    
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if(numbers==null||numbers.length==0){
    		return false;
    	}
    	int len=numbers.length;
        for(int i=0; i<numbers.length; i++){
            int num=numbers[i]%len;
            if(numbers[num]>len){
                duplication[0]=num;
                return true;
            }else{
                numbers[num]+=len;
            }
        }
        return false;
    }
    
    /*
     * 52、构建乘积数组
     */
    public int[] multiply(int[] A) {
    	
    	int len=A.length;
    	int[] a=new int[len];
    	int[] b=new int[len];
    	int[] re=new int[len];
    	a[0]=A[0];
    	for(int i=1; i<len; i++){
    		a[i]=a[i-1]*A[i];
    	}
    	b[len-1]=A[len-1];
    	for(int i=len-2; i>=0; i--){
    		b[i]=b[i+1]*A[i];
    	}
    	
    	re[0]=b[1];
    	re[len-1]=a[len-2];
    	for(int i=1; i<len-1; i++){
    		re[i]=a[i-1]*b[i+1];
    	}
    	return re;
    }
    
    /*
     * 53、正则表达式匹配
     */
    private boolean isMatch(char[] charS, char[] charP, int s, int p){
    	if(s>=charS.length&&p>=charP.length){
    		return true;
    	}
    	if(p>=charP.length){
    		return false;
    	}
    	if(p<charP.length-1&&charP[p+1]=='*'){
    		if(s<charS.length&&(charS[s]==charP[p]||charP[p]=='.')){
    			return isMatch(charS, charP, s, p+2)
    					||(s<charS.length&&isMatch(charS, charP, s+1, p))
    					||(s<charS.length&&isMatch(charS, charP, s+1, p+2));
    		}else{
    			return isMatch(charS, charP, s, p+2);
    		}
    	}
    	
    	if(s<charS.length&&(charS[s]==charP[p]||charP[p]=='.')){
    		return isMatch(charS, charP, s+1, p+1);
    	}
    	return false;
    }
    
    public boolean match(char[] str, char[] pattern){
        return isMatch(str, pattern, 0, 0);
    }
    
    public static boolean match2(char[] str, char[] pattern){
        int m=pattern.length;
        int n=str.length;  
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        
        for(int i=2; i<=m; i++){
            dp[i][0]=dp[i-2][0]&&(pattern[i-1]=='*');
        }
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                char p=pattern[i-1], s=str[j-1];
                //System.out.print(p+" "+s+" ");
                if(p=='*'){
                    dp[i][j]=(i>1&&dp[i-2][j])
                        ||((i>1)&&(pattern[i-2]=='.'||pattern[i-2]==s)&&(dp[i-1][j]||dp[i][j-1]));
                }else{
                    dp[i][j]=dp[i-1][j-1]&&((p=='.')||(p==s));
                }
            }
        }
        return dp[m][n];
    }
    
    /*
     * 54、表示数值的字符串
     */    
    private static boolean isAllDigit(char[] chars, int index){
        if(index>=chars.length){
            return false;
        }
        while(index<chars.length){
            char c=chars[index];
            if(c<'0'||c>'9'){
                return false;
            }
            index++;
        }
        
        return true;
    }
    
    private static boolean isAllDigitWithE(char[] chars, int i){
        while(i<chars.length){
            char c=chars[i];
            if(c=='e'||c=='E'){
                if(++i>=chars.length){
                    return false;
                }
                if(chars[i]=='+'||chars[i]=='-'){
                    i++;
                }
                return isAllDigit(chars, i);
            }else if(c>='0'&&c<='9'){
                i++;
                continue;
            }else{
                return false;
            }         	   
        }
        return true;
    }
    
    public static boolean isNumeric(char[] str) {
        int i=0;
        if(str[0]=='+'||str[i]=='-'){
            i++;
        }
        while(i<str.length){
            char c=str[i];            
            if(c>='0'&&c<='9'){
                i++;
                continue;
            }else if(c=='.'){
                return isAllDigitWithE(str, i+1);
            }else if(c=='e'||c=='E'){
                if(++i>=str.length){
                    return false;
                }
                if(str[i]=='+'||str[i]=='-'){
                    i++;
                }
                return isAllDigit(str, i);                
            }else{
                return false;
            }            
        }
        return true;        
    }
    
    /*
     * 55、字符流中第一个不重复的字符
     */
    static class Solution {    	
    	int index;
        int[] isA;    	
        public Solution(){
        	index=0;
        	isA=new int[128];
        	Arrays.fill(isA, -1);
        }
    	
        public void Insert(char ch){
        	if(isA[ch]==-2){
        		return;
        	}else if(isA[ch]==-1){
            	isA[ch]=index++;
            }else{
            	isA[ch]=-2;
            }  
        }
        
        public char FirstAppearingOnce(){
        	int first=Integer.MAX_VALUE;
        	char re='#';        	
        	for(char i=' '; i<isA.length; i++){
        		if(isA[i]<0){
        			continue;
        		}
        		if(isA[i]<first){
        			first=isA[i];
        			re=i;
        		}
        	}
        	return re;
        }
    } 
    
    /*
     * 56、链表中环的入口结点
     */
    public ListNode EntryNodeOfLoop(ListNode pHead){
        ListNode fast=pHead, slow=pHead;
       	while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow){
                fast=pHead;
                while(true){
                    if(fast==slow){
                        return fast;
                    }
                    fast=fast.next;
                    slow=slow.next;
                }
            }
        }
        return null;
    }
    
    /*
     * 57、删除链表中重复的结点
     */
    public ListNode deleteDuplication(ListNode pHead){
    	if(pHead==null||pHead.next==null){
    		return pHead;
    	}
    	ListNode re=new ListNode(-1);
    	re.next=pHead;
    	
    	
    	ListNode p=re, q=re.next;
    	
    	while(q!=null){
    		ListNode r=q.next;
    		boolean flag=false;
    		while(r!=null&&r.val==q.val){
    			flag=true;
    			r=r.next;
    		}
    		if(flag){
    			q=r;
    			p.next=q;
    		}
    		else{
    			p.next=q;
    			p=q;
    			q=p.next;		
    		}   		
    	}
    	return re.next;
    }
    
    /*
     * 58、二叉树的下一个结点
     */
    static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
    
    public TreeLinkNode GetNext(TreeLinkNode pNode){
        if(pNode.right!=null){
            TreeLinkNode root=pNode.right;
            while(root.left!=null){
                root=root.left;
            }
            return root;
        }
        if(pNode.next!=null){
            TreeLinkNode p=pNode, root=pNode.next;
            while(root!=null&&p==root.right){
                p=root;
                root=root.next;
            }
            return root;
        }
        return null;    
    } 
    
    /*
     * 59、对称二叉树
     */
    private boolean isMirror(TreeNode p, TreeNode q){
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null){
            return false;
        }
        if(p.val==q.val){
            return isMirror(p.left, q.right)&&isMirror(p.right, q.left);
        }else{
            return false;
        }        
    }
    
    boolean isSymmetrical(TreeNode pRoot){
        return pRoot==null||isMirror(pRoot.left, pRoot.right);
    }
    
    /*
     * 60、把二叉树打印成多行
     */
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
    	ArrayList<ArrayList<Integer>> re=new ArrayList<ArrayList<Integer>>();
    	if(pRoot==null){
    		return re;
    	}
    	List<TreeNode> list=new ArrayList<TreeNode>();
    	list.add(pRoot);
    	int cur=0;
    	int last=1;
    	while(cur<list.size()){
    		last=list.size();
    		ArrayList<Integer> temp=new ArrayList<Integer>();
    		while(cur<last){
    			TreeNode tempNode=list.get(cur);
    			temp.add(tempNode.val);
    			if(tempNode.left!=null){
    				list.add(tempNode.left);
    			}
    			if(tempNode.right!=null){
    				list.add(tempNode.right);
    			}
    			cur++;
    		}
    		re.add(temp);
    	}
    	Collections.reverse(list);
    	return re;
    	
    }
    
    /*
     * 61、按之字形顺序打印二叉树
     */
    public ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> re=new ArrayList<ArrayList<Integer>>();
        if(pRoot==null){
            return re;
        }
        ArrayList<TreeNode> list=new ArrayList<TreeNode>();
        list.add(pRoot);
        int cnt=1;
        int cur=0, size=1;
        while(cur<list.size()){
            size=list.size();
            ArrayList<Integer> temp=new ArrayList<Integer>();
            for(int i=cur; i<size; i++){
                TreeNode root=list.get(i);
                temp.add(root.val);
                if(root.left!=null){
                    list.add(root.left);
                }
                if(root.right!=null){
                    list.add(root.right);
                }
            }
            if((cnt++&1)==0){
                Collections.reverse(temp);
            }
            re.add(temp);
            cur=size;
        }        
        return re;
    }
    
    /*
     * 62、序列化二叉树
     */    
	static void dfs(StringBuilder s, TreeNode root){
		if(root==null){
			s.append("$,");
			return;
		}
		s.append(root.val).append(",");
		dfs(s, root.left);
		dfs(s, root.right);
	}
	
    static String Serialize(TreeNode root) {
    	StringBuilder s=new StringBuilder();
    	dfs(s, root);
    	return s.toString();
    }
    
    static int index;
    
    static TreeNode create(String s){
    	if(index>=s.length()){
    		return null;
    	}
    	
    	TreeNode nNode;
    	if(s.charAt(index)=='$'){
    		nNode=null;
    		index+=2;
    	}else{
    		int i=0;
    		for(i=index+1; i<s.length(); i++){
    			if(s.charAt(i)==','){
    				break;
    			}
    		}    		
    		int val=Integer.valueOf(s.substring(index, i));
    		nNode=new TreeNode(val);
    		index=i+1;
    		nNode.left=create(s);
        	nNode.right=create(s);
    	}
    	return nNode;
    }
    
    static TreeNode Deserialize(String str) {
    	index=0;
    	return create(str);
    }
    
    /*
     * 63、二叉搜索树的第k个结点
     */
    int n;
    TreeNode KthNode(TreeNode pRoot, int k){
        n=k;
        return getN(pRoot);
    }

    TreeNode getN(TreeNode root){
        if(root==null){
            return null;
        }
        
        TreeNode re=getN(root.left);
        if(re==null){
            if(n==1){
                re=root;
            }else{
                n--;
                re=getN(root.right);
            }
        }
        return re;
    }
    
    /*
     * 65、滑动窗口的最大值
     */
    public static ArrayList<Integer> maxInWindows(int [] num, int size){
        ArrayList<Integer> re=new ArrayList<Integer>();
        if(size==0||size>num.length){
        	return re;
        }
        LinkedList<Integer> list=new LinkedList<Integer>();
        int i=0;
        for(i=0; i<size; i++){
        	while(!list.isEmpty()&&num[list.getLast()]<num[i]){
        		list.removeLast();
        	}
        	list.addLast(i);        	
        }
        re.add(num[list.getFirst()]);
        System.out.println(list);
        for(; i<num.length; i++){
        	if(list.getFirst()+size<=i){
        		list.removeFirst();
        	}
        	
        	while(!list.isEmpty()&&num[list.getLast()]<num[i]){
        		list.removeLast();
        	}
        	list.addLast(i);
        	re.add(num[list.getFirst()]);
        }        
        return re;    	
    }
    
    /*
     * 67、机器人的运动范围
     */
    private int cnt=0;
    
    private boolean isOK(int i, int j, int threshold){
    	int sumI=0, sumJ=0;
    	while(i>0){
    		sumI+=i%10;
    		i/=10;
    	}
    	while(j>0){
    		sumJ+=j%10;
    		j/=10;
    	}
    	return (sumI+sumJ)<=threshold;
    }
    
    private void dfsGet(boolean[][] nums, int i, int j, int m, int n, int threshold){
    	if(nums[i][j]){
    		return ;
    	}
    	nums[i][j]=true;
    	if(isOK(i, j, threshold)){
    		cnt++;
    		if(i>0){
    			dfsGet(nums, i-1, j, m, n, threshold);
    		}
    		
    		if(j>0){
    			dfsGet(nums, i, j-1, m, n, threshold);
    		}
    		
    		if(i<m-1){
    			dfsGet(nums, i+1, j, m, n, threshold);
    		}
    		
    		if(j<n-1){
    			dfsGet(nums, i, j+1, m, n, threshold);
    		}
    	}    	
    }    
    
    public int movingCount(int threshold, int rows, int cols){
        boolean[][] nums=new boolean[rows][cols];
        dfsGet(nums, 0, 0, rows, cols, threshold);
    	return cnt; 
    }
    
	public static void main(String[] args) {
		System.out.println(isNumeric("1234.86".toCharArray()));
		//System.out.println(maxInWindows(new int[]{10,14,12,11}, 1));
//		String s="1,2,$,5,6,$,$,7,$,$,3,$,$,";
//		TreeNode root=Deserialize(s);
//		System.out.println(levelOrder2(root));
//		System.out.println(Serialize(root));
	}

}
