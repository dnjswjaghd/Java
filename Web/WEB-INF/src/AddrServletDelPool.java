package jin.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import jin.db.ConnectionPoolBean;

public class AddrServletDelPool extends HttpServlet {
   Connection con;
   Statement stmt;
   PreparedStatement pstmt;
   String sql = "delete from BOARD where SEQ=?";
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
   public void doGet(HttpServletRequest req, HttpServletResponse res) //Get방식으로 받는거라 doGet해줘야함
      throws ServletException, IOException { //SQL문수행 -> list.html 
      res.setContentType("text/html;charset=utf-8"); 
      //PrintWriter pw = res.getWriter();
	  req.setCharacterEncoding("UTF-8");
	  String seqStr = req.getParameter("seq");
	  ConnectionPoolBean pool = null; 
	  Connection con = null;
	  Statement stmt = null;
	  PreparedStatement pstmt = null;
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
		pool = getPool();
		con = pool.getConnection();
		pstmt = con.prepareStatement(sql);
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
	  pw.println("location.href='list_pool.do'");
	  pw.println("</script>");
   }
  
}
