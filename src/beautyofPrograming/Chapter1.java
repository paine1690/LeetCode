package beautyofPrograming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 		第一章 游戏之乐 -游戏中碰到的题目
 * @author Paine
 *
 */
public class Chapter1 {

	/*
	 * 1.1 CPU曲线听你指挥
	 */

	/*
	 * 1.2 中国象棋将帅问题
	 */
	//9进制，两位，两重循环
	public static void chess(){
		char i=81;
		while(i-->0){
			if((i/9%3)==(i%9%3)){
				continue;
			}
			System.out.println("a"+(i/9+1)+"b"+(i%9+1));
		}
	}
	
	//n重循环
	public static void n(){
		int counter=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<4; j++){
				for(int k=0; k<3; k++){
					System.out.println("counter="+counter+"i="+i+", j="+j+", k="+k);
				    counter++;
				}
			}
		}
	}
	
	public static void n2(){
		int var=5*4*3;
		int counter;
		for(counter=0; counter<var; counter++){
			System.out.println("counter="+counter+"i="+((counter/(3*4))%5)+", j="+((counter/3)%4)+", k="+(counter%3));
		}	
	}
	
	/*
	 * 1.3 一摞烙饼的排序
	 */
	public static void laobing(int[] nums){
		CPrefixSort sort=new CPrefixSort(nums);
		sort.print();
	}
	
	/*
	 * 1.4 买书问题
	 */
	
	/*
	 * 1.5 快速找出故障机器
	 */
	
	// leetcode 136. Single Number 出现两次，只有一个出现一次
	public static int sigleNUmber(int[] nums){
		int re=0;
		for(int num:nums){
			re^=num;
		}
		return re;
	}
	
	// leetcode 260. Single Number III 出现两次，有两个数出现一次
	public static int[] sigleNumber3(int[] nums){
		int x=0;
		for(int num:nums){
			x^=num;
		}
		x=x&(-x);
		int a=0, b=0;
		for(int num:nums){
			if((num&x)==0){
				a^=num;
			}else{
				b^=num;
			}
		}			
		return new int[]{a, b};
	}
	
	// leetcode 137. Single Number II 出现三次，有一个坏的
	public static int sigleNumber2(int[] nums){
		int one=0;
		int two=0;
		for(int num: nums){
			two|=one&num;
			one^=num;
			int three=one&two;
			one&=~three;
			two&=~three;
		}		
		return one;
	}
	
	/*
	 * 1.7 光影切割问题
	 * 
	 * 1、求逆序数
	 * 2、leetcode 315
	 * 
	 */
	static int[] nums;
	static int[] copy;
	
	private static int merge(int s1, int e1, int s2, int e2){
		int i=s1, j=s2, k=0;
		int re=0;
		
		while(i<=e1&&j<=e2){
			if(nums[i]<=nums[j]){
				copy[k++]=nums[i++];
			}else{
				copy[k++]=nums[j++];
				re+=e1-i+1;
			}
		}		
		while(i<=e1){
			copy[k++]=nums[i++];
		}
		
		while(j<=e2){
			copy[k++]=nums[j++];
		}		
		for(i=0; i<k; i++){
			nums[s1+i]=copy[i];
		}
		System.out.println(re);
		return re;
	}
	
	private static int reverse(int start, int end){
		if(start==end){
			return 0;
		}
		int mid=start+(end-start)/2;
		return reverse(start, mid)+reverse(mid+1, end)+merge(start, mid, mid+1, end);
	}
	
	
	public static int shadow(int[] nums_, int start, int end){
		nums=nums_;
		copy=new int[nums.length];
		return reverse(0, nums.length-1);
	}
	
   /*
    * 1.8 小飞的电梯调度算法
    */
    public static int[] nPerson(int[] nums){
    	int minFloor=0, targetFloor=1;
    	int n1=0, n2=nums[1], n3=0;
    	for(int i=2; i<nums.length; i++){
    		n3+=nums[i];
    		minFloor+=nums[i]*(i-1);
    	}
    	
    	for(int i=2; i<nums.length; i++){
    		if(n1+n2-n3<0){
    			minFloor+=n1+n2-n3;
    			targetFloor=i;
    			n1+=n2;
    			n2=nums[i];
    			n3-=nums[i];
    		}else{
    			break;
    		}
    	}
    	return new int[]{minFloor, targetFloor};
    }   
    
    /*
     * 1.15 构造数独
     */
    //回溯法
    private static boolean isValid(int[][] sodu, int m, int n){
    	int val=sodu[m][n];
    	for(int i=0; i<9; i++){
    		if(i!=m&&sodu[i][n]==val){
    			return false;
    		}
    	}
    	for(int j=0; j<9; j++){
    		if(j!=n&&sodu[m][j]==val){
    			return false;
    		}
    	}
    	for(int i=m/3*3; i<m/3*3+3; i++){
    		for(int j=n/3*3; j<n/3*3+3; j++){
    			if(i!=m&&j!=n&&sodu[i][j]==val){
    				return false;
    			}
    		}
    	}    	
    	return true;
    }
    
    public static void sodukuCreate(){
    	int[][] sodu=new int[9][9];
    	int k=0;
    	Random ra=new Random(); 
    	for(int i=1; i<9; i++){
    		int temp=ra.nextInt(80)+1;
    		sodu[temp/9][temp%9]=i+1;
    	}
    	
    	while(true){
    		int i=k/9;
    		int j=k%9;
    		while(true){
    			sodu[i][j]++;
    			if(sodu[i][j]==10){
    				sodu[i][j]=0;
    				k--;
    				break;
    			}else if(isValid(sodu, i, j)){
    				k++;
    				break;
    			}
    		}
    		if(k==81){
    			break;
    		}
    	}
    	
    	for(int i=0; i<9; i++){
    		System.out.println(Arrays.toString(sodu[i]));
    	}
    }
    
    //置换法
    private static void lrSwitch(int[][] sodu, int starti, int startj){
    	for(int i=starti; i<starti+3; i++){
    		int lefti=starti+(i+1)%3;
    		int righti=starti+(i+2)%3;
    		for(int j=startj; j<startj+3; j++){
    			sodu[lefti][j-3]=sodu[i][j];
    			sodu[righti][j+3]=sodu[i][j];
    		}
    	}
    }
    
    private static void udSwitch(int[][] sodu, int starti, int startj){
    	for(int j=startj; j<startj+3; j++){
    		int upj=startj+(j+1)%3;
    		int downj=startj+(j+2)%3;
    		for(int i=starti; i<starti+3; i++){
    			sodu[i-3][upj]=sodu[i][j];
    			sodu[i+3][downj]=sodu[i][j];
    		}
    	}
    }
    
    
    public static void soduCreate(){
    	int[][] sodu=new int[9][9];
    	List<Integer> list=new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    	Random r=new Random();
    	for(int i=3; i<6; i++){//先随机生成中间的3*3
    		for(int j=3; j<6; j++){
    			int index=r.nextInt(list.size());
    			sodu[i][j]=list.get(index);
    			list.remove(index);
    		}
    	}
    	lrSwitch(sodu, 3, 3);
    	udSwitch(sodu, 3, 3);
    	udSwitch(sodu, 3, 0);
    	udSwitch(sodu, 3, 6);
    	for(int i=0; i<9; i++){
    		System.out.println(Arrays.toString(sodu[i]));
    	}
    }
    
    /*
     * 1.16 24点游戏
     */
    public static void pointGame(){
    	new Point2(new int[]{1,4,5,6});
    	new Point2(new int[]{5,5,5,1});
    	new Point2(new int[]{3,3,7,7});
    	new Point2(new int[]{3,8,8,10});
    	new Point2(new int[]{4,4,10,10});
    	new Point2(new int[]{9,9,6,2});
    }
  
    
    
    
	public static void main(String[] args){
		pointGame();
//		soduCreate();
//		int[] nums={5,2,6,1};
//		System.out.println(shadow(nums, 0, nums.length-1));
//		laobing(new int[]{0,1,3,4,2,5});
//		laobing(new int[]{3,2,1,6,5,4,9,8,7,0});
//		chess();
	}
}

