package codingInterviews;

public class Chapter7 {

	/*
	 * 49、把字符串转换成整数
	 */
    public static int myAtoi(String str) {
        if(str.length()==0){
            return 0;
        }
        long re=0;
        char[] chars=str.toCharArray();
        int i=0, flag=1;
        while(i<chars.length&&chars[i]==' '){
            i++;
        }
        if(chars[i]=='+'){
            i++;
        }else if(chars[i]=='-'){
            flag=-1;
            i++;
        }
        
        while(i<chars.length){
            int num=chars[i]-'0';
            if(num<0||num>9){
                break;
            }            
            re=re*10+num;    
            if(re>Integer.MAX_VALUE){
            	break;
            }
            i++;
        }        
        if(flag==1){
        	if(re>Integer.MAX_VALUE){
        		return Integer.MAX_VALUE;
        	}
        }else{
        	if(re>(long)Integer.MAX_VALUE+1){
        		return Integer.MIN_VALUE;
        	}        		
        }
        return (int)re*flag;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
