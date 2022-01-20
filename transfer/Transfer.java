package bank;

import java.sql.SQLException;

public interface Transfer 
{
    boolean isMember(String email, String name); //1: S
	boolean checkBalance(String sender, long amount); //2: S
    boolean minus(String sender, long amount); //3: U
	boolean plus(String receiver, long amount); //4: U
	boolean log(String sender, String receiver, long amount);//5: I
    void showResult(String sender, String receiver); //6: S
	void closeAll(); //7
    
	boolean transfer(String sender, String receiver, long amount, String email) throws SQLException; //8: for User 
}  