/*
 * 1.16 24点游戏
 */
//记忆化搜索
//参考：http://bylijinnan.iteye.com/blog/1606892
class Point2{
	private int len;
	private List<Node>[] S;
	
	@SuppressWarnings("unchecked")
	public Point2(int[] nums){
		this.len=nums.length;
		int m=(1<<len)-1;//2^n-1
		S=new ArrayList[m+1];
		
		//只有一个元素，不用计算
		for(int i=0; i<len; i++){
			int num=nums[i];
			Node node=new Node(num, ""+num);
			List<Node> list=new ArrayList<Node>();
			list.add(node);
			S[(1<<i)]=list;
		}
		
		for(int i=1; i<=m; i++){
			S[i]=f(i);
		}
		
		List<Node> Sm=S[m];
		for(Node node: Sm){
			if(Math.abs(node.val-24)<0.0000001){
				System.out.println(""+node.exp);
				return;
			}
		}		
	}
	
	private List<Node> f(int i){
		List<Node> Si=S[i];
		
		if(Si!=null&&!Si.isEmpty()){
			return Si;
		}
		List<Node> re=new ArrayList<Node>();
		
		for(int x=1; x<i; x++){
			if((x&i)==x){
				re.addAll(fork(f(x),f(i-x)));
			}
		}		
		return re;
	}
	
