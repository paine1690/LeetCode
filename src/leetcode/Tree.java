package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	//104. Maximum Depth of Binary Tree
    public int maxDepth(TreeNode root) {
    	if(root==null){
			return 0;
		}
		return Math.max(maxDepth(root.left)+1, maxDepth(root.right)+1);
    }
    
    //111. Minimum Depth of Binary Tree
    
    
    //226. Invert Binary Tree
    public TreeNode invertTree(TreeNode root) {
    	if(root==null){
    		return root;
    	}
        TreeNode temp=invertTree(root.left);
        root.left=invertTree(root.right);
        root.right=temp;
        return root;
    }
    
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
    
    //144. Binary Tree Preorder Traversal
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
    
    //94. Binary Tree Inorder Traversal
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
