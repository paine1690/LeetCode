package beautyofPrograming;

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
    
	public static void main(String[] args){
//		int[] nums={5,2,6,1};
//		System.out.println(shadow(nums, 0, nums.length-1));
		laobing(new int[]{0,1,3,4,2,5});
		laobing(new int[]{3,2,1,6,5,4,9,8,7,0});
//		chess();
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

