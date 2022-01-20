package bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TUser {
	private String sender;
	private String receiver;
	private int amount;
	private String email;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public TUser(){
		try {
			System.out.print("송신인: ");
			setSender(br.readLine());
			System.out.print("수신인: ");
			setReceiver(br.readLine());
			System.out.print("보낼 금액: ");
			setAmount(Integer.parseInt(br.readLine()));
			System.out.print("송신인 Email: ");
			setEmail(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new TransferImpl(this);
	}
	public String getSender() {
		// TODO Auto-generated method stub
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setValues(String sender, String receiver, int amount, String email) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.email = email;
	}
	public static void main(String[] args) {
		new TUser();
	}
}
