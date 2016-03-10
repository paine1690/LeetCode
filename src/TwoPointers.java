
public class TwoPointers {
	/*
	 * 直接处理字符串
	 */
	public static boolean isPalindrome(String s) {
		if(s==null){
			return true;
		}
		s=s.replaceAll("\\W", "").toLowerCase();
		if(s.length()<2){
			return true;
		}
		for(int i=0; i<s.length()/2; i++){
			if(s.charAt(i)==s.charAt(s.length()-i-1)){
				continue;
			}
			return false;
		}
		return true;
	}
	
	
	/*
	 * 不用正则表达式
	 */
	public static boolean isPalindrome2(String s) {
		if(s==null){
			return true;
		}
		int i=0, j=s.length()-1;
		char[] chars=s.toCharArray();
		
		while(i<j){
			if(!Character.isLetterOrDigit(chars[i])){
				i++;
			} else if(!Character.isLetterOrDigit(chars[j])){
				j--;
			} else if(Character.toLowerCase(chars[i])!=Character.toLowerCase(chars[j])){
				System.out.println(i);
				System.out.println(j);
				return false;
			}else{
				i++;
				j--;
			}
		}
		return true;
	}
	 
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	if(n==0){
    		return;
    	}
    	int[] nums=new int[m+n];
    	
        int i=0, j=0, k=0;
        while(i<m&&j<n){
        	if(nums1[i]<nums2[j]){
        		nums[k++]=nums1[i++];
        	}else{
        		nums[k++]=nums2[j++];
        	}
        }
        while(i<m){
        	nums[k++]=nums1[i++];
        }
        while(j<n){
        	nums[k++]=nums2[j++];
        }
        for(i=0; i<m+n; i++){
        	nums1[i]=nums[i];
        }
    }
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s=".a";
		System.out.println(isPalindrome2(s));
	}

}
