package jin.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddrServletDel extends HttpServlet {
   Connection con;
   Statement stmt;
   PreparedStatement pstmt;
   String sql = "delete from BOARD where SEQ=?";
   public void init(){ //DB연결 
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
   public void doGet(HttpServletRequest req, HttpServletResponse res) //Get방식으로 받는거라 doGet해줘야함
      throws ServletException, IOException { //SQL문수행 -> list.html 
      res.setContentType("text/html;charset=utf-8"); 
      //PrintWriter pw = res.getWriter();
	  req.setCharacterEncoding("UTF-8");
	  String seqStr = req.getParameter("seq");
	  int seq = -1;
	  if(seqStr != null) {
		seqStr = seqStr.trim();
		try{
			seq = Integer.parseInt(seqStr);
		}catch(Exception se){
			res.sendRedirect("list.do");
		}
	  }
	  PrintWriter pw = res.getWriter();
	  pw.println("<script>");
	  try{
		pstmt.setInt(1, seq);
		int i = pstmt.executeUpdate();
		if(i>0){
			pw.println("alert('삭제성공')");
		}else{
			pw.println("alert('삭제실패')");
		}
	  }catch(SQLException se){
		  pw.println("alert('삭제실패')");
	  }
	  //pw.println("<a href='list.do'>리스트</a></br>");
	  pw.println("location.href='list.do'");
	  pw.println("</script>");
   }
   public void destroy(){ //DB연결해제 
        try{
         if(stmt != null) stmt.close();
         if(con != null) con.close();
      }catch(SQLException se){}
   }
}
