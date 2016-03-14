
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
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
