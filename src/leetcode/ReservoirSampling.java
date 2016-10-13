package leetcode;

import java.util.Random;

/**
 * 蓄水池取样问题
 * @author Paine
 *
 */
public class ReservoirSampling {

	
	//382. Linked List Random Node
	ListNode head;
	
    public ReservoirSampling(ListNode head) {
        this.head=head;        
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
    	ListNode head=this.head;
    	Random r=new Random();
    	int re=head.val;
    	int i=1;
        while(head.next!=null){
        	head=head.next;        	
        	int m=r.nextInt(i+1);
        	if(m==0){
        		re=head.val;
        	}
        	i++;
        }
        return re;
    }
    
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
