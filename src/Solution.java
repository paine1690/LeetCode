import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	public static int[] twoSum(int[] nums, int target){
		int[] re = new int[2];
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<nums.length;i++){
			map.put(nums[i], i+1);
		}
		
		for(int i=0; i<nums.length; i++){
			if(map.containsKey(target-nums[i])){
				if(map.get(target-nums[i]) != i+1){
					re[0] = i+1;
					re[1] = map.get(target-nums[i]);
					break;
				}
			}
		}
		return re;
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int flag = 0;
		int sum = l1.val+l2.val;
		if(sum>=10){
			sum = sum%10;
			flag = 1;
		}
		
        ListNode re = new ListNode(sum);
        ListNode tail = re;
        
		while(l1.next!=null && l2.next!=null){
			int t1=l1.val;
			int t2=l2.val;
			ListNode nNode = new ListNode(t1+t2);
			tail.next = nNode;
			tail = nNode;
			l1=l1.next;
			l2=l2.next;
		}
		while(l1.next!=null){
			ListNode nNode = new ListNode(l1.val);
			tail.next = nNode;
			tail = nNode;
			l1=l1.next;
		}
		while(l2.next!=null){
			ListNode nNode = new ListNode(l2.val);
			tail.next = nNode;
			tail = nNode;
			l1=l2.next;
		}
		return re;
    }
	
	public static int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		String ss=String.valueOf(chars);
        int x = 0;
        int head = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0; i<chars.length; i++){
        	if(!map.containsKey(chars[i])){
        		map.put(chars[i], i);
        		if(map.size()>x){
        			x = map.size();
        		}
        	}
        	else{
        		if(map.size()>x){
        			x = map.size();
        		}
        		
        		int temp = map.get(chars[i]);
        		
        		for(int j=head; j<=temp; j++){
        			map.remove(chars[j]);
        		}
        		
        		head=temp+1;
        		map.put(chars[i],i);
        	}
        }
        return x;
    }
	
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m=nums1.length;
        int n=nums2.length;
        int mid=(m+n)/2;
        int[] temp=new int[m+n];
        double re=0;
        if(m==0){
        	for(int i=0;i<n;i++){
        		temp[i]=nums2[i];
        	}
        }
        else if(n==0){
        	for(int i=0;i<m;i++){
        		temp[i]=nums1[i];
        	}
        }
        
        else{
            int i=0;
            int j=0;
            int k=0;
            while(i<m&&j<n){
                if(nums1[i]<nums2[j]){
                    temp[k++]=nums1[i++];
                }
                else{
                    temp[k++]=nums2[j++];
                }
            }
            while(i<m){
                temp[k++]=nums1[i++];
            }
            while(j<n){
                temp[k++]=nums2[j++];
            }
            
        }
        if((m+n)%2==1){
        	re=temp[mid];
        }
        else{
        	re=(temp[mid]+temp[mid-1])/2.0;
        }
        
        return re;
        
    }
	
	public static String longestPalindrome(String s) {
        char[] chars=s.toCharArray();
        int length = chars.length;
        int re_l=0;
        int re_r=0;
        int re_length=1;
        for(int i=0; i<chars.length-1; i++){
        	int first=0;
        	int last=0;
        	
        	if(chars[i]==chars[i+1]){
        		first=i;
        		last=i+1;
        	}
        	
        	for(int l=first-1,r=last+1; l>=0&&r<=length-1; l--,r++){
    			if(chars[l]==chars[r]){
    				first=l;
    				last=r;
    			}
    			else{
    				break;
    			}
    		}
    		if((last-first+1)>re_length){
    			re_l=first;
    			re_r=last;
    			re_length=last-first+1;
    		}
    		
    		if(i+2<length){
        		if(chars[i]==chars[i+2]){
        			first=i;
        			last=i+2;
        		}
        	}
    		
    		for(int l=first-1,r=last+1; l>=0&&r<=length-1; l--,r++){
    			if(chars[l]==chars[r]){
    				first=l;
    				last=r;
    			}
    			else{
    				break;
    			}
    		}
    		if((last-first+1)>re_length){
    			re_l=first;
    			re_r=last;
    			re_length=last-first+1;
    		}
        }
        System.out.println(re_l);
        System.out.println(re_r);
        char[] re=new char[re_length];
        for(int i=0; i<re_length; i++){
        	re[i]=chars[i+re_l];
        }
        
        String re_s=String.valueOf(re);
        return re_s;
    }
	

    public static String convert(String s, int numRows) {
    	int n=s.length();
    	if(numRows==1||numRows>=n){
    		return s;
    	}
    	
    	else{
            int dk=numRows*2-2;
            char[] ss=s.toCharArray();
            char[] chars=new char[n];
            int k=0;
            for(int i=0; i<numRows; i++){
            	for(int j=i; j<n&&k<n; j+=dk){
            		chars[k++]=ss[j];

            		int temp=j+dk-2*i;
            		if(i>0&&i<numRows-1&&temp<n){	
            			chars[k++]=ss[temp];
            		}
            	}
            }
            String re=String.valueOf(chars);
        	return re;
    	}
        
    }

    public static int reverse(int x) {
        long re=0;
        while(x!=0){
        	re=re*10+x%10;
        	if(re>Integer.MAX_VALUE||re<Integer.MIN_VALUE){
        		re=0;
        		break;
        	}
        	x=x/10;
        }
        return (int)re;
       
    	
    }
	
    public static boolean isPalindrome(int x) {
    	if(x<10){
            if(x>=0){
                return true;
            }
            else{
                return false;
            }
        }
        long wei =1;
    	int temp=x;
    	while(x!=0){
    		x=x/10;
    		wei*=10;
    	}
    	wei=wei/10;
    	int high=(int)wei;
    	while(high>=10){
    		if(temp/high != temp%10){
    			return false;
    		}
    		temp=(temp%high)/10;
    		high/=100;
    	}
    	return true;
           	    	  
    }
	
    public static int maxArea(int[] height) {
        int re=0;
        int n=height.length;
        int l=0;
        int r=n-1;
        int temp=0;
        while(l!=r){
        	if(height[l]<height[r]){
        		temp=height[l]*(r-l);
        		l++;
        	}
        	else{
        		temp=height[r]*(r-l);
        		r--;
        	}
        	if(temp>re){
    			re=temp;
    		}
        	System.out.println(String.valueOf(1));
        }
        
        return re;
    }
    
    public static String intToRoman(int num) {
        String re="";
        int[] step={1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] add ={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int temp=0;
        
        for(int i=0;i<step.length;i++){
        	temp=num/step[i];
        	for(int j=0;j<temp;j++){
        		re+=add[i];
        	}
        	num=num%step[i];
        }
       
    	return re;
    }
    
    public static int romanToInt(String s) {
    	int re=0;
    	int o=0;
        int[] step={1000, 500, 100, 50, 10, 5, 1};
        char[] add ={'M','D','C','L','X','V','I'};
        HashMap<Character, Integer> map=new HashMap<Character, Integer>();
        for(int i=0;i<step.length;i++){
        	map.put(add[i], step[i]);
        }
        char[] chars=s.toCharArray();
        for(int i=chars.length-1; i>=0; i--){
        	int n=map.get(chars[i]);
        	System.out.println(n);
        	if(n>o){
        		re+=n;
        	}
        	else{
        		re-=n;
        	}
        	o=n;
        }
        
        
        return re;
    }
    
    public static boolean wordPattern(String pattern, String str) {
        char[] chars=pattern.toCharArray();
        String[] strs=str.split(" ");
        if(chars.length!=strs.length){
            return false;
        }
        HashMap<Character, String> map=new HashMap<Character, String>();
        Set set=new HashSet();
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
    public static boolean isValidSudoku(char[][] board) {
        Set<Character> set=new HashSet<Character>();
    	char s='s';
    	set.clear();
       
    	
    	return true;
    }
    
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
    
    public static List<Integer> findSubstring(String s, String[] words) {
        int step=words[0].length();
        int times=words.length;
    	int len=step*times;
    	List<Integer> re=new ArrayList<Integer>();
    	HashMap<String, Integer> map=new HashMap<String, Integer>();
    	for(int i=0; i<words.length; i++){
    		if(map.containsKey(words[i])){
    			map.put(words[i], map.get(words[i])+1);
    		}
    		else{
    			map.put(words[i],1);
    		}
    	}
    	
        int i=0;
        while((i+len)<=s.length()){
        	map.putAll(map);
        	int count=0;
        	for(int j=0; j<times; j++){
        		String nstr="";
        		int temp=i+j*step;
        		nstr=s.substring(temp, temp+step);
        		
        		if(!map.containsKey(nstr)){
        			break;
        		}
        		else{
        			count++;
        			if(map.get(nstr)>1){
        				map.put(nstr, map.get(nstr)-1);
        			}
        			else{
        				map.remove(nstr);
        			}
        		}
        	}
        	if(count==times){
        		re.add(i);
        	}
        	if(count>0){
        		map.clear();
        		for(int l=0; l<words.length; l++){
            		if(map.containsKey(words[l])){
            			map.put(words[l], map.get(words[l])+1);
            		}
            		else{
            			map.put(words[l],1);
            		}
            	}
        	}
        	i++;
        }
        
        return re;
    }
    public ListNode swapPairs(ListNode head) {
    	if(head==null||head.next==null){
        	return head;
        }
        ListNode re=new ListNode(0);
        ListNode loop=re;
        ListNode temp=head;
        ListNode tail=null;
        ListNode next=null;
        while(temp!=null&&temp.next!=null){  	
        	tail=temp.next;
            next=tail.next;            
            
            tail.next=temp;
            loop.next=tail;
            loop=temp;
           
        	
        	temp=next;
        	if(temp==null){
        		break;
        	}   	
        }
        if(temp!=null){
            loop.next=temp;
        }

        return re.next;
    }
    
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
    public static int removeDuplicates(int[] nums) {
        int len=nums.length;
        if(len<2){
        	return len;
        }
        for(int i=1; i<nums.length; i++){
        	if(nums[i]==nums[i-1]){
        		len--;
        	}
        }
        
        return len;
    }
    
  
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
    public static void moveZeroes(int[] nums) {
        if(nums.length<2){
        	System.out.println(Arrays.toString(nums));
        	return;
        }
        int i=0;
        while(i<nums.length){
        	if(nums[i]==0){
        		int count=1;
        		for(int j=i+1; j<nums.length; j++){
        			if(nums[j]==0){
        				count++;
        			}else{
        				nums[j-count]=nums[j];
        			}
        		}
        		for(int k=nums.length-count; k<nums.length; k++){
        			nums[k]=0;
        		}
        		System.out.println(Arrays.toString(nums));
        		return;
        	}
        	i++;
        }
    }
    public static class ListNode {
    	 int val;
    	 ListNode next;
    	 ListNode(int x) { val = x; }
    }
    
    public static ListNode reverseList_iterator(ListNode head) {
    	if(head==null||head.next==null){
            return head;
        }
    	ListNode nNode=head;
    	ListNode re=null;
    	while(nNode!=null){
    		ListNode pNode=new ListNode(nNode.val);
    		pNode.next=re;
    		re=pNode;
    		nNode=nNode.next;

    	}
    	
    	return re;
    }
    
    public static ListNode reverseList_recursion(ListNode head) {
    	/**
    	 * 迭代法
    	 * */
    	if(head==null||head.next==null){
            return head;
        }
    	
    	ListNode nNode=head.next;
    	ListNode re=reverseList_recursion(nNode);
    	
    	head.next=null;
    	nNode.next=head;
    	
    	return re;
    }
    
    public void deleteNode(ListNode node) {
    	
        node.val=node.next.val;
        node.next=node.next.next;
    }
    
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){
        	return false;
        }
    	ListNode slow=head;
    	ListNode fast=head;
    	while(fast!=null&&fast.next!=null){
    		slow=slow.next;
    		fast=fast.next.next;
    		if(slow==fast){
    			return true;
    		}
    	}
    	
    	return false;
    }
    public static ListNode removeElements(ListNode head, int val) {
    	ListNode re=new ListNode(0);
    	re.next=head;
    	ListNode lNode=re;
    	ListNode rNode=head;
    	
    	while(rNode!=null){
    		if(rNode.val==val){
    			rNode=rNode.next;
    			lNode.next=rNode;
    		}else{
    			lNode=rNode;
    			rNode=rNode.next;
    		}
    	}
    	
    	return re.next;
        
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String p="abba";
		String s="barfoothefoobarman";
		String[] ss=new String[]{"foo","bar"};
		int a=19;
		ListNode list=new ListNode(1);
		list.next=new ListNode(1);
		removeElements(list, 1);
		
		
		
	}

}
