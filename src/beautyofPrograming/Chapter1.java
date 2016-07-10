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
			System.out.println("a"+(i%9+1)+"b"+(i/9+1));
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
		int counter=0;
		int temp=0;
		while(temp++<var){
			System.out.println("counter="+counter+"i="+(temp%3)+", j="+(temp/3)+", k="+(temp/(3*4)));
		    counter++;
		}		
	}
	
	/*
	 * 1.3 一摞烙饼的排序
	 */
	public static void laobing(int[] nums){
		CPrefixSort sort=new CPrefixSort(nums);
		sort.print();
	}
	
	public static void main(String[] args){
		laobing(new int[]{0,1,3,4,2,5});
		laobing(new int[]{3,2,1,6,5,4,9,8,7,0});
	}
}



/*
 * 1.3 一摞烙饼的排序
 */
class CPrefixSort{
	private int[] cakeArray;//烙饼信息数组
	private int[] reverseCakeArray;//当前翻转信息
	
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
		
		cakeArray=nums;
		reverseCakeArray=new int[cakeArray.length];
		for(int i=0; i<cakeArray.length; i++){
			reverseCakeArray[i]=cakeArray[i];
		}			
		
		maxSwap=cakeArray.length*2-3;//初始化上界
		swapArray=new int[maxSwap];
		reverseCakeArraySwap=new int[maxSwap];
		search=0;
		search(0);
	}
	
	private void search(int step){
		search++;
		int lower=lowerBound(reverseCakeArray);
		if(step+lower>=maxSwap||step>=maxSwap){
			return;
		}
		
		if(isSorted(reverseCakeArray)){
			if(step<maxSwap){
				maxSwap=step;
				for(int i=0; i<maxSwap; i++){
					swapArray[i]=reverseCakeArraySwap[i];
				}
			}
			return;
		}
		
		for(int i=1; i<reverseCakeArray.length; i++){
			reverse(reverseCakeArray, 0, i);
			reverseCakeArraySwap[step]=i;
			search(step+1);
			reverse(reverseCakeArray, 0, i);
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

