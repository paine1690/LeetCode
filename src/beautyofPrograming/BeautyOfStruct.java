package beautyofPrograming;

import java.util.ArrayList;
import java.util.List;

/**
 * 		第三章 结构之法 -字符串及链表的探索
 * @author Paine
 *
 */
 
 
public class BeautyOfStruct {

	/*
	 * 3.1 字符串移位包含  
	 */
	private static int[] getNext(String P){
		int m=P.length();
		int[] next=new int[m+1];
		next[0]=next[1]=0;
		int j=0;
		for(int i=1; i<m; i++){
			while(j>0&&P.charAt(i)!=P.charAt(j)){
				j=next[j];
			}
			if(P.charAt(i)==P.charAt(j)){
				j++;
			}
			next[i+1]=j;
		}		
		return next;
	}
	private static boolean kmp(String T, String P){
		int[] next=getNext(P);
		int j=0;
		for(int i=0; i<T.length(); i++){
			while(j>0&&T.charAt(i)!=P.charAt(j)){
				j=next[j];
			}
			if(T.charAt(i)==P.charAt(j)){
				j++;
			}
			if(j==P.length()){
				return true;
			}
		}
		return false;
	}
	public static boolean isIn(String s1, String s2){
		s1+=s1.substring(0, s2.length()-1);
		System.out.println(s1);
		return kmp(s1, s2);
	}
	
	
	/*
	 * 3.2 电话号码与对应英语单词
	 */
	//leetcode 17. Letter Combinations of a Phone Number.
    static String[] number=new String[]{
    		"",
    		"",
    		"abc",
    		"def",
    		"ghi",
    		"jkl",
    		"mno",
    		"pqrs",
    		"tuv",
    		"wxyz",
    };
    static int[] total=new int[]{0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
    
    //循环求法
    public static List<String> letterCombinations(String digits) {
    	List<String> re=new ArrayList<String>();
        int len=digits.length();
        if(len==0){
        	return re;
        }
        int[] answer=new int[len];          
        
        while(true){
        	StringBuilder s=new StringBuilder();
        	for(int i=0; i<len; i++){
        		s.append(number[digits.charAt(i)-'0'].charAt(answer[i]));
        	}
        	re.add(s.toString());
        	int k=len-1;
        	while(k>=0){
        		if(answer[k]<total[digits.charAt(k)-'0']-1){
        			answer[k]++;
        			break;
        		}else{
        			answer[k]=0;
        			k--;
        		}
        	}      	
        	if(k<0){
        		break;
        	}
        }    
    	return re;
    }
    
    //递归求法
    public static List<String> letterCombinations2(String digits) {
    	int len=digits.length();
    	List<String> re=new ArrayList<String>();
    	if(len==0){
    		return re;
    	}    	
    	char[] chars=new char[len];
    	dfsGet(digits, 0, chars, re);
    	return re;    	
    }
    
    private static void dfsGet(String dig, int index, char[] chars, List<String> re){
    	if(index==dig.length()){
    		re.add(new String(chars));
    		return;
    	}
    	int num=dig.charAt(index)-'0';
    	for(int i=0; i<total[num]; i++){
    		chars[index]=number[num].charAt(i);
    		dfsGet(dig, index+1, chars, re);
    	}
    }	
	
    /*
     * 3.3 计算字符串相似度
     */
    //递归实现
    public static int calDis(String a, String b){
    	return calStringDistance(a, 0, a.length()-1, b, 0, b.length()-1);
    }
	private static int calStringDistance(String a, int aStart, int aEnd, String b, int bStart, int bEnd){
		if(aStart>aEnd){
			if(bStart>bEnd){
				return 0;
			}else{
				return bEnd-bStart+1;
			}
		}
		if(bStart>bEnd){
			if(aStart>aEnd){
				return 0;
			}else{
				return aEnd-aStart+1;
			}
		}
		
		if(a.charAt(aStart)==b.charAt(bStart)){
			return calStringDistance(a, aStart+1, aEnd, b, bStart+1, bEnd);
		}else{
			int t1=calStringDistance(a, aStart+1, aEnd, b, bStart, bEnd);
			int t2=calStringDistance(a, aStart, aEnd, b, bStart+1, bEnd);
			int t3=calStringDistance(a, aStart+1, aEnd, b, bStart+1, bEnd);
			return Math.min(t1, Math.min(t2, t3))+1;
		}
	}
    //非递归实现-动态规划
	public static int calDis2(String a, String b){
		int m=a.length();
		int n=b.length();
		int[][] dp=new int[m+1][n+1];
		for(int j=1; j<n+1; j++){
			dp[0][j]=j;
		}
		for(int i=1; i<m+1; i++){
			dp[i][0]=i;
		}
		for(int i=1; i<m+1; i++){
			for(int j=1; j<n+1; j++){
				if(a.charAt(i-1)==b.charAt(j-1)){
					dp[i][j]=dp[i-1][j-1];
				}else{
					dp[i][j]=Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
				}				
			}
		}
		return dp[m][n];
	}
	
	
    
    /*
     * 3.4 从无头链表删除节点
     */
	public void deledeNode(ListNode node){
		node.val=node.next.val;
		node.next=node.next.next;
	}
	//反转链表 206. Reverse Linked List
    public static ListNode reverseList_iterator(ListNode head) {
    	if(head==null||head.next==null){
            return head;
        }
    	ListNode pNode=head;
    	ListNode re=null;
    	while(pNode!=null){
    		ListNode temp=pNode.next;
    		pNode.next=re;
    		re=pNode;
    		pNode=temp;
    	}
    	return re;
    }
    
    public static ListNode reverseList_recursion(ListNode head) {
    	if(head==null||head.next==null){
            return head;
        }
    	
    	ListNode nNode=head.next;
    	ListNode re=reverseList_recursion(nNode);
    	
    	head.next=null;
    	nNode.next=head;
    	
    	return re;
    }
	
	
	public static void main(String[] args) {
		System.out.println(calDis2("SNOWY", "SUNNY"));
		System.out.println(calDis2("a", "b"));
		System.out.println(calDis2("abdd", "aebdd"));
		System.out.println(calDis2("travelling", "traveling"));

	}
}

class ListNode{
	int val;
	ListNode next;
	public ListNode(int val){
		this.val=val;
	}
}
