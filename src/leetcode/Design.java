package leetcode;

import java.util.Collection;
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
	
	public Design(){
		System.out.println("fsd");
		WordDictionary dic=new WordDictionary();
		dic.addWord("a");
		dic.addWord("ab");
		//System.out.println(dic.root.nexts.get('a').nexts.get('b').isWord);
		System.out.println(dic.search("ab"));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Design d=new Design();
	}

}


