package leetcode;

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
	private static boolean isEquel(char a, char b){
		return (((a=='(')&&(b==')'))||((a=='{')&&(b=='}'))||((a=='[')&&(b==']')));
	}
	
    public static boolean isValid(String s) {
    	Stack<Character> stack=new Stack<Character>();
    	for(int i=0; i<s.length(); i++){
    		char temp=s.charAt(i);
    		if(temp=='('||temp=='{'||temp=='['){
    			stack.push(temp);
    		}else{
    			if(stack.isEmpty()){
    				return false;
    			}else{
    				if(!isEquel(stack.pop(), temp)){
    					return false;
    				}
    			}
    		}
    	}
        return true;
    }
    
    
	public static void main(String[] args) {
		String s="(){}[]";
		System.out.println(isValid(s));
	}

}
