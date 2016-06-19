package leetcode;
import java.util.*;

public class HashTable {
	/*
	 * 类名HashTable是按照leetcode上面的tag起的，实际用的是HashMap
	 */
	
	//1. Two Sum
	public static int[] twoSum(int[] nums, int target){
		int[] re = new int[2];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();		
		for(int i=0; i<nums.length; i++){
			if(map.containsKey(target-nums[i])){
				if(map.get(target-nums[i]) != i){
					re[0] = i;
					re[1] = map.get(target-nums[i]);
					break;
				}
			}else{
				map.put(nums[i], i);
			}
		}
		return re;
	}
	
	//242. Valid Anagram
	public static boolean isAnagram(String s, String t) {
    	if(s.length()!=t.length()){
    		return false;
    	}
    	HashMap<Character, Integer> map=new HashMap<Character, Integer>();
    	char[] c1=s.toCharArray();
    	char[] c2=t.toCharArray();
    	
    	for(int i=0; i<c1.length; i++){
    		if(map.containsKey(c1[i])){
    			map.put(c1[i], map.get(c1[i])+1);
    		}
    		else{
    			map.put(c1[i], 1);
    		}
    	}
    	for(int i=0; i<c2.length; i++){
    		if(!map.containsKey(c2[i])){
    			return false;
    		}
    		else{
    			if(map.get(c2[i])==1){
    				map.remove(c2[i]);
    			}
    			else{
    				map.put(c2[i], map.get(c2[i])-1);
    			}
    		}
    	}
    	return true;
    }
	
	//290. Word Pattern
	public boolean wordPattern(String pattern, String str) {
        char[] chars=pattern.toCharArray();
        String[] strs=str.split(" ");
        if(chars.length!=strs.length){
            return false;
        }
        HashMap<Character, String> map=new HashMap<Character, String>();
        Set<String> set=new HashSet<String>();
        
    	for(int i=0;i<chars.length;i++){
    		if(map.containsKey(chars[i])){
    			if(!strs[i].equals(map.get(chars[i]))){
    				return false;
    			}
    		}
    		else{
    			if(set.contains(strs[i])){
    				return false;
    			}
    			map.put(chars[i], strs[i]);
    			set.add(strs[i]);
    		}
    	}
    	
    	return true;
    }
	
	//217. Contains Duplicate
	public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set=new HashSet<Integer>();
    	for(int i=0; i<nums.length; i++){
    		if(set.contains(nums[i])){
    			return true;
    		}else{
    			set.add(nums[i]);
    		}
    	}
    	
    	return false;
    }
	
	//219. Contains Duplicate II
	public boolean containsNearbyDuplicate(int[] nums, int k) {
    	int len=nums.length;
    	if(len<2){
    		return false;
    	}
    	HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
    	for(int i=0; i<len; i++){
    		if(!map.containsKey(nums[i])){
    			map.put(nums[i], i);
    		}else{
    			if((i-map.get(nums[i])<=k)){
    				return true;
    			}
    			map.put(nums[i], i);
    		}
    	}	
    	return false; 
    }
	
	//136. Single Number   应该用位运算更快
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
	
    //205. Isomorphic Strings
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
    
    //205. Isomorphic Strings
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
    
    //299. Bulls and Cows
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
    
    //187. Repeated DNA Sequences
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
    
    
    //49. Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> re;
        Map<String, List<String>> map=new HashMap<String, List<String>>();
        for(int i=0; i<strs.length; i++){
        	char[] chars=strs[i].toCharArray();
        	Arrays.sort(chars);
        	String temp=new String(chars);
        	if(!map.containsKey(temp)){
        		List<String> list=new ArrayList<String>();
        		list.add(strs[i]);
        		map.put(temp, list);
        	}else{
        		map.get(temp).add(strs[i]);
        	}
        }
        re=new ArrayList<List<String>>(map.values());
        for(List<String> list: re){
        	Collections.sort(list);
        }
        return re;
    }
    
    //347. Top K Frequent Elements
    public static List<Integer> topKFrequent(int[] nums, int k) {
    	Map<Integer, Integer> map=new HashMap<Integer, Integer>();
    	for(int i=0; i<nums.length; i++){
    		if(!map.containsKey(nums[i])){
    			map.put(nums[i], 1);
    		}else{
    			map.put(nums[i], 1+map.get(nums[i]));
    		}
    	}
    	List<Map.Entry<Integer, Integer>> list=new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
    	Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>(){
    		public int compare( Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
    			return o2.getValue().compareTo(o1.getValue());
    		}
    	});
    	List<Integer> re=new ArrayList<Integer>();
    	for(int i=0; i<k; i++){
    		re.add(list.get(i).getKey());
    	}
    	return re;
    }
    
    //3. Longest Substring Without Repeating Characters
	public static int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		int x = 0;
		int head = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < chars.length; i++) {
			if (!map.containsKey(chars[i])) {
				map.put(chars[i], i);
				if (map.size() > x) {
					x = map.size();
				}
			} else {
				if (map.size() > x) {
					x = map.size();
				}
				int temp = map.get(chars[i]);

				for (int j = head; j <= temp; j++) {
					map.remove(chars[j]);
				}
				head = temp + 1;
				map.put(chars[i], i);
			}
		}
		return x;
	}
  	
	
  	
  	
	public static void main(String[] args) {
		//int[] nums={-1, -1};
		//System.out.println(simplifyPath("/..."));
		
	}

}
