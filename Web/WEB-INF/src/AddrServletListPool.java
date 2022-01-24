package jin.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import jin.db.ConnectionPoolBean;

public class AddrServletListPool extends HttpServlet {
   Connection con;
   Statement stmt;
   private ConnectionPoolBean getPool() throws  SQLException{ //*중요
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
   public void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException { //SQL문수행 -> list.html 

      res.setContentType("text/html;charset=utf-8"); 
      PrintWriter pw = res.getWriter();
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
			pw.println("<a href='board/input.html'>글쓰기pool</a>");
			pw.println("&nbsp;&nbsp;&nbsp;");
			pw.println("<a href='index.html'>인덱스</a>");
			pw.println("<hr width='600' size='2' noshade>");
			pw.println("</center>");
			pw.println("<table border='1' width='600' align='center' cellpadding='2'>");
			pw.println("<tr>");
			pw.println("<th align='center' width='10%'>글번호</th>");
			pw.println("<th align='center' width='15%'>작성자</th>");
			pw.println("<th align='center' width='30%'>이메일</th>");
			pw.println("<th align='center' width='30%'>글제목</th>");
			pw.println("<th align='center' width='15%'>날짜</th>");
			pw.println("</tr>");
			 
			ConnectionPoolBean pool = null;  
            ResultSet rs = null;
			Connection con = null;
			Statement stmt = null;
            String sql = "select * from BOARD order by seq desc"; 
            try{
			   pool = getPool();
			   con = pool.getConnection();
			   stmt = con.createStatement();
               rs = stmt.executeQuery(sql);
               while(rs.next()){
                  int seq = rs.getInt(1);
                  String name = rs.getString(2);
                  String email = rs.getString(3);
				  String subject = rs.getString(4);
                  Date rdate = rs.getDate(6);
                  pw.println("<tr>");
				  pw.println("<td align='center'>"+seq+"</td>");
				  pw.println("<td>"+name+"</td>");
				  pw.println("<td>"+email+"</td>");
				  pw.println("<td align='center'><a href='content.do?seq="+seq+"'>"+subject+"</a></td> "); 
				  pw.println("<td>"+rdate+"</td>");
				  //pw.println("<td align='center'><a href='del.do?seq="+seq+"'>삭제</a></td>");
                  pw.println("</tr>");
               }
            }catch(SQLException se){
            }finally{
               try{
                  if(rs != null) rs.close();
				  if(stmt != null) stmt.close(); 
				  if(con != null) pool.returnConnection(con);
               }catch(SQLException se){}
            }
			pw.println("<br/>");
            pw.println("<hr width='600' size='2' noshade>");
         pw.println("</table>");
      pw.println("</center>");
   }
}
