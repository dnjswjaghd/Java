package jin.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddrServletIn extends HttpServlet {
   Connection con;
   Statement stmt;
   PreparedStatement pstmt;
   String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
   public void init(){ //DB���� 
      String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
      String usr = "servlet";
      String pwd = "java";
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         con = DriverManager.getConnection(url, usr, pwd);
         pstmt = con.prepareStatement(sql);
      }catch(ClassNotFoundException cnfe){
         System.out.println("#Oracle driver loading failed");
      }catch(SQLException se){}
   }
   public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException { //SQL������ -> list.html 
      res.setContentType("text/html;charset=utf-8"); 
	  req.setCharacterEncoding("UTF-8");
	  String name = req.getParameter("name");
	  String email = req.getParameter("email");
	  String subject = req.getParameter("subject");
	  String content = req.getParameter("content");

	  PrintWriter pw = res.getWriter();
	  pw.println("<script>");
	  try{
		pstmt.setString(1, name);
		pstmt.setString(2, email);
		pstmt.setString(3, subject);
		pstmt.setString(4, content);
		int i = pstmt.executeUpdate();
		if(i>0){
			pw.println("alert('�Է¼���')");
		}else{
			pw.println("alert('�Է½���')");
		}
	  }catch(SQLException se){
	  }
	  //pw.println("<a href='list.do'>����Ʈ</a></br>"); 
	  pw.println("location.href='../list.do'");
	  pw.println("</script>");
	  System.out.println("AddrServletIn name: "+name+", email: "+email+"");
   }
   public void destroy(){ //DB�������� 
        try{
         if(stmt != null) stmt.close();
         if(con != null) con.close();
      }catch(SQLException se){}
   }
}
