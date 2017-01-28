package codingInterviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    
    /*
     * 24、二叉搜索树的后续遍历序列
     */
    private static boolean isBST(int[] nums, int start, int end){
    	if(start>=end){
    		return true;
    	}
    	int mid=start;
    	while(nums[mid]<nums[end]){
    		mid++;
    	}
    	for(int i=mid; i<end; i++){
    		if(nums[i]<nums[end]){
    			return false;
    		}
    	}
    	return isBST(nums, start, mid-1)&&isBST(nums, mid, end-1);    	
    }
    
    
    public static boolean verifySquenceOfBST(int [] sequence) {
        if(sequence.length==0){
        	return false;
        }    
    	return isBST(sequence, 0, sequence.length-1);
    }
    
    /*
     * 25、二叉树中和为某一值的路径
     */
    private void dfs(ArrayList<ArrayList<Integer>> re, ArrayList<Integer> list, TreeNode root, int target){
        list.add(root.val);
        target-=root.val;
        if(root.left==null&&root.right==null){
            if(target==0){
                re.add(new ArrayList<Integer>(list));
            }
            return;
        }
        
        if(root.left!=null){
            dfs(re, list, root.left, target);
            list.remove(list.size()-1);
        }        
        
        if(root.right!=null){
            dfs(re, list, root.right, target);
            list.remove(list.size()-1);
        }
    }
    
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> re=new ArrayList<ArrayList<Integer>>();
        if(root!=null){
            ArrayList<Integer> list=new ArrayList<Integer>();    
        	dfs(re, list, root, target);
        }       
        return re;
    }
    
    /*
     * 26、复杂链表的复制
     */
    public RandomListNode Clone(RandomListNode pHead){
        if(pHead==null){
            return null;
        }
        RandomListNode p=pHead;
        while(p!=null){
            RandomListNode next=p.next;
            RandomListNode node=new RandomListNode(p.label);
            p.next=node;
            node.next=next;
            p=next;
        }
        
        p=pHead;
        while(p!=null){
            RandomListNode next=p.next;    
            if(p.random!=null){
                next.random=p.random.next;
            }            
            p=next.next;
        }     
        p=pHead;
        RandomListNode re=pHead.next;
        while(p.next!=null){
            RandomListNode next=p.next;
            p.next=next.next;
            p=next;
        }
        return re;
    }
    
    /*
     * 27、二叉搜索树与双向链表
     */
    TreeNode last=null;
    private void dfs(TreeNode root){
    	if(root==null){
    		return;
    	}
    	
    	dfs(root.left);    	
    	root.left=last;
    	if(last!=null){
    		last.right=root;
    	}
    	last=root;
    	dfs(root.right);
    }
    
    public TreeNode Convert(TreeNode pRootOfTree) {
    	dfs(pRootOfTree);   
    	while(last!=null&&last.left!=null){
    		last=last.left;
    	}
    	return last;
    }
    
    /*
     * 28、字符串的排列
     */
    private static void swap(char[] chars, int i, int j){
    	if(i!=j){
    		char temp=chars[i];
    		chars[i]=chars[j];
    		chars[j]=temp;
    	}
    }
    
    private static void dfsGet(ArrayList<String> re, char[] chars, int start, int end){
    	if(start==end){
    		re.add(new String(chars));
    	}
    	
    	for(int i=start; i<=end; i++){
    		if(i!=start&&chars[i]==chars[start]){
    			continue;
    		}
    		swap(chars, start, i);
    		dfsGet(re, chars, start+1, end);
    		swap(chars, start, i);
    	}
    }
    
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> re=new ArrayList<String>();
        char[] chars=str.toCharArray();
        Arrays.sort(chars);
        dfsGet(re, chars, 0, chars.length-1);
        Collections.sort(re);
        return re;
    }
    
	public static void main(String[] args) {
		System.out.println(Permutation("aabc"));
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

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}