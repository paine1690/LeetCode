package leetcode;
import java.util.Arrays;
import java.util.Comparator;

class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) { val = x; }
}

public class Sort {
	
	
	
	//O(logn)
	public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len=citations.length;
        int i=0;
        for(i=1; i<=len; i++){
        	if(citations[len-i]<i){
        		break;
        	}
        }
		return i-1;
    }
	//O(n)
	public static int hIndex2(int[] citations) {
		int len=citations.length;
		int[] B=new int[len+1];
		for(int i=0; i<len; i++){
			if(citations[i]>len){
				B[len]++;
			}else{
				B[citations[i]]++;
			}
		}
		for(int i=len-1; i>=0; i--){
			B[i]=B[i]+B[i+1];
		}
		for(int i=len; i>=0; i--){
			if(B[i]>=i){
				return i;
			}
		}		
		return 0;
	}
	
	//147. Insertion Sort List
    public ListNode insertionSortList(ListNode head) {
    	ListNode node=head;
        while(node!=null){
        	ListNode lNode=head;
        	int x=node.val;
        	while(lNode!=node){
        		if(lNode.val<=x){
        			lNode=lNode.next;
        		}else{
        			int temp=x;
        			x=lNode.val;
        			lNode.val=temp;
        		}  
        	}
        	node.val=x;        	
        	node=node.next;
        }
    	return head;
    }
    
    //147. Insertion Sort List
    public static ListNode insertionSortList2(ListNode head){
    	ListNode re=new ListNode(-1);
    	ListNode node=head;
    	while(node!=null){
    		ListNode nextNode=node.next;
    		node.next=null;
    		ListNode lNode=re;
    		while(lNode.next!=null&&lNode.next.val<=node.val){
    			lNode=lNode.next;
    		}
    		node.next=lNode.next;
    		lNode.next=node;
    		node=nextNode;
    	}
    	return re.next;
    }
    
    //347. Top K Frequent Elements
    
    //179. Largest Number    
    public static String largestNumber(int[] nums) {
    	
    	String[] strs=new String[nums.length];
    	for(int i=0; i<nums.length; i++){
    		strs[i]=String.valueOf(nums[i]);
    	}
    	
    	Arrays.sort(strs, new Comparator<String>(){
			public int compare(String a, String b) {
				String left=a+b;
				String right=b+a;
				
				return -left.compareTo(right);
			}
    	});
    	
    	StringBuilder s=new StringBuilder();
    	for(int i=0; i<strs.length; i++){
    		s.append(strs[i]);
    	}
    	
    	while(s.charAt(0)=='0'&&s.length()>1){
    		s.deleteCharAt(0);
    	}
    	
    	return s.toString();
    }
    
    //475. Heaters
    public int findRadius(int[] houses, int[] heaters) {
        int re=Integer.MIN_VALUE;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int j=0;
        for(int i=0; i<houses.length; i++){
        	while(j<heaters.length-1&&Math.abs(houses[i]-heaters[j])>Math.abs(houses[i]-heaters[j+1])){
        		j++;
        	}
        	re=Math.max(re, Math.abs(houses[i]-heaters[j]));
        }        
        return re;
    }
    
    //148. Sort List 链表排序
    private void swap(ListNode i, ListNode j){
    	int temp=i.val;
    	i.val=j.val;
    	j.val=temp;
    }    
    
    private ListNode partition(ListNode start, ListNode end){
    	int x=start.val;
    	ListNode i=start, j=start.next;
    	
    	while(j!=end){
    		if(j.val<x){
    			i=i.next;
    			swap(i, j);
    		}
    		j=j.next;
    	}
    	swap(start, i);
    	return i;
    }
    
    ListNode quickSort(ListNode start, ListNode end){
    	if(start!=end){
    		ListNode mid=partition(start, end);
    		quickSort(start, mid);
    		quickSort(mid.next, end);
    	}
    	return start;
    }
    
    public ListNode sortList(ListNode head) {
        return quickSort(head, null);
    }
    
    //506. Relative Ranks    
    private static void merge(int[] nums, int[] pos, int start, int mid, int end){
    	int[] temp=new int[end-start+1];
    	int i=start, j=mid+1, k=0;
    	
    	while(i<=mid&&j<=end){
    		if(nums[pos[i]]<=nums[pos[j]]){
    			temp[k++]=pos[i++];
    		}else{
    			temp[k++]=pos[j++];
    		}
    	}
    	while(i<=mid){
    		temp[k++]=pos[i++];
    	}
    	while(j<=end){
    		temp[k++]=pos[j++];
    	}
    	for(k=0; k<temp.length; k++){
    		pos[start+k]=temp[k];
    	}
    }
    
    private static void sort(int[] nums, int[] pos, int start, int end){
    	if(start<end){
    		int mid=start+(end-start)/2;
    		sort(nums, pos, start, mid);
    		sort(nums, pos, mid+1, end);
    		merge(nums, pos, start, mid, end);
    	}
    }
    
    public static String[] findRelativeRanks(int[] nums) {
    	int len=nums.length;
        String[] re=new String[len];
        int[] pos=new int[len];
        for(int i=0; i<pos.length; i++){
        	pos[i]=i;
        }
    	sort(nums, pos, 0, nums.length-1);   
    	int i=0, j=pos.length-1;
    	while(i<j){
    		int tmep=pos[i];
    		pos[i]=pos[j];
    		pos[j]=tmep;
    		i++;
    		j--;
    	}
    	String[] an=new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
    	for(i=0; i<3&&i<pos.length; i++){
    		re[pos[i]]=an[i];
    	}
    	for(i=3; i<pos.length; i++){
    		re[pos[i]]=String.valueOf(i+1);
    	}
    	return re;
    }
    
	public static void main(String[] args) {
		System.out.println(Arrays.toString(findRelativeRanks(new int[]{4,6,5,3,9})));
//		int[] nums={3, 0, 6, 1, 5};
//		System.out.println(hIndex2(nums));
	}

}
