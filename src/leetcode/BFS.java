package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BFS {
	//127. Word Ladder
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    	int len=endWord.length();
    	char[] chars=endWord.toCharArray();
    	Set<String> wordSet=new HashSet<String>(wordList);		
		int re=1;
		List<String> list=new ArrayList<String>();
    	list.add(beginWord);
    	int cur=0, last=1;
    	while(cur<list.size()){
    		for(int index=cur; index<last; index++){
    			String s=list.get(index);
    			if(s.equals(endWord)){
    				return re;
    			}    			
    			chars=s.toCharArray();    			
    			for(int i=0; i<len; i++){
    				char ctemp=chars[i];
    				for(char c='a'; c<='z'; c++){
    					chars[i]=c;
    					String temp=new String(chars);   	    					
    					if(wordSet.contains(temp)){
    						wordSet.remove(temp);
    						list.add(temp);
    					}
    				}
    				chars[i]=ctemp;
    			}
    		}
    		re++;
    		cur=last;
    		last=list.size();
    	}
    	return 0;
    }
    
    
    
	public static void main(String[] args) {
		Set<String> set=new HashSet<String>();
		set.add("hot");
		set.add("dog");
		set.add("dot");
		System.out.println(ladderLength("hot", "dog", set));

	}

}
