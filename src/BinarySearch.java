
public class BinarySearch {
	//没有具体功能，为了确保编译没有错误。
	static boolean  isBadVersion(int version){
		return true;
	}
	
    public int firstBadVersion(int n) {
    	int left=1, right=n;
        while(left<right){
        	int mid=left+(right-left)/2;
        	if(isBadVersion(mid)){
        		right=mid;
        	}else{
        		left=mid+1;
        	}
        }    	
    	return left;
    }
    
    public static int mySqrt(int x) {
    	if(x<2){
    		return x;
    	}
        int left=1, right=x/2+1;
    	while(left<=right){
    		int mid=left+(right-left)/2;
    		int temp=x/mid;
    		if(temp==mid){
    			return mid;
    		}else if(temp<mid){
    			right=mid-1;
    		}else{
    			left=mid+1;
    		}
    	}
    	return right;
    }
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(mySqrt(15));

	}

}
