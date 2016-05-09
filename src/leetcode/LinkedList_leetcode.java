package leetcode;
import java.util.Random;


public class LinkedList_leetcode {
	
	public static class ListNode {
		int val;
   	 	ListNode next;
   	 	ListNode(int x) { val = x; }
	}
	
	/*
     * 打印链表的方法
     */
    public static void printList(ListNode head){
    	if(head==null){
    		System.out.println("null");
    		return;
    	}
    	System.out.print(head.val);
    	ListNode pNode=head.next;
    	while(pNode!=null){
    		System.out.print("->"+pNode.val);
    		pNode=pNode.next;
    	}
    	System.out.println("");
    }
    /*
     * 随机生成链表的方法
     */
    public static ListNode listNodeGenerator(int n){
    	if(n<1){
    		return null;
    	}
    	Random rand=new Random(47);
    	ListNode head=null;
    	for(int i=0; i<n; i++){
    		ListNode pNode=new ListNode(rand.nextInt(100));
    		pNode.next=head;
    		head=pNode;
    	}
    	
    	return head;
    }
    
    /*
     * 根据数组生成链表
     */
    public static ListNode listNodeGenerator(int[] a){
    	if(a.length==0){
    		return null;
    	}
    	ListNode head=new ListNode(a[0]);
    	ListNode pNode=head;
    	for(int i=1; i<a.length; i++){
    		ListNode newNode=new ListNode(a[i]);
    		pNode.next=newNode;
    		pNode=newNode;
    	}
    	
    	return head;
    }
	
    //24. Swap Nodes in Pairs
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
    
    //206. Reverse Linked List
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
    
    //206. Reverse Linked List
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
    
    //237. Delete Node in a Linked List
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
    
    //141. Linked List Cycle
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
    
    //203. Remove Linked List Elements
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
    
    //19. Remove Nth Node From End of List
    public static ListNode removeNthFromEnd(ListNode head, int n) {
    	ListNode re=new ListNode(0);
        re.next=head;
        ListNode rNode=head;
        for(int i=1; i<n; i++){
        	rNode=rNode.next;
        }
        ListNode lNode=re;
        while(rNode.next!=null){
        	rNode=rNode.next;
        	lNode=lNode.next;
        }
        lNode.next=lNode.next.next;
        return re.next;
    }
    
    //83. Remove Duplicates from Sorted List
    public ListNode deleteDuplicates(ListNode head) {
    	if(head==null||head.next==null){
    		return head;
    	}
    	ListNode lNode, rNode;
    	lNode=head;
    	rNode=head.next;
    	
    	while(rNode!=null){
    		if(lNode.val==rNode.val){
    			rNode=rNode.next;
    			lNode.next=rNode;
    		}else{
    			lNode=rNode;
    			rNode=rNode.next;
    		}
    	}
    	return head;
    }
    
    //86. Partition List
    public static ListNode partition(ListNode head, int x) {
    	if(head==null||head.next==null){
    		return head;
    	}
    	ListNode re=new ListNode(1);
    	ListNode temp=new ListNode(1);
    
    	ListNode lNode=re;
    	ListNode rNode=temp;
    	while(head!=null){
    		if(head.val<x){
    			lNode.next=head;
    			head=head.next;
    			lNode=lNode.next;
    			lNode.next=null;//为什么一定要null呢？？？
    		} else{
    			rNode.next=head;
    			head=head.next;
    			rNode=rNode.next;
    			rNode.next=null;//
    		}
    	}
    	lNode.next=temp.next;
    	
    	return re.next;
    }
    
    //143. Reorder List
    public static void reorderList(ListNode head) {
        if(head==null||head.next==null){
        	return;
        }
        if(head.next.next==null){
        	return;
        }
    	int i=0;
    	ListNode pNode=head;
        while(pNode!=null){
        	i++;
        	pNode=pNode.next;
        }
        i=i/2;

        pNode=head;
        for(int j=1; j<i; j++){
        	pNode=pNode.next;
        }
        ListNode rNode=pNode.next;
        pNode.next=null;
        pNode=rNode.next;
        rNode.next=null;
        
        while(pNode!=null){
        	ListNode temp=pNode.next;
        	pNode.next=rNode;
        	rNode=pNode;
        	pNode=temp;
        }
        ListNode lNode=head;
        ListNode re=new ListNode(0);
        pNode=re;
        while(lNode!=null&&rNode!=null){
        	pNode.next=lNode;
        	pNode=rNode;
        	ListNode temp=lNode.next;
        	lNode.next=rNode;
        	rNode=rNode.next;
        	lNode=temp;

        }
    }
	
