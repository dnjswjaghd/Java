package algo6;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Printer {
	int[] a = {1, 1, 9, 1, 1, 1};
	int index = 0;
	int count;
	public int solution(int[] pr, int loc) {
		int answer=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int p : pr) {
        	pq.offer(p);
        }
        while(!pq.isEmpty()) {
        	for(int i=0; i<pr.length; i++) {
        		if(pr[i]==pq.peek()) {
        			if(i==loc) {
        				answer++;
        				System.out.println(answer);
        				return answer;
        			}
        			pq.poll();
        			answer++;
        		}
        		
        	}
        }
        
        System.out.println(pq);
        
        
       
        System.out.println(answer);
        return answer;
    }
	public static void main(String[] args) {
		Printer a = new Printer();
		a.solution(a.a, a.index);
	}
}
