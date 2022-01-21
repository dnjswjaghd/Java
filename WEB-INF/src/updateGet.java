package jin.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class updateGet extends HttpServlet {
   Connection con;
   Statement stmt;
   PreparedStatement pstmt;
   String sql;
   public void init(){ //DB연결 
      String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
      String usr = "servlet";
      String pwd = "java";
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         con = DriverManager.getConnection(url, usr, pwd);
		 stmt = con.createStatement();
         //pstmt = con.prepareStatement(sql);
      }catch(ClassNotFoundException cnfe){
         System.out.println("#Oracle driver loading failed");
      }catch(SQLException se){}
   }
   public void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException { //SQL문수행 -> list.html 
      res.setContentType("text/html;charset=utf-8"); 
	  req.setCharacterEncoding("UTF-8");
	  int seq = -1;
	  String seqStr = req.getParameter("seq");
	  seqStr = seqStr.trim();
	  if(seqStr.length() != 0){
		seq = Integer.parseInt(seqStr);
	  }else{
		res.sendRedirect("list.do");
	  }
	  String name = req.getParameter("writer");
	  System.out.println("name: "+name);
	  String email = req.getParameter("email");
	  String subject = req.getParameter("subject"); 
	  String content = req.getParameter("content");
	  System.out.println("email: "+email+" name: "+name+ " subject: "+subject+" content: "+content+ "seq: "+seq);
	  sql = "update BOARD set EMAIL='"+email+"', WRITER='"+name+"', SUBJECT='"+subject+"', CONTENT='"+content+"' where SEQ='"+seq+"'";
	  System.out.println("email: "+email+" name: "+name+ " subject: "+subject+" content: "+content+ "seq: "+seq);
	  PrintWriter pw = res.getWriter(); 
	  pw.println("<script>");
	  try{
		  System.out.println("1");
		int i = stmt.executeUpdate(sql);
		System.out.println("2");
		if(i>0){
			pw.println("alert('수정성공')");
		}else{
			pw.println("alert('수정실패')");  
		}
	  }catch(SQLException se){
	  }
	  //pw.println("<a href='list.do'>리스트</a></br>"); 
	  pw.println("location.href='list.do'");
	  pw.println("</script>");
	  System.out.println("AddrServletIn name: "+name+", email: "+email+"");
   }
   public void destroy(){ //DB연결해제 
        try{
         if(stmt != null) stmt.close();
         if(con != null) con.close();
      }catch(SQLException se){}
   }
}
