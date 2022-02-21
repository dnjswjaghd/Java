package algo5;

import java.util.ArrayList;
import java.util.Stack;

public class CompressString2 {
	public static String s ="aabbaccc" ;
	public int solution(String s) {
	        int answer = s.length();
	        
	        for (int i = 1; i <= s.length() / 2; i++) {
	            String pdS = s.substring(0, i);
	            int count = 1;
	            String perloop = "";
	            String last = "";
	            for (int j = i; j < s.length(); j += i) {
	                if (j + i > s.length()) {
	                    last = s.substring(j);
	                    continue;
	                }
	                if (pdS.equals(s.substring(j, j + i))) {
	                    count++;
	                } else {
	                    perloop += pdS;
	                    if (count != 1) {
	                        perloop = count + perloop;
	                    }
	                    pdS = s.substring(j, j + i);
	                    count = 1;
	                }
	            }
	            perloop += pdS + last;
	            if (count != 1) {
	                perloop = count + perloop;
	            } 
	            
	            answer = Math.min(answer, perloop.length());
	        }

	        return answer;
	    	}
	public static void main(String[] args) {
		CompressString2 cs = new CompressString2();
		cs.solution(s);

	}

}
