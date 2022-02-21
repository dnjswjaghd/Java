package algo4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Scoville {
	int[] scoville = {1, 2, 3, 9, 10, 12};
	int k = 7;
	public int solution(int[] scoville, int K) {
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
        int answer = 0;
        Arrays.sort(scoville);
        for(int i=0; i<scoville.length; i++) {
        	if(scoville[i]<k) {
        		a.add(scoville[i]);
        	}
        }
        
        for(int i=0; i<a.size()-2; i+=2) {
        	if(a.get(i)+a.get(i+1)*2>k) {
        		answer++;
        	}else if(a.get(i)+a.get(i+1)*2<=a.get(i+3)){
        		b.add(a.get(i)+a.get(i+1)*2);
        		answer++;
        	}else {
        		b.add(a.get(i+3));
        	}
        }
        
        
        System.out.println("a: "+a);
        
        return answer;
    }
	public static void main(String[] args) {
		Scoville sv = new Scoville();
		System.out.println(sv.solution(sv.scoville, sv.k));
	}

}
