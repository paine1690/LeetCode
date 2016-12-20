package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Stack_leetcode {
	
	//232. Implement Queue using Stacks
	class MyQueue {
		Stack<Integer> value=new Stack<Integer>();
		Stack<Integer> temp=new Stack<Integer>();
		
	    // Push element x to the back of queue.
	    public void push(int x) {
	        if(value.isEmpty()){
	        	value.push(x);
	        }else{
	        	while(!value.isEmpty()){
	        		temp.push(value.pop());
	        	}
	        	value.push(x);
	        	while(!temp.isEmpty()){
	        		value.push(temp.pop());
	        	}
	        }
	    }

	    // Removes the element from in front of queue.
	    public void pop() {
	        value.pop();
	    }

	    // Get the front element.
	    public int peek() {
	        return value.peek();
	    }

	    // Return whether the queue is empty.
	    public boolean empty() {
	        return value.isEmpty();
	    }
	}
	
	//20. Valid Parentheses
	private static boolean isEqual(char a, char b){
		return (((a=='(')&&(b==')'))||((a=='{')&&(b=='}'))||((a=='[')&&(b==']')));
	}
	
    public static boolean isValid(String s) {
    	Stack<Character> stack=new Stack<Character>();
    	for(int i=0; i<s.length(); i++){
    		char temp=s.charAt(i);
    		if(temp=='('||temp=='{'||temp=='['){
    			stack.push(temp);
    		}else{
    			if(stack.isEmpty()||!isEqual(stack.peek(), temp)){
    				return false;
    			}else{
    				stack.pop();
    			}
    		}
    	}
        return stack.isEmpty();
    }
    
    //155. Min Stack
    class MinStack {
    	Stack<Integer> stack=new Stack<Integer>();
    	Stack<Integer> minStack=new Stack<Integer>();
    	int min=0;
        public void push(int x) {
        	if(minStack.isEmpty()||x<=minStack.peek()){
        		minStack.push(x);
        	}
            stack.push(x);
        }

        public void pop() {
            if(minStack.peek().equals(stack.pop())){
            	minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
    
    //225. Implement Stack using Queues
    class MyStack {
        // Push element x onto stack.
    	Queue<Integer> value=new java.util.LinkedList<Integer>();
    	Queue<Integer> temp=new java.util.LinkedList<Integer>();
    	
        public void push(int x) {
        	if(value.isEmpty()){
        		value.offer(x);
        	}else{
        		temp.offer(x);
        		while(!value.isEmpty()){
        			temp.offer(value.poll());
        		}
        		while(!temp.isEmpty()){
        			value.offer(temp.poll());
        		}
        	}
        }

        // Removes the element on top of the stack.
        public void pop() {
            value.poll();
        }

        // Get the top element.
        public int top() {
            return value.peek();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return value.isEmpty();
        }
    }
    
    //84. Largest Rectangle in Histogram
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack=new Stack<Integer>();
    	int re=0;
        
    	for(int i=0; i<heights.length; i++){
    		int num=heights[i];
    		if(stack.isEmpty()||num>=stack.peek()){
    			stack.push(num);
    		}else{
    			int count=0;
    			while(!stack.isEmpty()&&num<stack.peek()){
    				count++;
    				re=Math.max(re, stack.pop()*count);
    			}
    			while(count>0){
    				stack.push(num);
    				count--;
    			}
    			stack.push(num);
    		}
    	}
    	int count=0;
        while(!stack.isEmpty()){
        	count++;
        	re=Math.max(re, stack.pop()*count);
        }
        return re;
    }
    
    public static int largestRectangleArea2(int[] heights) {
    	Stack<Integer> stack=new Stack<Integer>();
    	int re=0;
    	for(int i=0; i<heights.length; i++){
    		if(stack.isEmpty()||heights[i]>=heights[stack.peek()]){
    			stack.push(i);
    		}else{
    			while(!stack.isEmpty()&&heights[i]<heights[stack.peek()]){
    				int pop=stack.pop();
    				int width=stack.isEmpty()? i: i-stack.peek()-1;
    				re=Math.max(re, width*heights[pop]);
    			}
    			stack.push(i);
    		}
    	}
    	while(!stack.isEmpty()){
    		int pop=stack.pop();
    		int width=stack.isEmpty()? heights.length: heights.length-stack.peek()-1;
    		re=Math.max(re, width*heights[pop]);
    	}
    	return re;
    }
    
    //85. Maximal Rectangle
    private static int[][] pre(char[][] matrix){
    	int m=matrix.length;
    	int n=matrix[0].length;
    	int[][] re=new int[m][n];
    	for(int j=0; j<n; j++){
    		re[0][j]=matrix[0][j]-'0';
    	}
    	for(int i=1; i<m; i++){
    		for(int j=0; j<n; j++){
    			if(matrix[i][j]=='1'){
    				re[i][j]=re[i-1][j]+1;
    			}else{
    				re[i][j]=0;
    			}
    		}
    	}    	
    	return re;
    }
    
    public static int maximalRectangle(char[][] matrix) {
    	if(matrix.length==0||matrix[0].length==0){
    		return 0;
    	}
    	int re=0, count=0;
        int[][] nums=pre(matrix);
    	Stack<Integer> stack=new Stack<Integer>();
    	for(int i=0; i<nums.length; i++){
    		int[] heights=nums[i];
    		for(int j=0; j<heights.length; j++){
    			int num=heights[j];
    			count=0;
    			if(stack.isEmpty()||num>=stack.peek()){
    				stack.push(num);
    			}else{
    				while(!stack.isEmpty()&&num<stack.peek()){
    					count++;
    					re=Math.max(re, count*stack.pop());
    				}
    				while(count>=0){
    					stack.push(num);
    					count--;
    				}
    			}
    		}
    		count=0;
    		while(!stack.isEmpty()){
    			count++;
    			re=Math.max(re, count*stack.pop());
    		}
    		
    	}
    	return re;
    }
    
    
    public static int getLuckyPrice(int price, List<Integer> unluckNumbers) {
        Set<Integer> set=new HashSet<Integer>(unluckNumbers);
    	int re=0;
    	while(price!=0){
    		int temp=price%10;
    		System.out.println(temp);
    		while(temp==0||set.contains(temp)){
    			temp++;
    		}
    		System.out.println("temp"+temp);
    		re=re*10+temp;
    		price/=10;
    	}
    	return re;
    }
    
    
    public static int getLongestLength(List<Integer> array) {
        int len=array.size();
        int[] dp=new int[len];
        for(int i=1; i<array.size(); i++){
        	for(int k=dp[i-1]; k>=0; k--){
        		int j=i-dp[i-k]*2-1;
        		if(j>=0&&array.get(i)+array.get(j)==0){
        			dp[i]=dp[i-k]+1;
        			break;
        		}
        	}
        }
        int re=0; 
        System.out.println(Arrays.toString(dp));
        for(int i=dp.length-1; i>=0; i--){
        	dp[i]+=dp[i-dp[i]];
        }
        System.out.println(Arrays.toString(dp));
        for(int i=0; i<dp.length; i++){
        	re=Math.max(re, dp[i]);
        }
        return re*2;
    }
    
    //456. 132 Pattern
    public boolean find132pattern(int[] nums) {
    	Stack<Integer> stack=new Stack<Integer>();
    	int pre=Integer.MIN_VALUE;
    	for(int i=nums.length-1; i>=0; i--){
    		if(nums[i]<pre){
    			return true;
    		}
    		
    		while(!stack.isEmpty()&&nums[i]>stack.peek()){
    			pre=stack.pop();
    		}
    		stack.push(nums[i]);
    	}
    	return false;
    }
    
    //150. Evaluate Reverse Polish Notation
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<Integer>();
        for(int i=0; i<tokens.length; i++){
        	String s=tokens[i];
        	if(s.equals("+")){
        		int num1=stack.pop();
        		int num2=stack.pop();
        		stack.push(num1+num2);
        	}else if(s.equals("-")){
        		int num1=stack.pop();
        		int num2=stack.pop();
        		stack.push(num2-num1);
        	}else if(s.equals("*")){
        		int num1=stack.pop();
        		int num2=stack.pop();
        		stack.push(num1*num2);
        	}else if(s.equals("/")){
        		int num1=stack.pop();
        		int num2=stack.pop();
        		stack.push(num2/num1);
        	}else{
        		stack.push(Integer.valueOf(s));
        	}
        }
        return stack.pop();
    }
    
    
    
	public static void main(String[] args) {
		List<Integer> list=Arrays.asList(1,-1,1,-1,1,1,-1,-1);
		System.out.println(getLongestLength(list));
		
		
//		char[][] matrix=new char[][]{
//			{'1','0','1','0','0'},
//			{'1','0','1','1','1'},
//			{'1','1','1','1','1'},
//			{'1','0','0','1','0'}
//		};
//		System.out.println(maximalRectangle(matrix));
//		System.out.println(largestRectangleArea2(new int[]{4,2,0,3,2,5}));
//		String s="(){}[]";
//		System.out.println(isValid(s));
	}

}
