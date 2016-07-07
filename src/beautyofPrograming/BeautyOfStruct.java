package beautyofPrograming;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 		第三章 结构之法 -字符串及链表的探索
 * @author Paine
 *
 */
 
 
public class BeautyOfStruct {

	/*
	 * 3.1 字符串移位包含  
	 */
	private static int[] getNext(String P){
		int m=P.length();
		int[] next=new int[m+1];
		next[0]=next[1]=0;
		int j=0;
		for(int i=1; i<m; i++){
			while(j>0&&P.charAt(i)!=P.charAt(j)){
				j=next[j];
			}
			if(P.charAt(i)==P.charAt(j)){
				j++;
			}
			next[i+1]=j;
		}		
		return next;
	}
	private static boolean kmp(String T, String P){
		int[] next=getNext(P);
		int j=0;
		for(int i=0; i<T.length(); i++){
			while(j>0&&T.charAt(i)!=P.charAt(j)){
				j=next[j];
			}
			if(T.charAt(i)==P.charAt(j)){
				j++;
			}
			if(j==P.length()){
				return true;
			}
		}
		return false;
	}
	public static boolean isIn(String s1, String s2){
		s1+=s1.substring(0, s2.length()-1);
		System.out.println(s1);
		return kmp(s1, s2);
	}
	
	
	/*
	 * 3.2 电话号码与对应英语单词
	 */
	//leetcode 17. Letter Combinations of a Phone Number.
    static String[] number=new String[]{
    		"",
    		"",
    		"abc",
    		"def",
    		"ghi",
    		"jkl",
    		"mno",
    		"pqrs",
    		"tuv",
    		"wxyz",
    };
    static int[] total=new int[]{0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
    
    //循环求法
    public static List<String> letterCombinations(String digits) {
    	List<String> re=new ArrayList<String>();
        int len=digits.length();
        if(len==0){
        	return re;
        }
        int[] answer=new int[len];          
        
        while(true){
        	StringBuilder s=new StringBuilder();
        	for(int i=0; i<len; i++){
        		s.append(number[digits.charAt(i)-'0'].charAt(answer[i]));
        	}
        	re.add(s.toString());
        	int k=len-1;
        	while(k>=0){
        		if(answer[k]<total[digits.charAt(k)-'0']-1){
        			answer[k]++;
        			break;
        		}else{
        			answer[k]=0;
        			k--;
        		}
        	}      	
        	if(k<0){
        		break;
        	}
        }    
    	return re;
    }
    
    //递归求法
    public static List<String> letterCombinations2(String digits) {
    	int len=digits.length();
    	List<String> re=new ArrayList<String>();
    	if(len==0){
    		return re;
    	}    	
    	char[] chars=new char[len];
    	dfsGet(digits, 0, chars, re);
    	return re;    	
    }
    
    private static void dfsGet(String dig, int index, char[] chars, List<String> re){
    	if(index==dig.length()){
    		re.add(new String(chars));
    		return;
    	}
    	int num=dig.charAt(index)-'0';
    	for(int i=0; i<total[num]; i++){
    		chars[index]=number[num].charAt(i);
    		dfsGet(dig, index+1, chars, re);
    	}
    }	
	
    /*
     * 3.3 计算字符串相似度
     */
    //递归实现
    public static int calDis(String a, String b){
    	return calStringDistance(a, 0, a.length()-1, b, 0, b.length()-1);
    }
	private static int calStringDistance(String a, int aStart, int aEnd, String b, int bStart, int bEnd){
		if(aStart>aEnd){
			if(bStart>bEnd){
				return 0;
			}else{
				return bEnd-bStart+1;
			}
		}
		if(bStart>bEnd){
			if(aStart>aEnd){
				return 0;
			}else{
				return aEnd-aStart+1;
			}
		}
		
		if(a.charAt(aStart)==b.charAt(bStart)){
			return calStringDistance(a, aStart+1, aEnd, b, bStart+1, bEnd);
		}else{
			int t1=calStringDistance(a, aStart+1, aEnd, b, bStart, bEnd);
			int t2=calStringDistance(a, aStart, aEnd, b, bStart+1, bEnd);
			int t3=calStringDistance(a, aStart+1, aEnd, b, bStart+1, bEnd);
			return Math.min(t1, Math.min(t2, t3))+1;
		}
	}
    //非递归实现-动态规划
	public static int calDis2(String a, String b){
		int m=a.length();
		int n=b.length();
		int[][] dp=new int[m+1][n+1];
		for(int j=1; j<n+1; j++){
			dp[0][j]=j;
		}
		for(int i=1; i<m+1; i++){
			dp[i][0]=i;
		}
		for(int i=1; i<m+1; i++){
			for(int j=1; j<n+1; j++){
				if(a.charAt(i-1)==b.charAt(j-1)){
					dp[i][j]=dp[i-1][j-1];
				}else{
					dp[i][j]=Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
				}				
			}
		}
		return dp[m][n];
	}
	
	
    
    /*
     * 3.4 从无头链表删除节点
     */
	public void deledeNode(ListNode node){
		node.val=node.next.val;
		node.next=node.next.next;
	}
	//反转链表 206. Reverse Linked List
    public static ListNode reverseList_iterator(ListNode head) {
    	if(head==null||head.next==null){
            return head;
        }
    	ListNode pNode=head;
    	ListNode re=null;
    	while(pNode!=null){
    		ListNode temp=pNode.next;
    		pNode.next=re;
    		re=pNode;
    		pNode=temp;
    	}
    	return re;
    }
    
    public static ListNode reverseList_recursion(ListNode head) {
    	if(head==null||head.next==null){
            return head;
        }
    	
    	ListNode nNode=head.next;
    	ListNode re=reverseList_recursion(nNode);
    	
    	head.next=null;
    	nNode.next=head;
    	
    	return re;
    }
	
	/*
	 * 3.5 最短摘要的生成
	 */
    private static Map<String, Integer> map;
    
    private static boolean isAllExisted(){
    	Collection<Integer> c=map.values();
    	for(int i: c){
    		if(i==0){
    			return false;
    		}
    	}
    	return true;
    }    
    
    public static String extract(String description, String[] keyWords){
    	String[] descriptions=description.split(" ");
    	int len=descriptions.length;
    	int tLen=len+1;
        int start=0;
        int end=0;
        int reStart=0;
        int reEnd=0;
        
    	map=new HashMap<String, Integer>();
    	for(String s:keyWords){
    		map.put(s, 0);
    	}
    	while(true){
    		while(!isAllExisted()&&end<len){
    			if(map.containsKey(descriptions[end])){
    				map.put(descriptions[end], map.get(descriptions[end])+1);
    			}
    			end++;
    		}
    		while(isAllExisted()){
    			if(end-start<tLen){
    				tLen=end-start;
    				reStart=start;
    				reEnd=end-1;
    			}
    			
    			if(map.containsKey(descriptions[start])){
    				map.put(descriptions[start], map.get(descriptions[start])-1);
    			}
    			start++;   			
    		}
    		if(end>=len){
    			break;
    		}
    	}
    	StringBuilder s=new StringBuilder();
    	for(int i=reStart; i<reEnd; i++){
    		s.append(descriptions[i]).append(" ");
    	}
    	return s.append(descriptions[reEnd]).toString();
    }
    
    /*
     * 3.6 判断两个链表是否相交
     */
    //160. Intersection of Two Linked Lists
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	if(headA==null||headB==null){
    		return null;
    	}
    	int m=0, n=0;
    	ListNode pNode=headA;
    	while(pNode!=null){
    		m++;
    		pNode=pNode.next;
    	}
    	pNode=headB;
    	while(pNode!=null){
    		n++;
    		pNode=pNode.next;
    	}
    	int t=0;
    	ListNode aNode, bNode;
    	if(m>=n){
    		t=m-n;
    		aNode=headA;
    		bNode=headB;
    	}else{
    		t=n-m;
    		aNode=headB;
    		bNode=headA;
    	}
    	while(t>0){
    		aNode=aNode.next;
    		t--;
    	}
    	while(aNode!=null&&bNode!=null){
    		if(aNode==bNode){
    			return aNode;
    		}
    		aNode=aNode.next;
    		bNode=bNode.next;
    	}
    	return null;
    }
    
