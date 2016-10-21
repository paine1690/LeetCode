package codingInterviews;

import java.util.Arrays;

/**
 * 		第三章 高质量的代码
 * 
 * 规范性
 * 完整性    测试样例：功能测试，边界测试，负面测试
 * 鲁棒性    链表或指针  多考虑null
 * @author Paine
 *
 */
public class Chapter3 {	
	/*
	 * 11、数值的整数次方
	 */	
	public static double pow(double x, int n){
		if(n<0){
			if(n==Integer.MIN_VALUE){
				return 1/pow(x, Integer.MAX_VALUE)*x;
			}else{
				return 1/pow(x, -n);
			}
		}
		if(n==0){
			return 1.0;
		}
		double re=1.0;
		while(n!=0){
			if((n&1)==1){
				re*=x;
			}
			x*=x;
			n>>=1;
		}
		return re;		
	}
	
	
	/*
	 * 12、 打印从1到最大的n位数
	 * 注意数字溢出  数组 回溯
	 */
	private static void printNums(int[] nums){
		int i=0;
		while(i<nums.length&&nums[i]==0){
			i++;
		}
		StringBuilder s=new StringBuilder();
		for(; i<nums.length; i++){
			s.append(nums[i]);
		}		
		System.out.println(s.toString());
	}
	
	private static void dfsNums(int[] nums, int index){
		if(index==nums.length){
			printNums(nums);
			return;
		}
		for(int i=0; i<10; i++){
			nums[index]=i;
			dfsNums(nums, index+1);
		}		
	}
	
	public static void printNums(int n){
		int[] nums=new int[n];
		dfsNums(nums, 0);
		
	}
	
	/*
	 * 13、O(1)时间删除链表结点
	 * 
	 * leetcode 237. Delete Node in a Linked List
	 */
	
	
	/*
	 * 14、 调整数组顺序使奇数位于偶数前面
	 */
	public static void adjust(int[] nums){
		int i=0, j=nums.length-1;
		while(i<=j){
			if((nums[i]&1)==1){
				i++;
				continue;
			}
			if((nums[j]&1)!=1){
				j--;
				continue;
			}
			int temp=nums[i];
			nums[i]=nums[j];
			nums[j]=temp;
			i++;
			j--;
		}
		System.out.println(Arrays.toString(nums));
	}
	
	public static void reOrderArray(int [] array) {
		int[] nums=new int[array.length];
		int c=0;
		for(int i=0; i<array.length; i++){
			if((array[i]&1)==1){
				nums[c++]=array[i];
			}
		}
		for(int i=0; i<array.length; i++){
			if((array[i]&1)==0){
				nums[c++]=array[i];
			}
		}
		for(int i=0; i<nums.length; i++){
			array[i]=nums[i];
		}
	}
	
	/*
	 * 15、链表中倒数第k个结点
	 * 注意边界条件
	 */
	public static ListNode findKthToTail(ListNode head, int k){
		if(head==null||k==0){
			return null;
		}
		ListNode pNode=head;
		while(--k>0){
			pNode=pNode.next;
			if(pNode==null){
				return null;
			}
		}
		
		ListNode re=head;
		while(pNode.next!=null){
			pNode=pNode.next;
			re=re.next;
		}
		return re;		
	}
	
	/*
	 * 16、反转链表
	 */
	public static ListNode reverseList(ListNode head){
		if(head==null){
			return head;
		}
		ListNode pNode=head, re=null;
		while(pNode!=null){
			ListNode next=pNode.next;
			pNode.next=re;
			re=pNode;
			pNode=next;
		}
		return re;		
	}
	
	/*
	 * 17、合并两个排序的链表
	 */
	public static ListNode merge(ListNode list1, ListNode list2){
		ListNode re=new ListNode(-1);
		ListNode pNode=re;
		while(list1!=null&&list2!=null){
			if(list1.val<=list2.val){
				pNode.next=list1;
				pNode=list1;
				list1=list1.next;				
			}else{
				pNode.next=list2;
				pNode=list2;
				list2=list2.next;
			}
		}
		if(list1!=null){
			pNode.next=list1;
		}
		if(list2!=null){
			pNode.next=list2;
		}
		
		return re.next;
	}
	
	/*
	 * 18、树的子结构
	 */
	private static boolean isSame(TreeNode root1, TreeNode root2){
		if(root2==null){
			return true;
		}
		if(root1==null){
			return false;
		}
		if(root1.val==root2.val){
			return isSame(root1.left, root2.left)&&isSame(root1.right, root2.right);
		}
		return false;		
	}
	
	public static boolean subTree(TreeNode root1, TreeNode root2){
		if(root1==null||root2==null){
			return false;
		}
		if(root1.val==root2.val&&isSame(root1, root2)){
			return true;
		}
		return subTree(root1.left, root2)||subTree(root1.right, root2);
	}
	
	
	
	public static void main(String[] args) {
		
//		reOrderArray(nums);
//		System.out.println(Arrays.toString(nums));
	}

}

class ListNode{
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}











