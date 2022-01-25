package jin.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import jin.db.ConnectionPoolBean;

//update ACC set BALANCE=((select BALANCE from ACC where EMAIL='one@daum.net')-10000) where EMAIL='one@daum.net'

public class sbUpdate extends HttpServlet {
   Connection con;
   Statement stmt;
   PreparedStatement pstmt;
   String sql;
   private ConnectionPoolBean getPool() throws SQLException {
		ServletContext application = this.getServletContext();
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool");
		if(pool == null){
			try{
				pool = new ConnectionPoolBean();
				application.setAttribute("pool", pool);
			}catch(ClassNotFoundException cnfe){
				System.out.println("드라이버로딩 실패");
			}
		}
		return pool;
	}
   public void service(HttpServletRequest req, HttpServletResponse res) //Get방식으로 받는거라 doGet해줘야함
      throws ServletException, IOException { //SQL문수행 -> list.html 
      res.setContentType("text/html;charset=utf-8"); 
	  req.setCharacterEncoding("utf-8");
      PrintWriter pw = res.getWriter();
	  String seqStr = req.getParameter("seq"); 
	  String name = ""; 
	  String email = "";
	  String subject = "";
	  String content = "";
	  int seq = -1;
	  if(seqStr != null) { 
		seqStr = seqStr.trim();
		try{
			seq = Integer.parseInt(seqStr);
		}catch(Exception se){
			res.sendRedirect("update.do"); 
			System.out.println("8");
		}
	  } 
	  ResultSet rs = null;
	   ConnectionPoolBean pool = null;
				Connection con = null;
				Statement stmt = null;
	  sql = "select * from BOARD where SEQ="+seq+" order by seq desc";
	  try{
		pool = getPool();
		con = pool.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){ 
		  name = rs.getString(2);
		  email = rs.getString(3);
		  subject = rs.getString(4);
		  content = rs.getString(5);
		}
	 
	    pw.println("<meta charset='utf-8'>");
		pw.println("<style>");
			pw.println("table, th, td {");
			   pw.println("border: 1px solid black;");
			   pw.println("border-collapse: collapse;");
			pw.println("}");
			pw.println("th, td {");
			   pw.println("padding: 5px;");
			pw.println("}");
			pw.println("a { text-decoration:none }");
		pw.println("</style>");
		pw.println("<body onload='javascript:document.f.email.focus();'>");
		pw.println("<center>");
		pw.println("<hr width='600' size='2' noshade>");
		pw.println("<h2>Simple Board with Servlet</h2>");
		pw.println("<a href='list.do'>글목록pool</a>"); 
		pw.println("<hr width='600' size='2' noshade>");
		pw.println("</center>");
		pw.println("<form name='f' method='post' action='upGet.do'>");
		pw.println("<input type='hidden' name='seq' value='"+seq+"'>");
		pw.println("<input type='hidden' name='writer' value='"+name+"'>");
		pw.println("<table border='1' width='600' align='center' cellpadding='3' cellspacing='1'><tr>");
		pw.println("<td width='30%' align='center'>글쓴이</td>");
		pw.println("<td align='center'><input type='text' name='name' size='60' value='"+name+"' disabled></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td width='30%' align='center'>이메일</td>");
		pw.println("<td align='center'><input type='text' name='email' size='60' value='"+email+"'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td width='30%' align='center'>글제목</td>");
		pw.println("<td align='center'><input type='text' name='subject' size='60' value='"+subject+"'></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td width='30%' align='center'>글내용</td>");
		pw.println("<td align='center'><textarea name='content' rows='5' cols='53'>"+content+"</textarea></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td colspan='2' align='center'>"); 
		pw.println("<input type='submit' value='수정'>");
		pw.println("</td>");
		pw.println("</tr>");

	   }catch(Exception e){
		   System.out.println("5");
	  }	  
	  	  pw.println("<script>");
	  //pw.println("location.href='update.do'");
	  pw.println("</script>"); 
   }
   public void destroy(){ //DB연결해제 
        try{
         if(stmt != null) stmt.close();
         if(con != null) con.close();
      }catch(SQLException se){}
   }
}
