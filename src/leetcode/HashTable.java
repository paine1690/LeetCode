package leetcode;
import java.util.*;

public class HashTable {
	/*
	 * 类名HashTable是按照leetcode上面的tag起的，实际用的是HashMap
	 */
	
    public static int singleNumber(int[] nums) {
    	Set<Integer> set=new HashSet<Integer>();
    	for(int i=0; i<nums.length; i++){
    		if(set.contains(nums[i])){
    			set.remove(nums[i]);
    		}else{
    			set.add(nums[i]);
    		}
    	}
    	
    	Iterator<Integer> it=set.iterator();
    	return it.next();
    }
	
    public static boolean isIsomorphic(String s, String t) {
    	if(s.length()<2){
    		return true;
    	}
    	
    	Map<Character, Integer> map1=new HashMap<Character, Integer>();
    	Map<Integer, Character> map2=new HashMap<Integer, Character>();
    	for(int i=0; i<s.length(); i++){
    		if(!map1.containsKey(s.charAt(i))){
    			if(map2.containsValue(t.charAt(i))){
    				return false;
    			}else{
    				map1.put(s.charAt(i), i);
    				map2.put(i, t.charAt(i));
    			}
    		}else{
    			if(!map2.get(map1.get(s.charAt(i))).equals(t.charAt(i))){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static boolean isIsomorphic2(String s, String t) {
    	if(s.length()<2){
    		return true;
    	}
    	Map<Character, Character> map=new HashMap<Character, Character>();
    	Set<Character> set=new HashSet<Character>();
    	for(int i=0; i<s.length();i++){
    		if(!map.containsKey(s.charAt(i))){
    			if(set.contains(t.charAt(i))){
    				return false;
    			}else{
    				map.put(s.charAt(i), t.charAt(i));
    				set.add(t.charAt(i));
    			}
    		}else{
    			if(t.charAt(i)!=map.get(s.charAt(i))){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static String getHint(String secret, String guess) {
    	int bulls=0, cows=0;
        int[] map=new int[10];
        for(int i=0; i<secret.length(); i++){
        	if(secret.charAt(i)==guess.charAt(i)){
        		bulls++;
        	}else{
        		map[secret.charAt(i)-'0']++;
        	}
        }
        for(int i=0; i<guess.length(); i++){
        	if(guess.charAt(i)!=secret.charAt(i)){
        		if(map[guess.charAt(i)-'0']!=0){
        			cows++;
        			map[guess.charAt(i)-'0']--;
        		}
        	}
        }
    	return bulls+"A"+cows+"B";
    }
    
    public static List<String> findRepeatedDnaSequences(String s) {
    	List<String> re=new ArrayList<String>();
    	if(s.length()<=10){
    		return re;
    	}
    	Map<String, Integer> map=new HashMap<String, Integer>();    	
    	for(int i=10; i<=s.length(); i++){
    		String k=s.substring(i-10, i);
    		if(map.containsKey(k)){
    			int val=map.get(k);
    			if(val==1){
    				re.add(k);
    			}
    			map.put(k, val+1);
    		}else{
    			map.put(k, 1);
    		}
    	}
    	return re;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={1,5,6,4,1,6,4};
		String[] strs={"eat", "tea", "tan", "ate", "nat", "bat"};
		String s="0000000000000000";
		String t="1222";
		System.out.println(findRepeatedDnaSequences(s));
		
	}

}
