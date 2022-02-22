package algo3;

import java.util.Arrays;

public class PhoneList {//2´Ü°è
		public String[] phone_book= {"123", "456", "789", "1234"};
	   /* public boolean solution(String[] phone_book) { 
	        boolean answer = true;
	        Arrays.sort(phone_book);
	        String k="";
	       
	        for(String pb : phone_book) {    	
	        	if(answer==false) {
	        		break;
	        	}
	        	int choosedIndex = Arrays.binarySearch(phone_book, pb); //index°¡ À½¼ö°æ¿ì»ý°¢
	        	for(String pb2 : phone_book) {
	        		if(Arrays.binarySearch(phone_book, pb)>=0) {
		        		if(pb2.startsWith(pb) && Arrays.binarySearch(phone_book, pb2)!=choosedIndex ) {
		        			answer = false;
		        			break;
		        		}
	        		}
	        	}
	        	
	        	if(choosedIndex != pb.indexOf(k) && pb.startsWith(k) && choosedIndex<0) {
	        		answer=false;
	        		break;
	        	}
	        }
	        System.out.println("answer: "+answer);
	        return answer;
	    }*/
	
		   public boolean solution(String[] phone_book) {
		       Arrays.sort(phone_book);
		       for(int i=0; i<phone_book.length -1; i++){
		           if(phone_book[i+1].startsWith(phone_book[i])){
		               return false;
		           }
		       }
			    return true;
		}
	public static void main(String[] args) {
		PhoneList pl = new PhoneList();
		pl.solution(pl.phone_book);

	}

}
/*
int result = Arrays.binarySearch(phone_book, phone_book[i]);
if(result<0) {
	answer=false;
	break;
}*/
