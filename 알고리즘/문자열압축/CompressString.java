package algo5;

import java.util.ArrayList;
import java.util.Stack;

public class CompressString {
	public static String s ="aabbaccc" ;
	public int solution(String s) {
	        int answer = 0;
	        ArrayList<String> sa = new ArrayList<>();
	        ArrayList<String> sa2 = new ArrayList<>();
	        Stack<String> ss = new Stack<>();
	        ss.push("");
	        //for(int i=0; i<s.length();i++) {
	        
	        	for(int j=1; j<=s.length()/2; j++) {
	        		int count=1;
	        		String pdS = s.substring(0, j);
	        		ss.push(pdS);
	        		String perloop="";
	        		for(int i=0; i<s.length()-j; i+=j) {
	        			String cutWord = s.substring(i,i+j);
		        		if(ss.peek().equals(s.substring(i,i+j))) {
		        			if(i!=0) {
		        				count++;
		        				
		        				//ss.push(cutWord);
		        			}
		        			if(i+j >= s.length()-j) {
		        				String a =String.valueOf(count);
			        			if(count==1) {
			        				perloop=perloop.concat(ss.peek()); // s ="aabbaccc" ;
				        			//sa.add(String.valueOf(count));
			        			}else {
			        				a =String.valueOf(count);
			        				perloop=perloop.concat(a.concat(ss.peek()));
			        			}
		        			}		
		        		}else{
		        			//ss.push(cutWord);
		        			if(count==1||count==0) {
		        				perloop=perloop.concat(ss.peek());
		        				ss.pop();
		        				ss.push(cutWord);
		        				count=1;
		        				continue;
		        			}else if(count>1){
		        				String a =String.valueOf(count);
		        				perloop= perloop.concat(a.concat(ss.peek()));
		        				ss.pop();
		        				ss.push(cutWord);
		        				count=1;
		        				continue;
		        			}else {
		        				count=1;
		        			}
		        			
		        		}
		        		ss.push(cutWord);
		        		System.out.println(cutWord);
		        		
		        		
		        		if(i+j >= s.length()-j) {
		        			String a =String.valueOf(count);
		        			if(count==1) {		
		        				perloop=perloop.concat(s.substring(i+j,s.length()));
			        			//sa.add(String.valueOf(count));
		        			}else {
		        				a =String.valueOf(count);
		        				perloop=perloop.concat(a.concat(s.substring(i+j,s.length())));
		        			}
		        		}
		        		count=0;
	        		}
	        		System.out.println("perloop: "+perloop);
	        		sa.add(perloop);
	        		/*
	        		for(String sa22: sa) {
	        			System.out.println("완성된 배열: "+sa22);
	        		}
	        		*/
	        	}
	    //}
	        	
	        return answer;
	        
	    }
	public static void main(String[] args) {
		CompressString cs = new CompressString();
		cs.solution(s);

	}

}
