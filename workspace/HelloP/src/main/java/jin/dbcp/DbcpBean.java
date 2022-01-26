package jin.dbcp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbcpBean {
	private DataSource ds;
	public DbcpBean() {
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("#tomcat�� ���� dbcp��ü �̸�(jdbc/myoracle)�� ��ã��"); 
		}
	}
	public DataSource getDs(){
		return ds;
	}
}