    /*
     * 3.7 队列中去最大值问题
     */
    
    /*
     * 3.8 求二叉树中节点的最大距离
     */
    private static int maxDistance;
    
    private static int maxDeep(TreeNode node){
    	if(node==null){
    		return 0;
    	}
    	int left=maxDeep(node.left);
    	int right=maxDeep(node.right);
    	maxDistance=Math.max(maxDistance, right+left);
    	return Math.max(left, right)+1;
    }
    public static int maxDis(TreeNode root){
    	maxDistance=0;
    	int left=maxDeep(root.left);
    	int right=maxDeep(root.right);
    	return Math.max(maxDistance, right+left);
    }
    
    /*
     * 3.9 重建二叉树
     */
    //105. Construct Binary Tree from Preorder and Inorder Traversal
    private static int getIndex(int[] nums, int start, int end, int x){
    	for(int i=start; i<=end; i++){
    		if(nums[i]==x){
    			return i;
    		}
    	}
    	return -1;
    }
    
    private static TreeNode buildTree(int[] preOrder, int index, int[] inOrder, int start, int end){
    	TreeNode root=null;
    	if(start<=end){
    		root=new TreeNode(preOrder[index]);
    		if(start!=end){
    			int mid=getIndex(inOrder, start, end, preOrder[index]);
    			root.left=buildTree(preOrder, index+1, inOrder, 0, mid-1);
    			root.right=buildTree(preOrder, index+mid-start+1, inOrder, mid+1, end);
    		}
    	} 	
    	return root;
    }
    
