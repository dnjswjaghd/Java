package algo3;

import java.util.Arrays;

public class PhoneList2 {//2´Ü°è
		public String[] phone_book= {"123", "456", "789", "1234"};
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
		PhoneList2 pl = new PhoneList2();
		pl.solution(pl.phone_book);

	}

}
