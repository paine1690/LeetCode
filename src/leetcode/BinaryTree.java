package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 		树
 * 
 * 
 * @author Paine
 *
 */
public class BinaryTree {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	//144. Binary Tree Preorder Traversal	先序遍历
    private static void preOrder(TreeNode root, List<Integer> re){
    	if(root!=null){
        	re.add(root.val);
        	preOrder(root.left, re);
        	preOrder(root.right, re);
    	}
    	
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        preOrder(root, re);
        return re;
    }
    
    //94. Binary Tree Inorder Traversal		中序遍历
    private void inOrder(TreeNode root, List<Integer> re){
    	if(root!=null){
        	inOrder(root.left, re);
        	re.add(root.val);
        	inOrder(root.right, re);
    	}
    	
    }
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> re=new ArrayList<Integer>();
    	inOrder(root, re);
        return re;
    }
    
    //145. Binary Tree Postorder Traversal		后序遍历
    private static void postOrder(TreeNode root, List<Integer> re){
    	if(root!=null){
    		postOrder(root.left, re);
    		postOrder(root.right, re);
    		re.add(root.val);
    	}
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        postOrder(root, re);
        return re;
    }
	
    //102. Binary Tree Level Order Traversal		层序遍历
    private static void level(List<List<Integer>> re, Queue<TreeNode> p, Queue<TreeNode> q){
    	List<Integer> level=new ArrayList<Integer>();
    	while(!p.isEmpty()){
    		TreeNode temp=p.poll();
    		level.add(temp.val);
    		if(temp.left!=null)		q.offer(temp.left);
    		if(temp.right!=null)	q.offer(temp.right);
    	}
    	re.add(level);
    	if(!q.isEmpty()){
    		level(re, q, p);
    	}
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        if(root==null){
    		return re;
    	}
    	Queue<TreeNode> p=new LinkedList<TreeNode>();
    	Queue<TreeNode> q=new LinkedList<TreeNode>();
    	p.offer(root);
    	level(re, p, q);
    	return re;
    }
	
    //107. Binary Tree Level Order Traversal II		层序遍历
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        if(root==null){
    		return re;
    	}
    	Queue<TreeNode> p=new LinkedList<TreeNode>();
    	Queue<TreeNode> q=new LinkedList<TreeNode>();
    	p.offer(root);
    	level(re, p, q);
    	Collections.reverse(re);
    	return re;
    }
    
	//104. Maximum Depth of Binary Tree		树的深度
    public static int maxDepth(TreeNode root) {
    	if(root==null){
			return 0;
		}
		return Math.max(maxDepth(root.left)+1, maxDepth(root.right)+1);
    }
    
    //226. Invert Binary Tree		反转二叉树
    public TreeNode invertTree(TreeNode root) {
    	if(root==null){
    		return root;
    	}
        TreeNode temp=invertTree(root.left);
        root.left=invertTree(root.right);
        root.right=temp;
        return root;
    }
    
    //110. Balanced Binary Tree		平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root==null){
        	return true;
        }
        if(root.left==null&&root.right==null){
        	return true;
        }
    	if(Math.abs(maxDepth(root.left)-maxDepth(root.right))>1){
    		return false;
    	}
    	return isBalanced(root.left)&&isBalanced(root.right);
    }
    
    //110. Balanced Binary Tree		平衡二叉树
    private static int getDeep(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	root.val=Math.max(getDeep(root.left)+1, getDeep(root.right)+1);
    	return root.val;
    }
    private static boolean dfs(TreeNode root){
    	if(root==null){
        	return true;
        }
        if(root.left==null&&root.right==null){
        	return true;
        }
        int l=0, r=0;
        if(root.left!=null){
        	l=root.left.val;
        }
        if(root.right!=null){
        	r=root.right.val;
        }
    	if(Math.abs(l-r)>1){
    		return false;
    	}
    	return dfs(root.left)&&dfs(root.right);
    	
    }
    public boolean isBalanced2(TreeNode root) {
       getDeep(root);
       return dfs(root);
    }
    
    /**
     * 上面的都是一些二叉树的基础操作
     */
    //100. Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	if(p==null&&q==null){
    		return true;
    	}
    	if((p!=null&&q!=null)&&(p.val==q.val)){
            return (isSameTree(p.left, q.left))&&(isSameTree(p.right, q.right));
    	}
        else{
        	return false;
        }
    }
    
    //112. Path Sum
    public boolean hasPathSum(TreeNode root, int sum) {
    	if(root==null){
    		return false;
    	}
        if(root.left==null&&root.right==null){
        	return root.val==sum;
        }else {
        	return (hasPathSum(root.left, sum-root.val))||(hasPathSum(root.right, sum-root.val));
        }    	
    }
    
    //257. Binary Tree Paths
    private static List<String> add(int val, List<String> str){
    	List<String> temp=new ArrayList<String>();
    	for(String s:str){
    		temp.add(String.valueOf(val)+"->"+s);
    	}
    	return temp;
    }
    private static List<String> treePath(TreeNode root){
    	List<String> temp=new ArrayList<String>();
    	if(root.left==null&&root.right==null){
    		temp.add(String.valueOf(root.val));
    		return temp;
    	}
    	if(root.left!=null){
    		temp.addAll(add(root.val, treePath(root.left)));
    	}
    	if(root.right!=null){
    		temp.addAll(add(root.val, treePath(root.right)));
    	}
    	return temp;
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> re=new ArrayList<String>();
        if(root!=null){
        	re=treePath(root);
        }
        return re;
    }
    
    //111. Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
        if(root==null){
			return 0;
		}else if(root.left==null&&root.right==null){
        	return 1;
        }else if(root.left==null){
        	return minDepth(root.right)+1;
        }else if(root.right==null){
        	return minDepth(root.left)+1;
        }else{
        	return Math.min(minDepth(root.left)+1, minDepth(root.right)+1);
        }
        
    }
    
    //101. Symmetric Tree
    private static boolean isSym(TreeNode l, TreeNode r){
    	if(l==null&&r==null){
    		return true;
    	}
    	if(l!=null&&r!=null){
    		if(l.val==r.val){
    			return isSym(l.right, r.left)&&isSym(l.left, r.right);
    		}
    	}
    	return false;
    }
    public boolean isSymmetric(TreeNode root) {
        if(root!=null){
        	return isSym(root.left, root.right);
        }
    	return true;
    }
    
    //124. Binary Tree Maximum Path Sum
    private static int maxPathRe=0;
    private static int maxPath(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	int l=maxPath(root.left);
    	int r=maxPath(root.right);
    	if(l>0||r>0){
    		if((l+r+root.val)>maxPathRe){
    			maxPathRe=l+r+root.val;
    		}
    		l+=root.val;
    		r+=root.val;
    		root.val=Math.max(l, r);
    		
    	}
    	if(root.val>maxPathRe){
    		maxPathRe=root.val;
    	}
    	return root.val;
    }
    public int maxPathSum(TreeNode root) {
    	maxPathRe=Integer.MIN_VALUE;
        maxPath(root);
        return maxPathRe;
    }
    
    //116. Populating Next Right Pointers in Each Node
    public class TreeLinkNode {
    	int val;
    	TreeLinkNode left, right, next;
    	TreeLinkNode(int x) { val = x; }
    }
    
    private static void levelT(Queue<TreeLinkNode> p, Queue<TreeLinkNode> q){
    	TreeLinkNode pNode=p.poll();
    	System.out.println(pNode.val);
    	if(pNode.left!=null){
    		q.offer(pNode.left);
    		q.offer(pNode.right);
    	}
    	while(!p.isEmpty()){
    		TreeLinkNode qNode=p.poll();
    		pNode.next=qNode;
    		if(qNode.left!=null){
    			q.offer(qNode.left);
    			q.offer(qNode.right);
    		}
    		pNode=qNode;
    	}
    	if(!q.isEmpty()){
    		levelT(q, p);
    	}
    }
    public void connect(TreeLinkNode root) {
    	Queue<TreeLinkNode> p=new LinkedList<TreeLinkNode>();
    	Queue<TreeLinkNode> q=new LinkedList<TreeLinkNode>();
    	if(root!=null){
        	p.offer(root);
        	levelT(p, q);        	
        }
    }
   
    
	public static void main(String[] args) {

	}

}
