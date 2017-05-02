package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;



/**
 * 		树
 * 
 * 
 * @author Paine
 *
 */
public class BinaryTree {
	
	public static class TreeNode {
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
    
    //105. Construct Binary Tree from Preorder and Inorder Traversal 先序和中序 确定二叉树
    private static int getIndex(int[] nums, int start, int end, int num){
    	for(int i=start; i<=end; i++){
    		if(nums[i]==num){
    			return i;
    		}
    	}
    	return -1;
    }
    private static TreeNode build(int[] inorder, int start, int end, int pre[], int index){
    	TreeNode root=null;
    	if(start<=end){
    		root=new TreeNode(pre[index]);
    		if(start!=end){
    			int mid=getIndex(inorder, start, end, pre[index]);
        		root.left=build(inorder, start, mid-1, pre, index+1);
            	root.right=build(inorder, mid+1, end, pre, index+1+mid-start);
    		}
    	}
    	return root;    	
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(inorder, 0, preorder.length-1, preorder, 0); 
    }
    
    //106. Construct Binary Tree from Inorder and Postorder Traversal 中序和后序 确定二叉树
    private static TreeNode build2(int[] inorder, int start, int end, int[] postorder, int index){
    	TreeNode root=null;
    	if(start<=end){
    		root=new TreeNode(postorder[index]);
    		if(start!=end){
    			int mid=getIndex(inorder, start, end, postorder[index]);
    			root.right=build2(inorder, mid+1, end, postorder, index-1);
    			root.left=build2(inorder, start, mid-1, postorder, index-1-end+mid);
    		}
    	}
    	return root;
    }
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return build2(inorder, 0, inorder.length-1, postorder, postorder.length-1);
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
    
	//102. Binary Tree Level Order Traversal		层序遍历    非递归实现
    public static List<List<Integer>> levelOrder2(TreeNode root) {
    	List<List<Integer>> re=new ArrayList<List<Integer>>();
    	if(root==null){
    		return re;
    	}
    	List<TreeNode> list=new ArrayList<TreeNode>();
    	list.add(root);
    	int cur=0;
    	int last=1;
    	while(cur<list.size()){
    		last=list.size();
    		List<Integer> temp=new ArrayList<Integer>();
    		while(cur<last){
    			TreeNode tempNode=list.get(cur);
    			temp.add(tempNode.val);
    			if(tempNode.left!=null){
    				list.add(tempNode.left);
    			}
    			if(tempNode.right!=null){
    				list.add(tempNode.right);
    			}
    			cur++;
    		}
    		re.add(temp);
    	}    	
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
    public static boolean isBalanced(TreeNode root) {
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
    public static boolean isBalanced2(TreeNode root) {
       getDeep(root);
       return dfs(root);
    }
    
    
    
    //110. Balanced Binary Tree		平衡二叉树
   	private int balance(TreeNode root){
        if(root==null){
            return 0;
        }        
        int left=balance(root.left);
        int right=balance(root.right);
        if(left==-1||right==-1||Math.abs(left-right)>1){
            return -1;
        }else{
            return 1+Math.max(left, right);
        }        
    }

    public boolean isBalanced3(TreeNode root) {
        return balance(root)!=-1;		
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
   
    //116. Populating Next Right Pointers in Each Node
    public void connect2(TreeLinkNode root) {
    	if(root==null||root.left==null){
    		return;
    	}
    	root.left.next=root.right;
    	if(root.next!=null){
    		root.right.next=root.next.left;
    	}
    	connect2(root.left);
    	connect2(root.right);
    }
    
    //236. Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
        	return null;
        }
        if(root==p||root==q){
        	return root;
        }
        TreeNode l=lowestCommonAncestor(root.left, p, q);
        TreeNode r=lowestCommonAncestor(root.right, p, q);
        if(l!=null&&r!=null){
        	return root;
        }
        return l==null? r:l;        
    }
    
    //236. Lowest Common Ancestor of a Binary Tree  通过路径记录
    private static boolean getPath(List<TreeNode> list, TreeNode root, TreeNode tar){
    	 	
    	if(root==null){
    		return false;
    	}    	
    	list.add(root);   
    	if(root==tar){
    		return true;
    	}
    	
    	if(root.left!=null){
    		if(getPath(list, root.left, tar)){
        		return true;
        	}else{
        		list.remove(list.size()-1);    	
        	}
    	}
    	
    	if(root.right!=null){
    		if(getPath(list, root.right, tar)){
        		return true;
        	}else{
        		list.remove(list.size()-1);
        	}
    	}
    	return false;
    }
    
    private static TreeNode getLow(List<TreeNode> l1, List<TreeNode> l2){
    	int i=0;
    	for(i=0; i<l1.size()&&i<l2.size(); i++){
    		if(l1.get(i).val!=l2.get(i).val){
    			break;
    		}
    	}
    	return l1.get(i-1);  
    }
    
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    	List<TreeNode> l1=new LinkedList<TreeNode>();
    	List<TreeNode> l2=new LinkedList<TreeNode>();
    	getPath(l1, root, p);
    	getPath(l2, root, q); 
    	return getLow(l1, l2);
    }
    
