package leetcode;

/**
 * 		二叉搜索树
 * 
 * 
 * @author Paine
 *
 */
public class BinarySearchTree {

	public static class TreeNode {
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
    
    //98. Validate Binary Search Tree   中序遍历
    private static TreeNode pre=null;
    private static boolean isValid(TreeNode root) {
        if(root==null){
        	return true;
        }
        if(isValid(root.left)){
        	if(pre==null||root.val>pre.val){
        		pre=root;
        		return isValid(root.right);
        	}else{
        		return false;
        	}
        }else{
        	return false;
        }
    }
    public static boolean isValidBST(TreeNode root) {
    	pre=null;
    	return isValid(root);
    }
    
	public static void main(String[] args) {
		TreeNode root=new TreeNode(0);
		TreeNode left=new TreeNode(-1);
		root.right=left;
		System.out.println(isValidBST(root));
	}

}
