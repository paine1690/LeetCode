import java.util.Arrays;

public class HashTable {
	/*
	 * 类名HashTable是按照leetcode上面的tag起的，实际用的是HashMap
	 */
	public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len=citations.length;
        int i=0;
        for(i=1; i<=len; i++){
        	if(citations[len-i]<i){
        		break;
        	}
        }
		return i-1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={0,0,0,0,0,0};
		System.out.println(hIndex(nums));
	}

}
