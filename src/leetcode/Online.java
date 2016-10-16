package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Online {

	
	
    public static boolean validWordSquare(List<String> words) {
        if(words.size()==0){
        	return true;
        }
        int index=0;
        System.out.println(index);
        while(index<words.size()&&index<words.get(index).length()){
        	int i=0;
        	for(i=index+1; i<words.size()&&index<words.get(i).length()&&i<words.get(index).length(); i++){        		
        		if(words.get(i).charAt(index)!=words.get(index).charAt(i)){
        			return false;
        		}
        	}
        	
        	if((i<words.size()&&index<words.get(i).length())||i<words.get(index).length()){
        		return false;
        	}
        	index++;
        }
        return true;        
    }
 
   
    
    
    
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("abcd");
		list.add("bnrt");
		list.add("crm");
		list.add("dt");
		System.out.println(validWordSquare(list));

	}

}
