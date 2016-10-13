package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cube {
	static int M, N, P;
	static Integer[][][] big;
	static List<Integer[]> result=null;
	
	
	static void printCube(Integer[][][] cube){
		int len=cube.length;
		for(int i=0; i<len; i++){
			for(int j=0; j<len; j++){
				for(int k=0; k<len; k++){
					System.out.print(cube[i][j][k]+" ");
				}
			}
		}
		System.out.println("");
	}
	static Integer[][][] creatCube(int[] nums, int len){
		int count=0;
		Integer[][][] re=new Integer[len][len][len];
		for(int i=0; i<len; i++){
			for(int j=0; j<len; j++){
				for(int k=0; k<len; k++){
					re[i][j][k]=nums[count++];
				}
			}
		}
		return re;
	}
	
	static boolean isOK(Integer[][][] cube){
		int len=cube.length;
		for(int i=0; i<len; i++){
			for(int j=0; j<len; j++){
				for(int k=0; k<len; k++){
					if(cube[i][j][k]!=0&&cube[i][j][k]%P!=0){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	static void addCube(Integer[][][] cube, int startI, int startJ, int startK){
		int len=cube.length;
		for(int i=0; i<len; i++){
			for(int j=0; j<len; j++){
				for(int k=0; k<len; k++){
					big[startI+i][startJ+j][startK+k]+=cube[i][j][k];
				}
			}
		}
	}
	
	static void minusCube(Integer[][][] cube, int startI, int startJ, int startK){
		int len=cube.length;
		for(int i=0; i<len; i++){
			for(int j=0; j<len; j++){
				for(int k=0; k<len; k++){
					big[startI+i][startJ+j][startK+k]-=cube[i][j][k];
				}
			}
		}
	}
	
	static void dfs(List<Integer[]> re, List<Integer[][][]> list, int index){
		if(index>=list.size()){
			if(isOK(big)){
				result=new ArrayList<Integer[]>(re);
			}
			return;
		}
		
		Integer[][][] cube=list.get(index);
		int len=cube.length;
		int stop=M-len;
		for(int i=0; i<=stop&&result==null; i++){
			for(int j=0; j<=stop&&result==null; j++){
				for(int k=0; k<=stop&&result==null; k++){
					addCube(cube, i, j, k);
					re.add(new Integer[]{i, j, k});
					dfs(re, list, index+1);
					if(result!=null){						
						break;
					}
					minusCube(cube, i, j, k);
					re.remove(re.size()-1);
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		M=s.nextInt();
		N=s.nextInt();
		P=s.nextInt();
		int[] nums=new int[M*M*M];
		for(int i=0; i<nums.length; i++){
			nums[i]=s.nextInt();
		}
		big=creatCube(nums, M);
		List<Integer[][][]> list=new ArrayList<Integer[][][]>();
		for(int i=0; i<N; i++){
			int len=s.nextInt();
			int[] sNums=new int[len*len*len];
			for(int j=0; j<sNums.length; j++){
				sNums[j]=s.nextInt();
			}
			list.add(creatCube(sNums, len));
		}
		List<Integer[]> re=new ArrayList<Integer[]>();
		dfs(re, list, 0);
		for(Integer[] temp:result){
			for(int i=0; i<temp.length; i++){
				System.out.print(temp[i]+" ");
			}
			System.out.println("");
		}
	}
}
