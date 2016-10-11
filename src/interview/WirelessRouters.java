package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WirelessRouters {

	static Room[] rooms;
	static int[] points;
	static int[] heap;
	static int heapLen;	
	
	private static int getMaxFromHeap(){
		int re=heap[0];
		int temp=heap[0];
		heap[0]=heap[heapLen-1];
		heap[heapLen-1]=temp;
		heapLen--;
		return re;
	}

	private static void heapBuild(){
		for(int i=heapLen/2-1; i>=0; i--){
			int j=i;
			int max=j;
			int left=2*i+1;
			int right=left+1;
			while(left<heapLen){
				if(rooms[heap[left]].sPoint>rooms[heap[max]].sPoint){
					max=left;
				}
				if(right<heapLen&&rooms[heap[right]].sPoint>rooms[heap[max]].sPoint){
					max=right;
				}
				if(max!=j){
					int temp=heap[j];
					heap[j]=heap[max];
					heap[max]=temp;
					j=max;
					left=2*j+1;
					right=left+1;
				}else{
					break;
				}
			}
		}
	}
	
	private static void addDoor(int x, int y){
		Room roomX=rooms[x];
		Room roomY=rooms[y];
		
		roomX.sPoint+=points[y];
		roomY.sPoint+=points[x];
		
		roomX.neighbor.add(y);
		roomY.neighbor.add(x);
	}
	
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int N=s.nextInt();
		int M=s.nextInt();
		heapLen=N;
		heap=new int[heapLen];
		for(int i=0; i<N; i++){
			heap[i]=i+1;
		}
		
		points=new int[N+1];
		rooms=new Room[N+1];
		for(int i=1; i<=N; i++){
			points[i]=s.nextInt();
			rooms[i]=new Room(points[i]);
		}	
		
		for(int i=0; i<N-1; i++){
			int x=s.nextInt();
			int y=s.nextInt();
			addDoor(x, y);			
		}
		

		s.close();
		heapBuild();

		int re=0;
		for(int i=0; i<M; i++){
			int index=getMaxFromHeap();
			Room max=rooms[index];
			re+=max.sPoint;
			
			for(int j: max.neighbor){
				Room roomJ=rooms[j];				
				roomJ.sPoint-=points[j]+points[index];
				roomJ.neighbor.remove(new Integer(index));
				
				for(int k: roomJ.neighbor){
					rooms[k].sPoint-=points[j];
				}
			}
			heapBuild();
		}
		System.out.println(re);
	}
}


class Room{
	int sPoint=0;
	List<Integer> neighbor=new ArrayList<Integer>();
	public Room(int point){
		sPoint=point;
	}
	
}