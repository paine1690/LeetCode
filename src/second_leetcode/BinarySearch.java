package second_leetcode;

/**
 * 		二分查找问题
 * 
 * @author Paine
 *
 */
public class BinarySearch {
	
	/**
	 * 返回第一个大于某数的序号
	 */
	
	
	//278. First Bad Version   
	private static boolean isBadVersion(int version){
		return true;
	}
    public int firstBadVersion(int n) {
        int start=1, end=n;
        while(start<=end){
        	int mid=(start+end)>>>1;
        	if(isBadVersion(mid)){
        		end=mid-1;
        	}else{
        		start=mid+1;
        	}
        }
        return start;
    }
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