    //114. Flatten Binary Tree to Linked List
    TreeNode temp=null;
    public void flatten(TreeNode root) {
        if(root==null){
        	return;
        }
    	temp=root;
        flatten(root.left);
        if(temp!=root&&temp!=null){
        	temp.right=root.right;
        	root.right=root.left;
        	root.left=null;
        	while(temp.right!=null){
        		temp=temp.right;
        	}
        }
        flatten(root.right);
    }
    
    //222. Count Complete Tree Nodes
    private static int getLeft(TreeNode root){
    	int re=0;
    	while(root!=null){
    		re++;
    		root=root.left;
    	}
    	return re;
    }
    private static int getRight(TreeNode root){
    	int re=0;
    	while(root!=null){
    		re++;
    		root=root.right;
    	}
    	return re;
    }
    public int countNodes(TreeNode root) {
    	if(root==null){
    		return 0;
    	}
    	int l=getLeft(root.left);
    	int r=getRight(root.right);
    	if(r==l){
    		return (1<<l+1)-1;
    	}
    	return countNodes(root.left)+countNodes(root.right)+1;
    }
    
    //113. Path Sum II
	@SuppressWarnings("unchecked")
	private static void sum(TreeNode root, int sum, List<List<Integer>> re, Stack<Integer> stack){
    	if(root==null){
    		return;
    	}
    	stack.push(root.val);
    	if(root.left==null&&root.right==null&&root.val==sum){
    		re.add((List<Integer>) stack.clone());
    	}
    	sum(root.left, sum-root.val, re, stack);
    	sum(root.right, sum-root.val, re, stack);
    	stack.pop();
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        Stack<Integer> stack=new Stack<Integer>();
        sum(root, sum, re, stack);
        return re;
    }
    
    //117. Populating Next Right Pointers in Each Node II
    public void connect3(TreeLinkNode root) {
    	TreeLinkNode head=root;
    	TreeLinkNode nextHead=null;
    	while(head!=null){
			TreeLinkNode node=head;
			TreeLinkNode last=null;
			while(node!=null){
				if(node.left!=null&&node.right!=null){
					node.left.next=node.right;
				}
				TreeLinkNode temp;
				if(node.left!=null){
					temp=node.left;
				}else if(node.right!=null){
					temp=node.right;
				}else{
					temp=null;
				} 
				if(temp!=null){
					if(nextHead==null){
						nextHead=temp;
						last=temp;
					}else{
						last.next=temp;
					}
					while(last.next!=null){
						last=last.next;
					}
				}
				node=node.next;
			}
			head=nextHead;
			nextHead=null;
    	}
    }
    
    //129. Sum Root to Leaf Numbers
    private static int getSums(TreeNode root, int re){
    	int temp=re*10+root.val;
    	if(root.left==null&&root.right==null){
    		return temp;
    	}else if(root.left!=null&&root.right!=null){
    		return getSums(root.left, temp)+getSums(root.right, temp);
    	}else if(root.left!=null){
    		return getSums(root.left, temp);
    	}else{
    		return getSums(root.right, temp);
    	}
    }
    public int sumNumbers(TreeNode root) {
    	if(root==null){
    		return 0;
    	}
    	int re=0;
    	return getSums(root, re);
    }
    
    //103. Binary Tree Zigzag Level Order Traversal
    private static int flag;
    private static void zigzag(Queue<TreeNode> p, Queue<TreeNode> q, List<List<Integer>> re){
    	List<Integer> temp=new ArrayList<Integer>();
    	TreeNode node;
    	while(!p.isEmpty()){
    		node=p.poll();
    		temp.add(node.val);
    		if(node.left!=null) q.offer(node.left);
    		if(node.right!=null) q.offer(node.right);
    	}
    	if((flag++&1)==1){
    		Collections.reverse(temp);
    	}
    	re.add(temp);
    	if(!q.isEmpty()){
    		zigzag(q, p, re);
    	}
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        if(root!=null){
        	flag=0;
        	Queue<TreeNode> p=new LinkedList<TreeNode>();
            Queue<TreeNode> q=new LinkedList<TreeNode>();
            p.offer(root);
            zigzag(p, q, re);
        }        
        return re;
    }
    
