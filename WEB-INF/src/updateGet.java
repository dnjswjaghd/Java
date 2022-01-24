package jin.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import jin.db.ConnectionPoolBean;

public class updateGet extends HttpServlet {
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
	  ConnectionPoolBean pool = null;
	  Connection con = null;
	  Statement stmt = null;
	  pw.println("<script>");
	  
	  try{
		 System.out.println("1"); 
		pool = getPool();
		con = pool.getConnection();
		stmt = con.createStatement();
		int i = stmt.executeUpdate(sql);
		System.out.println("2");
		if(i>0){
			pw.println("alert('수정성공p')");
		}else{
			pw.println("alert('수정실패p')");  
		}
	  }catch(SQLException se){
	  }
	  //pw.println("<a href='list.do'>리스트</a></br>"); 
	  pw.println("location.href='list_pool.do'");
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