    /*
     * 按照网上的方法，修改分割两个数组的方法
     * 快慢两个指针，如果是奇数，前面数组比后面的大一个，所以后面要再加一个判断
     */
    //143. Reorder List
    public static void reorderList2(ListNode head) {
        if(head==null||head.next==null){
        	return;
        }
        if(head.next.next==null){
        	return;
        }
        
        ListNode pNode=head, qNode=head;
        while(qNode.next!=null){
        	qNode=qNode.next;
        	if(qNode.next==null){
        		break;
        	}
        	pNode=pNode.next;
        	qNode=qNode.next;
        }
        
        
        ListNode rNode=pNode.next;
        pNode.next=null;
        pNode=rNode.next;
        rNode.next=null;
        
        while(pNode!=null){
        	ListNode temp=pNode.next;
        	pNode.next=rNode;
        	rNode=pNode;
        	pNode=temp;
        }
        ListNode lNode=head;
        printList(rNode);
        printList(lNode);
        ListNode re=new ListNode(0);
        pNode=re;
        while(lNode!=null&&rNode!=null){
        	pNode.next=lNode;
        	pNode=rNode;
        	ListNode temp=lNode.next;
        	lNode.next=rNode;
        	rNode=rNode.next;
        	lNode=temp;
        }
        if(lNode!=null){
        	pNode.next=lNode;
        }
    }
    
