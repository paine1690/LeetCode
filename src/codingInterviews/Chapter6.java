package codingInterviews;

import java.util.ArrayList;

public class Chapter6 {

	
	/*
	 * 38、数字在排序数组中出现的次数
	 */
    public int GetNumberOfK(int [] array , int k) {
        int i=0, j=array.length-1;
        int left=0, right=0;
        
        while(i<=j){
        	int mid=i+(j-i)/2;
        	if(array[mid]>=k){
        		j=mid-1;
        	}else{
        		i=mid+1;
        	}        	
        }
        left=j;
        
        i=0;
        j=array.length-1;
        while(i<=j){
        	int mid=i+(j-i)/1;
        	if(array[mid]<=k){
        		i=mid+1;
        	}else{
        		j=mid-1;
        	}
        }
        right=i;
        return right-left-1;
    }
	
	/*
	 * 39、二叉树的深度
	 */
	public int TreeDepth(TreeNode pRoot){
		if(pRoot==null){
			return 0;
		}
		return Math.max(TreeDepth(pRoot.left), TreeDepth(pRoot.right))+1;		
    }
	
	//平衡二叉树
	private int deepTh;
    public boolean IsBalanced_Solution(TreeNode root) {
		if(root==null){
			deepTh=0;
			return true;
		}
		int left=0, right=0;
		if(IsBalanced_Solution(root.left)){
			left=deepTh;
		}else{
			return false;
		}
		if(IsBalanced_Solution(root.right)){
			right=deepTh;
		}else{
			return false;
		}
		if(Math.abs(left-right)<=1){
			deepTh=Math.max(left, right)+1;
			return true;
		}else{
			return false;
		}
    }
	
    /*
     * 40、数组中只出现一次的数字
     */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int a=0, b=0;
    	for(int i=0; i<array.length; i++){
    		a^=array[i];
    	}
    	
    	b=a&(-a);
    	for(int i=0; i<array.length; i++){
    		if((array[i]&b)==0){
    			num1[0]^=array[i];
    		}else{
    			num2[0]^=array[i];
    		}
    	}
    }
	
    /*
     * 41、和为s的两个数字VS和为s的连续正数序列
     */
	public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        int a=Integer.MAX_VALUE, b=Integer.MAX_VALUE;
        int i=0, j=array.length-1;
        while(i<j){
        	int s=array[i]+array[j];
        	if(s==sum){
        		if(a==Integer.MAX_VALUE){
        			a=i;
        			b=j;
        		}else if(i*j<a*b){
        			a=i;
        			b=j;
        		}
        		i++;
        		j--;
        	}else if(s>sum){
        		j--;
        	}else{
        		i++;
        	}
        }
        ArrayList<Integer> re=new ArrayList<Integer>();
        if(a!=Integer.MAX_VALUE){
        	re.add(array[a]);
            re.add(array[b]);
        }
        return re;        
    }
    
    public static ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> re=new ArrayList<ArrayList<Integer>>();
        int end=(1+sum)/2;
        int i=1, j=2, s=3;
        
        while(i<j){
        	if(s==sum){
        		ArrayList<Integer> list=new ArrayList<Integer>();
        		for(int k=i; k<=j; k++){
        			list.add(k);
        		}
        		re.add(list);
        		s-=i++;
        	}else if(s<sum){
        		j++;
        		if(j<=end){
        			s+=j;
        		}else{
        			break;
        		}
        	}else{
        		s-=i++;
        	}
        }
        return re;
    }
    
    /*
     * 42、反转单词顺序VS左旋转字符串
     */
    private static void reverse(char[] chars, int i, int j){
    	char temp;
    	while(i<j){
    		temp=chars[i];
    		chars[i]=chars[j];
    		chars[j]=temp;
    		i++;
    		j--;
    	}
    }
    
    public static String ReverseSentence(String str) {
        char[] chars=str.toCharArray();
        int i=0,j=1;
        while(j<chars.length){
        	if(chars[j]==' '){
        		reverse(chars, i, j-1);
        		i=j+1;
        		j=i+1;
        	}else{
        		j++;
        	}
        }
        if(i<chars.length){
        	reverse(chars, i, j-1);
        }
        reverse(chars, 0, chars.length-1);
        return new String(chars);        
    }
    
    public static String LeftRotateString(String str,int n) {
    	if(str.length()==0){
    		return str;
    	}
    	n=n%str.length();
        char[] chars=str.toCharArray();
        reverse(chars, 0, n-1);
        reverse(chars, n, chars.length-1);
        reverse(chars, 0, chars.length-1);
        return new String(chars);
    }
    
    
    
    
    
	/*
	 * 47、不用加减乘除做加法
	 */
	public static int add(int num1, int num2){		
		while(num2!=0){
			int sum=num1^num2;
			int carry=(num1&num2)<<1;
			num1=sum;
			num2=carry;
		}	
		return num1;
	}
	
	
	public static void main(String[] args) {
		System.out.println(ReverseSentence("i am a student "));

	}

}
