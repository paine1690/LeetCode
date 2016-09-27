package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class Xiaomi {

	private static void getNum(Map<Character, List<Integer>> map, String str, String[] strs){
		System.out.println(str);
		int[] nums=new int[10];
		List<Integer> re=new ArrayList<Integer>();
		for(int i=0; i<str.length(); i++){
			List<Integer> list=map.get(str.charAt(i));
			for(int j=0; j<list.size(); j++){
				nums[list.get(j)]++;
			}
		}
		System.out.println(Arrays.toString(nums));
		for(int i=0; i<nums.length; i++){
			if(nums[i]>=strs[i].length()){
				re.add(i);
				nums[i]-=strs[i].length();
				i--;
			}
		}
		System.out.println(re);
	}
    
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		Map<Character, List<Integer>> map=new HashMap<Character, List<Integer>>();
		String[] strs={"ZERO", "ONE", "TWO", "THREE", "FOUR", 
				"FIVE", "SIX", "SEVEN", "EIGHT", "NINE"	
		};
		for(int i=0; i<strs.length; i++){
			String temp=strs[i];
			Set<Character> set=new HashSet<Character>();
			for(int j=0; j<temp.length(); j++){
			
				char c=temp.charAt(j);
				if(set.contains(c)){
					continue;
				}else{
					set.add(c);
					if(map.containsKey(c)){
						map.get(c).add(i);
					}else{
						List<Integer> list=new ArrayList<Integer>();
						list.add(i);
						map.put(c, list);
					}
				}
			}				
		}
		System.out.println(map);
		int n=s.nextInt();
		for(int i=0; i<n; i++){
			String temp=s.nextLine();
			getNum(map, temp, strs);
		}
		
	}
}