    public static TreeNode reBuild(int[] preOrder, int[] inOrder){
    	return buildTree(preOrder, 0, inOrder, 0, inOrder.length-1);
    }
    
    
    
    
	public static void main(String[] args) {
		String description="hello software hello test world spring sun flower hello";
        String[] keywords = {"hello","world"};
		System.out.println(extract(description, keywords));
		maxQueue q=new maxQueue();
		int data[] = {7, 4, 15, 9, 5, 10, 13, 3, 20, 17, 19};  
	    for (int i=0; i<data.length; i++) {
	    	q.enQueue(data[i]); 
		    System.out.println(i+" "+q.max());
	    }	        
	    for (int i=0; i<data.length; i++)  {
	    	q.deQueue();
	    	System.out.println(i+" "+q.max());
	    }
	        

	}
}

/*
 * 3.7 队列中去最大值问题
 */
class maxStack{
	private Stack<Integer> stack=new Stack<Integer>();
	private Stack<Integer> maxStack=new Stack<Integer>();
	
	boolean isEmpty(){
		return stack.isEmpty()&&maxStack.isEmpty();
	}
	
	void push(int x){
		if(maxStack.isEmpty()||x>=maxStack.peek()){
			maxStack.push(x);
		}
		stack.push(x);
	}
	
	int pop(){
		int x=stack.pop();
		if(maxStack.peek()==x){
			maxStack.pop();
		}
		return x;
	}
	
	int getMax(){
		return maxStack.peek();
	}
}

class maxQueue{
	private maxStack stackA=new maxStack();
	private maxStack stackB=new maxStack();
	
	void enQueue(int x){
		stackB.push(x);
	}
	
	int deQueue(){
		if(stackA.isEmpty()){
			while(!stackB.isEmpty()){
				stackA.push(stackB.pop());
			}
		}		
		return stackA.pop();
	}
	
	int max(){
		int a=stackA.isEmpty()? Integer.MIN_VALUE:stackA.getMax();
		int b=stackB.isEmpty()? Integer.MIN_VALUE:stackB.getMax();
		return Math.max(a, b);
	}
}

class ListNode{
	int val;
	ListNode next;
	public ListNode(int val){
		this.val=val;
	}
}

class TreeNode{
	public TreeNode(int i) {
		// TODO Auto-generated constructor stub
	}
	int val;
	TreeNode left;
	TreeNode right;
}
