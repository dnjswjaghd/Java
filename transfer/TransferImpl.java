package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransferImpl implements Transfer {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	Connection con;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsmd;
	long senderBalance;
	
	TUser tu;
	private String sender;
	private String receiver;
	private int amount;
	private String email;
	
	public TransferImpl(TUser tu){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "scott", "tiger");
			con.setAutoCommit(false); //중요!
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			this.tu=tu;
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
		sender=tu.getSender();
		receiver=tu.getReceiver();
		amount=tu.getAmount();
		email=tu.getEmail();
		transfer(sender,receiver,amount,email);
	}
	@Override
	public boolean isMember(String email, String name) {
		String sql ="select EMAIL from ACC where EMAIL='"+email+"' and NAME='"+name+"'";
		String tempEmail=null;
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString(1)!=null) {
					tempEmail = rs.getString(1);
				}
			if(tempEmail!=null) {
				//System.out.println("들어온 email값: "+ tempEmail);
				return true;
			}
			}
		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println("해당되는 사람의 email없음");
		return false;
	}
	@Override
	public boolean checkBalance(String sender, long amount) {
		String sql = "select BALANCE from ACC where NAME='"+sender+"'";
		int tempsenderBalance =0;
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				tempsenderBalance = rs.getInt(1);
			System.out.println("sender's balance: "+ tempsenderBalance + " 이체시도금액: "+amount+"원");
				senderBalance = tempsenderBalance;
			}
			if(tempsenderBalance >= amount) {
				//System.out.println("잔액충분");
				return true;
			}
		}catch(SQLException see) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println("잔액부족");
		return false;
	}
	@Override
	public boolean minus(String sender, long amount) {
		if(checkBalance(sender,amount)) {
			//System.out.println("minus전 senderbalance: "+senderBalance +"원");
			String sql = "update ACC set BALANCE=((select BALANCE from ACC where NAME='"+sender+"')-"+amount+") where NAME='"+sender+"'";
			String sql2 ="select BALANCE from ACC where NAME='"+sender+"'";
			try {
				stmt.executeUpdate(sql);
				rs = stmt.executeQuery(sql2);
				//rs.beforeFirst();
				while(rs.next()) {
					senderBalance = rs.getLong(1);
					//System.out.println(""+sender+"잔액 확인: "+senderBalance+"원");
					return true;
				}
			}catch(SQLException see) {
				try {
					con.rollback(sp1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else {
			return false;
		}
		System.out.println("minus false");
		return false;
	}
	@Override
	public boolean plus(String receiver, long amount) {
			//System.out.println("plus전 receiver's balance: "+senderBalance +"원");
			String sql = "update ACC set BALANCE=((select BALANCE from ACC where NAME='"+receiver+"')+"+amount+") where NAME='"+receiver+"'";
			String sql2 ="select BALANCE from ACC where NAME='"+receiver+"'";
			try {
				stmt.executeUpdate(sql);
				rs = stmt.executeQuery(sql2);
				//rs.beforeFirst();
				while(rs.next()) {
					senderBalance = rs.getLong(1);
					//System.out.println(""+receiver+"잔액 확인: "+senderBalance+"원");
					return true;
				}
			}catch(SQLException see) {
				try {
					con.rollback(sp1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		System.out.println("plus false");
		return false;
	}

	@Override
	public boolean log(String sender, String receiver, long amount) {
		String sql ="insert into TRAN_LOG values(TRAN_LOG_SEQ.nextval, '"+sender+"', '"+receiver+"' ,'"+amount+"',CURRENT_TIMESTAMP)";
		String sql2 = "select SENDER,RECEIVER,AMOUNT,RDATE from TRAN_LOG where SENDER='"+sender+"' and RECEIVER='"+receiver+"'";
		try {
			stmt.executeUpdate(sql);
			rs = stmt.executeQuery(sql2);
			rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=cc; i++) {
					System.out.print(rs.getString(i)+"\t");
				}
				System.out.println("");
			}
			return true;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}
	@Override
	public void showResult(String sender, String receiver) {
		String sql = "select NAME, BALANCE from ACC where NAME='"+sender+"'";
		String sql2 = "select NAME, BALANCE from ACC where NAME='"+receiver+"'";
		try {
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			while(rs.next()) {
				System.out.println("");
				for(int i=1; i<=cc; i++) {
					System.out.print(rs.getString(i)+"\t");
				}	
				
			}
			rs = stmt.executeQuery(sql2);
			rsmd = rs.getMetaData();
			cc = rsmd.getColumnCount();
			rs.beforeFirst();
			while(rs.next()) {
				System.out.println("");
				for(int i=1; i<=cc; i++) {
					System.out.print(rs.getString(i)+"\t");
				}	
				System.out.println("");
			}
		}catch(SQLException se) {	
			se.printStackTrace();
		}
	}
	@Override
	public void closeAll() {
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	Savepoint sp1;
	@Override
	public boolean transfer(String sender, String receiver, long amount, String senderEmail)  {
		try {
			if(isMember(senderEmail, sender)) {
				sp1 =con.setSavepoint("sp1name");
				minus(sender,amount);
				log(sender,receiver,amount);
				plus(receiver,amount);
				//log(sender,receiver,amount);
				showResult(sender, receiver);
				closeAll();
				System.out.println("거래성공");
				return true;
			}
		}catch(SQLException se) {
		}
		System.out.println("거래실패");
		return false;
	}
}
