package beautyofPrograming;

import java.util.Arrays;

/**
 * 		第四章 数学之趣 -数学游戏的乐趣
 * @author Paine
 *
 */
public class Chapter4 {
	/*
	 * 4.3 买票找零
	 */
	public static int Catalan(int nn){
		if(nn==0){
			return 1;
		}
		int re=0;
		for(int i=0; i<nn; i+=2){
			re+=Catalan(i)*Catalan(nn-i-2);
		}
		return re;
	}
	
	/*
	 * 4.4 点是否在三角形内
	 */
	//面积法 海伦公示
	private static double Area(Point3 A, Point3 B, Point3 C){
		double[] abc=compute(A, B, C);
		double a=abc[0], b=abc[1], c=abc[2];
		double p=(a+b+c)/2;
		return Math.sqrt(((p-a)*(p-b)*(p-c))*p);
	}
	
	private static double[] compute(Point3 A, Point3 B, Point3 C){
		double a=Math.sqrt((B.x-C.x)*(B.x-C.x)+(B.y-C.y)*(B.y-C.y));
		double b=Math.sqrt((A.x-C.x)*(A.x-C.x)+(A.y-C.y)*(A.y-C.y));
		double c=Math.sqrt((B.x-A.x)*(B.x-A.x)+(B.y-A.y)*(B.y-A.y));
		return new double[]{a, b, c};
	}
	
	public static boolean isIn(Point3 A, Point3 B, Point3 C, Point3 D){
		double s=Area(A, B, C);
		double s1=Area(A, B, D);
		double s2=Area(B, C, D);
		double s3=Area(C, A, D);
		return (s1+s2+s3-s)<=0.000001;
	}
	
	//向量叉积
	
	//计算向量AB AC的叉积
	private static double product(Point3 A, Point3 B, Point3 C){
		return (B.x-A.x)*(C.y-A.y)-(C.x-A.x)*(B.y-A.y);
	}
	
	public static boolean isIn2(Point3 A, Point3 B, Point3 C, Point3 D){
		return product(A, B, D)>=0&&product(B, C, D)>=0&&product(C, A, D)>=0;
	}
	
	/*
	 * 4.5 磁带文件存放优化
	 */
	private static void mergeSort(double[] nums, int[] pos, int start, int end){
		if(start>=end){
			return;
		}
		int mid=start+(end-start)/2;
		mergeSort(nums, pos, start, mid);
		mergeSort(nums, pos, mid+1, end);
		int[] temp=new int[end-start+1];
		
		int i=start, j=mid+1, k=0;
		while(i<=mid&&j<=end){
			if(nums[pos[j]]>nums[pos[i]]){
				temp[k++]=pos[j++];
			}else{
				temp[k++]=pos[i++];
			}
		}
		while(i<=mid){
			temp[k++]=pos[i++];
		}
		while(j<=end){
			temp[k++]=pos[j++];
		}
		for(k=0; k<temp.length; k++){
			pos[start+k]=temp[k];
		}				
	}
	
	public static int[] getBest(double[] p, int[] l){
		for(int i=0; i<p.length; i++){
			p[i]*=l[i];
		}
		int[] pos=new int[p.length];
		for(int i=0; i<pos.length; i++){
			pos[i]=i;
		}
		System.out.println(Arrays.toString(p));
		mergeSort(p, pos, 0, p.length-1);
		return pos;
	}
	
	/*
	 * 4.10 数字哑谜和回文
	 */
	public static void huiwen(){
		boolean[] isUsed=new boolean[10];
		boolean flag;
		for(int num=1234; num<100000; num++){
			Arrays.fill(isUsed, false);
			flag=false;
			int number=num, re_number=0;
			for(int i=0; i<5; i++){
				int temp=number%10;
				if(isUsed[temp]){
					flag=true;
					break;
				}
				isUsed[temp]=true;
				number/=10;
				re_number=re_number*10+temp;
			}
			if(!flag&&re_number%num==0){
				int x=re_number/num;
				if(x<10&&!isUsed[x]){
					System.out.println(num+"*"+x+"="+re_number);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
//		huiwen();
//		System.out.println(Arrays.toString(getBest(new double[]{0.6, 0.4, 0.7, 0.4, 0.5, 0.4}, new int[]{60, 10, 6, 45, 1, 4})));
//		Point3 A=new Point3(0,3);
//		Point3 B=new Point3(0,0);
//		Point3 C=new Point3(4,0);
//		Point3 D=new Point3(1,1);
//		System.out.println(isIn2(A, B, C, D));
	}

}

class Point3{
	double x, y;
	public Point3(double x, double y){
		this.x=x;
		this.y=y;
	}
}
