package leetcode;

import java.util.HashMap;

public class Design {

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
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