    //199. Binary Tree Right Side View
    private static void level(Queue<TreeNode>p, Queue<TreeNode> q, List<Integer> re){
    	TreeNode temp=null;
    	while(!p.isEmpty()){
    		temp=p.poll();
    		if(temp.left!=null) q.offer(temp.left);
    		if(temp.right!=null) q.offer(temp.right);
    	}
    	re.add(temp.val);
    	if(!q.isEmpty()){
    		level(q, p, re);
    	}
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        if(root!=null){
        	Queue<TreeNode> p=new LinkedList<TreeNode>();
        	Queue<TreeNode> q=new LinkedList<TreeNode>();
        	p.offer(root);
        	level(p, q, re);
        }
        return re;
    }
    
    //199. Binary Tree Right Side View
    public List<Integer> rightSideView2(TreeNode root) {
    	List<Integer> re=new ArrayList<Integer>();
    	if(root!=null){
    		Queue<TreeNode> q=new LinkedList<TreeNode>();
    		q.offer(root);
    		q.offer(null);
    		TreeNode node;
    		while(!q.isEmpty()){
    			node=q.poll();
    			if(node==null){
    				if(q.isEmpty()){
    					break;
    				}else{
    					q.offer(null);
    				}
    			}else{
    				if(q.peek()==null){
    					re.add(node.val);
    				}
    				if(node.left!=null) q.offer(node.left);
    				if(node.right!=null) q.offer(node.right);
    			}
    		}
    	}
    	return re;
    }
    
    
    //wap
    public static TreeNode cxrea(String[] strs){
    	if(strs.length==0||strs[0].equals("null")){
    		return null;
    	}
    	Queue<TreeNode> q=new LinkedList<TreeNode>();
    	String s=strs[0];
    	TreeNode root=new TreeNode(Integer.valueOf(s));
    	q.offer(root);
    	int i=1;
    	while(i<strs.length){
    		TreeNode node=q.poll();
    		while(node==null){
    			node=q.poll();
    		}   		
    		
    		s=strs[i];
    		if(s.equals("null")){
    			node.left=null;
    			q.offer(null);
    		}else{
    			TreeNode nNode=new TreeNode(Integer.valueOf(s));
    			node.left=nNode;
    			q.offer(nNode);
    		}
    		if(++i>=strs.length){
    			break;
    		}
    		
    		s=strs[i];
    		if(s.equals("null")){
    			node.right=null;
    			q.offer(null);
    		}else{
    			TreeNode nNode=new TreeNode(Integer.valueOf(s));
    			node.right=nNode;
    			q.offer(nNode);
    		}
    		i++;
    	}
    	
    	return root;
    }
    
    //297. Serialize and Deserialize Binary Tree
    static class Codec {

    	private void seria(StringBuilder s, TreeNode root){
    		if(root==null){
    			s.append("*,");
    			return ;
    		}else{
    			s.append(root.val).append(',');
    			seria(s, root.left);
    			seria(s, root.right);
    		}
    	}
    	
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder s=new StringBuilder();
            seria(s, root);
            return s.toString();
        }
        
        private int index;
        
