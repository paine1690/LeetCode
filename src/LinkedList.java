import java.util.Random;


public class LinkedList {
	
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
        	for(int k=i; i<j; i++){
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
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] aa={1,1,1,1,2,3};
		ListNode list=listNodeGenerator(aa);
		printList(deleteDuplicates2(list));
		
	}

}
