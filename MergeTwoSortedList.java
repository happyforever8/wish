package wish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class MergeTwoSortedList {
	

	static class Interval {
		   int start, end;
		   Interval(int start, int end) {
		       this.start = start;
		       this.end = end;
		  }
	public static void main(String[] args) {
		
		
		Interval inter1 = new Interval(1, 2);
		Interval inter2 = new Interval(3, 4);
		Interval inter3 = new Interval(2, 3);
		Interval inter4 = new Interval(5, 6);
		
		List<Interval> list1 = Arrays.asList(inter1, inter2);
		
		List<Interval> list2 = Arrays.asList(inter3, inter4);
		
		
		List<Interval> result = mergeTwoInterval(list1, list2);
		
		for (Interval in : result){
			System.out.println(in.start + " , " + in.end);
		}
	}
	
    public static List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        List<Interval> result = new ArrayList<>();
        
        int i = 0, j = 0;
        
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).start <= list2.get(j).start) {
                addToResult(result, list1.get(i));
                i++;
            } else {
                addToResult(result, list2.get(j));
                j++;
            }
        }
        
        while (i < list1.size()) {
            addToResult(result, list1.get(i));
            i++;
        }
        
        while (j < list2.size()) {
            addToResult(result, list2.get(j));
            j++;
        }
        
        return result;
    }
    
    private static void addToResult(List<Interval> list, Interval interval) {
        if (list.size() == 0) {
            list.add(interval);
            return;
        }
        
        Interval last = list.get(list.size() - 1);
        
        if (last.end < interval.start) {
            list.add(interval);
            return;
        }
        
        if (last.end < interval.end) {
            last.end = interval.end;
            return;
        }
    }
}
}



