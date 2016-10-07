package codingInterviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 		第四章 解决面试题的思路
 * 
 * @author Paine
 *
 */
public class Chapter4 {
	
	/*
	 * 19、二叉树镜像
	 */
	public static void mirror(TreeNode root){
		if(root==null){
			return;
		}
		TreeNode temp=root.left;
		root.left=root.right;
		root.right=temp;
		mirror(root.left);
		mirror(root.right);
	}
	
	/*
	 * 20、顺时针打印矩阵
	 */
	private static void printCircle(ArrayList<Integer> re, int[][] matrix, int m, int n, int start){
		int endJ=n-start-1;
		int endI=m-start-1;
		
		for(int j=start; j<=endJ; j++){
			re.add(matrix[start][j]);
		}
		
		if(start<endI){
			for(int i=start+1; i<=endI; i++){
				re.add(matrix[i][endJ]);
			}
		}
		
		if(start<endI&&start<endJ){
			for(int j=endJ-1; j>=start; j--){
				re.add(matrix[endI][j]);
			}
		}		
		
		if(start<endI-1&&start<endJ){
			for(int i=endI-1; i>start; i--){
				re.add(matrix[i][start]);
			}
		}	
	}
	
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
    	ArrayList<Integer> re=new ArrayList<Integer>();
    	if(matrix.length==0||matrix[0].length==0){
    		return re;
    	}
    	int m=matrix.length;
    	int n=matrix[0].length;
    	for(int start=0; start*2<Math.min(m,  n); start++){
    		printCircle(re, matrix, m, n, start);
    	}
    	return re;
    }
    
    /*
     * 22、栈的压入、弹出序列
     */
    public static boolean isPopOrder(int[] pushA, int[] popA){
    	Stack<Integer> stack=new Stack<Integer>();
    	int tar=0;
    	int a=0;
    	while(tar<popA.length){
    		while(stack.isEmpty()||stack.peek()!=popA[tar]){
    			if(a<pushA.length){
    				stack.push(pushA[a++]);
    			}else{
    				return false;
    			}    			
    		}    		
    		stack.pop();
    		tar++;    		
    	}
    	return stack.isEmpty();
    }
    
    /*
     * 23、从上往下打印二叉树
     */
    public static ArrayList<Integer> printFromTopToBottom(TreeNode root){
    	ArrayList<Integer> re=new ArrayList<Integer>();
    	List<TreeNode> list=new ArrayList<TreeNode>();
    	if(root==null){
    		return re;
    	}
    	list.add(root);
    	int cur=0, last=1;
    	while(cur<list.size()){
    		last=list.size();
    		for(int i=cur; i<last; i++){
    			TreeNode node=list.get(i);
    			re.add(node.val);
    			if(node.left!=null){
    				list.add(node.left);
    			}
    			if(node.right!=null){
    				list.add(node.right);
    			}
    		}
    		cur=last;
    	}    	
    	return re;
    }
    
	public static void main(String[] args) {
		System.out.println(isPopOrder(new int[]{}, new int[]{}));
//		int[][] matrix=new int[][]{
//			{1,2,3,4},
//			{5,6,7,8},
//			{9,10,11,12},
//			{13,14,15,16},
//			
//		};
//		System.out.println(printMatrix(matrix));
	}
}

/*
 * 21、包含min函数的栈
 */
class minStack{
	Stack<Integer> stack=new Stack<Integer>();
	Stack<Integer> min=new Stack<Integer>();
	
	public int min(){
		return min.peek();
	}
	
	public void push(int val){
		if(stack.isEmpty()||val<=min.peek()){
			min.push(val);
		}
		stack.push(val);
	}
	
	public int pop(){
		int val=stack.pop();
		if(val==min.peek()){
			min.pop();
		}
		return val;
	}
}

