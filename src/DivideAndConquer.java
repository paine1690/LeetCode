
public class DivideAndConquer {
	
	private static int partition(int[] nums, int p, int r){
		int x=nums[r];
		int i=p-1;
		int temp;
		for(int j=p; j<r; j++){
			if(nums[j]<=x){
				i++;
				temp=nums[i];
				nums[i]=nums[j];
				nums[j]=temp;				
			}
		}
		temp=nums[i+1];
		nums[i+1]=nums[r];
		nums[r]=temp;
		return i+1;
	}

	public static int findKthLargest(int[] nums, int k) {
		int len=nums.length;
		if(len==1){
			return nums[0];
		}
		int p=0, r=len-1,re=0;
		while(p<r){
			re=partition(nums, p, r);
			if(len-re==k){
				return nums[re];
			}else if(len-re<k){
				r=re-1;
			}else{
				p=re+1;
			}
		}
		return nums[r];		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={2,2};
		System.out.println(findKthLargest(nums, 1));
	}

}
