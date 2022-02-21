package algo3;

import java.util.HashMap;

public class PhoneList3 {//2´Ü°è
		public String[] phone_book= {"123", "456", "789", "1234"};
		   public boolean solution(String[] phone_book) {
		       boolean answer =true;
		       HashMap<String, Integer> hm = new HashMap<>();
		       
		       for(int i=0; i<phone_book.length; i++) {
		    	   hm.put(phone_book[i], i);
		       }
		       
		       for(int i=0; i<phone_book.length;i++) {
		    	   if(answer==false) {
		    		   break;
		    	   }
		    	   for(int j=0; j<phone_book[i].length(); j++) {
		    		   if(hm.containsKey(phone_book[i].substring(0,j))) {
		    			   answer = false;
		    			   break;
		    		
		    		   }
		    	   }
		       }
		       
		       return answer;
		}
		
	public static void main(String[] args) {
		PhoneList3 pl = new PhoneList3();
		System.out.println(pl.solution(pl.phone_book));
		
	}

}
