package leetcode;

import java.util.Map;
import java.util.TreeMap;

public class Treemap_leetcode {
	//436. Find Right Interval
    static class Interval {
    	int start;
    	int end;
    	Interval() { start = 0; end = 0; }
    	Interval(int s, int e) { start = s; end = e; }
    }
    
	public static int[] findRightInterval(Interval[] intervals) {
		int len=intervals.length;
		int[] re=new int[len];
		TreeMap<Integer, Integer> map=new TreeMap<Integer, Integer>();
		for(int i=0; i<len; i++){
			map.put(intervals[i].start, i);
		}
		
		for(int i=0; i<len; i++){
			Map.Entry<Integer, Integer> entry=map.ceilingEntry(intervals[i].end);
			re[i]=entry==null? -1:entry.getValue();
		}
		return re;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
