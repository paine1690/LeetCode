package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class Design {
	//208. Implement Trie (Prefix Tree)
	class TrieNode {
		boolean isWord;
		HashMap<Character, TrieNode> nexts;
	    public TrieNode() {
	        nexts=new HashMap<Character, TrieNode>();
	    }
	}
	
	class Trie {
	    private TrieNode root;

	    public Trie() {
	        root = new TrieNode();
	    }

	    // Inserts a word into the trie.
	    public void insert(String word) {
	        TrieNode pNode=root;
	        int i=0, len=word.length();
	        char[] s=word.toCharArray();
	        // traverse existing
	        while(i<len){
	        	TrieNode next=pNode.nexts.get(s[i]);
	        	if(next!=null){
	        		pNode=next;
	        		i++;
	        	}else{
	        		break;
	        	}
	        }
	        // append new nodes
	        while(i<len){
	        	TrieNode newNode=new TrieNode();
	        	pNode.nexts.put(s[i], newNode);
	        	pNode=newNode;
	        	i++;
	        }
	        // set word end
	        pNode.isWord=true;
	        
	    }

	    // Returns if the word is in the trie.
	    public boolean search(String word) {
	        TrieNode pNode=root;
	        int i=0, len=word.length();
	        char[] s=word.toCharArray();
	        while(i<len){
	        	TrieNode next=pNode.nexts.get(s[i]);
	        	if(next==null){
	        		return false;
	        	}
	        	pNode=next;
	        	i++;
	        }
	        return pNode.isWord;
	    }

	    // Returns if there is any word in the trie
	    // that starts with the given prefix.
	    public boolean startsWith(String prefix) {
	    	TrieNode pNode=root;
	        int i=0, len=prefix.length();
	        char[] s=prefix.toCharArray();
	        while(i<len){
	        	TrieNode next=pNode.nexts.get(s[i]);
	        	if(next==null){
	        		return false;
	        	}
	        	pNode=next;
	        	i++;
	        }
	        return true;
	    }
	
	}
	
	
	//211. Add and Search Word - Data structure design
	class WordDictionary {
		private TrieNode root;
		public WordDictionary(){
			root=new TrieNode();
		}
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        TrieNode pNode=root;
	        int i=0, len=word.length();
	        
	        while(i<len){
	        	TrieNode next=pNode.nexts.get(word.charAt(i));
	        	if(next==null){
	        		break;
	        	}
	        	pNode=next;
	        	i++;
	        }
	        
	        while(i<len){
	        	TrieNode newNode=new TrieNode();
	        	pNode.nexts.put(word.charAt(i), newNode);
	        	pNode=newNode;
	        	i++;
	        }
	        
	        pNode.isWord=true;
	    }

	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	    	return searchFrom(root, word, 0);
	    }
	    
	    private boolean searchFrom(TrieNode node, String word, int index){
	    	if(index==word.length()){
	    		System.out.println("fsd");
	    		return node.isWord;
	    	}
	    	
	        TrieNode pNode=node;
	        if(word.charAt(index)!='.'){
	        	TrieNode next=pNode.nexts.get(word.charAt(index));
	        	if(next==null){
	        		return false;
	        	}else{
	        		return searchFrom(next, word, index+1);
	        	}
	        }else{
	        	boolean re=false;
	        	java.util.Collection<TrieNode> coll=pNode.nexts.values();
	        	for(TrieNode tNode:coll){
	        		re|=searchFrom(tNode, word, index+1);
	        	}
	        	return re;
	        }        
	    }
	}
	
	//307. Range Sum Query - Mutable 树状数组
	class NumArray2 {
		private int[] nums;
		private int[] tree;
		private int len;
		
	    public NumArray2(int[] nums) {
	    	this.nums=nums;
	    	len=nums.length;
	    	tree=new int[len+1];
	    	for(int i=0; i<nums.length; i++){
	    		put(i+1, nums[i]);
	    	}	    	
	    }
	    private int getSum(int index){
	    	int sum=0;
	    	while(index>0){
	    		sum+=tree[index];
	    		index-=lowBit(index);
	    	}	    	
	    	return sum;
	    }
	    
	    public void update(int i, int val) {
	    	int diff=val-nums[i];
	    	nums[i]=val;
	    	put(i+1, diff);
	    }
	    
	    private void put(int index, int val){
	    	while(index<=len){
	        	tree[index]+=val;
	        	index+=lowBit(index);
	        }
	    }
	    
	    public int sumRange(int i, int j) {
	        return getSum(j+1)-getSum(i);
	    }
	    
	    private int lowBit(int index){
	    	return index&(-index);
	    }
	}
	//307. Range Sum Query - Mutable 线段树
	class NumArray {
		private class TreeNode{
			public int start;
			public int end;
			public int sum;
			public TreeNode left, right;
			public TreeNode(int start, int end){
				this.start=start;
				this.end=end;
				this.sum=0;
			}
		}
		
		private TreeNode root;
		private int[] nums;
		
		public NumArray(int[] nums){
			this.nums=nums;
			this.root=buildTree(0, nums.length-1);
		}
		
		public void update(int i, int val){
			update(root, i, val);
		}
		
		public int sumRange(int i, int j){
			return sumRange(root, i, j);
		}
		
		
		private int sumRange(TreeNode node, int start, int end){
			if(start>end){
				return 0;
			}
			
			if(node.start==start&&node.end==end){
				return node.sum;
			}
			int mid=node.start+(node.end-node.start)/2;
			if(end<=mid){
				return sumRange(node.left, start, end);
			}else if(start>mid){
				return sumRange(node.right, start, end);
			}else{
				return sumRange(node.left, start, mid)+sumRange(node.right, mid+1, end);
			}		
		}	
		
		private void update(TreeNode root, int pos, int val){
			if(root==null){
				return;
			}
			if(root.start==pos&&root.end==pos){
				root.sum=val;
				nums[pos]=val;
				return;
			}
			int mid=root.start+(root.end-root.start)/2;
			if(pos<=mid){
				update(root.left, pos, val);
			}else{
				update(root.right, pos, val);
			}
			root.sum=root.left.sum+root.right.sum;			
		}
		
		private TreeNode buildTree(int start, int end){
			if(start>end){
				return null;
			}
			TreeNode root=new TreeNode(start, end);
			if(start==end){
				root.sum=nums[start];
			}else{
				int mid=start+(end-start)/2;
				root.left=buildTree(start, mid);
				root.right=buildTree(mid+1, end);
				root.sum=root.left.sum+root.right.sum;
			}
			return root;			
		}
		
	}
	
	
	public Design(){
		System.out.println("fsd");
		NumArray n=new NumArray(new int[]{1,3,5});
		System.out.println(n.sumRange(0, 2));
		n.update(1, 2);
		System.out.println(n.sumRange(0, 2));
//		WordDictionary dic=new WordDictionary();
//		dic.addWord("a");
//		dic.addWord("ab");
		//System.out.println(dic.root.nexts.get('a').nexts.get('b').isWord);
			}
	
	public static void main(String[] args) {
		Design s=new Design();
		
	}

}


