package jin.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class sbContent extends HttpServlet {
   Connection con;
   Statement stmt;
   int seq = -1;
   public void init(){ //DB연결 
      String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
      String usr = "servlet";
      String pwd = "java";
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         con = DriverManager.getConnection(url, usr, pwd);
         stmt = con.createStatement();
      }catch(ClassNotFoundException cnfe){
         System.out.println("#Oracle driver loading failed");
      }catch(SQLException se){}
   }
   public void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
      res.setContentType("text/html;charset=utf-8"); 
	  req.setCharacterEncoding("utf-8");
			String seqStr = req.getParameter("seq");
			PrintWriter pw = res.getWriter();
			ResultSet rs = null;
			
			if(seqStr != null){
				seqStr = seqStr.trim();
				if(seqStr.length()!=0){
					try{
						seq = Integer.parseInt(seqStr);
					}catch(Exception ie){
						res.sendRedirect("content.do");
					}
				}else{
					res.sendRedirect("content.do");
				}
			}
			String name ="";
		    String email="";
		    String subject="";
		    String content ="";
            String sql = "select * from BOARD where SEQ="+seq+" order by seq desc";
            try{
               rs = stmt.executeQuery(sql);
               while(rs.next()){
                  seq = rs.getInt(1);
                  name = rs.getString(2);
                  email = rs.getString(3);
				  subject = rs.getString(4);
                  content = rs.getString(5);
				  //pw.println("<td align='center'><a href='del.do?seq="+seq+"'>삭제</a></td>");
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
			pw.println("<center>");
			pw.println("<hr width='600' size='2' noshade>");
			pw.println("<h2>Simple Board with Servlet by Jinun</h2>");
			pw.println("&nbsp;&nbsp;&nbsp;");
			pw.println("<a href='board/input.html'>글쓰기 </a>");
			pw.println("<hr width='600' size='2' noshade>");
			pw.println("<table border='1' width='600' align='center' cellpadding='3'>");
			pw.println("<tr>");
			pw.println("<td width='100' align='center'>글번호</td>");
			pw.println("<td>"+seq+"</td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td align='center'>글쓴이</td>");
			pw.println("<td>"+name+"</td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td align='center'>이메일</td>");
			pw.println("<td>"+email+"</td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td align='center'>글제목</td>");
			pw.println("<td><a href='board/content.html'>"+subject+"</a></td> ");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td align='center'>글내용</td>");
			pw.println("<td>"+content+"</td>");
			pw.println("</tr>");
            }catch(SQLException se){
            }finally{
               try{
                  if(rs != null) rs.close();
               }catch(SQLException se){}
            }
			pw.println("<br/>");
            pw.println("<hr width='600' size='2' noshade>");
			pw.println("</table>");
			pw.println("<hr width='600' size='2' noshade>");
			pw.println("<b>");
			pw.println("<a href='update.do?seq="+seq+"'>수정</a>");
			pw.println(" | ");
			pw.println("<a href='del.do?seq="+seq+"'>삭제</a>");
			pw.println(" | ");
			pw.println("<a href='list.do'>목록!</a>"); 
			pw.println("</b>");
		 pw.println("</center>");
   }	
   public void destroy(){ //DB연결해제 
        try{
         if(stmt != null) stmt.close();
         if(con != null) con.close();
      }catch(SQLException se){}
   }
}
