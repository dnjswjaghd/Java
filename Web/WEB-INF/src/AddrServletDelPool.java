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
   private ConnectionPoolBean getPool() throws  SQLException{ //*�߿�
		ServletContext application = this.getServletContext();
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool");
		if(pool == null){
			try{
				pool = new ConnectionPoolBean();
				application.setAttribute("pool", pool);
			}catch(ClassNotFoundException cnfe){
				System.out.println("����̹��ε� ����");
			}
		}
		return pool;
   }
   public void doGet(HttpServletRequest req, HttpServletResponse res) //Get������� �޴°Ŷ� doGet�������
      throws ServletException, IOException { //SQL������ -> list.html 
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
			pw.println("alert('��������')");
		}else{
			pw.println("alert('��������')");
		}
	  }catch(SQLException se){
		  pw.println("alert('��������')");
	  }
	  //pw.println("<a href='list.do'>����Ʈ</a></br>");
	  pw.println("location.href='list_pool.do'");
	  pw.println("</script>");
   }
  
}
