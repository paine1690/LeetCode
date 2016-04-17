package leetcode;

import java.util.Stack;

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
    
    //173. Binary Search Tree Iterator
    static class BSTIterator {
    	Stack<TreeNode> stack=new Stack<TreeNode>();
        public BSTIterator(TreeNode root) {
            TreeNode temp=root;
            while(temp!=null){
            	stack.push(temp);
            	temp=temp.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !(stack.isEmpty());
        }
        
        /** @return the next smallest number */
        public int next() {
        	TreeNode temp=stack.pop();
        	int re=temp.val;
        	if(temp.right!=null){
        		temp=temp.right;
        		while(temp!=null){
        			stack.push(temp);
        			temp=temp.left;
        		}
        	}
        	return re;
        }
    }
    
    //108. Convert Sorted Array to Binary Search Tree
    private static TreeNode arrayToBST(int[] nums, int start, int end){
    	if(start==end){
        	return new TreeNode(nums[start]);
    	}
    	if(start<end){
    		int mid=(start+end)>>>1;
    		TreeNode root=new TreeNode(nums[mid]);
    		root.left=arrayToBST(nums, start, mid-1);
    		root.right=arrayToBST(nums, mid+1, end);
    		return root;
    	}
    	return null;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
    	if(nums.length==0){
    		return null;
    	}
        return arrayToBST(nums, 0, nums.length-1);
    }
    
    
	public static void main(String[] args) {
		TreeNode root=new TreeNode(2);
		TreeNode left=new TreeNode(1);
		TreeNode right=new TreeNode(3);
		root.left=left;
		root.right=right;
		BSTIterator i = new BSTIterator(root);
		while(i.hasNext()){
			System.out.println(i.next());
		}
	}

}
