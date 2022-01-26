<%@ page contentType="text/html;charset=utf-8" import="java.sql.*, javax.sql.*"%> 
	<jsp:useBean id="dbcp" class="jin.dbcp.DbcpBean" scope="application"/>

<%
	  request.setCharacterEncoding("UTF-8");  
	  String name = request.getParameter("name");
	  String email = request.getParameter("email");
	  String subject = request.getParameter("subject");
	  String content = request.getParameter("content");
	  out.println("<script>");
	  DataSource ds = null;
	  Connection con =null;
	  Statement stmt = null;
	  PreparedStatement pstmt = null;
	  String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	  try{
		ds = dbcp.getDs();
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, email);
		pstmt.setString(3, subject);
		pstmt.setString(4, content);
		int i = pstmt.executeUpdate();
		if(i>0){
			out.println("alert('입력성공 with jspp')"); 
		}else{
			out.println("alert('입력실패 with jsp')");
		}
	  }catch(SQLException se){
	  }
	  out.println("location.href='list.jsp'"); 
	  out.println("</script>");
	  System.out.println("AddrServletIn name: "+name+", email: "+email+"");
%>