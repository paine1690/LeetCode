package leetcode;

import java.util.Queue;
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
	public static void main(String[] args) {
		String s="(){}[]";
		System.out.println(isValid(s));
	}

}
