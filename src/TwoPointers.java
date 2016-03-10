
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
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="race a car";
		System.out.println(isPalindrome(s));
	}

}
