package leetcode;

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
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