        private TreeNode deseria(String s){
        	if(index>=s.length()){
        		return null;
        	}
        	TreeNode root;
        	if(s.charAt(index)=='*'){
        		root=null;
        		index+=2;
        	}else{
        		int end=index+1;
        		while(end<s.length()&&s.charAt(end)!=','){
        			end++;
        		}
        		root=new TreeNode(Integer.valueOf(s.substring(index, end)));
        		index=end+1;   
        		root.left=deseria(s);
        		root.right=deseria(s);
        	}
        	return root;
        }
        
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            index=0;
            return deseria(data);
        }
    }
    
    //404. Sum of Left Leaves
    private int sum;
    
    private int countSon(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	
    	int left=countSon(root.left);
    	if(left==1){
    		sum+=root.left.val;
    	}
    	return left+1+countSon(root.right);
    }
    
    public int sumOfLeftLeaves(TreeNode root) {
    	sum=0;
    	countSon(root);
    	return sum;
    }
    
    //437. Path Sum III    
    private int pathGet(TreeNode root, int tar){
    	int re=0;
    	if(root!=null){
    		if(root.val==tar){
    			re++;
    		}
    		re+=pathGet(root.left, tar-root.val);
    		re+=pathGet(root.right, tar-root.val);
    	}
    	return re;
    }
    
    public int pathSum3(TreeNode root, int sum) {
    	if(root==null){
    		return 0;
    	}
		return pathGet(root, sum)+pathSum3(root.left, sum)+pathSum3(root.right, sum);        
    }
    
    //515. Find Largest Value in Each Tree Row
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        if(root==null){
        	return re;
        }
        List<TreeNode> list=new ArrayList<TreeNode>();
        list.add(root);
        int cur=0, size=1;
        while(cur<list.size()){
        	size=list.size();
        	int max=Integer.MIN_VALUE;
        	for(int i=cur; i<size; i++){
        		TreeNode node=list.get(i);
        		max=Math.max(max, node.val);
        		if(node.left!=null){
        			list.add(node.left);
        		}
        		if(node.right!=null){
        			list.add(node.right);
        		}
        	}
        	re.add(max);
        	cur=size;
        }
        return re;
    }

    //513. Find Bottom Left Tree Value
    public int findBottomLeftValue(TreeNode root) {
    	List<TreeNode> list=new ArrayList<TreeNode>();
        list.add(root);
        int cur=0, size=1;
        while(cur<list.size()){
        	size=list.size();
        	for(int i=cur; i<size; i++){
        		TreeNode node=list.get(i);
        		if(node.left!=null){
        			list.add(node.left);
        		}
        		if(node.right!=null){
        			list.add(node.right);
        		}
        	}
        	if(size==list.size()){
        		return list.get(cur).val;
        	}
        	cur=size;
        }
    	return 0;
    }
    
    //508. Most Frequent Subtree Sum
    private int dfsFind(TreeNode root, Map<Integer, Integer> map){
    	if(root==null){
    		return 0;
    	}
    	int re=root.val+dfsFind(root.left, map)+dfsFind(root.right, map);
    	if(map.containsKey(re)){
    		map.put(re, map.get(re)+1);
    	}else{
    		map.put(re, 1);
    	}
    	return re;
    }
    
    
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map=new HashMap<Integer, Integer>();
    	dfsFind(root, map);
    	int max=Integer.MIN_VALUE;
    	Iterator<Integer> i=map.values().iterator();
    	while(i.hasNext()){
    		max=Math.max(max, i.next());
    	}
    	List<Integer> list=new ArrayList<Integer>();
    	for(Entry<Integer, Integer> entry: map.entrySet()){
    		if(entry.getValue()==max){
    			list.add(entry.getKey());
    		}
    	}
    	int[] re=new int[list.size()];
    	for(int j=0; j<re.length; j++){
    		re[j]=list.get(j);
    	}
    	return re;
    }
    
    //543. Diameter of Binary Tree
    private int diameter;
    private int getDeeps(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	int left=getDeeps(root.left);
    	int right=getDeeps(root.right);
    	
    	diameter=Math.max(left+right, diameter);    	
    	return Math.max(left,  right)+1;
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
    	diameter=0;
        getDeeps(root);
    	return diameter;
    }
    
    //563. Binary Tree Tilt
    
    static int re;
    
    private int getSum(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	int left=getSum(root.left);
    	int right=getSum(root.right);
    	re+=Math.abs(left-right);
    	return left+right+root.val;
    }
    
    public int findTilt(TreeNode root) {
        re=0;
        getSum(root);
        return re;
    }
    
    
    
    
	public static void main(String[] args) {
		TreeNode n1=new TreeNode(1);
		TreeNode n2=new TreeNode(2);
		TreeNode n3=new TreeNode(3);
		TreeNode n4=new TreeNode(4);
		TreeNode n5=new TreeNode(5);
		TreeNode n6=new TreeNode(6);
		TreeNode n7=new TreeNode(7);
		TreeNode n8=new TreeNode(8);
		TreeNode n9=new TreeNode(9);
		TreeNode n10=new TreeNode(10);
		
		TreeNode n11=new TreeNode(11);
		TreeNode n12=new TreeNode(12);
		
		
		n1.left=n2;
		n1.right=n3;
		n2.left=n4;
		n3.left=n5;
		n3.right=n6;
		n4.left=n7;
		n4.right=n8;
		n5.left=n9;
		n7.left=n10;
		n2.right=n11;
		n11.left=n12;
		System.out.print(isBalanced(n1));
		//lowestCommonAncestor2(n1, n5, n7);
//		String[] strs={
//			"1",
//			"2",
//			"3",
//			"null",
//			"4",
//			"null",
//			"null",
//			
//			"5",
//			"6",
//			"7",
//			"8"
//		};
//		TreeNode root=cxrea(strs);
//		System.out.println(levelOrder2(root));
		
//		int[][] test=new int[][]{};
//		System.out.println(test[0]);
	}

}
