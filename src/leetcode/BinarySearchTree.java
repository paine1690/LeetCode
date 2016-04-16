package leetcode;

/**
 * 		二叉搜索树
 * 
 * 
 * @author Paine
 *
 */
public class BinarySearchTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	//235. Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	if(root.val>Math.max(q.val, p.val)){
    		return lowestCommonAncestor(root.left, p, q);
    	}else if(root.val<Math.min(p.val, q.val)){
    		return lowestCommonAncestor(root.right, p, q);
    	}
    	return root;    	
    }
	public static void main(String[] args) {

	}

}