	private List<Node> fork(List<Node> a, List<Node> b){
		Set<Node> set=new HashSet<Node>();
		for(int i=0; i<a.size(); i++){
			for(int j=0; j<b.size(); j++){
				Node ai=a.get(i);
				Node bj=b.get(j);
				set.add(new Node(ai.val+bj.val, "("+ai.exp+"+"+bj.exp+")"));
				set.add(new Node(ai.val-bj.val, "("+ai.exp+"-"+bj.exp+")"));
				set.add(new Node(bj.val+ai.val, "("+bj.exp+"-"+ai.exp+")"));
				set.add(new Node(ai.val*bj.val, "("+ai.exp+"*"+bj.exp+")"));
				if(ai.val!=0){
					set.add(new Node(bj.val/ai.val, "("+bj.exp+"/"+ai.exp+")"));
				}
				if(bj.val!=0){
					set.add(new Node(ai.val/bj.val, "("+ai.exp+"/"+bj.exp+")"));
				}
			}
		}
		return new ArrayList<Node>(set);
	}
	
	
}

class Node{
	double val;
	public String exp;
	
	public Node(double val, String exp){
		this.val=val;
		this.exp=exp;
	}
	
	public boolean equals(Object o){//重写equals方法，防止重复
		if(!(o instanceof Node)){
			return false;
		}
		Node node=(Node)o;
		return Double.doubleToLongBits(this.val)==Double.doubleToLongBits(node.val);
	}
	
	public int hashCode(){//重写equals必须重写hashCode，因为要保证相同的对象有相同的hashCode
		int re=17;
		long toLong=Double.doubleToLongBits(val);
		re=37*re+(int)(toLong^(toLong>>>32));
		return re;
	}
}


//穷举 深搜
class Point{
	private double[] nums=new double[4];
    private String[] result=new String[4];
    
