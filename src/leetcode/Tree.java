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
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
