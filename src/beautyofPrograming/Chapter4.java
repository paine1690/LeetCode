package beautyofPrograming;

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
	//面积法
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
	
	public static void main(String[] args) {
		Point3 A=new Point3(0,3);
		Point3 B=new Point3(0,0);
		Point3 C=new Point3(4,0);
		Point3 D=new Point3(1,1);
		System.out.println(isIn2(A, B, C, D));

	}

}

class Point3{
	double x, y;
	public Point3(double x, double y){
		this.x=x;
		this.y=y;
	}
}