    public Point(int[] nums){
    	for(int i=0; i<nums.length; i++){
    		result[i]=String.valueOf(nums[i]);
    		this.nums[i]=nums[i];
    	}
    	if(point(4)){
    		System.out.println("success");
    	}
    }
    
    
    private boolean point(int n){
    	if(n==1){
    		if(Math.abs(nums[0]-24)<0.00000001){
    			System.out.println(nums[0]+"  "+result[0]);
    			return true;
    		}else{
    			return false;
    		}
    	}
    	for(int i=0; i<n; i++){
    		for(int j=i+1; j<n; j++){
    			double a=nums[i];
    			double b=nums[j];
    			nums[j]=nums[n-1];
    			
    			String sa=result[i];
    			String sb=result[j];
    			result[j]=result[n-1];
    			
    			nums[i]=a+b;
    			result[i]="("+sa+"+"+sb+")";
    			if(point(n-1)){
    				return true;
    			}
    			
    			nums[i]=a-b;
    			result[i]="("+sa+"-"+sb+")";
    			if(point(n-1)){
    				return true;
    			}
    			
    			nums[i]=b-a;
    			result[i]="("+sb+"-"+sa+")";
    			if(point(n-1)){
    				return true;
    			}
    			
    			nums[i]=a*b;
    			result[i]="("+sa+"*"+sb+")";
    			if(point(n-1)){
    				return true;
    			}
    			
    			if(b!=0){
    				nums[i]=a/b;
        			result[i]="("+sa+"/"+sb+")";
        			if(point(n-1)){
        				return true;
        			}
    			}    			
    			
    			if(a!=0){
    				nums[i]=b/a;
        			result[i]="("+sb+"/"+sa+")";
        			if(point(n-1)){
        				return true;
        			}
    			}
    			
    			
    			nums[i]=a;
    			nums[j]=b;
    			result[i]=sa;
    			result[j]=sb;
    		}
    	}
    	return false;
    }
}

/*
 * 1.3 一摞烙饼的排序
 */
class CPrefixSort{
	private int[] cakeArray;//烙饼信息数组	
	private int[] swapArray;//交换结果数组	
	private int[] reverseCakeArraySwap;//当前翻转交换结果
	
	
	private int maxSwap;//上界	
	private int search;//搜索次数
	
	public void print(){
		System.out.println("step: "+maxSwap+" search: "+search);
		StringBuilder s=new StringBuilder();
		for(int i=0; i<maxSwap; i++){
			s.append(swapArray[i]);
		}
		System.out.println(s.toString());
	}
	public CPrefixSort(int[] nums){
		
		cakeArray=nums.clone();		
		
		maxSwap=cakeArray.length*2-3;//初始化上界
		swapArray=new int[maxSwap];
		reverseCakeArraySwap=new int[maxSwap];
		search=0;
		search(0);
	}
	
	private void search(int step){
		search++;
		int lower=lowerBound(cakeArray);
		if(step+lower>=maxSwap||step>=maxSwap){
			return;
		}
		
		if(isSorted(cakeArray)){
			if(step<maxSwap){
				maxSwap=step;
				for(int i=0; i<maxSwap; i++){
					swapArray[i]=reverseCakeArraySwap[i];
				}
			}
			return;
		}
		
		for(int i=1; i<cakeArray.length; i++){
			reverse(cakeArray, 0, i);
			reverseCakeArraySwap[step]=i;
			search(step+1);
			reverse(cakeArray, 0, i);
		}		
	}	
	
	//反转的下界
	private int lowerBound(int[] nums){
		int re=0;
		for(int i=1; i<nums.length; i++){
			int t=nums[i]-nums[i-1];
			if(t==1||t==-1){
				
			}else{
				re++;
			}
		}
		if(nums[nums.length-1]!=nums.length-1){
			re++;
		}
		return re;
	}
	
	//反转数组
	private void reverse(int[] nums, int start, int end){
		int i=start, j=end;
		while(i<j){
			int temp=nums[i];
			nums[i]=nums[j];
			nums[j]=temp;
			i++;
			j--;
		}
	}
	
	//判断是否已经有序
	private boolean isSorted(int[] nums){
		for(int i=1; i<nums.length; i++){
			if(nums[i]<nums[i-1]){
				return false;
			}
		}
		return true;
	}	
}

