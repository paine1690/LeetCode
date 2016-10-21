package codingInterviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Chapter2 {

	
	/*
	 * 3、二维数组中的查找
	 */
    public boolean Find(int [][] array,int target) {
    	if(array.length==0||array[0].length==0){
    		return false;
    	}
    	int m=array.length, n=array[0].length;
    	int i=0, j=n-1;
    	while(i<m&&j>=0){
    		int num=array[i][j];
    		if(num==target){
    			return true;
    		}
    		if(num>target){
    			j--;
    		}else{
    			i++;
    		}
    	}
    	return false;
    }
	
	/*
	 * 4、替换空格
	 */
    public String replaceSpace(StringBuffer str) {
    	StringBuilder s=new StringBuilder();
    	for(int i=0; i<str.length(); i++){
    		if(str.charAt(i)==' '){
    			s.append("%20");
    		}else{
    			s.append(str.charAt(i));
    		}
    	}
    	return s.toString();	
    }
    
    /*
     * 5、从尾到头打印链表
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> re=new ArrayList<Integer>();
        ListNode pNode=listNode;
        while(pNode!=null){
        	re.add(pNode.val);
        	pNode=pNode.next;
        }
        Collections.reverse(re);
        return re;
    }
    
    /*
     * 6、重建二叉树
     */
    private int find(int[] nums, int start, int target){
    	for(int i=start; i<nums.length; i++){
    		if(nums[i]==target){
    			return i;
    		}
    	}
    	return -1;
    }
    
    private TreeNode build(int[] pre, int index, int[] in, int start, int end){
    	TreeNode root=null;
    	if(start<=end){
    		root=new TreeNode(pre[index]);
    		if(start!=end){
    			int mid=find(in, start, pre[index]);
    	    	root.left=build(pre, index+1, in, start, mid-1);
    	    	root.right=build(pre, index+mid-start+1, in, mid+1, end);   	
    		}
    	}    	
    	return root;
    }
    
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode re=null;
        re=build(pre, 0, in, 0, in.length-1);   
    	return re;
    }
    
    /*
     * 7、用两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
    	if(stack2.isEmpty()){
    		while(!stack1.isEmpty()){
    			stack2.push(stack1.pop());
    		}
    	}
    	return stack2.pop();
    }
    
    /*
     * 8、旋转数组的最小数字
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length==0){
        	return 0;
        }
        int i=0, j=array.length-1;
        while(i<j){
        	int mid=i+(j-i)/2;
        	if(array[mid]>array[j]){
        		i=mid+1;
        	}else{
        		j=mid;
        	}
        }
        return array[i];
    }
    
    /*
     * 9、斐波那契数列
     */
    public int Fibonacci(int n) {
    	if(n<=1){
    		return n;
    	}
    	int[] fib=new int[n+1];
    	fib[1]=1;
    	for(int i=2; i<fib.length; i++){
    		fib[i]=fib[i-1]+fib[i-2];
    	}
    	return fib[n];
    }
    
    //跳台阶
    public int JumpFloor(int target) {
    	return Fibonacci(target+1);
    }
    
    //变态跳台阶
    public int JumpFloorII(int target) {
    	if(target<=2){
    		return target;
    	}
        int[] d=new int[target+1];
        d[1]=1;
        d[2]=2;
        for(int i=3; i<=target; i++){
        	int temp=0;
        	for(int j=1; j<i; j++){
        		temp+=d[j];
        	}
        	d[i]=temp+1;
        }
        return d[target];
    }
    
    /*
     * 10、二进制中1的个数
     */
    public int NumberOf1(int n) {
    	int re=0;
    	while(n!=0){
    		re++;
    		n=(n-1)&n;
    	}
    	return re;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
