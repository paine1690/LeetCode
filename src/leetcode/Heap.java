package leetcode;

public class Heap {

	
	
	private static void heapBuild(int[] nums,int len){
		for(int i=len/2-1; i>=0; i--){//从length开始调整直至根节点
			int j=i;
			int max=j;
			int left=2*j+1;
			int right=left+1;
			
			while(left<len){//维护对的性质，O(h)树的高度
				if(nums[left]>nums[max]){
					max=left;
				}
				if(right<len&&nums[right]>nums[max]){
					max=right;
				}
				if(max!=j){
					int temp=nums[j];
					nums[j]=nums[max];
					nums[max]=temp;
					j=max;
					left=2*j+1;
					right=left+1;
				}else{
					break;
				}
			}
		}
	}
	
    public static int findKthLargest(int[] nums, int k) {
        int len=nums.length;
        if(len==1){
        	return nums[0];
        }
        heapBuild(nums, len);
        int temp;
        for(int i=1; i<k; i++){
        	temp=nums[0];
        	nums[0]=nums[len-1];
        	nums[len-1]=temp;
        	len--;
        	heapBuild(nums, len);
        }
    	return nums[0];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={3,2,1,5,6,4};
		System.out.println(findKthLargest(nums, 2));

	}

}
