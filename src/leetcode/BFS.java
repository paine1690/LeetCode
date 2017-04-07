package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BFS {
    
	//127. Word Ladder
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int re=1;
        Set<String> set=new HashSet<String>(wordList);
        List<String> list=new ArrayList<String>();
        list.add(beginWord);
        int cur=0;
        while(cur<list.size()){
        	int size=list.size();
        	for(int i=cur; i<size; i++){
        		String str=list.get(i);
        		if(str.equals(endWord)){
        			return re;
        		}
        		char[] chars=str.toCharArray();
        		for(int j=0; j<chars.length; j++){
        			char charJ=chars[j];        			
        			for(char c='a'; c<='z'; c++){
        				if(charJ!=c){
        					chars[j]=c;
        					String s=new String(chars);
        					if(set.contains(s)){
        						list.add(s);
        						set.remove(s);
        					}
        				}
        			}
        			chars[j]=charJ;        			
        		}
        	}
        	re++;
        	cur=size;
        }
        return 0;
    }
    
    //126. Word Ladder II
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> re=new ArrayList<List<String>>();
    	
    	
    	
    	
    	
    	
    	
    	return re;
    }
    
    
    
    
    
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("hot");
		list.add("dog");
		list.add("dot");
		System.out.println(list);
		
		
		//System.out.println(ladderLength("hot", "dog", list));

	}

}
