package second_leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 1、判断二叉搜索树	中序遍历
 * 2、第k小元素
 * 3、n个结点可以有多少二叉搜索树
 * 4、有序数组生成二叉搜索树
 * 5、二叉搜索树 迭代器 时间O(1) 空间 O(h)
 * 
 */


public class BinarySearchTree {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	//98. Validate Binary Search Tree	中序遍历
	private TreeNode pre;
	
	private boolean isValid(TreeNode root){
		if(root==null){
			return true;
		}		
		if(isValid(root.left)&&(pre==null||root.val>pre.val)){
			pre=root;
			return isValid(root.right);
		}else{
			return false;
		}
	}
	
    public boolean isValidBST(TreeNode root) {
    	pre=null;
    	return isValid(root);
    }
	
    //230. Kth Smallest Element in a BST
    private int countNodes(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	return 1+countNodes(root.left)+countNodes(root.right);
    }
    
    public int kthSmallest(TreeNode root, int k) {
        int cnt=countNodes(root.left)+1;
        if(cnt==k){
        	return root.val;
        }else if(cnt>k){
        	return kthSmallest(root.left, k);
        }else{
        	return kthSmallest(root.right, k-cnt);
        }
    }
    
	//96. Unique Binary Search Trees
    public int numTrees(int n) {
    	if(n==0||n==1){
    		return 1;
    	}    	
        int[] nums=new int[n+1];
        nums[0]=1;
        nums[1]=1;
        for(int i=2; i<=n; i++){
        	for(int j=0; j<i; j++){
        		nums[i]+=nums[j]*nums[i-j-1];
        	}
        }
        return nums[n];
    }
    
    //95. Unique Binary Search Trees II
    private List<TreeNode> create(int start, int end){
    	List<TreeNode> re=new ArrayList<TreeNode>();
    	if(start<=end){
    		if(start==end){
    			TreeNode root=new TreeNode(start);
    			re.add(root);
    		}else{
    			for(int mid=start; mid<=end; mid++){
    				List<TreeNode> l=create(start, mid-1);
    				List<TreeNode> r=create(mid+1, end);
    				for(int i=0; i<l.size(); i++){
    					for(int j=0; j<r.size(); j++){
    						TreeNode root=new TreeNode(mid);
    						root.left=l.get(i);
    						root.right=r.get(j);
    						re.add(root);
    					}
    				}
    			}
    		}
    	}else{
    		re.add(null);
    	}
    	return re;
    }
    
    public List<TreeNode> generateTrees2(int n) {
    	if(n==0){
    		return new ArrayList<TreeNode>();
    	}
        return create(1, n);
    }
	
    //108. Convert Sorted Array to Binary Search Tree
    private TreeNode createBST(int[] nums, int start, int end){
    	TreeNode root=null;
    	if(start<=end){
    		if(start==end){
    			root=new TreeNode(nums[start]);
    		}else{
    			int mid=start+(end-start)/2;
    			root=new TreeNode(nums[mid]);
    			root.left=createBST(nums, start, mid-1);
    			root.right=createBST(nums, mid+1, end);
    		}
    	}    	
    	return root;
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        return createBST(nums, 0, nums.length-1);
    }
	
    //99. Recover Binary Search Tree
    TreeNode preNode, mis1, mis2;    
    
    private void isValid2(TreeNode root){
    	if(root==null){
    		return;
    	}
    	isValid2(root.left);
    	if(preNode!=null&&root.val<preNode.val){
    		if(mis1==null){
    			mis1=preNode;
    			mis2=root;
    		}else{
    			mis2=root;
    		}
    	}
    	preNode=root;
    	isValid2(root.right);
    }
    
    public void recoverTree(TreeNode root) {
        preNode=null;
        mis1=null;
        mis2=null;
        isValid2(root);
        int temp=mis1.val;
        mis1.val=mis2.val;
        mis2.val=temp;
    }
    
    //173. Binary Search Tree Iterator
    static class BSTIterator {
    	Stack<TreeNode> stack=new Stack<TreeNode>();
    	
        public BSTIterator(TreeNode root) {
            while(root!=null){
            	stack.push(root);
            	root=root.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode re=stack.pop(), node=re.right;
            while(node!=null){
            	stack.push(node);
            	node=node.left;
            }
            return re.val;
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
