package interview;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;



/**
 * 
 * 	小数点保留2位小数
 * 
 * 
 * 
 * 
 * @author Paine
 *
 */

public class Format {
	static double d=3.1415926;
	
	/*
	 * 方法一:
	 * String.format
	 */
	static void f1(){
		System.out.println("f1:");
		System.out.println(String.format("%.2f", d));
		System.out.println(String.format("%.3f", d));
		System.out.println(String.format("%.4f", d));
		System.out.println(String.format("%.5f", d));
	}
	
	/*
	 * 方法二:
	 * DecimalFormat
	 */
	static void f2(){
		System.out.println("f2:");
		DecimalFormat df1=new DecimalFormat("#.00");
		DecimalFormat df2=new DecimalFormat("0.00");
		DecimalFormat df3=new DecimalFormat("00.00");
		DecimalFormat df4=new DecimalFormat("##.#");
		DecimalFormat df5=new DecimalFormat("0.0%");
		System.out.println(df1.format(d));
		System.out.println(df2.format(d));
		System.out.println(df3.format(d));
		System.out.println(df4.format(d));
		System.out.println(df5.format(d));
	}
	
	/*
	 * 方法三:
	 * BigDecimal 
	 */
	static void f3(){
		System.out.println("f3:");
		BigDecimal bd=new BigDecimal(d);
		double d1=bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		double d2=bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		double d3=bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);	
	}
	
	/*
	 * 方法四:
	 * NumberFormat
	 */
	static void f4(){
		System.out.println("f4:");
		NumberFormat nf=NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println(nf.format(d));
	}
	public static void main(String[] args) {
		f1();
		f2();
		f3();
		f4();

	}

}
