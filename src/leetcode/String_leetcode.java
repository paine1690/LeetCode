package leetcode;

public class String_leetcode {
	
	//344. Reverse String   反转字符串
    public static String reverseString(String s) {
        StringBuilder re=new StringBuilder();
        for(int i=s.length()-1; i>=0; i--){
        	re.append(s.charAt(i));
        }
    	return re.toString();
    }
	public static void main(String[] args) {
		System.out.println(reverseString("hello"));
	}

}