    //92. Reverse Linked List II
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==n){
        	return head;
        }
        ListNode re=new ListNode(0);
        re.next=head;
        int i;
        ListNode pNode=re;
        for(i=1; i<m; i++){
        	pNode=pNode.next;
        }
        ListNode lNode=pNode;
        ListNode rNode=pNode.next;
        ListNode qNode=rNode;
        ListNode temp;
        pNode=rNode.next;
        for(i=m; i<n; i++){
        	
        	temp=pNode.next;
        	pNode.next=rNode;
        	rNode=pNode;
        	pNode=temp;
        }
        qNode.next=pNode;
        lNode.next=rNode;
    	return re.next;
    }
    
    //160. Intersection of Two Linked Lists
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){
        	return null;
        }
        if(headA==headB){
        	return headA;
        }
        ListNode pNode=headA;
        int i=1, j=1;
        while(pNode.next!=null){
        	pNode=pNode.next;
        	i++;
        }
        pNode=headB;
        while(pNode.next!=null){
        	pNode=pNode.next;
        	j++;
        }
        ListNode slow, fast;
        if(i==j){
        	slow=headA;
        	fast=headB;
        }else if(i<j){
        	pNode=headB;
        	for(int k=i; k<j; k++){
        		pNode=pNode.next;
        	}
        	slow=headA;
        	fast=pNode;
        }else{
        	pNode=headA;
        	for(int k=j; k<i; k++){
        		pNode=pNode.next;
        	}
        	slow=headB;
        	fast=pNode;
        }  
    	while(fast!=null){
    		if(fast==slow){
    			return fast;
    		}
    		fast=fast.next;
    		slow=slow.next;
    	}
    	return null;
    }
    
    //82. Remove Duplicates from Sorted List II
    public static ListNode deleteDuplicates2(ListNode head) {
        if(head==null||head.next==null){
        	return head;
        }
        ListNode re=new ListNode(0);
        re.next=head;
        ListNode lNode=re, rNode=head;
    	while(rNode!=null&&rNode.next!=null){
    		int val=rNode.val;
    		
    		if(rNode.next.val==val){
    			rNode=rNode.next;
        		while(rNode!=null&&rNode.val==val){
        			rNode=rNode.next;
        		}
        		lNode.next=rNode;
    		}else{
    			lNode=lNode.next;
        		rNode=rNode.next;
    		}
    		
    	}
    	return re.next;
    }
    
    //234. Palindrome Linked List
    public static boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null){
        	return true;
        }
    	ListNode lNode=head, rNode=head.next;
    	while(rNode!=null){
    		rNode=rNode.next;
    		if(rNode!=null){
    			rNode=rNode.next;
    			lNode=lNode.next;
    		}
    	}
        rNode=lNode.next;
        lNode.next=null;
        lNode=head;
    	ListNode pNode=rNode.next;
    	rNode.next=null;
        while(pNode!=null){//反转链表
        	ListNode temp=pNode.next;
        	pNode.next=rNode;
        	rNode=pNode;
        	pNode=temp;
        }
        while(rNode!=null){
        	if(rNode.val!=lNode.val){
        		return false;
        	}
        	rNode=rNode.next;
        	lNode=lNode.next;
        }
    	
    	return true;
    }
    
    //21. Merge Two Sorted Lists
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode re=new ListNode(0);
    	ListNode pNode=re;
    	while(l1!=null&&l2!=null){
    		if(l1.val<l2.val){
    			pNode.next=l1;
    			pNode=pNode.next;
    			l1=l1.next;
    		}else{
    			pNode.next=l2;
    			pNode=pNode.next;
    			l2=l2.next;
    		}
    	}
    	if(l1!=null){
    		pNode.next=l1;
    	}
    	if(l2!=null){
    		pNode.next=l2;
    	}
    	
    	return re.next;
    }
    
    //23. Merge k Sorted Lists
    public static ListNode mergeKLists(ListNode[] lists) {
    	int len=lists.length;
    	if(len==0){
    		return null;
    	}
    	if(len==1){
    		return lists[0];
    	}
    	int step=1;
    	while(step<len){
    		for(int i=0; i<len; i+=step*2){
    			if(i+step<len){
    				lists[i]=mergeTwoLists(lists[i], lists[i+step]);
    			}
    		}
    		step=step*2;
    	}    	
    	return lists[0];
    }
    
    //148. Sort List
    private static ListNode getMid(ListNode head){ 
        ListNode pNode=head, qNode=head;
        while(qNode.next!=null){
        	qNode=qNode.next;
        	if(qNode.next==null){
        		break;
        	}
        	pNode=pNode.next;
        	qNode=qNode.next;
        }
        qNode=pNode.next;
        pNode.next=null;
    	return qNode;
    }
    public ListNode sortList(ListNode head) {
    	if(head==null||head.next==null){
    		return head;
    	}
    	ListNode head1=head;
    	ListNode head2=getMid(head);
    	head1=sortList(head1);
    	head2=sortList(head2);
    	return mergeTwoLists(head1, head2);
    }
    
    //2. Add Two Numbers
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	ListNode re=new ListNode(0);
    	ListNode pNode=re;
    	int flag=0;
    	while(l1!=null&&l2!=null){
    		int sum=l1.val+l2.val+flag;
    		if(sum>=10){
    			sum%=10;
    			flag=1;
    		}else{
    			flag=0;
    		}
    		pNode.next=new ListNode(sum);
    		pNode=pNode.next;
    		l1=l1.next;
    		l2=l2.next;
    	}
    	while(l1!=null){
    		int sum=l1.val+flag;
    		if(sum>=10){
    			sum%=10;
    			flag=1;
    		}else{
    			flag=0;
    		}
    		pNode.next=new ListNode(sum);
    		pNode=pNode.next;
    		l1=l1.next;
    	}
    	while(l2!=null){
    		int sum=l2.val+flag;
    		if(sum>=10){
    			sum%=10;
    			flag=1;
    		}else{
    			flag=0;
    		}
    		pNode.next=new ListNode(sum);
    		pNode=pNode.next;
    		l2=l2.next;
    	}
    	if(flag==1){
    		pNode.next=new ListNode(1);
    	}
		return re.next;
    }
    
    //328. Odd Even Linked List
    public static ListNode oddEvenList(ListNode head) {
        ListNode re=new ListNode(-1);
        ListNode odd=re;
        ListNode evenHead=new ListNode(0);
        ListNode even=evenHead;
        ListNode pNode=head;
        int i=1;
        while(pNode!=null){
        	ListNode next=pNode.next;
        	pNode.next=null;
        	if((i++&1)==1){
        		odd.next=pNode;
        		odd=pNode;
        	}else{
        		even.next=pNode;
        		even=pNode;
        	}        	
        	pNode=next;
        }
        odd.next=evenHead.next;
        return re.next;
    }
    
    
	public static void main(String[] args) {
		int[] aa={2,1,4,3,6,5,7,8};
//		int[] bb={5,6,4};
		ListNode list1=listNodeGenerator(aa);
//		ListNode list2=listNodeGenerator(bb);
		printList(oddEvenList(list1));
		
	}

}
