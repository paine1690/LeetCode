package leetcode;


import java.util.ArrayList;
import java.util.List;
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
    
    //230. Kth Smallest Element in a BST
    /*
     * 优化，若插入删除操作频繁，可以给TreeNode增加一个leftCount属性，用来记录左子树中节点的个数
     */
    private static int getCount(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	return 1+getCount(root.left)+getCount(root.right);
    	
    }
    public int kthSmallest(TreeNode root, int k) {
        int count=getCount(root.left)+1;
        if(count==k){
        	return root.val;
        }else if(count>k){
        	return kthSmallest(root.left, k);
        }else{
        	return kthSmallest(root.right, k-count);
        }
    }
    

    //99. Recover Binary Search Tree
    private static TreeNode preNode;
    private static TreeNode mis1, mis2;
    private static void pre(TreeNode root){
    	if(root==null){
    		return;
    	}
    	if(root.left!=null){
    		pre(root.left);
    	}
    	if(preNode!=null&&root.val<preNode.val){
    		if(mis1==null){
    			mis1=preNode;
    			mis2=root;
    		}else{
    			mis2=root;
    		}
    	}
    	preNode=root;
    	if(root.right!=null){
    		pre(root.right);
    	}
    }
    public static void recoverTree(TreeNode root) {
        preNode=null;
        mis1=null;
        mis2=null;
        pre(root);
        int temp=mis1.val;
        mis1.val=mis2.val;
        mis2.val=temp;
    }
    
    //95. Unique Binary Search Trees II
    private static List<TreeNode> trees(int n, List<TreeNode> left, List<TreeNode> right){
    	List<TreeNode> re=new ArrayList<TreeNode>();
    	if(left==null&&right==null){
    		TreeNode root=new TreeNode(n);
    		re.add(root);
    	}else if(left==null){
    		for(int i=0; i<right.size(); i++){
    			TreeNode root=new TreeNode(n);
    			root.right=right.get(i);
    			re.add(root);
    		}
    	}else if(right==null){
    		for(int i=0; i<left.size(); i++){
    			TreeNode root=new TreeNode(n);
    			root.left=left.get(i);
    			re.add(root);
    		}
    	}else{
    		for(int i=0; i<left.size(); i++){
    			for(int j=0; j<right.size(); j++){
    				TreeNode root=new TreeNode(n);
        			root.left=left.get(i);
        			root.right=right.get(j);
        			re.add(root);
    			}    			
    		}
    	}
    	return re;
    }
    private static List<TreeNode> generate(int start, int end){
    	if(start>end){
    		return null;
    	}
    	List<TreeNode> re=new ArrayList<TreeNode>();
    	for(int i=start; i<=end; i++){
    		List<TreeNode> temp=trees(i, generate(start, i-1), generate(i+1, end));
    		re.addAll(temp);
    	}

    	return re;
    }
    public static List<TreeNode> generateTrees(int n) {
    	if(n==0){
    		return new ArrayList<TreeNode>();
    	}
        return generate(1, n);
    }
    
    
	public static void main(String[] args) {
		System.out.println(generateTrees(3));
		
	}

}
