package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Prepare {

	
	
	private static int partition(int[] nums, int start, int end){
		int i=start, j=end;
		int x=nums[i];
		while(i<j){
			while(i<j&&bigger(nums[j], x)){
				j--;
			}
			if(i<j){
				nums[i++]=nums[j];
			}
			while(i<j&&bigger(x, nums[i])){
				i++;
			}
			if(i<j){
				nums[j--]=nums[i];
			}
		}
		nums[i]=x;
		System.out.println(Arrays.toString(nums));
		return i;
	}
	
	private static boolean bigger(int a, int b){
		String s1=String.valueOf(a);
		String s2=String.valueOf(b);
		
		if(s1.length()>s2.length()){
			return !bigger(b, a);
		}
		
		int i=0, j=0;
		while(i<s1.length()){
			if(s1.charAt(i)<s2.charAt(j)){
				return false;
			}
			else if(s1.charAt(i)>s2.charAt(j)){
				return true;
			}
			i++;
			j++;
		}
		return true;
	}
	
	private static void quik(int[] nums, int start, int end){
		
		if(start<end){
			int mid=partition(nums, start, end);
			quik(nums, start, mid-1);
			quik(nums, mid+1, end);
		}
	}
	
	private static void swap1(char[] chars, int i, int j){
		char temp=chars[j];
		for(int c=j; c>i; c--){
			chars[c]=chars[c-1];
		}
		chars[i]=temp;	
	}
	
	private static void swap2(char[] chars, int i, int j){
		char temp=chars[i];
		for(int c=i; c<j; c++){
			chars[c]=chars[c+1];
		}
		chars[j]=temp;
	}
	
	private static void per(List<String> re, char[] chars, int start){
		if(start>=chars.length){
			re.add(new String(chars));
		}
		
		for(int i=start; i<chars.length; i++){
			if(i!=start&&chars[i]==chars[start]){
				continue;
			}
			swap1(chars, start, i);
			per(re, chars, start+1);
			swap2(chars, start, i);
		}
	}
	
	public static List<String> pernu(String s){
		List<String> re=new ArrayList<String>();
		if(s.length()==0){
			return re;
		}
		char[] chars=s.toCharArray();		
		Arrays.sort(chars);
		per(re, chars, 0);	
		//Collections.sort(re);
		return re;
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(pernu("aab"));
		int[] nums={9,99,909,9901,8, 0};
		quik(nums, 0, nums.length-1);
		System.out.println(Arrays.toString(nums));
		
	}

}
