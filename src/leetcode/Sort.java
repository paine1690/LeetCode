package leetcode;
import java.util.Arrays;

public class Sort {
	
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	
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
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={3, 0, 6, 1, 5};
		System.out.println(hIndex2(nums));
	}

}
