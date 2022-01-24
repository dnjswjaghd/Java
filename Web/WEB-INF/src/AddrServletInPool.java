package jin.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import jin.db.ConnectionPoolBean;

public class AddrServletInPool extends HttpServlet {
   Connection con;
   Statement stmt;
   PreparedStatement pstmt;
   String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
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
   public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException { //SQL문수행 -> list.html 
      res.setContentType("text/html;charset=utf-8"); 
	  req.setCharacterEncoding("UTF-8");
	  String name = req.getParameter("name");
	  String email = req.getParameter("email");
	  String subject = req.getParameter("subject");
	  String content = req.getParameter("content");

	  PrintWriter pw = res.getWriter(); 
	  ConnectionPoolBean pool = null; 
	  pw.println("<script>");
	  try{
		pool = getPool();
		con =  pool.getConnection(); 
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, name);
		pstmt.setString(2, email);
		pstmt.setString(3, subject);
		pstmt.setString(4, content);
		int i = pstmt.executeUpdate();
		if(i>0){
			pw.println("alert('입력성공')");
		}else{
			pw.println("alert('입력실패')");
		}
	  }catch(SQLException se){
	  }
	  //pw.println("<a href='list.do'>리스트</a></br>"); 
	  pw.println("location.href='../list_pool.do'");
	  pw.println("</script>");
	  System.out.println("AddrServletIn name: "+name+", email: "+email+"");
	 }
}
